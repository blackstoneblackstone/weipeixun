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
    <title>学院门户</title>
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

<script type="text/x-handlebars-template" id="amz-tpl">
    {{>header header}}

    {{>menu menu}}

    {{>slider slider}}

    {{>list_news list1}}

    {{>gallery gallery}}

    {{>list_news list2}}

    {{>footer footer}}

    {{>navbar navbar}}
</script>

<script src="/js/lib/amazeui/js/jquery.min.js"></script>
<script src="/js/lib/amazeui/js/amazeui.js"></script>
<script src="/js/lib/amazeui/js/handlebars.min.js"></script>
<script src="/js/lib/amazeui/js/amazeui.widgets.helper.js"></script>
<script src="/js/wx/common.js"></script>
<script>

    var $tpl = $('#amz-tpl');
    var source = $tpl.text();
    var template = Handlebars.compile(source);

    var data = ${templateData};
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
