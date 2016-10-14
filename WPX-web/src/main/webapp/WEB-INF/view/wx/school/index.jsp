<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="format-detection" content="telephone=no"/>
    <script type="text/javascript" src="http://www.wexue.top:20000/lib/jquery/jQuery-2.1.4.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/js/lib/bootstrap-3.3.5-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://www.wexue.top:8081/cms/template/${school.modelstyle}.css">
    <script type="text/javascript" src="<%=basePath%>/js/lib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
    <title>${school.wdesc}</title>
</head>
<body>
<div class="ad" id="ad">
    <img src="http://www.wexue.top:20000/images/tpl1bg1.jpg">
    <%--<img src="http://www.wexue.top:20000/images/tpl1bg2.jpg">--%>
    <%--<img src="http://www.wexue.top:20000/images/tpl1bg3.jpg">--%>
</div>
<div class="functions-div">
    <c:if test="${empty school}">
        <div style="position: absolute;top:50px;color: red;font-size: 30px;left: 150px">
        未配置学院门户
        </div>
    </c:if>
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
