<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!--header-->
<jsp:include page="../header.jsp"></jsp:include>

<!--header-->

<!--menu-->
<jsp:include page="../menu.jsp"></jsp:include>
<!--menu-->
<style>

    .well {
        padding-top: 0px;
        padding-left: 15px;
    }
    .courseLogo{
        width: 100px;
    }

    [class*="span"] {
        margin-left: 10px;
    }
</style>
<!--content-->
<div id="content">
    <!--breadcrumbs-->
    <div id="content-header">
        <div id="breadcrumb">
            <a href="<%=basePath%>/platform/index" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
            <a href="<%=basePath%>/platform/project" title="Go to project" class="tip-bottom">Course</a>
        </div>
    </div>
    <!--End-breadcrumbs-->

    <!--Action boxes-->
    <div class="container-fluid">
        <div class="quick-actions_homepage">
            <button type="button" class="btn btn-default"
                    onclick="javascript:window.location.href='<%=basePath%>/platform/course/addJsp?type=1'"><i
                    class="icon-plus"></i> 新增公开课
            </button>
            <button type="button" class="btn btn-default"
                    onclick="javascript:window.location.href='<%=basePath%>/platform/course/addJsp?type=2'"><i
                    class="icon-plus"></i> 新增必修课
            </button>
            <hr>
        </div>

        <!--End-Action boxes-->
        <div>
            <span class="label label-important">项目：</span>
            <select id="selectProject">
                <c:forEach items="${projects}" var="pro">
                    <option value="${pro.id}">${pro.proname}</option>
                </c:forEach>
            </select>


            <!-- Tab panes -->
            <div class="widget-box">
                <div class="widget-title"><span class="icon"> <i class="icon-align-justify"></i> </span>
                    <h5>所属课程</h5>
                </div>
                <div class="widget-content nopadding">
                    <div class="todo">
                        <ul id="courses">
                            <li class="clearfix">
                                <div class="span1.5">
                                    <img class="courseLogo span1" src="<%=basePath%>/img/cms/course_default_logo.png"/>
                                </div>
                                <div class="span7"><h4>Luanch This theme on Themeforest <span
                                        class="date badge badge-success">公开课</span></h4>

                                    <div class="row-fluid">
                                        <dl class="span3">
                                            <dt>主讲人：</dt>
                                            <dt>负责人：</dt>
                                            <dt>参与人数：</dt>
                                        </dl>
                                        <dl class="span3">
                                            <dt>课程时间：</dt>
                                            <dt>上课地点：</dt>
                                        </dl>
                                    </div>
                                    <div class="row-fluid">
                                        课程简介：
                                    </div>
                                </div>
                                <div class="span2">
                                    <button class="btn btn-success">编辑</button>
                                    <button class="btn btn-danger">删除</button>
                                </div>
                            </li>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#selectProject").change(function () {
            var proid = $("#selectProject").val();
            showCourse(proid);
        });
        var id = $("#selectProject").val();
        showCourse(id);
    });


    function showCourse(proid) {
        var course = "";
        $.ajax({
            url: "<%=basePath%>/platform/course/courseByproId?proid=" + proid,
            type: "get",
            success: function (data) {
                if (data.success) {
                    var objs = data.obj;
                    for (var i = 0; i < data.obj.length; i++) {

                        course = course + '  <li class="clearfix"><div class="span1.5">'
                        + ' <img class="span1 courseLogo" src="' + objs[i].icon + '"/>'
                        + ' </div>'
                        + '<div class="span7"><h4>' + objs[i].coursename + '<span'
                        + 'class="date badge badge-success">公开课</span></h4>'
                        + '<div class="row-fluid">'
                        + '<dl class="span5">'
                        + '<dt>主讲人：';
                        for (var a = 0; a < objs[i].zhujiangren.length; a++) {
                            course = course + objs[i].zhujiangren[a] + ";";
                        }
                        course = course + '</dt><dt>负责人：';
                        for (var b = 0; b < objs[i].fuzeren.length; b++) {
                            course = course + objs[i].fuzeren[b] + ";";
                        }
                        course = course + '</dt><dt>参与人数：';
                        for (var c = 0; b < objs[i].canyuren.length; c++) {
                            course = course + objs[i].canyuren[c] + ";";
                        }
                        course = course + '</dt>'
                        + '<dt>参与人数：' + objs[i].canyuren + '</dt>'
                        + '</dl>'
                        + '<dl class="span5">'
                        + '<dt>课程时间：' + objs[i].starttime + '-' + objs[i].endtime + '</dt>'
                        + '<dt>上课地点：' + objs[i].place + '</dt>'
                        + ' </dl>'
                        + '</div> <div class="row-fluid">课程简介：' + objs[i].coursedesc
                        + '</div></div>'
                        + '<div class="span2">'
                        + '<button class="btn btn-success">编辑</button>'
                        + '<button class="btn btn-danger">删除</button>'
                        + '</div></li>';
                    }
                    $("#courses").html(course);
                }
            }

        });

    }

</script>
<!--content-->

<!--bottom-->
<jsp:include page="../bottom.jsp"></jsp:include>
<!--bottom-->
