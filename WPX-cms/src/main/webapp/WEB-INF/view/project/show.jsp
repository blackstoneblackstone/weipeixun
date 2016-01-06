<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  isELIgnored="false" %>
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

<!--content-->
<div id="content">
    <!--breadcrumbs-->
    <div id="content-header">
        <div id="breadcrumb">
            <a href="<%=basePath%>/platform/index" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
            <a href="<%=basePath%>/platform/project" title="Go to project" class="tip-bottom">Project</a>
        </div>
    </div>
    <!--End-breadcrumbs-->

    <!--Action boxes-->
    <div class="container-fluid">
        <div class="quick-actions_homepage">
            <ul class="quick-actions">
                <li class="bg_lb"><a href="<%=basePath%>/platform/project/addJsp"> <i class="icon-plus"></i> 新增项目 </a>
                </li>
            </ul>
        </div>
        <!--End-Action boxes-->
        <div class="widget-box">
            <div class="widget-title"><span class="icon"> <i class="icon-file"></i> </span>
                <h5>项目</h5>
            </div>
            <div class="widget-content nopadding">
                <ul class="recent-posts">
                    <c:forEach items="${projects}" var="project" varStatus="s">
                        <li>
                            <div class="user-thumb" width="40" height="40"></div>
                            <div class="article-post">
                                <div class="fr"><a href="<%=basePath%>/platform/project/editJsp?id=${project.id}" class="btn btn-primary btn-mini">Edit</a>
                                    <a href="<%=basePath%>/platform/project/delete?id=${project.id}" class="btn btn-danger btn-mini">Delete</a></div>
                                <span class="user-info">${project.proname} </span>

                                <p>${project.prodesc}</p>
                            </div>
                        </li>
                    </c:forEach>
                    <li>
                        <button class="btn btn-warning btn-mini">View All</button>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>


<!--content-->

<!--bottom-->
<jsp:include page="../bottom.jsp"></jsp:include>
<!--bottom-->
