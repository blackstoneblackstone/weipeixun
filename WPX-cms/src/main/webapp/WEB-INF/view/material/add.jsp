<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<link href="<%=basePath%>/css/fileinput.min.css" rel="stylesheet">
<form id="materialForm" action="<%=basePath%>/platform/material/add" enctype="multipart/form-data" method="post"
      class="form-horizontal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">新增资料</h4>
    </div>
    <div class="modal-body">

        <div class="form-group">
            <label for="inputName" class="col-sm-2 control-label">名称 :</label>

            <div class="col-sm-10">
                <input class="form-control" type="text" name="mname" id="inputName" required
                       placeholder="起个响亮的名字。。。">
            </div>
        </div>
        <div class="form-group">
            <label for="inputName" class="col-sm-2 control-label">文件 :</label>

            <div class="col-sm-10">
                <input id="file-0a" class="file" name="file" type="file" multiple data-min-file-count="1"
                       data-show-upload="false">
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" id="saveSubmit" data-loading-text="文件上传中..." class="btn btn-primary">保存</button>
    </div>
</form>
<script src="<%=basePath%>/js/fileinput.min.js"></script>
<script src="<%=basePath%>/js/fileinput_locale_zh.js"></script>
<script>
    $("#saveSubmit").click(function () {
        var $btn = $(this).button('loading');
        $("#materialForm").ajaxSubmit({
            dataType:"",
            success: function (data) {
                if (data.success) {
                    showTip(data.msg, "success");
                } else {
                    showTip(data.msg, "failure");
                }
                $btn.button('reset');
                setTimeout(function () {
                    location.reload();
                }, 1000);
                $("#Modal").modal("hide");
            }
        });
    });
</script>