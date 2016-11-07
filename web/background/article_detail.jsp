<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/30
  Time: 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="over-view"  style="padding:20px 30px;">
    <p class="title">
    <h3 style="text-align:center;">
        <strong>
            ${article.title}
        </strong>
    </h3>
    <p style="text-align: center;">
        <span>类型：</span>
        <span>${article.type.typeName}</span>
        <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <span>浏览：</span>
        <span>${article.pageViews}</span>
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
    <div id="showComment">
        <div style="margin-top: 20px;">
            <p style="margin-bottom: 0px;">
                <img src="${pageContext.request.contextPath}/images/评论.png">&nbsp;网友评论
            </p>
            <p style="border-top:2px solid #808080;"></p>

            <div>
                <c:forEach items="${article.commentList}" var="comment">
                    <div style="padding: 0px 10px;">
                        <p style="color: #195b5b;margin-bottom: 5px;">游客[ip:${comment.ip}]
                            <span style="margin-left: 25px;margin-right: 25px;"><fmt:formatDate value="${comment.commentTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/></span>
                            <a class="btn btn-danger btn-xs" href="${pageContext.request.contextPath}/comment_backdelete.action?comment.id=${comment.id}&comment.article.id=${article.id}" onclick="return confirm('确定删除这条评论吗？')">
                                删除
                            </a>
                        </p>
                        <p>${comment.content}</p>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>