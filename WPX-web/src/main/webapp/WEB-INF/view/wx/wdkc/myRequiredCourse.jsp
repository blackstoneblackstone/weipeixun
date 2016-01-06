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
    <title>我的必修课</title>
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
    {{>wxheader wxheader}}
    {{>list_news list1}}
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
        wxheader: {
                "avatar":"${sessionkey.useravator}"
        },
        list1: {
            "options": {
                "type": "thumb",
                "thumbPosition": "left"
            },
            "content": {
                "header": {
                    "title": "最近有这些课",
                    "link": "###",
                    "moreText": "还有 >>",
                    "morePosition": "top"
                },
                "main": [
                    {
                        "title": "如何有效的沟通交流",
                        "link": "http://www.douban.com/online/11614662/",
                        "desc": "囧人囧事囧照，人在囧途，越囧越萌。标记《带你出发，陪我回家》http://book.douban.com/subject/25711202/为“想读”“在读”或“读过”，有机会获得此书本活动进行3个月，每月送出三本书。会有不定期bonus！",
                        "img": "http://img5.douban.com/lpic/o636459.jpg"
                    },
                    {
                        "title": "java基础培训",
                        "link": "http://www.douban.com/online/11624755/",
                        "desc": "你最喜欢的艺术作品，告诉大家它们的------名图画，色彩，交织，撞色，线条雕塑装置当代古代现代作品的照片美我最喜欢的画群296795413进群发画，少说多发图，",
                        "img": "http://img3.douban.com/lpic/o637240.jpg"
                    },
                    {
                        "title": "真正的管理艺术",
                        "link": "http://www.douban.com/online/11645411/",
                        "desc": "还在苦恼圣诞礼物再也玩儿不出新意？快来抢2013最炫彩的跨国圣诞礼物！【参与方式】1.关注“UniqueWay无二之旅”豆瓣品牌小站http://brand.douban.com/uniqueway/2.上传一张**本人**在旅行中色彩最浓郁、最丰富的照片（色彩包含取景地、周边事物、服装饰品、女生彩妆等等，发挥你们无穷的创意想象力哦！^^）一定要有本人出现喔！3. 在照片下方，附上一句旅行宣言作为照片说明。 成功参与活动！* 听他们刚才说，上传照片的次",
                        "img": "http://www.wexue.top:20000/images/sky-1.jpg!300!300"
                    }
                ]
            }
        },
        gotop:{
            "id": "",

            "className": "",

            "theme": "default",

            "content": {
                "title":      "回到顶部" ,     // 显示文字（某些主题不显示）
                "icon":       "arrow-up" ,   // 图标名称，使用内置的 Icon Font
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
                "owner": "云适配",
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
