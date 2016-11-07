<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/30
  Time: 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
    function checkForm(){
        var commentContent = $("#commentContent").val();
        if(!commentContent){
            $("#errorMsg").text("请输入评论内容！");
            $("#commentContent").focus();
            return false;
        }else{
            return true;
        }
    }
</script>

<div>
    <c:if test="${hiddenIcon=='show'}">
        <p align="left" style="margin-bottom: 0px;padding-top: 6px;padding-left: 6px;">
            <img src="${pageContext.request.contextPath}/images/最新发布.png">
            <span style="font-size: 16px;vertical-align: middle">最新发布</span>
        </p>
    </c:if>
    <c:if  test="${hiddenIcon==null}">
        <p style="height: 20px;"></p>
    </c:if>
    <div class="over-view"  style="padding:0px 20px;">
        <p class="title">
        <h3 style="text-align:center;margin-top: 0px;">
            <strong>
                ${article.title}
            </strong>
        </h3>
        <p style="text-align: center;">
            <span>类型：</span>
            <span>${article.type.typeName}</span>
            <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <span>浏览：</span>
            <span>
                ${article.pageViews}
            </span>
            <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <span>来源：</span>
            <span>${article.source}</span>
        </p>
        <p style="border-top:1px solid #808080;"></p>
        </p>
        <p class="main"><p style="font-size: 120%;text-indent:2em;" id=main>${article.content}</p>
        </p>
        <p style="text-align: right;">
            <span>发布日期：</span>
            <span><fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/></span>
        </p>
    </div>

    <div id="writeComment" style="margin-top: 60px;">
        <div style="margin: 0px 27px;">
            <form action="${pageContext.request.contextPath}/comment_save.action" method="post" onsubmit="return checkForm()">
                <input type="hidden" name="comment.article.id" value="${article.id}">
                <p style="margin-bottom: 2px;">输入评论：</p>
                <textarea style="width: 430px;height: 120px;background-color: transparent;border: solid 2px #73737a;margin-bottom: -7px;" name="comment.content" id="commentContent"></textarea>
                <input type="submit" class="btn-primary" value="评论" style="margin-left: 10px;">
                <span id="errorMsg" style="color: red;"></span>
            </form>
        </div>
    </div>
    <div id="showComment">
        <div style="padding: 0px 25px;margin-top: 20px;">
            <p style="margin-bottom: 0px;">
                <img src="${pageContext.request.contextPath}/images/评论.png">&nbsp;网友评论
            </p>
            <p style="border-top:2px solid #808080;"></p>

            <div>
                <c:forEach items="${article.commentList}" var="comment">
                    <div style="padding: 0px 10px;">
                        <p style="color: #195b5b;margin-bottom: 5px;">游客[ip:${comment.ip}]
                            <span style="margin-left: 25px;"><fmt:formatDate value="${comment.commentTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/></span>
                        </p>
                        <p>${comment.content}</p>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>