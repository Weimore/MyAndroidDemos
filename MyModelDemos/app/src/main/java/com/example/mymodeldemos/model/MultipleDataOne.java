package com.example.mymodeldemos.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by 吴城林 on 2017/8/24.
 */

public class MultipleDataOne implements MultiItemEntity {


    public static final int TYPE_HEADER =100 ;
    public static final int TYPE_ONE = 101;
    public static final int TYPE_TWO = 102;

    public int type;
    private int spanSize;

    public int getSpanSize() {
        return spanSize;
    }

    private int imageRes;
    private String topic;
    private String introduce;

    public MultipleDataOne(int imageRes, String topic, String introduce) {
        this.type =TYPE_ONE;
        this.imageRes = imageRes;
        this.topic = topic;
        this.introduce = introduce;
        this.spanSize=1;
    }

    public MultipleDataOne(String topic){
        this.type = TYPE_HEADER;
        this.topic = topic;
        this.spanSize=2;
    }

    public MultipleDataOne(int imageRes, String topic) {
        this.type =TYPE_TWO;
        this.imageRes = imageRes;
        this.topic = topic;
        this.spanSize=2;
    }

    @Override
    public int getItemType() {
        return type;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        imageRes = imageRes;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "DataOne{" +
                "imageRes=" + imageRes +
                ", topic='" + topic + '\'' +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
