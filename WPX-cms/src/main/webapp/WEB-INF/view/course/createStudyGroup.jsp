<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<div class="form-horizontal">
    <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
            aria-hidden="true">&times;</span></button>
    <h4 class="modal-title" id="myModalLabel">
        创建学习小组
    </h4>
    <h6>
        你可以创建基于本课程参与人的课程大组和部分人的学习小组
    </h6>
</div>
    <div class="modal-body">
        <div>
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#bigGroup" aria-controls="bigGroup" role="tab"
                                                          data-toggle="tab">课程大组</a></li>
                <li role="presentation"><a href="#smallGroup" aria-controls="smallGroup" role="tab"
                                           data-toggle="tab">学习小组</a></li>
                <li role="presentation"><a href="<%=basePath%>/platform/group?courseid=${courseid}">已创建小组</a></li>
            </ul>
            <%--课程大组--%>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="bigGroup">
                    <div class="form-group">
                        <label for="inputName1" class="col-sm-3 control-label">群名 :</label>

                        <div class="col-sm-8">
                            <input class="form-control" type="text" name="big_gname" id="inputName1" required
                                   placeholder="起个有趣的名字吧,尽量明确所属课程">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputDesc1" class="col-sm-3 control-label">群简介 :</label>

                        <div class="col-sm-8">
                        <textarea rows="3" class="form-control" type="text" name="big_gdesc" id="inputDesc1" required
                                  placeholder="这是干什么的，详细介绍下！"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary">创建大组</button>
                    </div>
                </div>
                <%--学习小组--%>

                <div role="tabpanel" class="tab-pane" id="smallGroup">
                    <form id="smallGroupForm" action="<%=basePath%>/platform/course/createSmallStudyGroup"
                          method="post">
                        <input class="hidden" type="text" name="courseid" value="${courseid}" required>
                        <div class="form-group">
                            <label for="inputName" class="col-sm-3 control-label">群名 :</label>

                            <div class="col-sm-8">
                                <input class="form-control" type="text" name="gname" value="${coursename}第【N】小组" id="inputName" required
                                       placeholder="起个有趣的名字吧,尽量明确所属课程">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputDesc" class="col-sm-3 control-label">群简介 :</label>

                            <div class="col-sm-8">
                        <textarea rows="3" class="form-control" type="text" name="gdesc" id="inputDesc" required
                                  placeholder="这是干什么的，详细介绍下！"></textarea>
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

                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" id="small_group_submit" class="btn btn-primary">创建小组</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
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