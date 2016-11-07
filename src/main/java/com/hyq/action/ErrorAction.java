package com.hyq.action;

import com.hyq.util.ExceptionUtil;
import com.hyq.util.PrintException;
import com.hyq.util.SendMailUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/10/6.
 */
@Component
public class ErrorAction extends ActionSupport{

    public String processingError()throws Exception{
        //获取错误信息（exception是struts2框架中的一个默认的值）
        Exception ex = (Exception) ActionContext.getContext() .getValueStack().findValue("exception");

        SendMailUtil.send("1255426342@qq.com","博客系统报错","错误消息:"+ex.getMessage());
        SendMailUtil.send("15957196210@139.com","博客系统报错","错误消息:"+ex.getMessage());

        //将错误信息输出到日志文件中
        Logger logger = Logger.getLogger(this.getClass());
        logger.error(ExceptionUtil.getStackTrace(ex));

        //给出错误信息提示，并且跳回原界面
        PrintException.print(ServletActionContext.getResponse(),"错误操作导致系统异常");
        return null;
    }
}
