package com.example.mymodeldemos.model;

import java.util.List;

/**
 * Created by 吴城林 on 2017/9/15.
 */

public class Android {

    /**
     * error : false
     * results : [{"_id":"59b7a98e421aa911847a0392","createdAt":"2017-09-12T17:31:58.794Z","desc":"ViewStub学习","images":["http://img.gank.io/9f51b7bc-73d3-4ce4-bd59-c61cb0c64325"],"publishedAt":"2017-09-14T16:36:51.63Z","source":"web","type":"Android","url":"http://rkhcy.github.io/2017/09/12/ViewStub%E5%AD%A6%E4%B9%A0/","used":true,"who":"HuYounger"},{"_id":"59b7d963421aa911847a0394","createdAt":"2017-09-12T20:56:03.184Z","desc":"基于Snowflake算法而写的一种unique id生成器","publishedAt":"2017-09-14T16:36:51.63Z","source":"web","type":"Android","url":"https://github.com/zjutkz/ASnowflake","used":true,"who":null},{"_id":"59b8ad56421aa911878707ed","createdAt":"2017-09-13T12:00:22.260Z","desc":"看我逆向小米rom层应用做碎片化适配","publishedAt":"2017-09-14T16:36:51.63Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247486906&idx=1&sn=d6f473abffdea7b2ece6f6bb1f72e084","used":true,"who":"陈宇明"},{"_id":"59b9dfc1421aa9118c8262c1","createdAt":"2017-09-14T09:47:45.123Z","desc":"一个用 Kotlin 写的查单词 idea 插件","images":["http://img.gank.io/4939784c-eea2-4b60-9c7a-d1b4d9f9c8b5"],"publishedAt":"2017-09-14T16:36:51.63Z","source":"web","type":"Android","url":"https://github.com/jungletian/BreakWords","used":true,"who":"ZhangTitanjum"},{"_id":"59ba01b6421aa911878707f5","createdAt":"2017-09-14T12:12:38.108Z","desc":"Floo 开源了，一个支持 AOP、栈控制、跨页面带信、和动态变更映射的 URL 路由","publishedAt":"2017-09-14T16:36:51.63Z","source":"web","type":"Android","url":"https://github.com/drakeet/Floo","used":true,"who":"drakeet"},{"_id":"59b667cf421aa9118887ac12","createdAt":"2017-09-11T18:39:11.631Z","desc":"用少量Rxjava代码，为retrofit添加退避重试功能","publishedAt":"2017-09-12T08:15:08.26Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/fca90d0da2b5","used":true,"who":"小鄧子"},{"_id":"59b673ef421aa9118887ac13","createdAt":"2017-09-11T19:30:55.50Z","desc":"Android 端基于 OpenCV 的边框识别功能","publishedAt":"2017-09-12T08:15:08.26Z","source":"web","type":"Android","url":"https://pqpo.me/2017/09/11/opencv-border-recognition/","used":true,"who":"Linmin Qiu"},{"_id":"59b69738421aa9118c8262ad","createdAt":"2017-09-11T22:01:28.352Z","desc":"用ContentProvider初始化你的库","images":["http://img.gank.io/3b0b193d-6abf-4714-9d5a-5508404666f4"],"publishedAt":"2017-09-12T08:15:08.26Z","source":"web","type":"Android","url":"http://zjutkz.net/2017/09/11/%E4%B8%80%E4%B8%AA%E5%B0%8F%E6%8A%80%E5%B7%A7%E2%80%94%E2%80%94%E4%BD%BF%E7%94%A8ContentProvider%E5%88%9D%E5%A7%8B%E5%8C%96%E4%BD%A0%E7%9A%84Library/","used":true,"who":null},{"_id":"5996c673421aa96729c57262","createdAt":"2017-08-18T18:50:27.254Z","desc":"UiAutomator2.0升级填坑记","publishedAt":"2017-09-07T13:25:26.977Z","source":"web","type":"Android","url":"http://skyseraph.com/2017/06/04/Android/UiAutomator2.0%E5%8D%87%E7%BA%A7%E5%A1%AB%E5%9D%91%E8%AE%B0/","used":true,"who":"SkySeraph"},{"_id":"59a37311421aa901bcb7dbb2","createdAt":"2017-08-28T09:34:09.768Z","desc":"Scrum：官僚者们的游戏","publishedAt":"2017-09-07T13:25:26.977Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247486650&idx=1&sn=9c6e2e0fd5780aab2bcb02b823e7316e","used":true,"who":"陈宇明"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 59b7a98e421aa911847a0392
         * createdAt : 2017-09-12T17:31:58.794Z
         * desc : ViewStub学习
         * images : ["http://img.gank.io/9f51b7bc-73d3-4ce4-bd59-c61cb0c64325"]
         * publishedAt : 2017-09-14T16:36:51.63Z
         * source : web
         * type : Android
         * url : http://rkhcy.github.io/2017/09/12/ViewStub%E5%AD%A6%E4%B9%A0/
         * used : true
         * who : HuYounger
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
