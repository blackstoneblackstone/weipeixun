<%--
  Created by IntelliJ IDEA.
  User: lihb
  Date: 9/18/16
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    String serverPath = request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
    <title>登陆</title>
    <link href="<%=basePath%>/lib/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #FFFFFF;
            position: relative;
            text-align: center;
            margin: 0;
            padding: 0;
        }

        .header {
            height: 50px;
            box-shadow: 2px 2px 2px #888888;
        }

        .login-div {
            width: 600px;
            height: 400px;
            background-color: #FFFFFF;
            box-shadow: 2px 2px 2px 2px #888888;
            margin: 50px auto;
            position: relative;
            display: inline-block;
        }

        #qrcode {
            width: 300px;
            height: 300px;
            margin: 50px 50px;
        }

        table td {
            border: solid #CCCCCC 1px;
        }
    </style>
    <script type='text/javascript' src='<%=basePath%>/js/jQuery-2.1.4.min.js'></script>
    <script type='text/javascript' src='<%=basePath%>/lib/bootstrap-3.3.5-dist/js/bootstrap.min.js'></script>
    <script type="text/javascript" src="<%=basePath%>/js/jquery.qrcode.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/socket.min.js"></script>
    <script>
        $(function () {
            var websocket = new SockJS("<%=basePath%>/webSocketServer/login");
            websocket.onopen = function (evnt) {
            };
            websocket.onmessage = function (evnt) {
                console.log(evnt);
            };
            websocket.onerror = function (evnt) {
            };
            websocket.onclose = function (evnt) {
            }

            $('#qrcode').qrcode({
                render: "canvas",//设置渲染方式
                width: 256,     //设置宽度
                height: 256,     //设置高度
                background: "#ffffff",//背景颜色
                foreground: "green",
                text: '<%=basePath%>/auth/login/mobile?lc=${lc}'
            });
        });

    </script>
</head>
<body>
<div class="header">

</div>
<div class="login-div">
    <div id="qrcode">
    </div>
    <botton class="btn btn-group-sm">登陆</botton>
</div>
</body>
</html>
