<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!--header-->
<jsp:include page="header.jsp"></jsp:include>
<!--header-->

<!--menu-->
<jsp:include page="menu.jsp"></jsp:include>
<!--menu-->

<!--content-->
<div id="content">
    <!--breadcrumbs-->
    <div id="content-header">
        <div id="breadcrumb"><a href="platform.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
        </div>
    </div>
    <!--End-breadcrumbs-->

    <!--Action boxes-->
    <div class="container-fluid">
        <div class="quick-actions_homepage">
            <ul class="quick-actions">
                <li class="bg_lb"><a href="<%=basePath%>/platform/project/addJsp"> <i class="icon-dashboard"></i> 新增项目 </a></li>
                <li class="bg_lg span3"><a href="charts.html"> <i class="icon-signal"></i> 新增课程</a></li>
                <li class="bg_ly"><a href="widgets.html"> <i class="icon-inbox"></i>
                    课前调研</a></li>
                <li class="bg_lo"><a href="tables.html"> <i class="icon-th"></i> 新建小组</a></li>
                <li class="bg_ls"><a href="grid.html"> <i class="icon-fullscreen"></i>签到</a></li>
                </br>]
                <li class="bg_ly"><a href="<%=basePath%>/platform/addressbook"> <i class="icon-inbox"></i>
                    同步通讯录</a></li>

            </ul>
        </div>
        <!--End-Action boxes-->

    </div>
</div>

<!--content-->

<!--bottom-->
<jsp:include page="bottom.jsp"></jsp:include>
<!--bottom-->
