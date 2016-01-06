<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>培训课程-公开课</title>
    <meta name="description" content="天天微学-培训课程-公开课">
    <meta name="keywords" content="天天微学-培训课程-公开课">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="<%=basePath%>/common/bootstrap-3.3.5-dist/css/bootstrap.min.css"/>
    <script src="<%=basePath%>/js/lib/jQuery-2.1.4.min.js"></script>
    <script src="<%=basePath%>/common/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
    <style>
        body {
            text-align: center;
            background-color: #ffffff;
        }

        .header {
            padding-top: 30px;
            height: 170px;
            margin-bottom: 10px;
            background: url("<%=basePath%>/image/weixin/kcpx_bg.png") repeat-x;
        }

        .username {
            margin-top: 10px;
            color: white;
        }

        img {
            width: 100%;
        }

        .img {

            border: solid 5px white;
        }

        .CopyRight {
            color: gray;
            font-size: 12px;
        }

        .course-name {
            color: #1a1917;
            font-size: 14px;
            text-align: left;
        }

        .course-desc {
            color: blue;
            font-size: 12px;
            text-align: left;
        }
        html * {
            -webkit-font-smoothing: antialiased;
            font-family: "Lantinghei SC", "Open Sans", Arial, "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", "STHeiti", "WenQuanYi Micro Hei", SimSun, sans-serif;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row header">
        <div class="img-circle col-xs-4 col-xs-offset-4">
            <img class="img-circle img" src="${user.avatar}"/>

            <div class="username">${user.name}</div>
        </div>
    </div>
    <div class="row">
        <ol class="breadcrumb col-xs-4">
            <li><a href="#">最新课程</a></li>
        </ol>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <table class="table table-striped ">
                <tr>
                    <td class="col-xs-3"><img class="img-thumbnail" src="${user.avatar}"/></td>
                    <td class="col-xs-8">
                        <div class="course-name">培训培训管理者TTM-《培训项目设计与管理》</div>
                        <div class="course-desc">
                            开课时间：2015-12-13 09:00 </br>
                            课程时长：7小时</br>
                            开课地点：北京 </br>
                            授课讲师：张璐 </br>
                            所属项目：培训发展</br>
                        </div>
                    </td>
                    <td class="col-xs-1">
                        <button type="button" class="btn btn-success">预约</button>
                    </td>
                </tr>
                <tr>
                    <td class="col-xs-3"><img class="img-thumbnail" src="${user.avatar}"/></td>
                    <td class="col-xs-8">
                        <div class="course-name">培训培训管理者TTM-《培训项目设计与管理》</div>
                        <div class="course-desc">
                            开课时间：2015-12-13 09:00 </br>
                            课程时长：7小时</br>
                            开课地点：北京 </br>
                            授课讲师：张璐 </br>
                            所属项目：培训发展</br>
                        </div>
                    </td>
                    <td class="col-xs-1">
                        <button type="button" class="btn btn-success">预约</button>
                    </td>
                </tr>
                <tr>
                    <td class="col-xs-3"><img class="img-thumbnail" src="${user.avatar}"/></td>
                    <td class="col-xs-8">
                        <div class="course-name">培训培训管理者TTM-《培训项目设计与管理》</div>
                        <div class="course-desc">
                            开课时间：2015-12-13 09:00 </br>
                            课程时长：7小时</br>
                            开课地点：北京 </br>
                            授课讲师：张璐 </br>
                            所属项目：培训发展</br>
                        </div>
                    </td>
                    <td class="col-xs-1">
                        <button type="button" class="btn btn-success">预约</button>
                    </td>
                </tr>
                <tr>
                    <td class="col-xs-3"><img class="img-thumbnail" src="${user.avatar}"/></td>
                    <td class="col-xs-8">
                        <div class="course-name">培训培训管理者TTM-《培训项目设计与管理》</div>
                        <div class="course-desc">
                            开课时间：2015-12-13 09:00 </br>
                            课程时长：7小时</br>
                            开课地点：北京 </br>
                            授课讲师：张璐 </br>
                            所属项目：培训发展</br>
                        </div>
                    </td>
                    <td class="col-xs-1">
                        <button type="button" class="btn btn-success">预约</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="row">
        <ol class="breadcrumb col-xs-4">
            <li><a href="#">所有项目</a></li>
        </ol>
    </div>
    <div class="row">
        <div class="col-xs-6">
            <div class="thumbnail">
                <img src="${user.avatar}" alt="...">

                <div class="caption">
                    <h5>新员工培训</h5>

                    <%--<p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>--%>
                </div>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="thumbnail">
                <img src="${user.avatar}" alt="...">

                <div class="caption">
                    <h5>新员工培训</h5>
                    <%--<p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>--%>
                </div>
            </div>
        </div>

    </div>
    <div class="row">
        <div class="col-xs-6">
            <div class="thumbnail">
                <img src="${user.avatar}" alt="...">

                <div class="caption">
                    <h5>新员工培训</h5>

                    <%--<p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>--%>
                </div>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="thumbnail">
                <img src="${user.avatar}" alt="...">

                <div class="caption">
                    <h5>新员工培训</h5>
                    <%--<p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>--%>
                </div>
            </div>
        </div>

    </div>

    <div class="row CopyRight">
        <div class="col-xs-6 col-xs-offset-3">CopyRight:www.wexue.top</div>
    </div>
</div>

</body>
</html>