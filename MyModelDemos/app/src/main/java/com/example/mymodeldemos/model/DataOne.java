package com.example.mymodeldemos.model;

/**
 * Created by 吴城林 on 2017/8/24.
 */

public class DataOne {

//    public static final int TYPE_HEADER =0 ;
//    public static final int TYPE_ONE = 1;
//    public static final int TYPE_TWO = 2;
//
//    public int type;

    private int imageRes;
    private String topic;
    private String introduce;

    public DataOne(int imageRes, String topic, String introduce) {
        this.imageRes = imageRes;
        this.topic = topic;
        this.introduce = introduce;
    }

//    public DataOne(String topic){
//        this.type = TYPE_HEADER;
//    }

    public DataOne(int imageRes, String topic) {
        this.imageRes = imageRes;
        this.topic = topic;
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
