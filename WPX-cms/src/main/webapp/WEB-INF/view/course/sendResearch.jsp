<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
            aria-hidden="true">&times;</span></button>
    <h4 class="modal-title" id="myModalLabel">
        选择调研测试
    </h4>
</div>
<div class="modal-body">
    <form action="<%=basePath%>/platform/research/addSurveyJsp" class="form-li">
        <input name="courseid" value="${courseid}" class="hidden">
        <button type="submit" class="btn btn-default">
            <i class="glyphicon glyphicon-plus"></i> 新增调研测试
        </button>
    </form>
    <form action="<%=basePath%>/platform/research" class="form-li">
        <input name="courseid" value="${courseid}" class="hidden">
        <button type="submit" class="btn btn-info">
            <i class="glyphicon glyphicon-list-alt"></i> 已发送的调研测试
        </button>
    </form>
    <hr>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>选择</th>
            <th>描述</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${researchs}" var="research" varStatus="s">
            <tr>
                <td class="center"><input type="radio" name="research" value="${research.id}"></td>
                <td>
                        ${research.rdesc}
                </td>
                    <%--<td>--%>
                    <%--<c:if test="${research.rdesc=='research'}">--%>
                    <%----%>
                    <%--</c:if>--%>
                    <%--<c:if test="${research.rdesc=='research'}">--%>
                    <%--${research.rdesc}--%>
                    <%--</c:if>--%>
                    <%--</td>--%>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${empty researchs}">
        <div class="no-content">无内容</div>
    </c:if>
</div>
<div class="modal-footer">
    <c:if test="${prepage>0}">
        <button onclick=""
                id="btn-pre" class="btn btn-mini">上一页
        </button>
    </c:if>
    <button onclick=""
            id="btn-next" class="btn btn-mini btn-inverse">下一页
    </button>
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    <button type="button" data-loading-text="发送中..." class="btn btn-primary" autocomplete="off" id="btnSendResearch">发送
    </button>
</div>
<script>
    $("#btnSendResearch").click(function () {
        var researchid = $("input:radio[name=research]:checked").val();
        var courseid = "${courseid}";
        var $btn = $(this).button('loading');
        $.ajax({
            url: "<%=basePath%>/platform/course/sendResearch",
            data: {
                courseid: courseid,
                researchid: researchid
            },
            type: "get",
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
</script>