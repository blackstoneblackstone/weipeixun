<%--
  Created by IntelliJ IDEA.
  User: lihb
  Date: 1/2/16
  Time: 10:21 下午
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
<!doctype html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="<%=basePath%>/js/lib/amazeui/i/favicon.png">
    <link rel="stylesheet" href="<%=basePath%>/js/lib/amazeui/css/amazeui.min.css">
    <link rel="stylesheet" href="<%=basePath%>/js/lib/amazeui/css/amazeui.min.css">
    <link rel="stylesheet" href="<%=basePath%>/js/lib/weUI/weui.min.css">
    <link rel="stylesheet" href="<%=basePath%>/js/lib/amazeui/css/app.css">
