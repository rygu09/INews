package com.ustc.gry.inews.utils.http;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.app.App;
import com.ustc.gry.inews.bean.pic.ShowApiPictures;
import com.ustc.gry.inews.bean.pic.ShowApiResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/5
 */

public class RetrofitManager {

    private RetrofitService mRetrofitService;

    // 设缓存有效期为两天
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    // 30秒内直接读缓存
    private static final long CACHE_AGE_SEC = 0;

    private static volatile OkHttpClient sOkHttpClient;
    // 管理不同HostType的单例
    private static SparseArray<RetrofitManager> sInstanceManager = new SparseArray<>(HostType.TYPE_COUNT);
    // 拦截器，用来配置缓存策略
    private Interceptor mCacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();//获取请求

//            //判断网络条件，无网缓存里面取数据，有网直接获取网络上面的数据
//            //无网
//            if(!NetUtil.isConnected(App.getContext())){
//                request = request.newBuilder()
//                        .cacheControl(CacheControl.FORCE_CACHE)
//                        .build();
//                Logger.d("无网络，缓存里面取数据");
//            }
            //有网
//            Response originalResponse = chain.proceed(request);
            if(NetUtil.isConnected(App.getContext())){
                //max-age：该参数告诉浏览器将页面缓存多长时间，超过这个时间后才再次向服务器发起请求检查页面是否有更新。
                //对于静态的页面，比如图片、CSS、Javascript，一般都不大变更，因此通常我们将存储这些内容的时间设置为较长的时间，这样浏览器会不会向浏览器反复发起请求，也不会去检查是否更新了。
//                request = request.newBuilder()
//                        .removeHeader("Pragma")
//                        .removeHeader("Cache-Control")
//                        .header("Cache-Control", "public, max-age=" + CACHE_AGE_SEC)
//                        .build();
                Response originalResponse = chain.proceed(request);
                Logger.e("有网");

                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "max-age=" + CACHE_AGE_SEC)
                        .build();
            }else{//无网
//                request = request.newBuilder()
//                        .removeHeader("Pragma")
//                        .removeHeader("Cache-Control")
//                        .header("Cache-Control", "public, only-if-cached, max-stale="+CACHE_STALE_SEC)
//                        .build();

                //max-stale指示客户机可以接收超出时期间的响应消息。如果指定max-stale消息的值，那么客户机可以接收超出超时期指定值之内的响应消息
                //only-if-cached 请求报文专用 如果请求报文中有此标签，意味着，客户端只希望从缓存中读取资源。如果缓存中不存在，缓存会返回504 Gateway Timeout响应
                Response originalResponse = chain.proceed(request);
                Logger.e("无网");
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "only-if-cached, max-stale="+CACHE_STALE_SEC)
                        .build();
            }
        }
    };

    // 打印返回的json数据拦截器
    private Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();

            Request.Builder requestBuilder = request.newBuilder();
            requestBuilder.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_5) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1.1 Safari/605.1.15");
            request = requestBuilder.build();

            final Response response = chain.proceed(request);

            final ResponseBody responseBody = response.body();
            final long contentLength = responseBody.contentLength();

            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(charset);
                } catch (UnsupportedCharsetException e) {
                    Logger.e("");
                    Logger.e("Couldn't decode the response body; charset is likely malformed.");
                    return response;
                }
            }
            String json=buffer.clone().readString(charset);

            String printBody="";
            try {
                printBody=getBodytoString(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (contentLength != 0) {
                Logger.i("请求网址: " + request.url() + "\n请求方法: " +request.method() +"\n请求头: " + request.headers()+"请求体: " + request.body()+ "\n\n响应头：\n" + response.headers()+ "\n响应体:\n"+printBody);
            }

            return response;
        }
    };


    private RetrofitManager(int hostType) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.getHost(hostType))
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mRetrofitService = retrofit.create(RetrofitService.class);
    }

    @NonNull
    public static RetrofitManager getInstance(int hostType) {
        RetrofitManager instance = sInstanceManager.get(hostType);
        if (instance == null) {
            instance = new RetrofitManager(hostType);
            sInstanceManager.put(hostType, instance);
            return instance;
        } else {
            return instance;
        }
    }

    // 配置OkHttpClient
    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (sOkHttpClient == null) {
                    // OkHttpClient配置是一样的,静态创建一次即可
                    // 指定缓存路径,缓存大小100Mb
                    Cache cache = new Cache(new File(App.getContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);

                    sOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)//缓存
                            .addNetworkInterceptor(mCacheInterceptor)//网络拦截器
//                            .addInterceptor(mCacheInterceptor)//应用拦截器
                            .addInterceptor(mLoggingInterceptor)//应用拦截器，打印Log
                            .retryOnConnectionFailure(true)//重连
                            .connectTimeout(30, TimeUnit.SECONDS)//超时30s
                            .build();

                }
            }
        }
        return sOkHttpClient;
    }

    //新闻列表服务
    public Observable<ResponseBody> getNewsListObservable(String type, String id, int startPage){
        return mRetrofitService.getNewsList(type,id,startPage);
    }

    //新闻详情
    public Observable<ResponseBody> getNewsDetailObservable(String postId) {
        return mRetrofitService.getNewsDetail(postId);
    }


    //图片列表服务
    public Observable<ShowApiResponse<ShowApiPictures>> getPictureListObservable(String id, int startPage) {
        return mRetrofitService.getPictureList(id,startPage);
    }

    //视频列表服务
    public Observable<ResponseBody> getVideoListObservable(String id, int startPage) {
        return mRetrofitService.getVideoList(id,startPage);
    }

    private String getBodytoString(String json) throws JSONException {
        json = json.trim();
        String message="";
        if (json.startsWith("{")) {
            JSONObject jsonObject = new JSONObject(json);
            message = jsonObject.toString(2);
        }
        if (json.startsWith("[")) {
            JSONArray jsonArray = new JSONArray(json);
            message = jsonArray.toString(2);
        }
        return message;
    }

}
