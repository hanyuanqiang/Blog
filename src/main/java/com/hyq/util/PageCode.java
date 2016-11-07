package com.hyq.util;

/**
 * Created by Administrator on 2016/6/29.
 */
public class PageCode {
    public static String getPageCode(long totalNum,int currentPage,int pageSize,String url,String param){
        pageSize = Integer.parseInt(PropertiesUtil.getValue("pageSize"));
        //获取总页数
        long totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        StringBuffer pageCode = new StringBuffer();
        pageCode.append("<li><a href='/blog"+url+"?currentPage=1"+param+"'>首页</a></li>");
        if(currentPage==1){
            pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
        }else{
            pageCode.append("<li><a href='/blog"+url+"?currentPage="+(currentPage-1)+param+"'>上一页</a></li>");
        }

        for(int i=currentPage-2;i<=currentPage+2;i++){
            //只有当currentPage大于等于3时才不会进入下面if语句中,然后中间加载5个(若5个超出总页数则加载到最后一页)
            if(i<1||i>totalPage){
                continue;
            }
            if(i==currentPage){
                pageCode.append("<li class='active'><a href='#'  style='color: white'>"+i+"</a></li>");
            }else{
                pageCode.append("<li><a href='/blog"+url+"?currentPage="+i+param+"'>"+i+"</a></li>");
            }
        }

        if(currentPage==totalPage){
            pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
        }else{
            pageCode.append("<li><a href='/blog"+url+"?currentPage="+(currentPage+1)+param+"'>下一页</a></li>");
        }

        pageCode.append("<li><a href='/blog"+url+"?currentPage="+totalPage+param+"'>尾页</a></li>");
        return pageCode.toString();
    }
}
