<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/2
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
    <h3 align="center">游客留言管理</h3>
    <div style="padding: 20px 20px;">
        <c:forEach items="${leaveWordsList}" var="leaveWords">
            <div style="padding: 0px 10px;margin-bottom: 20px;">
                <p style="color: rgba(0, 159, 28, 0.93);margin-bottom: 5px;">
                    <c:if test="${leaveWords.status==0}">
                        <span style="color: red;font-size: 10px;">(新留言)</span>
                    </c:if>
                    <span>游客&nbsp;[${leaveWords.ip}]</span>
                    <span style="margin-left: 25px;margin-right: 25px;"><fmt:formatDate value="${leaveWords.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/></span>
                    <a class="btn btn-danger btn-xs" href="${pageContext.request.contextPath}/leaveWords_backdelete.action?leaveWordsId=${leaveWords.id}" onclick="return confirm('确定删除这条留言吗？')">
                        删除
                    </a>
                </p>
                <p>
                    ${leaveWords.content}
                    <c:if test="${leaveWords.tourEmail!=''}">
                        <span style="margin-left: 10px;">(&nbsp;联系方式：${leaveWords.tourEmail}&nbsp;)</span>
                    </c:if>
                </p>
            </div>
        </c:forEach>
    </div>
</div>
