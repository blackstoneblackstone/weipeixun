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
<!--header-->

<!--content-->
<div id="content" class="content">
    <!--Action boxes-->
    <div class="container-fluid">
        <div>
            <button type="button" class="btn btn-default" href="<%=basePath%>/platform/material/addJsp"
                    data-toggle="modal" data-target="#Modal">
                <i class="glyphicon glyphicon-plus"></i> 新增资料
            </button>
            <hr>
        </div>
        <!--End-Action boxes-->

        <div class="widget-box">
            <div class="widget-title"><span class="icon"><i class="icon-th"></i></span>
                <%--<h5>项目</h5>--%>
            </div>
            <div class="widget-content nopadding">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>标题</th>
                        <th>路径</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${materials}" var="material" varStatus="s">
                        <tr>
                            <td>#${ s.index + 1}</td>
                            <td>${material.mname}</td>
                            <td>${material.path}</td>
                            <td>
                                <button data-id="${material.id}" data-loading-text="删除中..."
                                        class="delete btn btn-xs btn-danger">删除
                                </button>
                                <a href="http://www.wexue.top:20000${material.path}" class="btn btn-xs btn-primary">下载
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:if test="${empty materials}">
                    <div class="no-content">无内容</div>
                </c:if>
            </div>
            <div class="page">
                <div class="right">
                    <c:if test="${prepage>0}">
                        <button onclick="javascript:window.location.href='<%=basePath%>/platform/material?startPage=${prepage}'"
                                id="btn-pre" class="btn btn-mini">上一页
                        </button>
                    </c:if>
                    <button onclick="javascript:window.location.href='<%=basePath%>/platform/material?startPage=${nextpage}'"
                            id="btn-next" class="btn btn-mini btn-inverse">下一页
                    </button>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#menu li:eq(6)").addClass("active");
        $(".delete").click(function () {
            var deleteBtn = $(this);
            var $btn = deleteBtn.button('loading');
            $.ajax({
                url: "<%=basePath%>/platform/material/delete?id=" + $(this).data("id"),
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
