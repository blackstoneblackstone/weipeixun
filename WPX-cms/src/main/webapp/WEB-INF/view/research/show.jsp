<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!--header-->
<jsp:include page="../header.jsp"></jsp:include>
<!--header-->

<!--content-->
<div class="content">
    <button class="btn btn-default" onclick="window.location.href='<%=basePath%>/platform/research/addSurveyJsp'">
        <i class="glyphicon glyphicon-plus"></i> 新增调研测试
    </button>
    <%--<button type="button" class="btn btn-primary" href="<%=basePath%>/platform/research/addTestJsp"--%>
    <%--data-toggle="modal" data-target="#Modal">--%>
    <%--<i class="glyphicon glyphicon-plus"></i> 新增测试--%>
    <%--</button>--%>
    <hr>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>序号</th>
            <th>描述</th>
            <%--<th>类型</th>--%>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${researchs}" var="research" varStatus="s">
            <tr>
                <td>#${ s.index + 1}</td>
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
                <td>
                        <%--<button class="btn btn-xs btn-primary"--%>
                        <%--href="<%=basePath%>/platform/project/editJsp?id=${project.id}"--%>
                        <%--data-toggle="modal" data-target="#Modal${project.id}">编辑--%>
                        <%--</button>--%>
                        <%--<div class="modal fade" id="Modal${project.id}" tabindex="-1" role="dialog"--%>
                        <%--aria-labelledby="myModalLabel">--%>
                        <%--<div class="modal-dialog" role="document">--%>
                        <%--<div class="modal-content">--%>
                        <%--<div class="loading"><img src="<%=basePath%>/img/loading.gif"></div>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                    <c:if test="${research.isuse==0}">
                        <button data-id="${research.id}" data-loading-text="删除中..."
                                class="delete btn btn-xs btn-danger">删除
                        </button>
                    </c:if>
                    <c:if test="${research.isuse==1}">
                    <span class="label label-info tool" title="使用中不可删除" data-toggle="tooltip" data-placement="right" >使用中
                    </span>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${empty researchs}">
        <div class="no-content">无内容</div>
    </c:if>
    <div class="page">
        <div class="right">
            <c:if test="${prepage>0}">
                <button onclick="javascript:window.location.href='<%=basePath%>/platform/research?courseid=${courseid}&startPage=${prepage}'"
                        id="btn-pre" class="btn btn-mini">上一页
                </button>
            </c:if>
            <c:if test="${!empty researchs}">
                <button onclick="javascript:window.location.href='<%=basePath%>/platform/research?courseid=${courseid}&startPage=${nextpage}'"
                        id="btn-next" class="btn btn-mini btn-inverse">下一页
                </button>
            </c:if>
        </div>
    </div>
    <div class="clear"></div>
</div>
<script>
    $(function () {
        $("#menu li:eq(4)").addClass("active");
        $(".delete").click(function () {
            var deleteBtn = $(this);
            var $btn = deleteBtn.button('loading');
            $.ajax({
                url: "<%=basePath%>/platform/research/delete?id=" + $(this).data("id"),
                success: function (data) {
                    location.reload();
                }
            });

        });
    });
</script>

<!--content-->
<!-- Modal -->

<!--bottom-->
<jsp:include page="../bottom.jsp"></jsp:include>
<!--bottom-->
