<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>

<form action="<%=basePath%>/platform/portal/add" method="GET" id="portalForm" class="form-horizontal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <h4 class="model-title" id="myModalLabel">新增模板</h4>
    </div>
    <div class="modal-body">
        <div class="form-group">
            <label class="col-sm-3 control-label">模板描述 :</label>

            <div class="col-sm-8">
                <input type="text" name="wdesc" required class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">模块 :</label>

            <div class="col-sm-8">
                <label>
                    <input class="icheck" type="checkbox" name="model" value="xyjj">
                    学院简介
                </label>
                <label>
                    <input class="icheck" type="checkbox" name="model" value="xygg">
                    学院公告
                </label>
                <label>
                    <input class="icheck" type="checkbox" name="model" value="gkk">
                    公开课
                </label>
                <label>
                    <input class="icheck" type="checkbox" name="model" value="bxk">
                    必修课
                </label>
                <label>
                    <input class="icheck" type="checkbox" name="model" value="jsk">
                    讲师库
                </label>
                <label>
                    <input class="icheck" type="checkbox" name="model" value="kcrj">
                    课程日历
                </label>

            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">模版样式 :</label>

            <div class="col-sm-4">
                <div class="panel panel-info">
                    <div class="panel-heading">方形图标模板<input class="icheck" type="radio" name="modelstyle" value="tpl1" checked></div>
                    <div class="panel-body">
                        <img src="<%=basePath%>/img/template/tpl1.png" class="img-responsive img-thumbnail" checked alt="暂时无图">
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="panel panel-success">
                    <div class="panel-heading">原型图标模板<input class="icheck" type="radio" value="tpl2" name="modelstyle"></div>
                    <div class="panel-body">
                        <img src="<%=basePath%>/img/template/tpl1.png" class="img-responsive img-thumbnail" alt="暂时无图">
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="saveSubmit" type="button" class="btn btn-primary">保存</button>
    </div>
</form>
<script>
    $(function () {
        $("#saveSubmit").click(function () {
            var $btn = $(this).button('loading');
            $("#portalForm").ajaxSubmit({
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
        $('.icheck').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green right',
            increaseArea: '20%' // optional
        });
    });

</script>