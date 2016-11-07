<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/2
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<script src="${pageContext.request.contextPath}/layer/layer.js"></script>--%>
<script>
    function openDialog(){
        $('#wwwww').modal('show');
    }

    function submitForm(){
        var leaveWordsContent = $("#leaveWordContent").val();
        var tourEmail = $("#tourEmail").val();
        if(leaveWordsContent){
            $("#leaveWordContent").val("");
            $('#wwwww').modal('hide');
            $("#errorMsg").text("");
            $.post("${pageContext.request.contextPath}/leaveWords_save.action",{"leaveWords.content":leaveWordsContent,"leaveWords.tourEmail":tourEmail},function(result){
                if(result.success){
                    layer.alert('感谢你的留言', {
                        title:'系统提示',
                        icon: 1,
                        skin: 'layui-layer-molv'
                    });

                }else{
                    layer.alert('位置异常导致留言失败', {
                        title:'系统提示',
                        icon: 5,
                        skin: 'layui-layer-molv'
                    });
                }
            },"json");
        }else {
            $("#errorMsg").text("请输入留言内容！");
            $("#leaveWordContent").focus();
        }
    }
</script>

<style>

</style>
<div style="padding-top: 10px;padding-right: 10px;">
    <p style="float: right">
        <a style="text-decoration: none;cursor: pointer;" onclick="openDialog()">
            给我留言
        </a>
    </p>
</div>
<div style="padding: 40px 20px;">
    <div class="form-horizontal">
        <p style="font-size: 40px;font-family: 方正舒体;margin-left: 40px;margin-bottom: 20px;">${admin.nickName}</p>
        <div class="form-group">
            <p class="col-sm-2 control-p leftlabel">
                <span>个性签名:</span>
            </p>
            <p class="col-sm-10">
                <span>${admin.signature}</span>
            </p>
        </div>
        <div class="form-group">
            <p class="col-sm-2 control-p leftlabel">
                <span>Email:</span>
            </p>
            <p class="col-sm-10">
                <span>${admin.email}</span>
            </p>
        </div>
        <div class="form-group">
            <p class="col-sm-2 control-p leftlabel">
                <span>城市:</span>
            </p>
            <p class="col-sm-10">
                <span>${admin.city}</span>
            </p>
        </div>
        <div class="form-group">
            <p class="col-sm-2 control-p leftlabel">
                <span>个人介绍:</span>
            </p>
            <p class="col-sm-8">
                <span style="padding-right: 20px;">${admin.introduce}</span>
            </p>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="wwwww" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="leaveWords">留言</h4>
            </div>
            <div class="modal-body">
                <div style="margin-left: 20px;">
                    <textarea  rows="7" placeholder="输入留言..." style="width: 350px;" id="leaveWordContent"    name="leaveWords.content"></textarea>
                </div>
                <div style="margin-left: 20px;margin-top: 20px;">
                    <span>联系方式(选填)</span>
                    <input type="text" id="tourEmail" placeholder="如需回复请填写联系方式..." style="width: 250px;">
                </div>
            </div>
            <div class="modal-footer">
                <span id="errorMsg" style="color: red;"></span>
                <button type="button" class="btn btn-primary" onclick="submitForm()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>