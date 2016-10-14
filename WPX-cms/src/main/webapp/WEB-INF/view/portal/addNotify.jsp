<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>

<form action="<%=basePath%>/platform/portal/updateSchoolNotify" method="post" id="notifyForm" class="form-horizontal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <h4 class="model-title" id="myModalLabel">新增公告</h4>
    </div>
    <div class="modal-body">
        <div class="form-group">
            <label class="col-sm-3 control-label">公告:</label>

            <div class="col-sm-8">
                <input name="id" value="${portal.id}" class="hidden">
                <textarea type="text" name="schoolnotify" required
                          class="form-control">${portal.schoolnotify}</textarea>
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="saveSubmit" type="button" data-loading-text="保存中..." class="btn btn-primary">保存</button>
    </div>
</form>
<script>
    $(function () {
        $("#saveSubmit").click(function () {
            var $btn = $(this).button('loading');
            $("#notifyForm").ajaxSubmit({
                success: function (data) {
                    if (data.success) {
                        showTip(data.msg, "success");
                    } else {
                        showTip(data.msg, "failure");
                    }
                    $btn.button('reset');
                    $("#Modal").modal("hide");
                }
            });
        });
    });

</script>