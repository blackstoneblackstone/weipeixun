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
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap-wysihtml5.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/colorpicker.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/datepicker.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/fullcalendar.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/jquery.gritter.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/matrix-style.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/matrix-media.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/uniform.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/select2.css"/>
    <link href="<%=basePath%>/font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <script src="<%=basePath%>/js/jquery.min.js"></script>
    <script src="<%=basePath%>/js/bootstrap.min.js"></script>
    <style>

        html * {
            -webkit-font-smoothing: antialiased;
            font-family: "Lantinghei SC", "Open Sans", Arial, "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", "STHeiti", "WenQuanYi Micro Hei", SimSun, sans-serif;
        }
    </style>

</head>
<body>
<!--Header-part-->
<div id="header">
    <%--<h1><a href="dashboard.html">微培训 Admin</a></h1>--%>
    <h2><a href="<%=basePath%>/platform/index"><span class="label label-important"
                                                     style="font-size: 30px;padding-top: 10px;padding-bottom: 10px">微</span></a>
        ${userinfo.qyname}
    </h2>
</div>
<!--close-Header-part-->
<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav">
        <li class="dropdown" id="profile-messages"><a title="" href="#" data-toggle="dropdown"
                                                      data-target="#profile-messages" class="dropdown-toggle"><i
                class="icon icon-user"></i> <span class="text">${userinfo.username}</span><b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a href="#"><i class="icon-user"></i> My Profile</a></li>
                <li class="divider"></li>
                <li><a href="#"><i class="icon-check"></i> My Tasks</a></li>
                <li class="divider"></li>
                <li><a href="admin-login.jsp"><i class="icon-key"></i> Log Out</a></li>
            </ul>
        </li>
        <li class="dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages"
                                                   class="dropdown-toggle"><i class="icon icon-envelope"></i> <span
                class="text">Messages</span> <span class="label label-c">5</span> <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a class="sAdd" title="" href="#"><i class="icon-plus"></i> new message</a></li>
                <li class="divider"></li>
                <li><a class="sInbox" title="" href="#"><i class="icon-envelope"></i> inbox</a></li>
                <li class="divider"></li>
                <li><a class="sOutbox" title="" href="#"><i class="icon-arrow-up"></i> outbox</a></li>
                <li class="divider"></li>
                <li><a class="sTrash" title="" href="#"><i class="icon-trash"></i> trash</a></li>
            </ul>
        </li>
        <li class=""><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">Settings</span></a></li>
        <li class=""><a title="" href="admin-login.jsp"><i class="icon icon-share-alt"></i> <span
                class="text">Logout</span></a>
        </li>
    </ul>
</div>
<!--close-top-Header-menu-->
<!--start-top-serch-->
<div id="search">
    <input type="text" placeholder="Search here..."/>
    <button type="submit" class="tip-bottom" title="Search"><i class="icon-search icon-white"></i></button>
</div>

