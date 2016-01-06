<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp"></jsp:include>
<title>我的课程</title>
<style>

    ul {
        margin: 0px;
    }

    article, section, time, aside {
        display: block;
    }

    aside {
        border-top: solid #c2c2c2 1px;
        padding-top: 5px;
        margin-right: 10px;
    }

    .point-time {
        position: absolute;
        width: 13px;
        height: 13px;
        top: 17px;
        left: 20%;
        background: #1c87bf;
        margin-left: -4px;
        border-radius: 50%;
        box-shadow: 0 0 0 5px #fff;
    }

    .text-red {
        color: #f6393f;
    }

    .text-blue {
        color: #1c87bf;
    }

    .text-green {
        color: #95c91e;
    }

    .text-yellow {
        color: #ffb902;
        font-size: 16px;
    }

    .text-purple {
        color: #d32d93;
    }

    .point-red {
        background-color: #f6393f;
    }

    .point-blue {
        background-color: #1c87bf;
    }

    .point-green {
        background-color: #95c91e;
    }

    .point-yellow {
        background-color: #ffb902;
    }

    .point-purple {
        background-color: #d32d93;
    }

    .content {
        width: 100%;
        margin: 50px auto;
        background-color: rgba(255, 255, 255, 1);
    }

    .content article {
        position: relative;
    }

    .content article > h3 {
        width: 20%;
        height: 20px;
        line-height: 20px;
        text-align: right;
        font-size: 1.4em;
        color: #1d1d1d;
        padding: 10px 0 20px;
    }

    .content article section {
        padding: 0 0 17px;
        position: relative;
    }

    .content article section:before {
        content: "";
        width: 5px;
        top: 17px;
        bottom: -17px;
        left: 20%;
        background: #e6e6e6;
        position: absolute;
    }

    .content article section:last-child:before {
        display: none;
    }

    .content article section time {
        width: 15%;
        display: block;
        position: absolute;
    }

    .content article section time > span {
        display: block;
        text-align: right;
    }

    .content article section aside {
        color: #3a3a38;
        margin-left: 25%;
        padding-bottom: 15px;
    }

    .course-teacher {
        height: 60px;
    }

    .course-teacher img {
        width: 50px;
        height: 50px;
        border: solid #cccccc 1px;
        border-radius: 50%;
        -webkit-border-radius: 50%;
        -webkit-border: solid #cccccc 1px;
        float: left;
    }

    .course-teacher ul {
        margin-top: 10px;
        margin-left: 50px;
    }

    p {
        margin: 0px;
    }

    .course-desc {
        float: right;
        margin-right: 30px;
    }

</style>
</head>
<body style="background: url('/image/wx/ajax-loader.gif') center no-repeat">
<script type="text/x-handlebars-template" id="amz-tpl">
    {{>wxheader wxheader}}

    <div class="content">
        <article>
            <h3>{{mycourse.year}}年</h3>
            {{#each mycourse.content}}
            <section>
                <span class="point-time point-{{type}}"></span>
                <time datetime="2013-03">
                    <span>{{month}}月</span>
                    <span>{{day}}日</span>
                </time>

                <aside>
                    <p class="course-title">
                        {{#if public}}
                        <span class="am-badge am-badge-success am-radius">公开课</span>
                        {{/if}}
                        {{#if require}}
                        <span class="am-badge am-badge-danger am-radius">必修课</span>
                        {{/if}}
                        <span>{{title}}</span>
                    </p>

                    <div class="course-teacher">
                        <img class="teacher-img" src="{{teacheravator}}"/>
                        <ul>
                            <li class="teacher-name text-yellow">{{teachername}}</li>
                            <li class="teacher-desc">{{teacherdesc}}</li>
                        </ul>
                    </div>
                    <div class="course-tip">
                        <span class="am-badge am-badge-warning am-radius">人数:{{people}}</span>
                    </div>
                    <div class="course-desc">
                        <div class="am-btn-group am-btn-group-xs">
                            <%--<button type="button" class="am-btn am-btn-primary am-round">不去了</button>--%>
                            <%--<button type="button" class="am-btn am-btn-primary am-round">请个假</button>--%>
                        </div>
                    </div>
                </aside>
            </section>
            {{/each}}
        </article>
    </div>
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
            "avatar": "${sessionkey.useravator}"
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
        mycourse: {
            year:"2016",
            content: [
            ]
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
    $.when($.myCourseData.myCourseData(data.mycourse.content)).done(function () {
        var html = template(data);
        $tpl.before(html);
        // 如果 Handlebars 渲染出来的 HTML 在 DOM ready 事件之后插入文档，需要手动初始化组件
        $.each(['header', 'mycourse', 'gotop', 'footer'], function (i, m) {
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