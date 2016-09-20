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
%>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <link href="<%=basePath%>/css/weui.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #FFFFFF;
            position: relative;
            text-align: center;
            margin: 0;
            padding: 0;
            width: 640px;
        }

        .header {
            height: 50px;
            box-shadow: 2px 2px 2px #888888;
        }

        .login-div {
            width: 400px;
            height: 300px;
            background-color: #FFFFFF;
            box-shadow: 2px 2px 2px 2px #888888;
            margin: 50px auto;
            position: relative;
            display: inline-block;
        }

        .actor img {
            height: 100px;
            width: 100px;
        }

    </style>
    <script type='text/javascript' src='<%=basePath%>/js/jquery.min.js'></script>
    <script>

        if (/Android (\d+\.\d+)/.test(navigator.userAgent)) {
            var version = parseFloat(RegExp.$1);
            if (version > 2.3) {
                var phoneScale = parseInt(window.screen.width) / 640;
                document.write('<meta name="viewport" content="width=640, minimum-scale = ' + phoneScale + ', maximum-scale = ' + phoneScale + ', target-densitydpi=device-dpi">');
            } else {
                document.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
            }
        } else {
            document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
        }


        function confirm() {

            $.ajax({
                url: "<%=basePath%>/auth/login/mobile/confirm",
                type: 'post',
                data: {
                    lc:${lc},
                    user:${user}
                },
                dataType:"json",
                success: function (data) {
                    console.log(data);
                    alert(data);
                }
            });
        }

    </script>
</head>
<body>
<div class="header">

</div>
<div class="login-div">
    <div class="actor">
        <img src="${user.avatar}">
    </div>
    <button onclick="confirm()" class="weui_btn weui_btn_primary">
        确认登陆
    </button>

    ${user}
</div>
</body>
</html>
