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
<%--<script src="<%=basePath%>/js/jquery.min.js"></script>--%>
<%--<script src="<%=basePath%>/js/jquery.easyui.min.js"></script>--%>
<%--<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/easyui.css">--%>
<!--menu-->
<%--<link href="<%=basePath%>/css/admin_style.css" rel="stylesheet"/>--%>
<!--content-->
<div id="content">
    <!--breadcrumbs-->
    <div id="content-header">
        <div id="breadcrumb"><a href="platform.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
            <a href="<%=basePath%>/platform/project" title="Go to project" class="tip-bottom">Project</a>
            <a href="#" title="Go to project" class="tip-bottom">Add</a>
        </div>
    </div>
    <!--End-breadcrumbs-->

    <!--Action boxes-->
    <div class="container-fluid">
        <div class="quick-actions_homepage">
            <div class="widget-box">
                <div class="widget-title"><span class="icon"> <i class="icon-align-justify"></i> </span>
                    <h5>新增用户</h5>
                </div>
                <div class="widget-content nopadding">
                    <form action="<%=basePath%>/platform/addressbook/add" method="post" class="form-horizontal">
                        <input name="departmentid" value="${partyId}" class="hidden">
                        <div class="control-group" id="userid-group">
                            <label class="control-label">账号 :</label>

                            <div class="controls">
                                <input type="text" name="userid" required class="span8"/>
                                <span id="userid-state" class="help-inline"></span>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">姓名 :</label>

                            <div class="controls">
                                <input type="text" name="name" required class="span8"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">性别</label>

                            <div class="controls">
                                <label>
                                    <input type="radio" name="gender" value="1" checked/>
                                    男</label>
                                <label>
                                    <input type="radio" name="gender" value="2"/>
                                    女</label>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">微信号 :</label>

                            <div class="controls">
                                <input type="text" name="weixinid" required class="span8"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">手机号 :</label>

                            <div class="controls">
                                <input type="text" name="mobile" id="number_validate" required class="span8" placeholder="手机号码"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">邮箱 :</label>

                            <div class="controls">
                                <input type="text" class="span8" name="email" placeholder="邮箱"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">地址 :</label>

                            <div class="controls">
                                <input type="text" class="span8" name="position" placeholder="地址"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="form-actions">
                                <button type="submit" class="btn btn-success">Save</button>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
            <!--End-Action boxes-->

        </div>
    </div>
    <script>
        $(function () {
            $("#iconUp").click(function () {
                ajaxFileUpload();
            });
            $("input[name=userid]").change(function () {
                isUserId();
            });
        });
        function isUserId() {
            var userid = $("input[name=userid]").val();
            var url = "<%=basePath%>/platform/addressbook/isUserId?userId=" + userid;
            var success = function (data) {
                if (data.success) {
                    $("#userid-group").addClass("error")
                    $("#userid-state").html('<font color=red>' + userid + '账户已经存在</font>');
                } else {
                    $("#userid-state").html('<font color=green>' + userid + '账户可用</font>');
                }
            }
            $.ajax({
                url: url,
                type: "get",
                success: success
            });
        }
    </script>
    <!--content-->

    <!--bottom-->
    <jsp:include page="../bottom.jsp"></jsp:include>
    <!--bottom-->