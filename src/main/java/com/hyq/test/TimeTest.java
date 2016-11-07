package com.hyq.test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/3.
 */
public class TimeTest {

    public static void main(String[] args) throws Exception{
        Calendar dateOne=Calendar.getInstance(),dateTwo= Calendar.getInstance();
        dateOne.setTime(new Date());	//设置为当前系统时间
        Thread.sleep(1100*2);
        dateTwo.setTime(new Date());
        long timeOne=dateOne.getTimeInMillis();
        System.out.println("timeOne"+timeOne);
        System.out.println(System.currentTimeMillis());
        long timeTwo=dateTwo.getTimeInMillis();
        System.out.println(timeTwo);
        long minute=(timeTwo-timeOne)/(1000*60);//转化minute
        System.out.println("相隔"+minute+"分钟");
    }
}
