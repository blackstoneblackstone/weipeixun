<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!--header-->
<jsp:include page="../header.jsp"></jsp:include>
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap-3.3.5-dist/css/bootstrap-switch.min.css"/>
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap-3.3.5-dist/css/green.css"/>
<!--header-->
<!--content-->
<div class="content">
    <!--Action boxes-->
    <button type="button" class="btn btn-default" href="<%=basePath%>/platform/portal/addJsp?type=1"
            data-toggle="modal" data-target="#Modal">
        <i class="glyphicon glyphicon-plus"></i> 新增学院门户
    </button>

    <hr>
    <table class="table table-bordered data-table">
        <thead>
        <tr>
            <th>序号</th>
            <th>名称</th>
            <th>模块</th>
            <th>元素</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${portals}" var="portal" varStatus="s">
            <tr>
                <td>#${ s.index + 1}</td>
                <td>
                        ${portal.wdesc}
                </td>
                <td style="width: 500px">
                        ${portal.model}
                </td>
                <td>
                    <a type="button" class="btn btn-xs btn-info"
                       href="<%=basePath%>/platform/portal/addDescriptionJsp?id=${portal.id}">
                        学院简介
                    </a>
                    <button type="button" class="btn btn-xs btn-warning"
                            href="<%=basePath%>/platform/portal/addNotifyJsp?id=${portal.id}"
                            data-toggle="modal" data-target="#Modal">
                        学院公告
                    </button>
                </td>
                <td>
                    <button data-id="${portal.id}" data-loading-text="删除中..." class="delete btn btn-xs btn-danger">
                        删除
                    </button>
                    <a target="_blank" href="http://www.wexue.top/school/index?corpId=${portal.corpid}"
                       class="btn btn-xs btn-success">预览</a>
                    <c:if test="${portal.isopen==1}">
                    <input class="iswitch" data-id="${portal.id}" name="openname" data-size="mini" data-on-text="启用"
                           data-on-color="success"
                           data-off-color="danger" data-off-text="禁用" checked type="radio">
                    </c:if>
                    <c:if test="${portal.isopen==0}">
                        <input class="iswitch" data-id="${portal.id}" name="openname" data-size="mini" data-on-text="启用"
                               data-on-color="success"
                               data-off-color="danger" data-off-text="禁用" type="radio">
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${empty portals}">
        <div class="no-content">无内容</div>
    </c:if>
    <div class="clear"></div>
</div>
<!--content-->
<script src="<%=basePath%>/lib/bootstrap-3.3.5-dist/js/icheck.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap-3.3.5-dist/js/bootstrap-switch.min.js"></script>
<script src="<%=basePath%>/js/bootstrap-wysiwyg.js"></script>
<script>
    $(function () {
        $("#menu li:eq(0)").addClass("active");
        $('.icheck').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
            increaseArea: '20%' // optional
        });
        $(".delete").click(function () {
            var deleteBtn = $(this);
            var $btn = deleteBtn.button('loading');
            $.ajax({
                url: "<%=basePath%>/platform/portal/delete?id=" + $(this).data("id"),
                success: function (data) {
                    if (data.success) {
                        showTip(data.msg, "success");
                        setTimeout(function () {
                            location.reload();
                        }, 1000);
                    } else {
                        showTip(data.msg, "failure");
                    }
                    $btn.button('reset');
                }
            });
        });

        $('.iswitch').bootstrapSwitch();
        $('.iswitch').on('switchChange.bootstrapSwitch', function(event, state) {
            if(state){
                $.ajax({
                    url: "<%=basePath%>/platform/portal/open?id=" + $(this).data("id"),
                    success: function (data) {
                        if (data.success) {
                            showTip(data.msg, "success");
                        } else {
                            showTip(data.msg, "failure");
                        }
                    }
                });
            }else{
                $.ajax({
                    url: "<%=basePath%>/platform/portal/noOpen?id=" + $(this).data("id"),
                    success: function (data) {
                        if (data.success) {
                            showTip(data.msg, "success");
                        } else {
                            showTip(data.msg, "failure");
                        }
                    }
            });
        }});
    });
</script>

<!--bottom-->
<jsp:include page="../bottom.jsp"></jsp:include>
<!--bottom-->
