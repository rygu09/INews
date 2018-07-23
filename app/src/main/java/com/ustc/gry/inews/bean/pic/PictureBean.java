package com.ustc.gry.inews.bean.pic;

import java.util.List;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/22
 */

/**
 * 响应体:
   │ {
   │   "showapi_res_error": "",
   │   "showapi_res_code": 0,
   │   "showapi_res_body": {
   │     "pagebean": {
   │       "allNum": 863,
   │       "contentlist": [
   │         {
   │           "list": [
   │             {
   │               "small": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/04\/5YFCL69J128O_113.jpg",
   │               "middle": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/04\/5YFCL69J128O_680x500.jpg",
   │               "big": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/04\/5YFCL69J128O.jpg"
   │             },
   │             {
   │               "small": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/49\/2667XUHVQ490_113.jpg",
   │               "middle": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/49\/2667XUHVQ490_680x500.jpg",
   │               "big": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/49\/2667XUHVQ490.jpg"
   │             },
   │             {
   │               "small": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/51\/OG55WW6U8QH3_113.jpg",
   │               "middle": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/51\/OG55WW6U8QH3_680x500.jpg",
   │               "big": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/51\/OG55WW6U8QH3.jpg"
   │             },
   │             {
   │               "small": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/55\/CL7XU3JQC5EL_113.jpg",
   │               "middle": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/55\/CL7XU3JQC5EL_680x500.jpg",
   │               "big": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/55\/CL7XU3JQC5EL.jpg"
   │             },
   │             {
   │               "small": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/59\/084508YZ0411_113.jpg",
   │               "middle": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/59\/084508YZ0411_680x500.jpg",
   │               "big": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/59\/084508YZ0411.jpg"
   │             },
   │             {
   │               "small": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/00\/U8R4V59R68Z1_113.jpg",
   │               "middle": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/00\/U8R4V59R68Z1_680x500.jpg",
   │               "big": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/00\/U8R4V59R68Z1.jpg"
   │             },
   │             {
   │               "small": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/02\/757W9OOAE6A3_113.jpg",
   │               "middle": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/02\/757W9OOAE6A3_680x500.jpg",
   │               "big": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/02\/757W9OOAE6A3.jpg"
   │             },
   │             {
   │               "small": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/57\/ACK21O53NC6B_113.jpg",
   │               "middle": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/57\/ACK21O53NC6B_680x500.jpg",
   │               "big": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/57\/ACK21O53NC6B.jpg"
   │             },
   │             {
   │               "small": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/54\/L7LYZ06X0FK1_113.jpg",
   │               "middle": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/126\/54\/L7LYZ06X0FK1_680x500.jpg",
   │               "big": "http:\/\/image.tianjimedia.com\/uploadImages\/2014\/12
   │ 6\/54\/L7LYZ06X0FK1.jpg"
   │             }
   │           ],
   │           "title": "单车小妹遇见阳光迎风笑脸靓丽无比",
   │           "ct": "2018-07-22 04:10:51.790",
   │           "typeName": "清纯",
   │           "itemId": "37072094",
   │           "type": 4001
   │         },
 */


public class PictureBean{
    private String ct;// 2016-03-10 04;//12;//06.606,
    private String itemId;// 39889571,
    private String title;// 清纯美女头像壁纸 葵花丛中的女孩,
    private String type;// 4001,
    private String typeName;// 清纯
    private List<PictureImage> list;//图片数组

    public String getTitle() {
        return title;
    }

    public List<PictureImage> getList() {
        return list;
    }
}

