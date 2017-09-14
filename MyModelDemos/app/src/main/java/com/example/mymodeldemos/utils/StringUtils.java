package com.example.mymodeldemos.utils;

/**
 * Created by 吴城林 on 2017/9/13.
 */

public class StringUtils {

    //第一个数据传入要分割的字符串，第二个传入分割符，第三个传入要取的位置
    public static String getSplit(String content,String split,int i){
        String date[] = content.split(split);
        if((i-1)>=0 && (i-1)<date.length){
            String result = date[i-1];
            return result;
        }
        return "error";
    }
}
