package com.hyq.action;

import com.hyq.service.UserService;
import com.hyq.util.DateUtil;
import com.hyq.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/3.
 */
@Controller
public class SessListenerAction extends ActionSupport implements ServletRequestAware{

    private HttpServletRequest request;

    @Resource
    private UserService userService;


    private static long data1 = System.currentTimeMillis();

    public static Thread t1;

    private HttpSession session;

    private String bgColor;

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    //每隔一分钟设置一次
    public String backlistener()throws Exception{
        session = request.getSession();

        if (StringUtil.isNotEmpty(bgColor)){
            session.setAttribute("bgColor",bgColor);
        }

        data1 = System.currentTimeMillis();

        //System.out.println("data1:"+ DateUtil.formatDate(new Date(data1),"yyyy-MM-dd HH:mm:ss"));

        if (t1==null||!t1.isAlive()){
            t1 = new Thread(new Runnable() {
                public void run() {
                while (true){
                    long data2 = System.currentTimeMillis();
                    //System.out.println("data2:"+DateUtil.formatDate(new Date(data2),"yyyy-MM-dd HH:mm:ss"));
                    long minute=(data2-data1)/(1000*60);//转化minute
                    //如果超过两分钟则表示断开连接(最大时间差为2分钟)
                    //System.out.println("minute: "+minute);
                    if (minute>2){
                        Long loginTime = (Long) session.getAttribute("loginTime");
                        if (loginTime!=null){
                            long min=(data1-loginTime)/(1000*60);   //转化minute
                            userService.updateTotalOnlineTime(min);
                            session.invalidate();   //清空会话内容
                            Logger logger = Logger.getLogger(this.getClass());
                            logger.info("---------// 退出系统，登录时长为："+min+"  //----------");
                        }else{
                            return;
                        }
                        break;
                    }
                    try {
                        Thread.sleep(1000*60*3);    //每隔三分钟判断一次
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                }
            });
            t1.start();
        }
        return null;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}
