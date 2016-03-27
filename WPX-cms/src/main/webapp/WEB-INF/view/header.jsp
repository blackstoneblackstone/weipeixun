<%--
  Created by IntelliJ IDEA.
  User: lihb
  Date: 10/1/15
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>后台-微培训</title>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0,initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="<%=basePath%>/lib/bootstrap-3.3.5-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/animate.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/platform.css"/>

    <script src="<%=basePath%>/js/jQuery-2.1.4.min.js"></script>
    <script src="<%=basePath%>/js/jquery.form.js"></script>
    <script src="<%=basePath%>/lib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

</head>
<body>
<div id="tip" class="tip alert alert-danger animated bounceInRight hidden">
    提示：
</div>
<div class="header">
</div>
<nav class="navbar navbar-default navbar-fixed-top">
    <!-- We use the fluid option here to avoid overriding the fixed width of a normal container within the narrow content columns. -->
    <div class="container-fluid">
        <div class="navbar-header">
            <img style="border:solid white 2px;width: 40px;margin-left: 40px;margin-top: 5px;margin-bottom: 5px;"
                 src="${userinfo.qyheader}">
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-6">
            <ul class="nav navbar-nav" id="menu">
                <li role="presentation">
                    <a href="<%=basePath%>/platform/portal"><i class="glyphicon glyphicon-home"></i>
                        <span>学院门户</span></a>
                </li>
                <li role="presentation">
                    <a href="<%=basePath%>/platform/project"><i class="glyphicon glyphicon-tasks"></i>
                        <span>项目</span></a></li>
                <li role="presentation">
                    <a href="<%=basePath%>/platform/course"><i class="glyphicon glyphicon-hdd"></i> <span>课程</span></a>
                </li>
                <li role="presentation">
                    <a href="<%=basePath%>/platform/group"><i class="glyphicon glyphicon-user"></i>
                        <span>学习小组</span></a></li>
                <li role="presentation">
                    <a href="<%=basePath%>/platform/research"><i class="glyphicon glyphicon-book"></i> <span>调研测试</span></a>
                </li>
                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                       aria-expanded="false" href="<%=basePath%>/platform/resource">
                        <i class="glyphicon glyphicon-th"></i>
                        <span id="speaker">资源管理</span>
                        <span class="badge">2</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="<%=basePath%>/platform/speaker">讲师库</a></li>
                        <%--<li><a href="form-validation.html">标签库</a></li>--%>
                        <%--<li><a href="<%=basePath%>/platform/place">场地库</a></li>--%>
                        <li><a href="<%=basePath%>/platform/addressbook">通讯录</a></li>
                    </ul>
                </li>
                <li role="presentation"><a href="<%=basePath%>/platform/material">
                    <i class="glyphicon glyphicon-folder-close"></i>
                    <span>培训资料</span></a></li>
                <li role="presentation">
                    <a href="<%=basePath%>/platform/notify"><i class="icon icon-pencil"></i>
                        <span>发消息</span></a>
                </li>
                <li role="presentation">
                    <a href="<%=basePath%>/platform/message">
                        <i class="icon icon-file"></i> <span>通知中心</span>
                    </a>

                </li>
                <li role="presentation">
                    <a href="<%=basePath%>/platform">
                        <i class="glyphicon glyphicon-user div-user"></i> <span
                            class="div-user">${userinfo.username}</span>
                    </a>
                </li>
                <li role="presentation">
                    <a href="<%=basePath%>/user/logout">
                        <i class="glyphicon glyphicon-off div-user"></i> <span class="div-user">退出</span>
                    </a>
                </li>


            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
</nav>