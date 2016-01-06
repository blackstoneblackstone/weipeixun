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
    <title>课程详情</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="/js/lib/amazeui/i/favicon.png">
    <link rel="stylesheet" href="/js/lib/amazeui/css/amazeui.min.css">
    <link rel="stylesheet" href="/js/lib/amazeui/css/app.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，微学 不提供支持。 请 <a
        href="http://browsehappy.com/" target="_blank">升级浏览器</a>
    以获得更好的体验！</p>
<![endif]-->
<div class="wx-header">
    <img class="wx-header-bg am-animation-slide-top" src="${course.icon}">
</div>

</div>
<script type="text/x-handlebars-template" id="amz-tpl">
    {{>header header}}
    {{>titlebar titlebar}}
    <div class="course-detail">
        <p>
            <label>时间：</label>
            ${course.starttime}<i class="am-icon-arrow-right"></i>${course.endtime}

        </p>

        <p>
            <label>地点：</label>
            ${course.place}

        </p>

        <p>
            <label>讲师：</label>
            ${course.teacher}
        </p>

        <p>
            <label>简介：</label>
            ${course.coursedesc}
        </p>

    </div>
    {{>gotop gotop}}
    {{>footer footer}}

</script>
<script src="/js/lib/amazeui/js/jquery.min.js"></script>
<script src="/js/lib/amazeui/js/amazeui.js"></script>
<script src="/js/lib/amazeui/js/handlebars.min.js"></script>
<script src="/js/lib/amazeui/js/amazeui.widgets.helper.js"></script>
<script>
    var $tpl = $('#amz-tpl');
    var source = $tpl.text();
    var template = Handlebars.compile(source);
    var data = {
        header: {
            "options": {
                "fixed": true
            },
            "content": {
                "title": "${course.coursename}",
                "right": [{
                    "link": "",
                    "customIcon": "${user.avatar}",
                    "className": "header-img"
                }],
                "left": [{
                    "className": "back",
                    "link": "<%=basePath%>/auth/authUser?corpId=${course.corpid}&state=require",
                    "icon": "angle-left"
                }]
            }
        },
        titlebar: {
            "content": {
                "title": "课程简介",
                "className": ""
            },
            "theme": "default"
        },
        gotop: {
            "id": "",

            "className": "",

            "theme": "default",

            "content": {
                "title": "回到顶部",     // 显示文字（某些主题不显示）
                "icon": "arrow-up",   // 图标名称，使用内置的 Icon Font
                "customIcon": ""          // 自定义图标 URL
            }
        },
        footer: {
            "options": {
                "modal": true,
                "techSupportCo": "LIHB",
                "techSupportSite": "http://www.wexue.top/"
            },
            "content": {
                "owner": "微学",
                "companyInfo": [
                    {
                        "detail": "CopyRight©2013  wexue Inc."
                    },
                    {
                        "detail": "京ICP备13033158"
                    }
                ]
            }
        }
    };
    var html = template(data);

    $tpl.before(html);


    /*
     // 如果 Handlebars 渲染出来的 HTML 在 DOM ready 事件之后插入文档，需要手动初始化组件
     $.each(['slider', 'menu', 'gallery', 'footer', 'navbar'], function(i, m) {
     var module = $.AMUI[m];
     module && module.init && module.init();
     })
     */
</script>
</body>
</html>
