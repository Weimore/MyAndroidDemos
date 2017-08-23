package com.example.mymodeldemos.application;

/**
 * Created by 吴城林 on 2017/8/23.
 */

public class ConstantValue {
    //app当前的可能各种状态
    public static final int STATUS_FORCE_KILLED = -1;  //系统被强杀状态
    public static final int STATUS_OFF_LINE = 1;   //未登录或离线状态
    public static final int STATUS_ON_LINE = 2;    //已登录状态
    public static final int STATUS_KICK_OUT = 3;   //登录失效状态（token过时 或 被挤下线）
}
