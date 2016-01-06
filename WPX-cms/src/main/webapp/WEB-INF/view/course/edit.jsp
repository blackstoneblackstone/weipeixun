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
            <a href="#" title="Go to project" class="tip-bottom">Edit</a>
        </div>
    </div>
    <!--End-breadcrumbs-->

    <!--Action boxes-->
    <div class="container-fluid">
        <div class="quick-actions_homepage">
            <div class="widget-box">
                <div class="widget-title"><span class="icon"> <i class="icon-align-justify"></i> </span>
                    <h5>编辑项目</h5>
                </div>
                <div class="widget-content nopadding">
                    <form action="<%=basePath%>/platform/project/edit" method="post" class="form-horizontal">
                        <div class="control-group">
                            <label class="control-label">ID :</label>

                            <div class="controls">
                                <input type="text" name="id" value="${project.id}" required class="span8" style="display: none"/>
                                ${project.id}
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">项目名 :</label>

                            <div class="controls">
                                <input type="text" name="name" value="${project.proname}" required class="span8" placeholder="新员工入职培训等。。。"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">项目简介 :</label>

                            <div class="controls">
                                <textarea class="span8" name="desc"  required placeholder="这是干什么的，详细介绍下！">${project.prodesc}</textarea>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">起止时间 :</label>

                            <div class="controls">
                                <input name="starttime" value="${project.starttime}" type="text"  data-date-format="yyyy-mm-dd"
                                       class="datepicker span3">到
                                <input name="endtime" type="text" value="${project.endtime}"  data-date-format="yyyy-mm-dd"
                                       class="datepicker span3">
                                <span class="help-block">项目结束后，将关闭！</span></div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">负责人 :</label>

                            <div class="controls">
                                <select multiple name="fuzeren">
                                    <c:forEach items="${project.leaders}" var="leader" varStatus="s">
                                        <option value="${leader.userId}" selected="selected">${leader.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">参与人 :</label>

                            <div class="controls">

                                <%--<ul id="tt2" class="easyui-combotree" data-options="url:'<%=basePath%>/platform/addressbook/treejson',--%>
                                <%--checkbox:true,--%>
                                <%--method:'get',--%>
                                <%--onClick: function(node){--%>
                                <%--$(this).tree('toggle', node.target);--%>
                                <%--},--%>
                                <%--onContextMenu: function(e,node){--%>
                                <%--e.preventDefault();--%>
                                <%--$(this).tree('select',node.target);--%>
                                <%--$('#mm').menu('show',{--%>
                                <%--left: e.pageX,--%>
                                <%--top: e.pageY--%>
                                <%--});--%>
                                <%--}"--%>
                                <%--></ul>--%>

                                <select multiple name="canyuren">
                                    <c:forEach items="${project.users}" var="user" varStatus="s">
                                        <option value="${user.userId}">${user.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-actions">
                                <button type="submit" class="btn btn-success">Save</button>
                            </div>
                        </div>
                        <div class="control-group">

                        </div>
                    </form>
                </div>

            </div>
            <!--End-Action boxes-->

        </div>
    </div>
    <!--content-->

    <!--bottom-->
    <jsp:include page="../bottom.jsp"></jsp:include>
    <!--bottom-->