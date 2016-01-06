<%--
  Created by IntelliJ IDEA.
  User: lihb
  Date: 10/1/15
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path ;
%>
<!--sidebar-menu-->
<div id="sidebar">
    <a href="#" class="visible-phone"><i class="icon icon-home"></i> 学院门户</a>
    <ul>
        <li><a href="<%=basePath%>/platform/home"><i class="icon icon-home"></i> <span>学院门户</span></a></li>
        <li><a href="<%=basePath%>/platform/project"><i class="icon icon-signal"></i> <span>项目</span></a></li>
        <li><a href="<%=basePath%>/platform/course"><i class="icon icon-inbox"></i> <span>课程</span></a></li>
        <li><a href="<%=basePath%>/platform/studyGroup"><i class="icon icon-th"></i> <span>学习小组</span></a></li>
        <li><a href="<%=basePath%>/platform/research"><i class="icon icon-fullscreen"></i> <span>调研测试</span></a></li>
        <li class="submenu"><a href="<%=basePath%>/platform/resource"><i class="icon icon-th-list"></i> <span>资源管理</span> <span
                class="label label-important">3</span></a>
            <ul>
                <li><a href="form-common.html">讲师库</a></li>
                <li><a href="form-validation.html">标签库</a></li>
                <li><a href="form-wizard.html">场地库</a></li>
                <li><a href="<%=basePath%>/platform/addressbook">通讯录</a></li>
            </ul>
        </li>
        <li><a href=""><i class="icon icon-tint"></i> <span>培训资料</span></a></li>
        <li><a href="buttons.html"><i class="icon icon-tint"></i> <span>子管理员</span></a></li>
        <li><a href="buttons.html"><i class="icon icon-tint"></i> <span>直播中心</span></a></li>
        <li><a href="interface.html"><i class="icon icon-pencil"></i> <span>通知中心</span></a></li>
        <li class="submenu"><a href="<%=basePath%>/platform/statistics"><i class="icon icon-file"></i> <span>统计分析</span> <span
                class="label label-important">5</span></a>
            <ul>
                <li><a href="index2.html">Dashboard2</a></li>
                <li><a href="gallery.html">Gallery</a></li>
                <li><a href="calendar.html">Calendar</a></li>
                <li><a href="invoice.html">Invoice</a></li>
                <li><a href="chat.html">Chat option</a></li>
            </ul>
        </li>
        <li class="submenu"><a href="#"><i class="icon icon-info-sign"></i> <span>子管理员</span> </a>
        </li>
    </ul>
</div>
<!--sidebar-menu-->