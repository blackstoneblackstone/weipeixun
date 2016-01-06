<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp"></jsp:include>
    <title>我的课程</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
</head>
<body style="background: url('/image/wx/ajax-loader.gif') center no-repeat">
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
<jsp:include page="../footer.jsp"></jsp:include>
<script src="/js/wx/service.js"></script>
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
    $.when($.myCourseData.myOrderCourseData(data.list1.content.main)).done(function () {
        var html = template(data);
        $tpl.before(html);
        // 如果 Handlebars 渲染出来的 HTML 在 DOM ready 事件之后插入文档，需要手动初始化组件
        $.each(['header', 'list_news', 'gotop', 'footer'], function (i, m) {
            var module = $.AMUI[m];
            module && module.init && module.init();
        })
    });

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
