package com.ustc.gry.inews.bean.news;

import java.io.Serializable;
import java.util.List;

/**
 * 作者： gry
 * 功能：
 * 创建时间： 2018/7/8
 */

public class NewsDetailBean implements Serializable {
    /**
     * docid
     */
    private String docid;
    /**
     * title
     */
    private String title;
    /**
     * source
     */
    private String source;
    /**
     * body
     */
    private String body;
    /**
     * ptime
     */
    private String ptime;
    /**
     * cover
     */
    private String cover;
    /**
     * 图片列表
     */
    private List<NewsDetailPic> img;

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<NewsDetailPic> getImg() {
        return img;
    }
    /**
     * 07-23 15:26:55.219 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ 请求网址: https://c.m.163.com/nc/article/DNDJ2SS9000189FH/full.html
     07-23 15:26:55.219 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ 请求方法: GET
     07-23 15:26:55.219 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ 请求头: User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_5) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1.1 Safari/605.1.15
     07-23 15:26:55.219 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ 请求体: null
     07-23 15:26:55.219 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │
     07-23 15:26:55.219 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ 响应头：
     07-23 15:26:55.219 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ Server: nginx
     07-23 15:26:55.219 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ Date: Mon, 23 Jul 2018 07:26:55 GMT
     07-23 15:26:55.219 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ Content-Type: application/json;charset=utf-8
     07-23 15:26:55.219 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ Transfer-Encoding: chunked
     07-23 15:26:55.219 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ Connection: keep-alive
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ Expires: Mon, 23 Jul 2018 07:36:55 GMT
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ X_cache: HIT from news-proxy0.dg.163.org
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ Cache-Control: max-age=0
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ 响应体:
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ {
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │   "DNDJ2SS9000189FH": {
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "template": "normal",
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "img": [
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       {
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "ref": "<!--IMG#0-->",
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "src": "http:\/\/cms-bucket.nosdn.127.net\/catchpic\/1\/1d\/1d8661a818a5985e44e89b72ad5b20ee.jpg",
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "alt": "",
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "pixel": "979*681"
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       },
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       {
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "ref": "<!--IMG#1-->",
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "src": "http:\/\/cms-bucket.nosdn.127.net\/catchpic\/5\/5c\/5c98eacbe14e0bd2b60eba33e71b6d72.jpg",
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "alt": "",
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "pixel": "622*335"
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       },
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       {
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "ref": "<!--IMG#2-->",
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "src": "http:\/\/cms-bucket.nosdn.127.net\/catchpic\/6\/6f\/6f1486719aba781d232f55734896aa66.jpg",
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "alt": "",
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "pixel": "664*460"
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       }
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     ],
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "sourceinfo": {
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       "ename": "T1462425711194",
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       "certificationImg": "http:\/\/img2.cache.netease.com\/m\/newsapp\/reading\/vip\/blue.png",
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       "certificationText": "央视网官方网易号",
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       "alias": "中央电视台新媒体平台",
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       "tname": "央视网",
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       "topic_icons": "http:\/\/dingyue.nosdn.127.net\/s6BrLi54RHhLpGk3rTyZxybRSLNmCUQPtIaMEzGWTZXR91462425710659.jpg",
     07-23 15:26:55.220 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       "tid": "T1462425711194"
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     },
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "searchKw": [
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       {
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "weight": "3.774642",
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "keyword": "南苏丹",
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "tag_source": 1
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       },
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       {
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "weight": "71.670998",
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "keyword": "中非合作",
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "tag_source": 1
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       },
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       {
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "weight": "22.292916",
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "keyword": "中国外交",
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "tag_source": 1
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       },
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       {
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "weight": "8.043458",
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "keyword": "蒙内铁路",
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "tag_source": 1
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       },
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       {
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "weight": "3.557581",
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "keyword": "安全合作",
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "tag_source": 1
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       },
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       {
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "weight": "7.429743",
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "keyword": "产能合作",
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "tag_source": 1
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       },
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       {
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "weight": "54.629635",
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "keyword": "中非关系",
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "tag_source": 1
     07-23 15:26:55.221 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       },
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       {
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "weight": "111.088407",
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "keyword": "习近平主席",
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "tag_source": 1
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       },
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       {
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "weight": "5.961899",
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "keyword": "兄弟情",
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "tag_source": 1
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       }
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     ],
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "topiclist_news": [
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       {
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "ename": "yaowenspecial",
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "hasCover": false,
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "tname": "要闻",
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "alias": "yaowenspecial",
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "subnum": "0",
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "tid": "T1467284926140",
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "cid": "C1348647991705"
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       },
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       {
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "ename": "iosnews",
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "hasCover": false,
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "tname": "头条",
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "alias": "Top News",
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "subnum": "0",
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "tid": "T1348647853363",
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "cid": "C1348646712614"
     07-23 15:26:55.222 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       },
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       {
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "ename": "androidnews",
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "hasCover": false,
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "tname": "头条",
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "alias": "Top News",
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "subnum": "0",
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "tid": "T1348647909107",
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │         "cid": "C1348646712614"
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │       }
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     ],
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "book": [],
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "link": [],
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "shareLink": "https:\/\/c.m.163.com\/news\/a\/DNDJ2SS9000189FH.html?spss=newsapp",
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "source": "央视网",
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "threadVote": 0,
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "title": "习近平为中非人民凝聚兄弟情",
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "body": "<p>　　<strong>央视网消息：“我高度重视中非关系<\/strong>，<strong>我深深感到<\/strong>，<strong>中非长期友好<\/strong>，<strong>命运休戚与共。双方是发展道路上的真诚伙伴<\/strong>，<strong>是国际事务中的天然同盟军。”“中国将秉持真实亲诚理念和正确义利观<\/strong>，<strong>同包括卢旺达在内的广大非洲友好国家一道<\/strong>，<strong>加强交流<\/st
     07-23 15:26:55.223 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ rong>，<strong>增进互信<\/strong>，<strong>扩大合作。”<\/strong><\/p><p>　　近日，国家主席习近平再次开启“非洲之行”，出访塞内加尔、卢旺达、南非、毛里求斯四个非洲国家，共温共聚“兄弟情”。<\/p><p>　　60余载守望相助，一直以来，巩固中非关系是中国外交的重要基石。秉持“己欲立而立人，己欲达而达人”的态度，习近平主席提出“四字箴言”“五大支柱”“十大合作计划”，引领中非关系行稳致远。<\/p><p>　　<strong>中非“兄弟情” 比黄金还要宝贵<\/strong><\/p><p>　　<strong>“中非友好源远流长<\/strong>，<strong>历久弥新<\/strong>，<strong>经受了国际风云变幻的考验<\/strong>，<strong>收获了比黄金还要宝贵的真情和信任。”<\/strong><\/p><p>　　中国人民永远不会忘记，北京奥运会、上海世博会的成功举办，得到非洲朋友们的大力支持和参与；抗击四川汶川特大地震灾害，得到非洲朋友们的真情相助&hellip;&hellip;<\/p><p>　　非洲人民永远不会忘记，埃博拉疫情来袭的生死关头，中国率先行动援非抗疫；当非洲之角发生旱情时，中国紧急把粮食送到灾区人民手中&hellip;&hellip;<\/p><p>　　<strong>“无论中国发展到哪一步<\/strong>，<strong>中国永远都把非洲国家当作自己的患难之交。”<\/strong>怀着对非洲的深情厚意，习近平主席一直对非洲国家发展念念不忘。<\/p><p>　　2013年，习近平就任国家主席后首次出访就到了非洲，提出真实亲诚中非关系理念和正确义利观，并郑重宣布中国永远做非洲国家的可靠朋友和真诚伙伴。2015年12月，习近平主席再次访问非洲，在中非合作论坛约翰内斯堡峰会上，他提出中非合作“五大支柱”和“十大合作计划”。<\/p><!--IMG#0--><p>　　2015年12月4日，习近平主席在约翰内斯堡出席中非领导人与工商界代表高层对话会暨第五届中非企业家大会闭幕式并发表题为《携手共进，谱写中非合作新篇章》的重要讲话。 图片来源：新华社<\/p><p>　　<strong>“高度政治互信是中非友好的基石。”“非洲是非洲人的非洲<\/strong>，<strong>非洲的事情应该由非洲人说了算。”<\/strong><\/p><p>　　中国不附加任何政治条件、不干涉内政、不提强人所难的要求，帮助非洲国家大力实施基础设施建设。这对曾历经屈辱苦难，把平等看得格外重要的非洲国家来说，显得尤为珍贵。<\/p><p>　　塞内加尔总统萨勒在此次与习近平主席的会谈中表示：“感谢中国对塞内加尔经济社会发展、实施复兴计划的宝贵支持。建立在团结、互信、相互尊重、互利共赢基础之上的塞中关系强而有力，令人满意。”津巴布韦总统姆南加古瓦也曾表示：“津巴布韦政府和人民永远不会忘记中国一直以来给予的支持，感谢中国多年来对津巴布韦改善民生的帮助。”时任赞比亚总统班达坦言：“来自中国的投资者对非洲国家经济发展的促进有目共睹，他们才是真正帮助我们的人”。<\/p><p>　　中国是非洲的“真朋友”“好朋友”“伟大的朋友”。非洲国家领导人这些对中国热情洋溢的称谓，是标注中非关系的最好定语。<\/p><p>　　<strong>全方位合作 共筑中非命运共同体<\/strong><\/p><p>　　<strong>“中国和非洲历来是休戚与共的命运共同体<\/strong>，<strong>是合作共赢的利益共同体。中非传统友好深得人心<\/strong>，<strong>发展同非洲国家的团结合作始终是中国对外政策基石。”<\/strong>中国和非洲，在彼此最困难的年代相互支持，如今又在合作共赢的基础上双双步入发展快车道。<\/p><p>　　2015年，习近平主席访问非洲期间为非洲国家带��
     07-23 15:26:55.224 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ �“十大合作计划”，并为此提供配套资金支持。中非合作领域更宽、范围更广。<\/p><p>　　习近平多次强调：<strong>中非关系最大的“义”<\/strong>，<strong>就是用中国发展助力非洲的发展<\/strong>，<strong>最终实现互利共赢、共同发展。<\/strong><\/p><p>　　言必信，行必果。三年来，中非“十大合作计划”绝大部分项目已提前完成。<\/p><p>　　工业合作领域，中国为非洲中小企业发展增资50亿美元专项贷款，新设100亿美元中非产能合作基金，同时为非洲国家提供15万人次的专业技术人才培训。埃及苏伊士运河经贸合作区自建设以来，产值已达7亿美元，为当地政府缴纳税费5800万美元，带动就业3300人。<\/p><!--IMG#1--><p>　　2017年6月9日，在肯尼亚蒙内铁路内罗毕南站，乘务员登上列车。蒙内铁路全长约480公里，2017年5月31日正式通车，6月1日开始客运服务。每天运行一对往返蒙巴萨与内罗毕的列车。图片来源：新华社<\/p><p>　　基础设施方面，中国帮助非洲建设了一批公路、铁路、机场、港口&hellip;&hellip;2017年5月31日通车的蒙内铁路是落实中非“十大合作计划”的重要成果，也是中非“三网一化”和产能合作的标志性工程。肯尼亚总统乌胡鲁·肯雅塔在视察蒙内铁路建设时说：“蒙内铁路将带领肯尼亚开启工业化进程，实现经济转型。”<\/p><p>　　安全合作领域，非洲是中国最主要的维和地区。目前中国在南苏丹、马里等地有约2500名维和驻军，占中国全球维和人员80%以上。2015年9月，习近平在联合国大会会议期间宣布，中国五年内将提供1亿美元支持非盟常备军建设，并建设8000人的联合国维和待命部队。<\/p><p>　　国际事务中，中国还通过外交渠道积极在非洲重大和平安全问题上“劝谈促和”，为推动南北苏丹和解、实现埃塞俄比亚和厄立特里亚恢复关系正常化等做出重要贡献。<\/p><p>　　中非携手，情真意切；中非合作，前景美好。<\/p><p>　　当前，中国正在中非合作论坛和共建“一带一路”框架下，加快推进同非洲的全方位合作，帮助非洲各国实现经济独立和自主发展，并且拿出了最大的决心和诚意：<strong>“任凭国际格局调整演变<\/strong>，<strong>中非平等互信、相互支持的兄弟情谊不会改变；任凭经济形势起伏跌宕<\/strong>，<strong>中非合作共赢、共同发展的根本宗旨不会改变；任凭时代社会发展变迁<\/strong>，<strong>中非相互理解、共同进步的协作精神不会改变；任凭出现各种威胁挑战<\/strong>，<strong>中非风雨同舟、患难与共的坚定意志不会改变。”<\/strong><\/p><p>　　<strong>中国梦牵手非洲梦 共圆人民幸福生活<\/strong><\/p><p>　　2015年在中非合作论坛15周年成果图片展上，一张印有水电站图像的纸币是中非合作改善民生的最好见证。长久以来，干旱缺水一直困扰非洲某些地区，人们不得不赶着骆驼、骑着毛驴跋涉取水。中国同几内亚合作建设的水电站项目，满足了当地百姓生活所需。为了让人民不忘中国情义，几内亚政府特意把水电站图像印在纸币上。<\/p><p>　　相知无远近，万里尚为邻。<strong>“联结我们的不仅是深厚的传统友谊、密切的利益纽带<\/strong>，<strong>还有我们各自的梦想。”<\/strong><\/p><p>　　中国人民正致力于实现中华民族伟大复兴的中国梦，而非洲人民也正致力于实现联合自强、发展振兴的非洲梦。中非是同甘共苦的好兄弟、是平等合作的好朋友、是共同发展的好伙伴。发展经济、改善民生、强国富民是中非人民的共同目标。<\/p><p>　　一直以来，中国秉持真实亲诚的对非
     07-23 15:26:55.224 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ 政策理念和正确义利观，注重授人以渔，帮非方筑巢引凤。“十大合作计划”中，习近平专门提出四个针对民生发展领域的计划：中非农业现代化合作计划、中非绿色发展合作计划、中非减贫惠民合作计划和中非公共卫生合作计划。一项项农业富民工程、幸福生活工程，以及人道主义援助，不仅帮助非洲减少了贫困，改善了民生，更帮助非洲提高了自主发展能力，增强了自身“造血”功能。<\/p><!--IMG#2--><p>　　2018年7月21日，国家主席习近平抵达达喀尔，开始对塞内加尔共和国进行国事访问。这是习近平在萨勒陪同下检阅仪仗队。 图片来源：新华社<\/p><p>　　<strong>“中国发展好了<\/strong>，<strong>非洲也有机遇；非洲发展起来了<\/strong>，<strong>中国也会受益；中国和非洲都发展进步了<\/strong>，<strong>世界会变得更美好。”<\/strong>一个多月后，中非合作论坛峰会将在北京召开。习近平主席将同非洲各国领导人一道，共商中非友好合作大计，共绘中非发展美好蓝图。<\/p><p>　　中国梦——非洲梦，为人民谋幸福的梦想，汇聚起数亿人民的智慧和力量，浇灌中非合作共赢、共同发展的友谊之树，必将在国际舞台上大放异彩。<\/p><p>原标题：习近平为中非人民凝聚兄弟情<\/p><p><!--viewpoint--><\/p>",
     07-23 15:26:55.224 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "tid": "",
     07-23 15:26:55.224 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "picnews": true,
     07-23 15:26:55.224 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "advertiseType": "0001",
     07-23 15:26:55.224 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "articleType": "wemedia",
     07-23 15:26:55.224 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "digest": "",
     07-23 15:26:55.224 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "boboList": [],
     07-23 15:26:55.224 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "ptime": "2018-07-23 14:54:46",
     07-23 15:26:55.225 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "ec": "张美玲_NN5644",
     07-23 15:26:55.225 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "docid": "DNDJ2SS9000189FH",
     07-23 15:26:55.225 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "threadAgainst": 4,
     07-23 15:26:55.225 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "hasNext": false,
     07-23 15:26:55.225 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "hideAd": true,
     07-23 15:26:55.225 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "dkeys": "习近平,中非,非洲,肯尼亚",
     07-23 15:26:55.225 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "ydbaike": [],
     07-23 15:26:55.225 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "replyCount": 0,
     07-23 15:26:55.225 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "voicecomment": "off",
     07-23 15:26:55.225 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "replyBoard": "news2_bbs",
     07-23 15:26:55.225 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "votes": [],
     07-23 15:26:55.225 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "topiclist": [],
     07-23 15:26:55.225 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │     "category": "要闻"
     07-23 15:26:55.225 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │   }
     07-23 15:26:55.225 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: │ }
     07-23 15:26:55.225 2375-2424/com.ustc.gry.inews I/PRETTY_LOGGER: └────
     */
}
