package com.ustc.gry.inews.utils;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;
import com.ustc.gry.inews.bean.news.NewsBean;
import com.ustc.gry.inews.bean.news.NewsDetailBean;
import com.ustc.gry.inews.bean.pic.PictureBean;
import com.ustc.gry.inews.bean.VideoBean;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/5
 */

public class JsonUtils {

    private static Gson mGson = new Gson();

    /**
     * 将对象准换为json字符串
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String serialize(T object) {
        return mGson.toJson(object);
    }

    /**
     * 将json字符串转换为对象
     * @param json
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T deserialize(String json, Class<T> clz) throws JsonSyntaxException {
        return mGson.fromJson(json, clz);
    }

    /**
     * 将json对象转换为实体对象
     * @param json
     * @param clz
     * @param <T>
     * @return
     * @throws JsonSyntaxException
     */
    public static <T> T deserialize(JsonObject json, Class<T> clz) throws JsonSyntaxException {
        return mGson.fromJson(json, clz);
    }

    /**
     * 将json字符串转换为对象
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T deserialize(String json, Type type) throws JsonSyntaxException {
        return mGson.fromJson(json, type);
    }

    /**
     * 新闻
     * 将responsebody转化为NewsBean
     * @param res
     * @param id
     * @return
     */
    public static List<NewsBean> readJsonNewsBeans(String res,String id) {
        List<NewsBean> beans = new ArrayList<NewsBean>();
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
            JsonArray jsonArray = jsonObj.getAsJsonArray(id);
            for (int i = 1; i < jsonArray.size(); i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                if (jo.has("skipType") && "special".equals(jo.get("skipType").getAsString())) {
                    continue;
                }
                if (jo.has("TAGS") && !jo.has("TAG")) {
                    continue;
                }

                if (!jo.has("imgextra")) {
                    NewsBean news = JsonUtils.deserialize(jo, NewsBean.class);
                    beans.add(news);
                }
            }
        } catch (Exception e) {
            Logger.e(e.getStackTrace().toString());
        }
        return beans;
    }

    /**
     * 新闻详情
     * 将responsebody转化为NewsDetailBean
     * @param res
     * @param docId
     * @return
     */
    public static NewsDetailBean readJsonNewsDetailBeans(String res, String docId) {
        NewsDetailBean newsDetailBean = null;
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
            JsonElement jsonElement = jsonObj.get(docId);
            if(jsonElement == null) {
                return null;
            }
            newsDetailBean = JsonUtils.deserialize(jsonElement.getAsJsonObject(), NewsDetailBean.class);
        } catch (Exception e) {
            Logger.e( "readJsonNewsDetailBeans error" );
        }
        return newsDetailBean;
    }

    /**
     * 图片
     * 将responsebody转化为PictureBean
     * @param res
     * @param id
     * @return
     */
    public static List<PictureBean> readJsonPictureBeans(String res, String id) {
        List<PictureBean> beans = new ArrayList<>();

        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
            JsonArray jsonArray = jsonObj.getAsJsonArray(id);
            for (int i = 1; i < jsonArray.size(); i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                PictureBean video = JsonUtils.deserialize(jo, PictureBean.class);
                beans.add(video);
            }
        } catch (Exception e) {
            Logger.e( "readJsonPictureBeans error" );
        }
        return beans;
    }

    /**
     * 视频
     * 将responsebody转化为VideoBean
     * @param res
     * @param id
     * @return
     */
    public static List<VideoBean> readJsonVideoBeans(String res, String id) {
        List<VideoBean> beans = new ArrayList<>();

        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
            JsonArray jsonArray = jsonObj.getAsJsonArray(id);
            for (int i = 1; i < jsonArray.size(); i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                VideoBean video = JsonUtils.deserialize(jo, VideoBean.class);
                beans.add(video);
            }
        } catch (Exception e) {
            Logger.e( "readJsonVideoBeans error" );
        }
        return beans;
    }
}
