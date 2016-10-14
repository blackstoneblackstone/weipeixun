<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lihb
  Date: 1/30/16
  Time: 1:32 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="format-detection" content="telephone=no"/>
    <script type="text/javascript" src="http://www.wexue.top:20000/lib/jquery/jQuery-2.1.4.min.js"></script>
    <link rel="stylesheet" href="/lib/bootstrap-3.3.5-dist/css/bootstrap.min.css">
    <script type="text/javascript" src="/lib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
    <title>学院门户</title>

</head>
<body>
<style>
    .functions li {
        display: inline-block;
        width: 120px;
        height: 50px;
        border: solid #CCCCCC 1px;
        margin: 10px;
        padding-top: 15px;
        padding-left: 15px;
        color: #f2f2f2;
        font-size: 16px;
    }

    .functions li > span {
        margin-right: 5px;

    }

    .fun1 {
        background-color: #d1ad5f;
        opacity: 0.8;
    }

    .fun2 {
        background-color: #6393aa;
        opacity: 0.8;
    }

    .fun3 {
        background-color: #82a262;
        opacity: 0.8;
    }

    .fun4 {
        background-color: #bc6249;
        opacity: 0.8;
    }

    .fun5 {
        background-color: #63639b;
        opacity: 0.8;
    }

    .fun6 {
        background-color: #b477af;
        opacity: 0.8;
    }

    .functions-div {
        position: absolute;
        bottom: 40px;
    }

    body {
        background-color: #CCCCCC;
    }

    .bottom > span {
        color: #f2f2f2;
    }

    .bottom > span.active {
        color: yellow;
    }

    .bottom {
        position: absolute;
        bottom: 20px;
        right: 50px;

    }

    .ad {
        position: absolute;
        bottom: 0px;
    }

    .ad > img {
        height: 100%;
        width: 100%;
        display: inline-block;
    }

    .ad > img:nth-child(2) {
        margin-left: 100%;
    }

    .ad > img:nth-child(3) {
        margin-left: 100%;
    }
</style>
<div class="ad" id="ad">
    <img src="http://www.wexue.top:20000/images/tpl1bg1.jpg">
    <%--<img src="http://www.wexue.top:20000/images/tpl1bg2.jpg">--%>
    <%--<img src="http://www.wexue.top:20000/images/tpl1bg3.jpg">--%>
</div>
<div class="functions-div">
    <ul class="functions">
        <c:if test="${desc == true}">
            <a href="http://www.wexue.top/school/schooldesc?corpid=${userinfo.corpid}">
                <li class="fun1"><span class="glyphicon glyphicon-education"></span>学院介绍</li>
            </a>
        </c:if>
        <c:if test="${publicCourse == true}">
            <a href="http://www.wexue.top/auth/authUser?state=public&corpId=${userinfo.corpid}">
                <li class="fun2"><span class="glyphicon glyphicon-certificate"></span>公开课</li>
            </a> </c:if>
        <c:if test="${requireCourse== true}">
            <a href="http://www.wexue.top/auth/authUser?state=public&corpId=${userinfo.corpid}">
                <li class="fun3"><span class="glyphicon glyphicon-fire"></span>必修课</li>
            </a> </c:if>
        <c:if test="${teacher== true}">
            <a href="http://www.wexue.top/">
                <li class="fun4"><span class="glyphicon glyphicon-heart"></span>推荐讲师</li>
            </a> </c:if>
        <c:if test="${courseNotify== true}">
            <a href="http://www.wexue.top/school/schoolnotify?corpid=${userinfo.corpid}">
                <li class="fun5"><span class="glyphicon glyphicon-bookmark"></span>学院公告</li>
            </a>
        </c:if>
        <c:if test="${ courseCalendar== true}">
            <a href="http://www.wexue.top/auth/authUser?state=mycourse&corpId=${userinfo.corpid}">
                <li class="fun6"><span class="glyphicon glyphicon-calendar"></span>课程日历</li>
            </a>
        </c:if>
    </ul>
</div>
<div class="bottom">
    <span class="glyphicon glyphicon-send active"></span>
    <span class="glyphicon glyphicon-send"></span>
</div>
<script>
    $(function () {
        var width = screen.width;
        var height = screen.height;

//        $("#ad").css("width","");
        var i = 0;
//        setInterval(function () {
//            i++;
//            console.log($("body"));
//            $("body").css("background-img", "url('http://www.wexue.top:20000/images/tpl1bg" + i + ".jpg')");
//            if (i == 3) {
//                i = 0;
//            }
//        }, 1000);
    });

</script>
</body>
</html>
