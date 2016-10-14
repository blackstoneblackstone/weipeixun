<%--
  Created by IntelliJ IDEA.
  User: lihb
  Date: 1/10/16
  Time: 8:38 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<jsp:include page="../header.jsp"></jsp:include>
<style>
    .function-select li {
        cursor: pointer;
        display: inline-flex;
        height: 50px;
        width: 100px;
        padding-top: 20px;
        padding-left: 40px;
    }

    .function-select li:hover {
        background-color: #ccc;

    }

    .home-template li {
        display: inline-block;
        text-align: center;
        background: url('/img/cms/radio_iphone0.png') center no-repeat;
        opacity: 1;
        cursor: pointer;
        margin-right: 10px;
    }

    .home-template > li > img {
        margin-top: 40px;
    }

    .home-template li:hover {
        background: url('/img/cms/radio_iphone.png') center no-repeat;

    }
    .btn-right{
        float: right;
    }

</style>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>/lib/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>/lib/ueditor/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="<%=basePath%>/lib/ueditor/lang/zh-cn/zh-cn.js"></script>


<div class="widget-box">
    <div class="widget-title"><span class="icon"> <i class="icon-list"></i> </span>
        <h5>配置学院门户<span>(只需要三步)</span></h5>
    </div>
    <div class="widget-content">
        <div class="widget-box">
            <div class="widget-title" id="widget_title">
                <h5><code id="title_tep1" class="btn-success">第一步:</code>选择展示模块</h5>
                <h5><code id="title_tep2">第二步:</code>选择模板</h5>
                <h5><code id="title_tep3">第三步:</code>预览提交</h5>
            </div>
            <div class="widget-content" id="home_content">
                <div id="tep1">
                    <ul class="function-select">

                        <li class="">
                            <input type="checkbox" name="functionItem" value="desc">
                            学院介绍
                        </li>

                        <li class="">
                            <input type="checkbox" name="functionItem" value="publicCourse">
                            公开课
                        </li>
                        <li class="">
                            <input type="checkbox" name="functionItem" value="requireCourse">
                            必修课
                        </li>
                        <li class="">

                            <input type="checkbox" name="functionItem" value="teacher">
                            讲师库

                        </li>
                        <li class="">
                            <input type="checkbox" name="functionItem" value="courseNotify">
                            学院公告
                        </li>
                        <li class="">

                            <input type="checkbox" name="functionItem" value="courseCalendar">
                            课程日历
                        </li>

                    </ul>
                    <br style="clear: both">

                    <div style="text-align: center">
                        <button class="btn-success" id="home_next2">下一步</button>
                    </div>
                </div>

                <div id="tep2" class="hidden">
                    <ul class="home-template" id="home_template">
                        <li data-name="tpl1">
                            <img src="/img/template/tpl3.png" style="display: inline;">
                            <br>
                            <label> 模板1 </label>
                        </li>
                        <li data-name="tpl2">
                            <img src="/img/cms/cate1.png" style="display: inline;">
                            <br>
                            <label> 模板2</label>
                        </li>
                    </ul>
                    <br style="clear: both">

                    <div style="text-align: center">
                        <button class="btn-success" id="home_pre2">上一步</button>
                        <button class="btn-success" id="home_next3">下一步</button>
                    </div>
                </div>

                <div id="tep3" class="hidden">
                    <div style="text-align: center;float: left">
                      <h5>下面是Iphone6预览画面</h5>
                    <iframe id="tpl_view" name="tplView" width="375px" height="627px">

                    </iframe>

                    </div>
                    <br style="clear: both">s
                    <div style="text-align: center;float: left">
                        <button class="btn-success" id="home_pre3">上一步</button>
                        <button class="btn-success" id="home_submit">提交</button>
                    </div>

                </div>


            </div>
        </div>
    </div>
</div>

<div class="widget-box">
    <div class="widget-title"><span class="icon"> <i class="icon-list"></i> </span>
        <h5>内容管理<code>2016-01-26创建</code></h5><button id="btn-save" class="btn-right btn-success">保存</button>
    </div>
    <div class="widget-content">
        <div class="widget-box">
            <div class="widget-title">
                <span class="icon"> <i class="icon-home"></i> </span>
                <h5>学院简介 </h5>
            </div>
            <div class="widget-content">
                <script id="DESCeditor" type="text/plain" style="width:1024px;height:500px;"></script>
            </div>
        </div>
        <div class="widget-box">
            <div class="widget-title">
                <span class="icon"> <i class="icon-list"></i> </span>
                <h5>学院公告</h5>
            </div>
            <div class="widget-content">
                <script id="NOTIFYeditor" type="text/plain" style="width:1024px;height:500px;"></script>
            </div>
        </div>
    </div>
</div>
<script>
    var data = {};
    var DESCeditor=UE.getEditor('DESCeditor');
    var NOTIFYeditor=UE.getEditor('NOTIFYeditor');
    $(function () {
        $("#menu li:eq(1)").addClass("active");
//        insertHtml();
        $("#btn-save").click(function(){
            $.ajax({
                type:"post",
                data:{"schooldesc":DESCeditor.getContent(),
                "schoolnotify":NOTIFYeditor.getContent()},
                url:"/platform/portal/save",
                success:function(){
                    alert("保存成功");
                }

            });
        });
        $("#home_pre2").click(function () {
            $("#tep2").addClass("hidden");
            $("#tep1").removeClass("hidden");
        });
        $("#home_pre3").click(function () {
            $("#tep3").addClass("hidden");
            $("#tep2").removeClass("hidden");
        });

        $("#home_next2").click(function () {
            var functions = new Array();
            $("input[name='functionItem']:checked").each(function () {
                functions.push(this.value);
            });
            data.functions=functions;
            $("#tep1").addClass("hidden");
            $("#tep2").removeClass("hidden");
            $("#title_tep1").removeClass("btn-success");
            $("#title_tep2").addClass("btn-success");
        });

        var templates = $("#home_template").find("li");
        templates.click(function () {
            var name = $(this).data("name");
            data.template = name;
            templates.css("background", "url('/img/cms/radio_iphone0.png') center no-repeat");
            $(this).css("background", "url('/img/cms/radio_iphone.png') center no-repeat");
        });

        $("#home_next3").click(function () {
            if (data.template == undefined) {
                alert("请选择模板");
                return;
            }
            $("#title_tep2").removeClass("btn-success");
            $("#title_tep3").addClass("btn-success");
            $("#tep2").addClass("hidden");
            $("#tep3").removeClass("hidden");

            $("#tpl_view").attr("src","/platform/portal/tpl?id="+ data.template+"&json="+JSON.stringify(data));
        });
        $("#home_submit").click(function () {
            var gcksImg=document.getElementById("tpl_view");
            var doc;
            if (document.all) { // IE
                doc = gcksImg.document;
            } else { // 标准
                doc = gcksImg.contentDocument;
            }
            $.ajax({
                type:"post",
                data:{"schoolindex":doc.body.innerHTML},
                url:"/platform/portal/saveindex",
                success:function(){
                    alert("保存成功");
                }
            });
        });
    });
    function insertHtml() {
        DESCeditor.execCommand('insertHtml', '${schooldesc}');
        NOTIFYeditor.execCommand('insertHtml','${schoolnotify}');
    }
</script>