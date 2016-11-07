package com.hyq.action;


import com.hyq.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/4.
 */
@Component
public class MyInterceptor implements Interceptor{


    public void destroy() {

    }

    public void init() {

    }

    public String intercept(ActionInvocation invocation) throws Exception {
        //System.out.println("方法:"+invocation.getInvocationContext().getName());

        String reqstring = invocation.getInvocationContext().getName();

        if(reqstring.indexOf("back")>0&&!reqstring.contains("backdetail")){
            Map session = invocation.getInvocationContext().getSession();
            User currentUser = (User)session.get("currentUser");
            if (currentUser==null){
                return "goLogin";
            }else {
                invocation.invoke();
            }
        }else{
            invocation.invoke();
        }

        return null;
    }

}
