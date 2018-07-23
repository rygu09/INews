package com.ustc.gry.inews.bean.pic;

import java.util.List;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/22
 */

public class ShowApiPictures {
    public PageBean pagebean;
    public String ret_code;
    public class PageBean {
        public String allNum;
        public String allPages;
        public String currentPage;
        public String maxResult;
        public List<PictureBean> contentlist;
    }
}
