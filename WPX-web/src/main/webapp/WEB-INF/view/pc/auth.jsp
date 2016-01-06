<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>应用授权安装-天天微学</title>
    <meta name="description" content="授权安装">
    <meta name="keywords" content="授权安装">
    <style type="text/css">
        * {
            margin: 0;
            padding: 0
        }

        body {
            font: 14px 微软雅黑;
            color: #666;
            line-height: 1.7;
            background: #4990e2 url("/image/pc/auth_bg.jpg") repeat center top;
        }

        h1 {
            font-size: 20px;
            color: #1a1917;
            font-weight: 500;
        }

        ul {
            list-style: none
        }

        img {
            border-radius: 5px;
        }

        a {
            text-decoration: none;
            color: #666
        }

        a:hover {
            text-decoration: underline;
        }

        .center {
            text-align: center
        }

        .ohidden {
            overflow: hidden;
        }

        .mt20 {
            margin-top: 20px;
        }

        .pb40 {
            padding-bottom: 40px;
        }

        .fr {
            float: right;
        }

        .wrap {
            width: 680px;
            margin: 40px auto;
            background: #fff;
            -webkit-box-shadow: 0 2px 4px rgba(0, 0, 0, .4);
            -moz-box-shadow: 0 2px 4px rgba(0, 0, 0, .4);
            box-shadow: 0 2px 4px rgba(0, 0, 0, .4);
        }

        .main {
            width: 600px;
            margin: 0 auto;
        }

        .logo {
            padding-top: 60px;
        }

        .logo img {
            width: 80px;
            height: 80px;
        }

        .btn {
            height: 42px;
            width: 320px;
            margin: 65px auto 10px;
            background-color: green;
            display: block;
            text-align: center;
            line-height: 42px;
            color: white;
            text-decoration: none;
            font-size: 16px;
            cursor: pointer;
        }

        .c4a90e3 {
            color: green;
        }

        .list a {
            display: block;
            width: 60px;
            float: left;
            margin-right: 20px;
        }

        .list img {
            width: 60px;
            height: 60px;
            vertical-align: middle;
        }

        .info {
            margin-top: 30px;
            border-top: 1px solid #ddd;
            padding-top: 10px;
            margin-bottom: 15px;
        }

        .btn:hover {
            background: darkgreen;
            text-decoration: none
        }
    </style>
    <style id="style-1-cropbar-clipper">
        .en-markup-crop-options div div:first-of-type {
            margin-left: 0px !important;
        }
    </style>
</head>

<body>
<div class="wrap">
    <div class="main">
        <div class="logo center"><img src="<%=basePath%>/image/pc/suite1.png" alt="微培训"></div>
        <h1 class="center">授权【微培训】套件托管你的应用</h1>

        <div class="mt20">
            <p>该套件集成了培训课程，我的课程等应用，以下通过“一键授权接入”实现安装，你可以选择安装一个应用或多个应用单元。</p></div>
        <a href="${authurl}" class="btn">立即授权安装</a>

        <p class="center">授权后表明你已同意<a target="_blank"
                                      href="/pc/provision.html"
                                      class="c4a90e3">《天天微学服务条款》</a></p>

        <p class="info ohidden">您还可<span style="color:#f05454">免费</span>安装以下套件：
        </p>

        <div class="list ohidden pb40">
            <a href="<%=basePath%>/app/pxkc"><img src="<%=basePath%>/image/pc/icon_11.png" alt="培训课程">培训课程</a>
            <a href="<%=basePath%>/app/wdpx"><img src="<%=basePath%>/image/pc/icon_3.png" alt="我的培训">我的培训</a>
            <a href="<%=basePath%>/app/qlxz"><img src="<%=basePath%>/image/pc/icon_4.png" alt="群聊小组">群聊小组</a>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=basePath%>/js/lib/jQuery-2.1.4.min.js"></script>


</body>
</html>