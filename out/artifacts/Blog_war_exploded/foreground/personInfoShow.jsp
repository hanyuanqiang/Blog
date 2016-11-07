<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/1
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="personInfo">
    <div align="center" class="div1">
        <div style="" class="div2">
            <img src="${admin.avatar}" class="img1"/>
        </div>
    </div>
    <p style="" align="center" class="p1">
        <a href="${pageContext.request.contextPath}/user_about_me.action" style="text-decoration: none;">${admin.nickName}</a>
    </p>
    <div style="border-top:1px dashed #cccccc;height: 1px;overflow:hidden;margin: 5px 20px;"></div>
    <div class="div3">
        <div id="calendar"></div>
        <script>
            $('#calendar').datepicker({
                inline: true,
                firstDay: 1,
                showOtherMonths: true,
                dayNamesMin: ['日', '一', '二', '三', '四', '五', '六']
            });
        </script>
    </div>
    <p style="margin-top: 20px;"></p>
    <div>
        <p style="margin-bottom: 0px;">
            <img src="${pageContext.request.contextPath}/images/博客分类.png">&nbsp;博文分类
        </p>
        <p style="border-top:1px solid #808080;"></p>

        <div>
            <ul style="padding-left: 5px;">
                <c:forEach items="${appli_typeList}" var="type">
                    <li style="list-style-type: none;margin-bottom: 10px;">
                        <img src="${pageContext.request.contextPath}/images/hand.png">
                        <a href="${pageContext.request.contextPath}/article_foreList.action?s_article.type.id=${type.id}" style="text-decoration: none;margin-left: 5px;font-size: 16px;">
                            ${type.typeName}&nbsp;(${type.articleNum})
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div style="margin-top: 30px;">
        <p style="margin-bottom: 0px;">
            <img src="${pageContext.request.contextPath}/images/日历16.png">&nbsp;博文归档
        </p>
        <p style="border-top:1px solid #808080;"></p>
        <div>
            <ul style="padding-left: 5px;">
                <c:forEach items="${year_month}" var="ym">
                    <li style="list-style-type: none;margin-bottom: 10px;">
                        <img src="${pageContext.request.contextPath}/images/日历2.png" style="margin-bottom: 4px;">
                        <a href="${pageContext.request.contextPath}/article_foreList.action?s_article.createTime=${fn:replace(ym.key, '年', '-').replaceAll('月','-01 00:00:00')}" style="text-decoration: none;margin-left: 5px;font-size: 16px;">
                            ${ym.key}&nbsp;(${ym.value})
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
