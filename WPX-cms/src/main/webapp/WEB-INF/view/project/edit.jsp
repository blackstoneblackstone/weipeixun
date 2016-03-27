<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<form action="<%=basePath%>/platform/project/edit" method="post" class="form-horizontal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">编辑项目</h4>
    </div>
    <div class="modal-body">
        <div class="form-group">
            <label class="col-sm-3 control-label">ID :</label>

            <div class="col-sm-8">
                <input type="text" name="id" value="${project.id}" required class="form-control" style="display: none"/>
                ${project.id}
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">项目名 :</label>

            <div class="col-sm-8">
                <input type="text" name="name" value="${project.proname}" required class="form-control"
                       placeholder="新员工入职培训等。。。"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">项目简介 :</label>

            <div class="col-sm-8">
                    <textarea class="form-control" name="desc" required
                              placeholder="这是干什么的，详细介绍下！">${project.prodesc}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">起止时间 :</label>

            <div class="col-sm-4">
                <input name="starttime" class="form-control" value="${project.starttime}" type="text"
                       data-date-format="yyyy-mm-dd">
            </div>
            <div class="col-sm-4">
                <input name="endtime" class="form-control" type="text" value="${project.endtime}"
                       data-date-format="yyyy-mm-dd">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">负责人 :</label>

            <div class="col-sm-8">
                <select class="form-control" name="fuzeren">
                    <c:forEach items="${project.leaders}" var="leader" varStatus="s">
                        <option value="${leader.userId}" selected="selected">${leader.name}</option>
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
        $('#datetimepicker').datetimepicker();
    });
</script>