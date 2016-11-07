<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/3
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
    function checkForm(){
        if ($("#wisdomContent").val()){
            return true;
        }else{
            $("#inputError").text("请输入内容.");
            return false;
        }
    }
</script>
<div>
    <div style="padding: 20px 20px;">
        <h3 align="center">名言管理</h3>
        <div id="writeWisdom" style="margin-bottom: 30px;">
            <div>
                <form action="${pageContext.request.contextPath}/wisdom_backsave.action" method="post" onsubmit="return checkForm()">
                    <p style="margin-bottom: 2px;">输入名言：</p>
                    <textarea id="wisdomContent" style="width: 430px;height: 120px;background-color: transparent;border: solid 2px #73737a;margin-bottom: -7px;" name="wisdom.content"></textarea>
                    <input type="submit" class="btn-primary" value="提交" style="margin-left: 10px;">
                    <span id="inputError" style="margin-left: 10px;color: red"></span>
                </form>
            </div>
        </div>
        <c:forEach items="${wisdomList}" var="wisdom" varStatus="status">
            <p>
                ${status.count}、${wisdom.content}

                    <c:choose>
                        <c:when test="${wisdom.status==0}">
                            <a class="btn btn-info btn-xs" style="margin-left: 30px;" href="${pageContext.request.contextPath}/wisdom_backChangeStatus.action?wisdomId=${wisdom.id}">
                                首页显示
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-success btn-xs" style="margin-left: 30px;" disabled="disabled">
                                正在使用
                            </a>
                        </c:otherwise>
                    </c:choose>

                <a class="btn btn-danger btn-xs" style="margin-left: 12px;" href="${pageContext.request.contextPath}/wisdom_backdelete.action?wisdomId=${wisdom.id}" onclick="return confirm('确定删除这条名言吗？')">
                    删除
                </a>
            </p>
        </c:forEach>
    </div>
</div>

