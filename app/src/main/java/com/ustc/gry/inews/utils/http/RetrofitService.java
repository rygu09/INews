package com.ustc.gry.inews.utils.http;

import com.ustc.gry.inews.bean.pic.ShowApiPictures;
import com.ustc.gry.inews.bean.pic.ShowApiResponse;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/5
 */

public interface RetrofitService {
    /**
     * 请求新闻列表 例子：https://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
     *
     * @param type      新闻类别：headline为头条,local为北京本地,fangchan为房产,list为其他
     * @param id        新闻类别id
     * @param startPage 开始的页码
     * @return 被观察对象
     */
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<ResponseBody> getNewsList(
            @Path("type") String type,
            @Path("id") String id,
            @Path("startPage") int startPage);

    /**
     * 新闻详情：例子：http://c.m.163.com/nc/article/BFNFMVO800034JAU/full.html
     *
     * @param docId 新闻详情的id
     * @return 被观察对象
     */
    @GET("nc/article/{docId}/full.html")
    Observable<ResponseBody> getNewsDetail(
            @Path("docId") String docId);


    /**
     * 美图大全响应  http://apis.baidu.com/showapi_open_bus/pic/pic_search?type=4001&page=0
     * @param type "id": 4001, //此id很重要，在【图片查询】接口里将使用此id进行分类查询
    "name": "清纯"
     * @param page 页数
     * @return
     */
    @GET("/showapi_open_bus/pic/pic_search")
    @Headers("apikey: " + "4720bdbcfb3aa457eefd38d2f8fa580f")
    Observable<ShowApiResponse<ShowApiPictures>> getPictureList(
            @Query("type") String type,
            @Query("page") int page);


    /**
     * 网易视频列表 例子：http://c.m.163.com/nc/video/list/00850FRB/n/0-10.html
     *
     * @param id        视频类别id
     * @param startPage 开始的页码
     * @return 被观察者
     */
    @GET("nc/video/list/{id}/n/{startPage}-10.html")
    Observable<ResponseBody> getVideoList(
            @Path("id") String id,
            @Path("startPage") int startPage);

}
