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
<!--content-->
<div class="content">
    <!--Action boxes-->
    <button type="button" class="btn btn-default" href="<%=basePath%>/platform/course/addJsp?type=1"
            data-toggle="modal" data-target="#Modal">
        <i class="glyphicon glyphicon-plus"></i> 新增公开课
    </button>
    <button type="button" class="btn btn-primary" href="<%=basePath%>/platform/course/addJsp?type=2"
            data-toggle="modal" data-target="#Modal">
        <i class="glyphicon glyphicon-plus"></i> 新增必修课
    </button>
    <hr>
    <div class="widget-box">
        <table class="table table-bordered data-table">
            <thead>
            <tr>
                <th>序号</th>
                <th>背景图</th>
                <th>名称</th>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>参加人数</th>
                <th>类型</th>
                <th>教学元素</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${courses}" var="course" varStatus="s">
                <tr>
                    <td>#${ s.index + 1}</td>
                    <td><img src="${course.icon}" class="cou-bg"></td>
                    <td>
                        <div class="tool" data-toggle="tooltip" data-placement="right"
                             data-title="${course.coursedesc}">${course.coursename}</div>
                    </td>
                    <td>${course.starttime}</td>
                    <td>${course.endtime}</td>
                    <td>${course.factperson}</td>
                    <td>
                        <c:if test="${course.coursetype==1}">
                            <span class="label label-success">公开课</span>
                        </c:if>
                        <c:if test="${course.coursetype==2}">
                            <span class="label label-danger">必修课</span>
                        </c:if>
                    </td>

                    <td>
                        <button class="btn btn-xs btn-info tool" title="点击发送图文,查看已发送图文" data-placement="top"
                                href="<%=basePath%>/platform/course/sendResearchJsp?id=${course.id}"
                                data-toggle="modal" data-target="#Modal">调研测试
                        </button>
                        <button class="btn btn-xs btn-warning tool"
                                title="新建学习小组" data-placement="top"
                                href="<%=basePath%>/platform/course/createStudyGroupJsp?id=${course.id}"
                                data-toggle="modal" data-target="#Modal">学习小组
                        </button>
                    </td>
                    <td>
                        <button class="btn btn-xs btn-primary"
                                href="<%=basePath%>/platform/course/editJsp?id=${course.id}"
                                data-toggle="modal" data-target="#Modal${course.id}">编辑
                        </button>
                        <div class="modal fade" id="Modal${course.id}" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="loading"><img src="<%=basePath%>/img/loading.gif"></div>
                                </div>
                            </div>
                        </div>
                        <button data-id="${course.id}" data-loading-text="删除中..." class="delete btn btn-xs btn-danger">
                            删除
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${empty courses}">
            <div class="no-content">无内容</div>
        </c:if>
    </div>

    <div class="page">
        <div class="right">
            <c:if test="${prepage>0}">
                <button onclick="javascript:window.location.href='<%=basePath%>/platform/course?startPage=${prepage}&projectid=${projectid}'"
                        id="btn-pre" class="btn btn-mini">上一页
                </button>
            </c:if>
            <c:if test="${!empty courses}">
                <button onclick="javascript:window.location.href='<%=basePath%>/platform/course?startPage=${nextpage}&projectid=${projectid}'"
                        id="btn-next" class="btn btn-mini btn-inverse">下一页
                </button>
            </c:if>
        </div>
    </div>

    <div class="clear"></div>
</div>
</div>
<!--content-->
<script>
    $(function () {
        $("#menu li:eq(2)").addClass("active");
        $(".delete").click(function () {
            var deleteBtn = $(this);
            var $btn = deleteBtn.button('loading');
            $.ajax({
                url: "<%=basePath%>/platform/course/delete?id=" + $(this).data("id"),
                success: function (data) {
                    location.reload();
                }
            });

        });
    });
</script>

<!--bottom-->
<jsp:include page="../bottom.jsp"></jsp:include>
<!--bottom-->
