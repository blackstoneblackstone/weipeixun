<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<form action="<%=basePath%>/platform/group/add" method="post" class="form-horizontal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">编辑群组</h4>
    </div>
    <div class="modal-body">
        <div class="form-group">
            <label for="inputName" class="col-sm-3 control-label">ID :</label>

            <div class="col-sm-8">
                <input type="text" name="id" value="${group.id}" required class="form-control" style="display: none"/>
                ${group.id}
            </div>
        </div>
        <div class="form-group">
            <label for="inputName" class="col-sm-3 control-label">群名 :</label>

            <div class="col-sm-8">
                <input class="form-control" type="text" name="gname" id="inputName" required
                       value="${group.gname}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputDesc" class="col-sm-3 control-label">群简介 :</label>

            <div class="col-sm-8">
                        <textarea rows="3" class="form-control" type="text" name="gdesc" id="inputDesc" required
                                >${group.gdesc}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">群成员 :</label>

            <div class="col-sm-4">
                <label class="label label-warning">全部学员</label>
                <select class="form-control" multiple name="users" id="course_users">
                    <c:forEach items="${users}" var="user" varStatus="s">
                        <option value="${user.userid}">${user.username}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-sm-4">
                <label class="label label-success">小组成员</label>
                <select class="form-control" multiple name="gusers" required id="course_gusers">
                    <c:forEach items="${gusers}" var="guser" varStatus="s">
                        <option value="${guser.userid}">${guser.username}</option>
                    </c:forEach>
                </select>
            </div>

        </div>

    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="submit" class="btn btn-primary">保存</button>
    </div>
</form>

<script>
    $(function () {
        var course_gusers = $("#course_gusers");
        var course_users = $("#course_users");
        $("#course_users > option").click(
                function () {
                    course_gusers.append($(this));
                }
        );
        $("#course_gusers > option").click(
                function () {
                    course_users.append($(this));
                }
        );
        course_gusers.click(
                function () {
                    $("#course_users > option").click(
                            function () {
                                course_gusers.append($(this));
                            }
                    );
                    $("#course_gusers > option").attr("checked", true);
                }
        );
        course_users.click(
                function () {
                    $("#course_gusers > option").click(
                            function () {
                                course_users.append($(this));
                            }
                    );
                    $("#course_gusers > option").attr("checked", true);
                }
        );
        $("#small_group_submit").click(function () {
            console.log(course_gusers.find("option").length);
            var $btn = $(this).button('loading');
            $("#smallGroupForm").ajaxSubmit({
                success: function (data) {
                    if (data.success) {
                        showTip(data.msg, "success");
                    } else {
                        showTip(data.msg, "failure");
                    }
                    $btn.button('reset');
                    $("#Modal").modal("hide");
                }
            });
        });
    })
    ;
</script>
