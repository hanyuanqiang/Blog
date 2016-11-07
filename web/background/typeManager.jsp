<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/30
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
    function updateType(id,typeName,typeDesc){
        $("#typeName").val(typeName);
        $("#typeDesc").val(typeDesc);
        $("#typeId").val(id);
        $('#myModal').modal('show');        //打开模态框
    }

    function closeModal(){
        $("#typeName").val('');
        $("#typeDesc").val('');
        $("#typeId").val('');
    }

    function submitForm(){
        if($("#typeName").val()&&$("#typeDesc").val()){
            $("form").submit();
        }
    }
</script>

<div>
    <p align="center">
        <button style="float: right;margin-right: 30px;margin-top: 10px;" class="btn btn-success btn-xs"
                data-toggle="modal" data-target="#myModal">新增分类</button>
    <h3 align="center">博客分类管理</h3>
    </p>
    <div style="border-top:1px dashed #cccccc;height: 1px;overflow:hidden;margin: 5px 0px;"></div>
    <div style="margin-left: 30px;">
        <ul class="list-unstyled">
            <c:forEach items="${typeList}" var="type">
                <li style="margin-top: 20px;">
                    <a href="#" title="${type.typeDesc}">${type.typeName}</a>
                    <button class="btn btn-info btn-xs" style="margin-left: 30px;" onclick="updateType('${type.id}','${type.typeName}','${type.typeDesc}')">
                        修改
                    </button>&nbsp;&nbsp;
                    <a class="btn btn-danger btn-xs" href="${pageContext.request.contextPath}/type_backdelete.action?typeId=${type.id}" onclick="return confirm('确定删除这条分类？')">
                        删除
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">博客分类管理</h4>
            </div>
            <div class="modal-body">
                <div style="margin-left: 20px;">
                    <form action="${pageContext.request.contextPath}/type_backsave.action">
                        <input type="hidden" name="type.id" id="typeId">
                        <input type="text" name="type.typeName" id="typeName"  placeholder="分类名称">
                        <p></p>
                        <textarea  id="typeDesc" rows="7" placeholder="分类描述...." style="width: 350px;"
                           name="type.typeDesc"></textarea>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModal()">关闭</button>
                <button type="button" class="btn btn-primary" onclick="submitForm()">保存</button>
            </div>
        </div>
    </div>
</div>