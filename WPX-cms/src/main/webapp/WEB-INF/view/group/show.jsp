<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<jsp:include page="../header.jsp"></jsp:include>

<div class="content">
    <a type="button" class="btn btn-default" href="<%=basePath%>/platform/group/addJsp">
        <i class="glyphicon glyphicon-plus"></i> 新增群组
    </a>
    <hr>
    <table class="table table-bordered data-table">
        <thead>
        <tr>
            <th>序号</th>
            <th>群组名</th>
            <th>简介</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${groups}" var="group" varStatus="s">
            <tr>
                <td>#${ s.index + 1}</td>
                <td>${group.gname}</td>
                <td>
                    <div title="${group.gdesc}" class="pro-desc">
                        <nobr>${group.gdesc}</nobr>
                    </div>
                </td>
                <td>
                        <%--<button class="btn btn-xs btn-primary"--%>
                        <%--href="<%=basePath%>/platform/group/editJsp?id=${group.id}"--%>
                        <%--data-toggle="modal" data-target="#Modal${group.id}">编辑--%>
                        <%--</button>--%>
                        <%--<div class="modal fade" id="Modal${group.id}" tabindex="-1" role="dialog"--%>
                        <%--aria-labelledby="myModalLabel">--%>
                        <%--<div class="modal-dialog" role="document">--%>
                        <%--<div class="modal-content">--%>
                        <%--<div class="loading"><img src="<%=basePath%>/img/loading.gif"></div>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                    <button data-id="${group.id}" data-loading-text="删除中..." class="delete btn btn-xs btn-danger">解散
                    </button>

                    <button class="btn btn-xs btn-info userView" data-loading-text="打开中..." data-id="${group.id}">查看成员
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${empty groups}">
        <div class="no-content">无内容</div>
    </c:if>
    <div class="page">
        <div class="right">
            <c:if test="${prepage>0}">
                <button onclick="javascript:window.location.href='<%=basePath%>/platform/group?startPage=${prepage}'"
                        id="btn-pre" class="btn btn-mini">上一页
                </button>
            </c:if>
            <c:if test="${not empty groups}">
                <button onclick="javascript:window.location.href='<%=basePath%>/platform/group?startPage=${nextpage}'"
                        id="btn-next" class="btn btn-mini btn-inverse">下一页
                </button>
            </c:if>
        </div>
    </div>
    <div class="clear"></div>
</div>
<script>
    $(function () {
        $("#menu li:eq(3)").addClass("active");
        $(".delete").click(function () {
            var deleteBtn = $(this);
            var $btn = deleteBtn.button('loading');
            $.ajax({
                url: "<%=basePath%>/platform/group/delete?id=" + $(this).data("id"),
                success: function (data) {
                    if (data.success) {
                        showTip(data.msg, "success");
                        setTimeout(function () {
                            location.reload();
                        }, 2000);
                    } else {
                        showTip(data.msg, "failure");
                    }
                    $btn.button('reset');
                }
            });

        });
        $(".userView").click(function () {
            var $btn = $(this).button('loading');
            $('#Modal').modal('show');
            $.ajax({
                url: "<%=basePath%>/platform/group/viewuser?groupid=" + $(this).data("id"),
                success: function (data) {
                    if (data.success) {
                        $('#Modal').on('shown.bs.modal', function () {
                            var modal = $(this);
                            var modalContent = '<div class="modal-header">' +
                                    '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span' +
                                    'aria-hidden="true">&times;</span></button>' +
                                    '<h4 class="modal-title" id="myModalLabel">' +
                                    '学习小组组员' +
                                    '</h4>' +
                                    '<h6>' +
                                    '组员都在这，看看吧。' +
                                    '</h6>' +
                                    '</div><div style="margin:20px">';
                            for (var i = 0; i < data.obj.length; i++) {
                                modalContent = modalContent + "<label class=\"label label-success\" style='margin-left: 20px'>" + data.obj[i].username + "</label>";
                            }
                            modalContent = modalContent + '</div><div class="modal-footer">' +
                            ' <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>' +
                            ' </div>';
                            modal.find('.modal-content').html(modalContent);

                        })
                    } else {
                        showTip("后台连不上了，程序猿走神了！")
                    }
                    $btn.button('reset');
                }
            });
        });
    });
</script>
<!--bottom-->
<jsp:include page="../bottom.jsp"></jsp:include>
<!--bottom-->
