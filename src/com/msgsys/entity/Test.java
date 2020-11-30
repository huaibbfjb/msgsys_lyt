package com.msgsys.entity;

import com.google.gson.Gson;

/**
 * @author ：liuyuntao
 * @date ：Created in 2020/11/30 9:56
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

public class Test {
    @org.junit.Test
    public void t1(){
        User user=new User(1,"lyt","123","123@qq.com");
        //创建Gson对象实例
        Gson gson=new Gson();
        //把user转成JSON
        String userJSONStr=gson.toJson(user);
        System.out.println(userJSONStr);
        //把JSON字符串转成JAVABEAN对象
        User user2=gson.fromJson(userJSONStr,User.class);
        System.out.println("user对象:"+user2);
        //把user转成JSON
        String userJSONStr2=gson.toJson(user2);
        System.out.println(userJSONStr2);
    }


}
