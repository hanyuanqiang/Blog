<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/30
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
    function checkForm(){
        var type = $("#type").val();
        var title = $("#title").val();
        var content = editor.$txt.formatText();
        if(type&&title&&content){
            $("#textareatag").val(editor.$txt.html());
            return true;
        }else{
            layer.alert('信息填写不完整', {
                skin: 'layui-layer-molv' //样式类名
                ,closeBtn: 0
                ,icon:5
            });
            return false;
        }
    }
</script>

<div style="padding:20px 30px;">
    <h3 align="center" style="margin-bottom: 30px;">撰写文章</h3>
    <form action="${pageContext.request.contextPath}/article_backsave.action" method="post" onsubmit="return checkForm()"  class="form-inline">
        <input type="hidden" name="article.id" value="${article.id}">
        <input type="hidden" name="article.createTime" value="${article.createTime}">
        <input type="hidden" name="article.pageViews" value="${article.pageViews}">
        <div>
            <strong style="font-family: 方正舒体;font-size: 20px;color: #0D3349">标题:</strong>
            <input type="text" name="article.title" style="width: 450px;" id="title" value="${article.title}">
        </div>
        <p style="height: 5px;"></p>
        <div>
            <label style="font-family: 方正舒体;font-size: 20px;color: #0D3349;">正文:</label>
            <div>
                <div id="content" style="height:650px;" id="content">${article.content}</div>

                <!--这里引用jquery和wangEditor.js-->
                <script type="text/javascript">
                    var editor = new wangEditor('content');
                    editor.config.uploadImgUrl = '${pageContext.request.contextPath}/upload.action';

                    editor.config.codeDefaultLang = 'java';


                    // 设置 headers（举例）
                    editor.config.uploadHeaders = {
                        'Accept' : 'text/x-json'
                    };

                    // 隐藏掉插入网络图片功能。该配置，只有在你正确配置了图片上传功能之后才可用。
                    //editor.config.hideLinkImg = true;
                    editor.config.uploadImgFileName = 'file';

                    editor.config.emotionsShow = 'icon';
                    editor.config.emotions = {
                        'default': {
                            title: '默认',
                            data: '${pageContext.request.contextPath}/wangEditor/test/emotions2.data'
                        },
                        'weibo':{
                            title: '微博',
                            data: '${pageContext.request.contextPath}/wangEditor/test/emotions.data'
                        },
                        'tieba':{
                            title: '贴吧',
                            data: '${pageContext.request.contextPath}/wangEditor/test/emotions3.data'
                        }
                    };
                    editor.create();
                </script>
            </div>
            <textarea id="textareatag" name="article.content" hidden></textarea>
        </div>
        <div class="form-group" style="margin-top: 20px;">
            <strong style="font-family: 方正舒体;font-size: 20px;color: #0D3349">选择分类:</strong>
            <select class="form-control" name="article.type.id" style="width: 200px;height: auto;" id="type">
                <option value="">选择分类...</option>
                <c:forEach items="${typeList}" var="type">
                    <option value="${type.id}" ${type.id==article.type.id?'selected':''}>${type.typeName}</option>
                </c:forEach>
            </select>
            <strong style="font-family: 方正舒体;font-size: 20px;color: #0D3349;margin-left: 30px;">来源:</strong>
            <select class="form-control" name="article.source" style="width: 100px;height: auto;">
                <option value="原创" ${article.source=='原创'?'selected':''}>原创</option>
                <option value="转载" ${article.source=='转载'?'selected':''}>转载</option>
            </select>
            <strong style="font-family: 方正舒体;font-size: 20px;color: #0D3349;margin-left: 30px;">访问权限:</strong>
            <select class="form-control" name="article.visitAuth" style="width: 150px;height: auto;">
                <option value="0"  ${article.visitAuth==0?'selected':''}>所有人可见</option>
                <option value="1"  ${article.visitAuth==1?'selected':''}>仅自己可见</option>
            </select>
        </div>
        <div style="margin-top: 20px;">
            <strong style="font-family: 方正舒体;font-size: 20px;color: #0D3349;vertical-align: top">关键字:</strong>
            <textarea cols="50" rows="5" name="article.keywords">${article.keywords}</textarea>
        </div>
        <p align="right" style="margin-top: 50px;">
            <span style="color: red;margin-right: 20px;" id="errorMsg"></span>
            <input type="submit" value="发布" class="btn btn-info">
        </p>
    </form>
</div>
