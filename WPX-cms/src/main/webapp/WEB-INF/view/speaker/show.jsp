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
<div class="content row">
    <div class="col-sm-1 shu">
        <a href="<%=basePath%>/platform/speaker"> <div class="active">讲师库</div></a>
        <a href="<%=basePath%>/platform/addressbook"><div>通讯录</div></a>
    </div>
    <!--Action boxes-->
    <div class="col-sm-11">
        <button type="button" class="btn btn-default" href="<%=basePath%>/platform/speaker/addJsp"
                data-toggle="modal" data-target="#Modal">
            <i class="glyphicon glyphicon-plus"></i> 新增讲师
        </button>
        <hr>
        <table class="table table-bordered data-table">
            <thead>
            <tr>
                <th>序号</th>
                <th>头像</th>
                <th>名字</th>
                <th>简介</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${speakers}" var="speaker" varStatus="s">
                <tr>
                    <td>#${ s.index + 1}</td>
                    <td><img src="${speaker.avator}" class="cou-bg"></td>
                    <td>${speaker.sname}</td>
                    <td>
                        <div title="${speaker.sdesc}" class="pro-desc">
                            <nobr>${speaker.sdesc}</nobr>
                        </div>
                    </td>
                    <td>
                        <button class="btn btn-xs btn-primary"
                                href="<%=basePath%>/platform/speaker/editJsp?id=${speaker.id}"
                                data-toggle="modal" data-target="#Modal${speaker.id}">编辑
                        </button>
                        <div class="modal fade" id="Modal${speaker.id}" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="loading"><img src="<%=basePath%>/img/loading.gif"></div>
                                </div>
                            </div>
                        </div>
                        <button data-id="${speaker.id}" data-loading-text="删除中..."
                                class="delete btn btn-xs btn-danger">删除
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${empty speakers}">
            <div class="no-content">无内容</div>
        </c:if>
        <div class="page">
            <div class="right">
                <c:if test="${prepage>0}">
                    <button onclick="javascript:window.location.href='<%=basePath%>/platform/speaker?startPage=${prepage}'"
                            id="btn-pre" class="btn btn-mini">上一页
                    </button>
                </c:if>
                <c:if test="${not empty speakers}">
                    <button onclick="javascript:window.location.href='<%=basePath%>/platform/speaker?startPage=${nextpage}'"
                            id="btn-next" class="btn btn-mini btn-inverse">下一页
                    </button>
                </c:if>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<script>
    $(function () {
        $("#menu li:eq(5)").addClass("active");
        $("#speaker").text("讲师库");
        $(".delete").click(function () {
            var deleteBtn = $(this);
            var $btn = deleteBtn.button('loading');
            $.ajax({
                url: "<%=basePath%>/platform/speaker/delete?id=" + $(this).data("id"),
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
