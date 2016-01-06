<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path ;
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description"
          content="微培训平台，基于微信企业号，腾讯微信官方推荐企业号第三方应用第一品牌，专注为中小企业提供微信办公云服务，帮助企业向移动互联转型。企业可以通过微培训云平台实现人力资源管理的重要一环，培训管理。">
    <meta name="keywords" content="微信企业号,微培训平台,人力资源,培训,微信办公平台,企业号第三方应用,微信企业号第三方,企业号服务商">
    <title>微培训</title>
    <link href="<%=basePath%>/css/pc/index.css" rel="stylesheet" media="screen" type="text/css">
    <link rel="icon" href="<%=basePath%>/image/pc/favicon.ico">
    <script charset="utf-8" src="js/pc/v.js"></script>
    <script language="javascript" type="text/javascript"
            src="<%=basePath%>/js/lib/jQuery-2.1.4.min.js"></script>
    <script language="javascript" type="text/javascript"
            src="<%=basePath%>/js/lib/bootstrap.min.js"></script>
    <script language="javascript" type="text/javascript"
            src="<%=basePath%>/js/pc/common.js"></script>
    <%--<script src="<%=basePath%>/js/pc/h.js" type="text/javascript"></script>--%>
    <style>
        .en-markup-crop-options div div:first-of-type {
            margin-left: 0px !important;
        }
    </style>
</head>
<body>

<div id="header">
    <div style="height: 5px;background-color: green"></div>
    <div class="navbar w960">
        <a title="微信企业培训应用，首选微培训云平台" href="/"
           class="logo"><img src="image/pc/logo.png" style="height: 64px" alt="微培训logo"></a>
        <a href="https://qy.weixin.qq.com/cgi-bin/3rd_info?action=getinfo&t=3rd_isp_cert&uin=566001800" target="_blank"
           style="position: absolute;left: 180px;top: 13px;"><img src="image/pc/qiyehaolink.png"
                                                                  alt="企业号登录"></a>
        <ul id="nav">
            <li class="active"><a href="/">首页</a></li>
            <li><a href="<%=basePath%>/app/weipeixun">应用中心</a></li>
            <%--<li><a href="<%=basePath%>/app/weipeixun" class="kt">免费开通</a></li>--%>
            <li class="login" style="margin-left:10px"><a href="http://www.wexue.top:8080/WPX-cms/" target="_blank" rel="nofollow">管理平台</a>
            </li>
        </ul>
    </div>
</div>
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class=""></li>
        <li data-target="#carousel-example-generic" data-slide-to="1" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2" class=""></li>
        <li data-target="#carousel-example-generic" data-slide-to="3" class=""></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item">
            <img alt="微信企业号" src="image/pc/banner.jpg">

            <div style="display:none">
                <h1>我们是<strong>微信企业号</strong>第一批优秀应用合作商</h1>

                <p><em>微培训</em>让企业用户仅需要扫描二维码即可将应用“安装”到微信上，从而实现随时随地通过微信办公</p></div>
            <div class="carousel-caption"></div>
            <a class="click_here" href="">免费开通</a>
        </div>
        <div class="item active">
            <a href="" target="_blank"><img alt="微培训云平台"
                                                                       src="image/pc/banner0.jpg"></a>

            <div style="display:none"><h3><em>为场景而生</em></h3>

                <p><em>微信企业号</em>应用平台，首选微培训云平台产品</p>

                <p>根据企业使用场景，分为移动沟通协作、移动销售管理、移动信息采集和社会化应用四大场景，满足企业使用微信企业号时 的个性化需求</p></div>
            <div class="carousel-caption">
            </div>

        </div>
        <div class="item">
            <img alt="微培训云平台" src="image/pc/banner1.jpg">

            <div style="display:none"><h3><em>为场景而生</em></h3>

                <p><em>微信企业号</em>应用平台，首选微培训云平台产品</p>

                <p>根据企业使用场景，分为移动沟通协作、移动销售管理、移动信息采集和社会化应用四大场景，满足企业使用微信企业号时 的个性化需求</p></div>
            <div class="carousel-caption">
            </div>
            <a class="click_here two " href="javascript:alert('开通')">免费开通</a>

        </div>
        <div class="item">
            <a href="/" target="_blank"><img alt="微培训云平台"
                                                                           src="image/pc/banner2.jpg"
                                                                           style="width:100%;"></a>

            <div class="carousel-caption">
            </div>
        </div>
    </div>
</div>

<div class="content">
    <div class="tit">
        <h3>无需安装APP，在微信上就能移动办公的平台</h3>

        <div class="lead">重新定义企业内部工作流程和运营管理方式，创造性解决企业内部消息通知不及时、消息通知（短彩信）成本高、<br>
            工作审批严重滞后、客户管理不规范、运营管理方式落后等传统难题。
        </div>
    </div>
    <div class="hidden center">
        <img alt="无需安装APP" style="width: 919px; height: 147px;" src="image/pc/c11.png">
    </div>
    <div class="tit">
        <h3>“为场景而生”——专为中小企业移动办公量身定制</h3>

        <div class="lead">四大移动办公场景，完美解决中小企业80%的工作和管理问题；<br>
            包括信息沟通、员工建设、沟通协作、客户管理（CRM）、运营管理（采购管理、销售汇报、店面巡检、问卷调查……）等。
        </div>
    </div>
    <div class="hidden">
        <a href="/" class="index_pic">
            <div class=""><img alt="信息沟通场景" style="width: 238px; height: 250px;"
                               src="image/pc/c4.jpg"></div>
            <div class="index_bgcolor1">
                <img alt="信息沟通场景" style="width: 238px; height: 250px;"
                     src="image/pc/c4.jpg">
                <span>信息沟通场景</span>
            </div>
        </a>
        <a href="/" class="index_pic sp">
            <div class=""><img alt="工作协同场景" style="width: 238px; height: 250px;"
                               src="image/pc/c1.jpg"></div>
            <div class="index_bgcolor2">
                <img alt="工作协同场景" style="width: 238px; height: 250px;"
                     src="image/pc/c1.jpg">
                <span>工作协同场景</span></div>
        </a>
        <a href="/" class="index_pic">
            <div class=""><img alt="销售管理场景" style="width: 238px; height: 250px;"
                               src="image/pc/c5.jpg"></div>
            <div class="index_bgcolor3">
                <img alt="销售管理场景" style="width: 238px; height: 250px;"
                     src="image/pc/c5.jpg">
                <span>销售管理场景</span></div>
        </a>
        <a href="/" class="index_pic sp">
            <div class=""><img alt="运营管理场景" style="width: 238px; height: 250px;"
                               src="image/pc/c7.jpg"></div>
            <div class="index_bgcolor4">
                <img alt="运营管理场景" style="width: 238px; height: 250px;"
                     src="image/pc/c7.jpg">
                <span>运营管理场景</span></div>
        </a>
    </div>


    <div class="tit">
        <h3 class="mt50">19个免费基础应用，丰富、实用、易用</h3>

        <div class="lead">围绕四大场景，打造专业、实用、易用的免费基础应用，<br>
            让企业轻松管理员工、发布新闻、审批工作、客户管理以及运营管理等，帮助提升企业工作效率和管理效率。
        </div>
    </div>
    <ul class="index_icon hidden">
        <a class="center" href="pc/app.html"><p><img alt="通讯录"
                                                     style="width: 80px; height: 80px;"
                                                     src="image/pc/address.png">
        </p>
            <span>通讯录</span>

            <p>快速联系、跨部门沟通</p></a>
        <a class="center" href="pc/app.html"><p><img alt="新闻公告"
                                                     style="width: 80px; height: 80px;"
                                                     src="image/pc/icon_11.png">
        </p>
            <span>新闻公告</span>

            <p>消息通知、生日提醒</p></a>
        <a class="center" href="pc/app.html"><p><img alt="会议助手"
                                                     style="width: 80px; height: 80px;"
                                                     src="image/pc/icon_4.png">
        </p>
            <span>会议助手</span>

            <p>会议室预订、会议纪要</p></a>
        <a class="center" href="pc/app.html"><p><img alt="审批请示"
                                                     style="width: 80px; height: 80px;"
                                                     src="image/pc/icon_18.png">
        </p>
            <span>审批请示</span>

            <p>领导审批、工作流转</p></a>
        <a class="center" href="pc/app.html"><p><img alt="任务分派" style="width: 80px; height: 80px;"
                                                     src="image/pc/icon_6.png">
        </p>
            <span>任务分派</span>

            <p>工作协同、任务安排</p></a>
        <a class="center" href="pc/app.html"><p><img alt="请假出差" style="width: 80px; height: 80px;"
                                                     src="image/pc/icon_17.png">
        </p>
            <span>请假出差</span>

            <p>请假申请、出差申请</p></a>
        <a class="center" href="pc/app.html"><p><img alt="微信考勤"
                                                     style="width: 80px; height: 80px;"
                                                     src="image/pc/icon_20.png">
        </p>
            <span>考勤打卡</span>

            <p>轻松签到、快乐签退</p></a>
        <a class="center" href="pc/app.html"><p><img alt="移动外勤"
                                                     style="width: 80px; height: 80px;"
                                                     src="image/pc/icon_13.png">
        </p>
            <span>移动外勤</span>

            <p>考勤签到，拜访记录</p></a>
        <a class="center" href="pc/app.html"><p><img alt="移动CRM"
                                                     style="width: 80px; height: 80px;"
                                                     src="image/pc/icon_12.png">
        </p>
            <span>移动CRM</span>

            <p>客户管理，商机管理</p></a>
        <a class="center" href="pc/app.html"><p><img alt="知识百科"
                                                     style="width: 80px; height: 80px;"
                                                     src="image/pc/icon_2.png">
        </p>
            <span>知识百科</span>

            <p>规章制度、产品知识</p></a>
        <a class="center" href="pc/app.html"><p><img alt="超级表单"
                                                     style="width: 80px; height: 80px;"
                                                     src="image/pc/icon_3.png">
        </p>
            <span>超级表单</span>

            <p>重新定义运营管理方式</p></a>

        <a href="pc/app.html" class="index_icon_more">更多...</a>

    </ul>
    <div class="tit" style="margin-top:70px;">
        <h3 class="mt50">4大产品套件“一键授权安装”</h3>

        <div class="lead">已拥有微信企业号的企业用户，可点击以下套件安装，快速接入微培训云平台</div>
    </div>
    <div class="hidden">
        <a href="/" class="index_suite_icon sp"><img alt="微信办公" style="width: 232px;"
                                                                                        src="image/pc/suite1.png"></a>
        <a href="/" class="index_suite_icon"><img alt="销售管理"
                                                                                             style="width: 232px;"
                                                                                             src="image/pc/suite2.png"></a>
        <a href="/" class="index_suite_icon"><img alt="企业文化"
                                                                                          style="width: 232px;"
                                                                                          src="image/pc/suite3.png"></a>
        <a href="/" class="index_suite_icon"><img alt="工作协同" style="width: 232px;"
                                                                                       src="image/pc/suite4.png"></a>
        <!--   <a href="/suite/resource_suite" class="index_suite_icon"><img data-src="/templets/default/images/suite5.png" alt="资源管理" style="width:184px;height:156px;"></a> -->
    </div>
    <div class="tit">
        <h3 class="mt50">没有微信企业号？也可免费体验</h3>
        <!-- <a class="lead" href="https://qy.weixin.qq.com/" target="_blank" rel="nofollow">什么是微信企业号？</a> -->
    </div>
    <div class="hidden">
        <a href="/" class="index_Btn">免费体验通道</a>
    </div>

    <div class="tit">
        <h3 class="mt50">精彩赏析 / 典型案例、用户感言</h3>

        <div class="lead">数十个行业真实案例，总有一个值得你学习和借鉴的。</div>
    </div>
    <div class="hidden">
        <a href="/" class="index_case"><img width="100%" height="115"
                                                                                        style="height: 115px; width: 232px;"
                                                                                        src="image/pc/1-15050R04331404.jpg">

            <div class="index_case_text">
                <h3>长江商学院广东校友会</h3>

                <p class="c999">分类：教育行业<br>
                    <span class="ellipsis">用途：校友信息三步走，共享互助微信有</span></p>
            </div>
        </a>
        <a href="/" class="index_case"><img width="100%" height="115"
                                                                                        style="height: 115px; width: 232px;"
                                                                                        src="image/pc/1-1505111H920437.jpg">

            <div class="index_case_text">
                <h3>华昌化工</h3>

                <p class="c999">分类：制造业<br>
                    <span class="ellipsis">用途：产品资料手机带，领导微信审批快</span></p>
            </div>
        </a>
        <a href="/" class="index_case"><img width="100%" height="115"
                                                                                        style="height: 115px; width: 232px;"
                                                                                        src="image/pc/1-1505111H354444.jpg">

            <div class="index_case_text">
                <h3>顺丰速运</h3>

                <p class="c999">分类：贸易流通<br>
                    <span class="ellipsis">用途：申请审批车辆预定，找人发推都用微信</span></p>
            </div>
        </a>
        <a href="/" class="index_case sp"><img width="100%" height="115"
                                                                                           style="height: 115px; width: 232px;"
                                                                                           src="image/pc/1-15050R0451H08.jpg">

            <div class="index_case_text">
                <h3>广州移动工作台</h3>

                <p class="c999">分类：IT行业<br>
                    <span class="ellipsis">用途：内部工单手机批，4G办理用微信</span></p>
            </div>
        </a>

    </div>
</div>
<div class="solid">他们都在用微培训</div>
<div class="hidden content link_wrap">
    <!-- 2015-8-7 -->
    <div class="add-company-bg">
        <a class="acb1"></a>
        <a class="acb2"></a>
        <a class="acb3"></a>
        <a class="acb4"></a>
        <a class="acb5"></a>
        <a class="acb6"></a>
        <a class="acb7"></a>
        <a class="acb8"></a>
        <a class="acb9"></a>
        <a class="acb10"></a>
    </div>
</div>
<div id="footer">
    <div class="footer w960">
        <div class="footerwrap">
            <dl class="first">
                <dt>产品</dt>
                <dd><a href="/pc/app.html">应用中心</a></dd>
            </dl>
            <dl>
                <dt>开通</dt>
                <dd><a href="#">免费安装</a></dd>
                <dd><a href="#">体验通道</a></dd>
            </dl>
            <dl>
                <dt>行业</dt>
                <dd><a href="/">案例赏析</a></dd>
            </dl>
            <dl>
                <dt>关于</dt>
                <dd><a href="/">关于我们</a></dd>
            </dl>
            <dl>
                <dt><a style="color: #cbcbcb;font-size: 14px;line-height: inherit;" href="http://wbg.do1.com.cn/Flink/"
                       target="_blank">友链</a></dt>
                <dd><a href="www.baidu.com" target="_blank" rel="nofollow">百度</a></dd>
                <dd><a href="www.qy.weixin.qq.com" target="_blank" rel="nofollow">微信企业号</a></dd>
            </dl>
        </div>
        <div class="footerwrap">
            <div class="footer_text">
                <p class="ml0">咨询热线：400-111-2626&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;公司总部：北京<a
                        href="http://www.weibo.com/u/5247508297?topnav=1&wvr=6&topsug=1" class="weibo" target="_blank" rel="nofollow">关注微博</a><br>Copyright
                    © 2014-2015 DO1.com.cn All Rights Reserved 粤B2-20062018号&nbsp;&nbsp;|&nbsp;&nbsp;北京先睿思能科技发展有限公司&nbsp;&nbsp;技术支持
                    <span style="" id="cnzz_stat_icon_1254638826"><a
                            href="http://www.cnzz.com/stat/website.php?web_id=1254638826" target="_blank" title="站长统计">
                        <img border="0"
                             hspace="0"
                             vspace="0"
                             src="image/pc/pic.gif"></a></span>
                </p>

            </div>
        </div>

    </div>
</div>

<div id="leftToolBar" class="leftToolBar">
    <div id="gongzhonghao_wrap">
        <div id="gongzhonghao" class="tooldiv">
            <img title="微培训云平台微信公众号" alt="微信号" src="image/pc/left_icon01.png"
                 class="k_png">

            <p>微信号</p>

            <div style="display: none;" class="qr_tooldiv"><img alt="微培训云平台公众号"
                                                                src="image/pc/admin_qrcode.png">

                <p>微信扫码，获取产品帮助</p>
            </div>
        </div>
    </div>
</div>

</body>
</html>