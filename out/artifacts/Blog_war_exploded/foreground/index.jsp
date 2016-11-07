<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/29
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>菜鸟韩的博客</title>
    <link rel="shortcut icon"type="image/x-icon" href="${pageContext.request.contextPath}/images/1111.ico"media="screen" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/foreground.css" />
    <link href="${pageContext.request.contextPath}/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/dist/css/bootstrap-theme.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/calendar/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/calendar/js/jquery-ui-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/wangEditor/dist/css/wangEditor.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/wangEditor/static/highlightjs/atelier-heath.dark.css">

    <script>
        function searchEvent(){
            var searchContent = $("#articleContent").val();var typeId = $("#typeId").val();
            window.location.href = "${pageContext.request.contextPath}/article_foreList.action?s_article.content="+searchContent;
        }

        function getTime(){
            var d = new Date();
            var my_hours=d.getHours();
            var my_minutes=d.getMinutes();
            var my_seconds=d.getSeconds();
            if(my_hours<10){
                my_hours="0"+my_hours
            }
            if(my_minutes<10){
                my_minutes="0"+my_minutes
            }
            if(my_seconds<10){
                my_seconds="0"+my_seconds
            }
            return my_hours+"&nbsp;:&nbsp;"+my_minutes+"&nbsp;:&nbsp;"+my_seconds;
        }

        function setTime(){
            $("#time").html(getTime());
        }

        window.setInterval(setTime,1000);
    </script>

    <script type="text/javascript">
        $(function () {
            var speed = 1200;//滚动速度
            var h=$(document).height();     //获取文档总高度
            //回到顶部
            $("#toTop").click(function () {
                $('html,body').animate({
                            scrollTop: '0px'
                        },
                        speed);
            });
            //回到底部

            $("#toBottom").click(function () {
                $('html,body').animate({
                            scrollTop: h+'px'
                        },
                        speed);
            });

            //监听鼠标滚动事件，显示"回到顶部"和"回到底部"
            $(window).scroll(function() {
                $("#scroll").removeAttr("hidden");
                clearTimeout(timeout);  //多次滚动时清除上次的setTimeout函数
                timeout = setTimeout(hiddenScroll,2000);    //每次滚动完成3秒后"回到顶部"和"回到底部"隐藏
            });


        });
        var timeout;
        //隐藏"回到顶部"和"回到底部"
        function hiddenScroll(){
            $("#scroll").attr("hidden",true);
        }

    </script>

    <style type="text/css">
        #scroll {
            position:fixed; top:400px; right:60px;
        }
        .scrollItem {
            padding: 0px;
            color: #00CCFF;
            width:20px;
            height:84px;
            border:#e1e1e1 1px solid;
            cursor:pointer;
            text-align:center;
        }
    </style>
</head>
<body>
<div id="scroll" hidden>
    <div class="scrollItem" id="toTop">回到顶部</div>
    <p style="height: 5px;margin: 0px;"></p>
    <div class="scrollItem" id="toBottom">回到底部</div>
</div>

<div class="index">
    <div class="head">
        <span id="time" style="float: right;margin: 5px 10px;font-size: 17px;"></span>
        <p class="p1">
            ${wisdom.content}
        </p>
        <div class="d1">
            <div class="dd1">
                <input type="text" class="s_field" name="s_article.content" id="articleContent" value="${s_article.content}" onkeydown="if(event.keyCode==13) searchEvent()">
                <input type="button" class="s_btn" value="搜索" onclick="searchEvent()">
            </div>
            <div class="dd2">
                <ul id="menu">
                    <li><a href="${pageContext.request.contextPath}/">首页</a></li>
                    <li><a href="${pageContext.request.contextPath}/article_foreList.action">博文目录</a></li>
                    <li><a href="${pageContext.request.contextPath}/user_about_me.action">关于我</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="center">

        <div class="left">
            <div>
                <jsp:include page="${leftPage}"></jsp:include>
            </div>
        </div>
        <div class="right">
            <div style="padding: 20px 7px;">
                <jsp:include page="/foreground/personInfoShow.jsp"></jsp:include>
            </div>
        </div>
    </div>
    <div class="footer">
        <p>Copyright &copy; 2016 ${admin.nickName}写字的地方</p>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-2.2.1.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/dist/js/bootstrap.js"></script>
</body>
</html>
