package com.hyq.util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/9/24.
 */
public class PrintException {

    public static void print(HttpServletResponse response,String message)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println("<script>\n" +
                "\n" +
                "    document.write('错误信息：【"+message+"】，3秒后跳回原页面');\n" +
                "    window.setTimeout(back,3000);\n" +
                "    function back(){\n" +
                "        window.history.back();\n" +
                "    }\n" +
                "</script>");
        out.flush();
        out.close();
    }

}
