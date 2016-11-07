<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/1
  Time: 0:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div  style="padding:20px 30px;">
    <form class="form-horizontal" role="form" enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/user_backsave.action">
        <input type="hidden" value="${currentUser.id}" name="user.id">
        <input type="hidden" value="${currentUser.avatar}" name="user.avatar">
        <input type="hidden" value="${currentUser.password}" name="user.password">
        <div class="form-group">
            <div class="col-sm-12">
                <div align="right">
                    <img src="${currentUser.avatar}" width="50" height="50">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="userName" class="col-sm-2 control-label">登录账号</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="userName" value="${currentUser.userName}" name="user.userName" readonly>
            </div>
        </div>
        <div class="form-group">
            <label for="totalOnlineTime" class="col-sm-2 control-label">累计在线时长</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="totalOnlineTime" value="${currentUser.totalOnlineTime}" name="user.totalOnlineTime" readonly>
            </div>
        </div>
        <div class="form-group">
            <label for="joinDate" class="col-sm-2 control-label">建站时间</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="joinDate" value="${currentUser.joinDate}" name="user.joinDate" readonly>
            </div>
        </div>
        <div class="form-group">
            <label for="nickName" class="col-sm-2 control-label">昵称</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="nickName" value="${currentUser.nickName}" name="user.nickName">
            </div>
        </div>
        <div class="form-group">
            <label for="signature" class="col-sm-2 control-label">个性签名</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="signature" value="${currentUser.signature}" name="user.signature">
            </div>
        </div>
        <div class="form-group">
            <label for="city" class="col-sm-2 control-label">城市</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="city" value="${currentUser.city}" name="user.city">
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">邮箱</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="email" value="${currentUser.email}" name="user.email">
            </div>
        </div>
        <div class="form-group">
            <label for="introduce" class="col-sm-2 control-label">个人简介</label>
            <div class="col-sm-5">
                <textarea class="form-control" id="introduce" cols="50" rows="5"  name="user.introduce">${currentUser.introduce}</textarea>
                <%--<input type="text" class="form-control" id="introduce" value="${currentUser.introduce}" name="user.introduce">--%>
            </div>
        </div>
        <div class="form-group">
            <label for="file" class="col-sm-2 control-label">修改头像</label>
            <div class="col-sm-3">
                <input type="file" class="form-control" id="file" name="file">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">保存</button>
            </div>
        </div>
    </form>
</div>

