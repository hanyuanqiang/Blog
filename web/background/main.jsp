<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/29
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="shortcut icon"type="image/x-icon" href="${pageContext.request.contextPath}/images/1111.ico"media="screen" />
    <link href="${pageContext.request.contextPath}/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/dist/css/bootstrap-theme.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.2.1.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/dist/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/wangEditor/dist/css/wangEditor.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/wangEditor/static/highlightjs/atelier-heath.light.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/wangEditor/dist/js/wangEditor.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/wangEditor/static/highlightjs/highlight.pack.js"></script>

    <script src="${pageContext.request.contextPath}/minicolors/jquery.minicolors.js"></script>
    <link href="${pageContext.request.contextPath}/minicolors/jquery.minicolors.css" rel="stylesheet">

    <script language=javascript>
        requestBeckground();
        function requestBeckground(bgColor){
            $.post("${pageContext.request.contextPath}/sess_backlistener.action",{bgColor:bgColor},function(result){

            },"json");
        }
        window.setInterval(requestBeckground,1000*60*2);      //每个两分钟请求一次后台

        $(document).ready( function() {

            $('.demo').each( function() {
                $(this).minicolors({
                    control: $(this).attr('data-control') || 'hue',
                    defaultValue: $(this).attr('data-defaultValue') || '',
                    inline: $(this).attr('data-inline') === 'true',
                    letterCase: $(this).attr('data-letterCase') || 'lowercase',
                    opacity: $(this).attr('data-opacity'),
                    position: $(this).attr('data-position') || 'bottom left',
                    change: function(hex, opacity) {
                        if( !hex ) return;
                        if( opacity ) hex += ', ' + opacity;
                        try {
                            console.log(hex);
                        } catch(e) {}
                    },
                    theme: 'bootstrap'
                });
            });

            $("#changeColor").blur(function(){
                $("body").css("background-color",$(".minicolors-swatch-color").css("background-color"));
                requestBeckground($(".minicolors-swatch-color").css("background-color"));
            });

        });
    </script>

    <style type="text/css">
        .ddvid {
            width:100%;
            height:100%;
            overflow-y:scroll;
            overflow-x:auto;
        }
    </style>
</head>
<body style="background-color: #d6d6cf;">

    <c:if test="${bgColor!=null}">
        <script>
            $("body").css("background-color","${bgColor}");
            $(".minicolors-swatch-color").css("background-color","${bgColor}");
        </script>
    </c:if>





    <div class="ddvid">
        <div style="border: 1px #13262a solid;margin:30px auto;width: 1250px;">
            <div style="float: left;width: 200px;border: 1px rebeccapurple solid;padding: 20px 30px;position: absolute;">
                <ul class="list-unstyled">
                    <li style="margin-top: 20px;">
                        <a href="${pageContext.request.contextPath}/article_backlist.action">博客管理</a>
                    </li>
                    <li style="margin-top: 20px;">
                        <a href="${pageContext.request.contextPath}/article_backwrite.action">撰写博客</a>
                    </li>
                    <li style="margin-top: 20px;">
                        <a href="${pageContext.request.contextPath}/type_backlist.action">分类管理</a>
                    </li>
                    <li style="margin-top: 20px;">
                        <a href="${pageContext.request.contextPath}/user_backupdate.action">个人中心</a>
                    </li>
                    <li style="margin-top: 20px;">
                        <a href="${pageContext.request.contextPath}/wisdom_backlist.action">至理名言</a>
                    </li>
                    <li style="margin-top: 20px;">
                        <a href="${pageContext.request.contextPath}/leaveWords_backlist.action">
                            游客留言
                            <c:if test="${noReadLWCount>0}">
                                (${noReadLWCount})
                            </c:if>
                        </a>
                    </li>
                    <li style="margin-top: 20px;">
                        <a href="${pageContext.request.contextPath}/user_backexit.action"  onclick="return confirm('确定退出系统？')">退出系统</a>
                    </li>
                    <li style="margin-top: 20px;">
                        <a href="${pageContext.request.contextPath}/system_backrefresh.action">刷新服务器缓存</a>
                    </li>

                    <li style="margin-top: 20px;">
                        <a class="demo" href="#" value="#2922ff" id="changeColor">更改背景颜色</a>
                    </li>
                </ul>

            </div>

            <div style="border: 1px #2922ff solid;float:left;width: 1025px;margin-left: 220px;">
                <jsp:include page="${rightPage}"></jsp:include>
            </div>
        </div>
    </div>
</body>
</html>
