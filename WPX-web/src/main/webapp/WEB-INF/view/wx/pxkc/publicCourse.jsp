<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<jsp:include page="../header.jsp"></jsp:include>
<link rel="stylesheet" href="/js/lib/amazeui/css/app.css">
<title>公开课</title>
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
    {{>header header}}
    {{>slider slider}}
    {{>publiccourselist publiccourselist}}
    {{>gotop gotop}}
    {{>footer footer}}
</script>

<jsp:include page="../footer.jsp"></jsp:include>
<script src="/js/wx/service.js"></script>
<script>
    var $tpl = $('#amz-tpl');
    var source = $tpl.text();
    var template = Handlebars.compile(source);
    console.log();
    var data = {
        header: {
            "options": {
                "fixed": true
            },
            "content": {
                "title": "公开课",
                "right": [{
                    "link": "/auth/authUser?state=mycourse&corpId=${sessionkey.corpid}",
                    "customIcon": "${sessionkey.useravator}",
                    "className": "header-img"
                }],
                "left": [{
                    "link": "/pxkc/portalWebJsp",
                    "icon": "home"
                }]
            }
        },
        slider: {
            "theme": "d2",
            //"sliderConfig": "{\"directionNav\":false}",
            "content": []
        },
        publiccourselist: {
            //"sliderConfig": "{\"directionNav\":false}",
            "content": [
                <%--{--%>
                <%--"detaillink": "/pxkc/publicCourseDetailJsp?courseid=COURSE22243cf3-7216-431b-91d1-1e394f58fc80&userid=${user.userId}", // 链接--%>
                <%--"orderlink": "http://www.sina.com", // 链--%>
                <%--"teacher": "李老师", // 缩略图--%>
                <%--"desc": '新员工入职培训',// 附加信息，支持DOM，为高级定制提供DOM接口--%>
                <%--"img": "http://www.ekaola.com/assets/package/cover/corp_phpyD9QiQ1448334050.png"--%>
                <%--}--%>
            ]
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
    $.when($.publicData.publicCourseData(data.publiccourselist.content), $.publicData.projectData(data.slider.content)).done(function () {
        var html = template(data);
        $tpl.before(html);
        // 如果 Handlebars 渲染出来的 HTML 在 DOM ready 事件之后插入文档，需要手动初始化组件
        $.each(['header', 'slider', 'publiccourselist', 'gotop', 'footer'], function (i, m) {
            var module = $.AMUI[m];
            module && module.init && module.init();
        })
    });
</script>
</body>
</html>
