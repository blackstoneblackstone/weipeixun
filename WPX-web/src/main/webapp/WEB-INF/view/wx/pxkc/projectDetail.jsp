<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<jsp:include page="../header.jsp"></jsp:include>
<link rel="stylesheet" href="/js/lib/amazeui/css/app.css">
<title>项目详情</title>
<meta name="description" content="">
<meta name="keywords" content="">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，微学 不提供支持。 请 <a
        href="http://browsehappy.com/" target="_blank">升级浏览器</a>
    以获得更好的体验！</p>
<![endif]-->
<div class="wx-header" >
    <img class="wx-header-bg am-animation-slide-top" style="margin-top: 50px;height: 165px" src="http://www.wexue.top:20000/images/sky-2.jpg">
</div>

</div>
<script type="text/x-handlebars-template" id="amz-tpl">
    {{>header header}}
    {{>titlebar titlebar}}
    <div class="course-detail">
        <p>
            <label>名称：</label>
            ${project.proname}
        </p>
        <p>
            <label>时间：</label>
            ${project.starttime}/${project.endtime}
        </p>

        <p>
            <label>简介：</label>
            ${project.prodesc}
        </p>

    </div>
    <c:if test="${project.state==0}">
        <div class="btn-order" id="btn_order">
            <a onclick="orderproject('${project.id}')" title="预约"
               class="am-icon-faq am-icon-btn am-icon-gear am-icon-spin"></a>
        </div>
    </c:if>
    {{>list_news list1}}
    {{>footer footer}}

</script>

<jsp:include page="../footer.jsp"></jsp:include>
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
                "title": "${project.proname}",
                "right": [{
                    "link": "",
                    "customIcon": "${user.useravator}",
                    "className": "header-img"
                }],
                "left": [{
                    "link": "/auth/authUser?corpId=${user.corpid}&state=public",
                    "icon": "angle-left"
                }]
            }
        },
        titlebar: {
            "content": {
                "title": "项目简介",
                "className": ""
            },
            "theme": "default"
        },

        list1: {
            "options": {
                "type": "thumb",
                "thumbPosition": "left"
            },
            "content": {
                "header": {
                    "title": "所属课程",
                    "link": "###",
                    "moreText": "更多 >>",
                    "morePosition": "top"
                },
                "main": [
//                    {
//                        "title": "我很囧，你保重....晒晒旅行中的那些囧！",
//                        "link": "http://www.douban.com/online/11614662/",
//                        "desc": "囧人囧事囧照，人在囧途，越囧越萌。标记《带你出发，陪我回家》http://book.douban.com/subject/25711202/为“想读”“在读”或“读过”，有机会获得此书本活动进行3个月，每月送出三本书。会有不定期bonus！",
////                        "img": "http://img5.douban.com/lpic/o636459.jpg"
//                    },
//                    {
//                        "title": "我最喜欢的一张画",
//                        "link": "http://www.douban.com/online/11624755/",
//                        "desc": "你最喜欢的艺术作品，告诉大家它们的------名图画，色彩，交织，撞色，线条雕塑装置当代古代现代作品的照片美我最喜欢的画群296795413进群发画，少说多发图，",
////                        "img": "http://img3.douban.com/lpic/o637240.jpg"
//                    },
//                    {
//                        "title": "“你的旅行，是什么颜色？” 晒照片，换北欧梦幻极光之旅！",
//                        "link": "http://www.douban.com/online/11645411/",
//                        "desc": "还在苦恼圣诞礼物再也玩儿不出新意？快来抢2013最炫彩的跨国圣诞礼物！【参与方式】1.关注“UniqueWay无二之旅”豆瓣品牌小站http://brand.douban.com/uniqueway/2.上传一张**本人**在旅行中色彩最浓郁、最丰富的照片（色彩包含取景地、周边事物、服装饰品、女生彩妆等等，发挥你们无穷的创意想象力哦！^^）一定要有本人出现喔！3. 在照片下方，附上一句旅行宣言作为照片说明。 成功参与活动！* 听他们刚才说，上传照片的次",
//                        "img": ""
//                    }
                ]
            }
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
