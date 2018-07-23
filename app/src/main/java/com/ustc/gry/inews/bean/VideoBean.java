package com.ustc.gry.inews.bean;

import java.io.Serializable;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/22
 */

public class VideoBean implements Serializable{

    /**
     * Jsonobject
     * {
           "sizeHD": 11562,
           "mp4Hd_url": "http://flv3.bn.netease.com/videolib3/1503/18/Roimx3257/HD/Roimx3257-mobile.mp4",
           "description": "不一样的美男妖性测评充气绵羊,花泽类在网上购买了一只毁三观的妖艳小白羊，不为此神兽做一期测评真是说不过去~\r\n新浪微博&amp;微信公众号：&#064;东北花泽类\r\n",
           "title": "第5弹：花美男评测充气绵羊",
           "mp4_url": "http://flv3.bn.netease.com/videolib3/1503/18/Roimx3257/SD/Roimx3257-mobile.mp4",
       *   "cover": "http://img5.cache.netease.com/3g/2015/3/19/201503191119186f6e6.jpg",
       *   "vid": "VAK2MR2FO",
           "sizeSHD": 19926,
           "playersize": 1,
           "ptime": "2015-03-18 15:21:24",
           "m3u8_url": "http://flv.bn.netease.com/videolib3/1503/18/Roimx3257/SD/movie_index.m3u8",
           "latest": "",
           "topicImg": "http://img1.cache.netease.com/m/newsapp/video/default.jpg",
           "votecount": 12,
           "length": 164,
           "videosource": "新媒体",
           "priority": "79",
           "m3u8Hd_url": "http://flv.bn.netease.com/videolib3/1503/18/Roimx3257/HD/movie_index.m3u8",
           "topicSid": "VAH83MP0F",
           "sizeSD": 7134,
           "playCount": 47823,
           "replyCount": 19,
           "replyBoard": "video_bbs",
           "replyid": "AK2MR2FO008535RB",
           "topicName": "东北花泽类《新技能get√》",
           "sectiontitle": "东北花泽类《新技能get√》",
           "topicDesc": "与众不同最时尚重口味How to类节目，寓教于乐，涨姿势必备，每周一集，让你技能满满！"
       }
     */
    
    /**
     * VideoBean
     * replyCount : 895
     * videosource : 新媒体
     * mp4Hd_url : null
     * cover : http://vimg2.ws.126.net/image/snapshot/2016/2/5/H/VBEMM3F5H.jpg
     * title : 渥太华600人欢乐春节快闪
     * playCount : 22751
     * replyBoard : video_bbs
     * sectiontitle :
     * description : 超过600人在-11度的天气下庆祝春节！
     * replyid : BEML6HHI008535RB
     * mp4_url : http://flv2.bn.netease.com/videolib3/1602/12/BFihm9923/SD/BFihm9923-mobile.mp4
     * length : 666
     * playersize : 1
     * m3u8Hd_url : null
     * vid : VBEML6HHI
     * m3u8_url : http://flv2.bn.netease.com/videolib3/1602/12/BFihm9923/SD/movie_index.m3u8
     * ptime : 2016-02-12 12:05:39
     */

    private String mp4Hd_url;

    private String description;

    /**
     * 标题
     */
    private String title;

    private String mp4_url;

    /**
     * 封面图片
     */
    private String cover;

    private String vid;

    private int playersize;

    private String ptime;

    private String m3u8_url;

    /**
     * 来源
     */
    private String videosource;

    private String m3u8Hd_url;

    /**
     * 播放数
     */
    private int playCount;

    /**
     * 评论数
     */
    private int replyCount;

    public String getMp4Hd_url() {
        return mp4Hd_url;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getMp4_url() {
        return mp4_url;
    }

    public String getCover() {
        return cover;
    }

    public String getVid() {
        return vid;
    }

    public int getPlayersize() {
        return playersize;
    }

    public String getPtime() {
        return ptime;
    }

    public String getM3u8_url() {
        return m3u8_url;
    }

    public String getVideosource() {
        return videosource;
    }

    public String getM3u8Hd_url() {
        return m3u8Hd_url;
    }

    public int getPlayCount() {
        return playCount;
    }

    public int getReplyCount() {
        return replyCount;
    }
}
