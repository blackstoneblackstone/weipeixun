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
                    <c:if test="${type==1}">
                        <h5>新增公开课程</h5>
                    </c:if>
                    <c:if test="${type==2}">
                        <h5>新增必修课程</h5>
                    </c:if>
                </div>
                <div class="widget-content nopadding">
                    <form action="<%=basePath%>/platform/course/add" method="post" class="form-horizontal">
                        <div class="control-group">
                            <label class="control-label">类型 :</label>

                            <div class="controls">
                                <input type="text" name="type" value="${type}" required class="span8"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">项目 :</label>

                            <div class="controls">
                                <select name="projectid">
                                    <c:forEach items="${projects}" var="project" varStatus="s">
                                        <option value="${project.id}">${project.proname}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">课程图标</label>

                            <div class="controls">
                                <input type="file" id="file" name="file"/>
                                <input type="text" id="courseIcon" name="icon" class="hidden"/>
                                <div id="wait_loading" style="padding: 50px 0 0 0;display:none;">
                                    <div style="width: 103px;margin: 0 auto;"><img id="iconImg"
                                                                                   src="<%=basePath%>/img/loading.gif"/>
                                    </div>
                                </div>
                                <input type="button" id="iconUp" class="btn btn-success" value="上传"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">课程名 :</label>

                            <div class="controls">
                                <input type="text" name="name" required class="span8" placeholder="如何写一份漂亮的文案。。。"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">课程简介 :</label>

                            <div class="controls">
                                <textarea class="span8" name="desc" required placeholder="这是干什么的，详细介绍下！"></textarea>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">起止时间 :</label>

                            <div class="controls">
                                <input name="starttime" type="text" data-date-format="yyyy-mm-dd"
                                       class="datepicker span3">到
                                <input name="endtime" type="text" data-date-format="yyyy-mm-dd"
                                       class="datepicker span3">
                                <span class="help-block">课程结束后，将关闭！</span></div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">主讲人 :</label>

                            <div class="controls">
                                <select multiple name="zhujiangren">
                                    <c:forEach items="${users}" var="user" varStatus="s">
                                        <option value="${user.userId}">${user.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">负责人 :</label>

                            <div class="controls">
                                <select multiple name="fuzeren">
                                    <c:forEach items="${users}" var="user" varStatus="s">
                                        <option value="${user.userId}">${user.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">参与人 :</label>

                            <div class="controls">

                                <select multiple name="canyuren">
                                    <c:forEach items="${users}" var="user" varStatus="s">
                                        <option value="${user.userId}">${user.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <c:if test="${type==1}">
                            <div class="control-group">
                                <label class="control-label">预计参与人数 :</label>

                                <div class="controls">
                                    <input type="text" name="expectperson" class="span8" placeholder="有可能有多少人参与课程"/>
                                </div>
                            </div>
                        </c:if>
                            <div class="control-group">
                                <label class="control-label">上课地点 :</label>

                                <div class="controls">
                                    <input type="text" name="place" class="span8" placeholder="在哪上课"/>
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
    <script src="<%=basePath%>/js/ajaxfileupload.js"></script>
    <script>
        $(function () {
            $("#iconUp").click(function () {
                ajaxFileUpload();
            });
        });
        function ajaxFileUpload() {
            // 开始上传文件时显示一个图片
            $("#wait_loading").ajaxStart(function () {
                $(this).show();
                // 文件上传完成将图片隐藏起来
            }).ajaxComplete(function () {
                //$(this).hide();
            });
            var elementIds = ["type"]; //flag为id、name属性名
            $.ajaxFileUpload({
                url: '<%=basePath%>/upLoadImage?type=course',
                type: 'post',
                secureuri: false, //一般设置为false
                fileElementId: 'file', // 上传文件的id、name属性名
                dataType: 'text', //返回值类型，一般设置为json、application/json
                elementIds: elementIds, //传递参数到服务器
                success: function (data, status) {
                    var reg = /<pre.+?>(.+)<\/pre>/g;
                    var result = data.match(reg);
                    datas = $.parseJSON(RegExp.$1);
                    $("#iconImg").attr("src", datas.obj.fileUrl);
                    $("#courseIcon").val(datas.obj.fileUrl);
                },
                error: function (data, status, e) {
                    console.log(data);
                }
            });
            //return false;
        }
    </script>
    <!--content-->

    <!--bottom-->
    <jsp:include page="../bottom.jsp"></jsp:include>
    <!--bottom-->