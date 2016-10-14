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
<div class="content">
    <!--Action boxes-->
    <table class="table table-bordered data-table">
        <thead>
        <tr>
            <th>序号</th>
            <th>日期</th>
            <th>标题</th>
            <th>描述</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${messages}" var="message" varStatus="s">
            <tr>
                <td>#${ s.index + 1}</td>
                <td>
                        ${message.createtime}
                </td>
                <td>${message.title}</td>
                <td>${message.desc}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${empty courses}">
        <div class="no-content">无内容</div>
    </c:if>
    <div class="page">
        <div class="right">
            <c:if test="${prepage>0}">
                <button onclick="javascript:window.location.href='<%=basePath%>/platform/message?startPage=${prepage}'"
                        id="btn-pre" class="btn btn-mini">上一页
                </button>
            </c:if>
            <button onclick="javascript:window.location.href='<%=basePath%>/platform/message?startPage=${nextpage}'"
                    id="btn-next" class="btn btn-mini btn-inverse">下一页
            </button>
        </div>
    </div>
    <div class="clear"></div>
</div>
</div>
<!--content-->
<script>
    $(function () {
        $("#menu li:eq(8)").addClass("active");
    });
</script>

<!--bottom-->
<jsp:include page="../bottom.jsp"></jsp:include>
<!--bottom-->
