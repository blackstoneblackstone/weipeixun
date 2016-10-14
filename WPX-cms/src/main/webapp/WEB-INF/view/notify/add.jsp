<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<jsp:include page="../header.jsp"></jsp:include>
<style>
    .app {
        text-align: center;
        cursor: pointer;
    }

    .app-icon {
        height: 100px;
    }

    .app-icon:hover {
        background-color: #f2f2f2;
    }

    .app-head {
        height: 30px;
    }

    .app-icon > img {
        height: 80px;
    }

</style>
<div class="content">
    <div class="panel panel-success" id="appList">
        <div class="panel-heading">选择一个应用发消息</div>
        <div class="panel-body">
            <c:forEach items="${apps}" var="app" varStatus="s">
                <a href="<%=basePath%>/platform/notify/sendMessage?agentid=${app.agentid}">
                    <div class="app col-sm-2">
                        <div class="panel panel-info">
                            <div class="panel-body app-icon">
                                <img src="${app.square_logo_url}">
                            </div>
                            <div class="panel-heading app-head">
                                    ${app.iname}
                            </div>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="../bottom.jsp"></jsp:include>