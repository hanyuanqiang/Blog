<%@ page import="com.hyq.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/28
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人博客后台登录</title>
    <link rel="shortcut icon"type="image/x-icon" href="${pageContext.request.contextPath}/images/1111.ico"media="screen" />
    <script src="${pageContext.request.contextPath}/js/jquery-2.2.1.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <style type="text/css">
        body{
            font:14px/28px "微软雅黑";
        }
        .contact *:focus{outline :none;}
        .contact{
            width: 700px;
            height: auto;
            background: #f6f6f6;
            margin: 0px auto;
            padding: 10px;
            //border: 1px red solid;
        }
        .contact ul {
            width: 650px;
            margin: 0 auto;
        }
        .contact ul li{
            border-bottom: 1px solid #dfdfdf;
            list-style: none;
            padding: 12px;
        }
        .contact ul li label {
            width: 120px;
            display: inline-block;
            float: left;
        }
        .contact ul li input[type=text],.contact ul li input[type=password]{
            width: 220px;
            height: 25px;
            border :1px solid #aaa;
            padding: 3px 8px;
            border-radius: 5px;
        }
        .contact ul li input:focus{
            border-color: #c00;

        }
        .contact ul li input[type=text]{
            transition: padding .25s;
            -o-transition: padding  .25s;
            -moz-transition: padding  .25s;
            -webkit-transition: padding  .25s;
        }
        .contact ul li input[type=password]{
            transition: padding  .25s;
            -o-transition: padding  .25s;
            -moz-transition: padding  .25s;
            -webkit-transition: padding  .25s;
        }
        .contact ul li input:focus{
            padding-right: 70px;
        }
        .btn{
            position: relative;
            left: 180px;
        }
        .tips{
            color: rgba(0, 0, 0, 0.5);
            padding-left: 10px;
        }
        .tips_true,.tips_false{
            padding-left: 10px;
        }
        .tips_true{
            color: green;
        }
        .tips_false{
            color: red;
        }

        #myTitle{
            margin-top: 80px;
            margin-bottom:20px;
            font-size: 40px;
            font-family: 华文隶书;
            color: #044942;
        //border: 1px red solid;
            height: 30px;
        }
    </style>
    <script type="text/javascript">
        var nametag = false;
        var passtag = false;
        var codetag = false;

        //检查输入的用户名
        function checkna(){
            var userName = $("#userName").val();
            if( userName.length <1 || userName.length >12)
            {
                $("#userNameError").html('<font class="tips_false">长度是1~12个字符</font>');
                nametag=false;
                $("#userName").focus();
            }else{
                nametag=true;
                $("#userNameError").html('<font class="tips_true">输入正确</font>');
            }
        }

        //验证密码
        function checkpsd1(){
            var password = $("#password").val();
            var flagZM=false ;
            var flagSZ=false ;
            var flagQT=false ;
            if(password.length<6 || password.length>12){
                passwordError.innerHTML='<font class="tips_false">长度错误</font>';
                passtag = false;
                $("#password").focus();
                return;
            }else {
                for(i=0;i < password.length;i++)
                {
                    if((password.charAt(i) >= 'A' && password.charAt(i)<='Z') || (password.charAt(i)>='a' && password.charAt(i)<='z'))
                    {
                        flagZM=true;
                    }
                    else if(password.charAt(i)>='0' && password.charAt(i)<='9')
                    {
                        flagSZ=true;
                    }else
                    {
                        flagQT=true;
                    }
                }
                if(!flagZM||!flagSZ||flagQT){
                    passwordError.innerHTML='<font class="tips_false">密码必须是字母数字的组合</font>';
                    passtag = false;
                    $("#password").focus();
                    return;
                }else{
                    passwordError.innerHTML='<font class="tips_true">输入正确</font>';
                    passtag = true;
                }

            }
        }

        function checkImageCode(){
            var codeValue = form1.imageCode.value;
            if(!codeValue){
                divimageCode.innerHTML='<font class="tips_false">请输入验证码</font>';
                codetag = false;
                $("#imageCode").focus();
            }else if(codeValue.length!=4){
                divimageCode.innerHTML='<font class="tips_false">验证码长度为4</font>';
                codetag = false;
                $("#imageCode").focus();
            }else{
                divimageCode.innerHTML='<font class="tips_true">输入正确</font>';
                codetag = true;
            }
        }


        function checkForm(){
            if(!nametag){
                $("#userName").focus();
                return false;
            }else if(!passtag) {
                $("#password").focus();
                return false;
            }else if(!codetag){
                $("#imageCode").focus();
                return false;
            }else{
                return true;
            }
        }
        function loadimage(){
            document.getElementById("randImage").src = "${pageContext.request.contextPath}/background/image.jsp?"+Math.random();//加个参数是为了让浏览器刷新缓存
        }
        function findPass(){

            $.post("${pageContext.request.contextPath}/user_findPassword",{}, function (result) {
                if(result.success){
                    layer.alert('账号密码已发送到你绑定的手机邮箱中，请查收!', {
                        skin: 'layui-layer-molv' //样式类名
                        ,closeBtn: 0
                    });
                }else{
                    layer.alert('未知异常导致请求失败', {
                        skin: 'layui-layer-molv' //样式类名
                        ,closeBtn: 0
                    });
                }
            },"json");
        }
    </script>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie:cookies) {
        if("blogLoginInfo".equals(cookie.getName())){
            String[] strings = cookie.getValue().split("-");
            User user = new User();
            user.setUserName(strings[0]);
            user.setPassword(strings[1]);
            pageContext.setAttribute("user",user);
        }
    }
%>

<p id="myTitle" align="center"></p>
<div class="contact" >
    <form name="form1" method="post" onsubmit="return checkForm()" action="${pageContext.request.contextPath}/user_login.action">
        <ul>
            <li>
                <label>姓名：</label>
                <input type="text" value="${user.userName}" name="user.userName" id="userName" placeholder="请输入用户名"  onblur="checkna()"/><span class="tips" id="userNameError">长度1~12个字符</span>
            </li>
            <li>
                <label>密码：</label>
                <input type="password" value="${user.password}" name="user.password" id="password" placeholder="请输入你的密码" onblur="checkpsd1()"/><span class="tips" id="passwordError">密码必须由字母和数字组成</span>
            </li>
            <li>
                <label>验证码：</label>
                <input type="text" value="${imageCode}" name="imageCode" id="imageCode" onblur="checkImageCode()" placeholder="输入验证码" style="width: 150px;" onkeydown="if(event.keyCode==13) $('form').submit()"/>
                &nbsp;&nbsp;
                <img onclick="javascript:loadimage();" title="换一张试试" name="randImage"
                        id="randImage" src="${pageContext.request.contextPath}/background/image.jsp" width="60" height="20" border="1"
                        align="absmiddle">
                <span class="tips" id="divimageCode"></span>
            </li>
        </ul>
        <p></p>
        <b class="btn"><input type="submit" value="登录"/>
            <input type="reset" value="取消" style="margin-left: 30px;"/>
            <input type="button" value="找回密码" style="margin-left: 30px;"  onclick="findPass()"/>
        </b>
        <p style="padding-left: 200px;">
            <span style="color: red;">${loginError}</span>
        </p>
    </form>
</div>

<div id="show"></div>
<script type="text/javascript">
    var str="个人博客后台管理";
    var len=str.length;
    var i=1;
    function showword(){
        document.getElementById("myTitle").innerHTML=str.substr(0,i);
        i++;
        if(i>len){
            clearInterval("done");
        }
    }
    var done=setInterval("showword()",300);
</script>
</body>
</html>
