<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<form action="<%=basePath%>/platform/project/add" method="post" class="form-horizontal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">新增项目</h4>
    </div>
    <div class="modal-body">

        <div class="form-group">
            <label for="inputName" class="col-sm-3 control-label">项目名 :</label>

            <div class="col-sm-8">
                <input class="form-control" type="text" name="name" id="inputName" required
                       placeholder="新员工入职培训等。。。">
            </div>
        </div>
        <div class="form-group">
            <label for="inputDesc" class="col-sm-3 control-label">项目简介 :</label>

            <div class="col-sm-8">
                        <textarea rows="3" class="form-control" type="text" name="desc" id="inputDesc" required
                                  placeholder="这是干什么的，详细介绍下！"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">起止时间 :</label>

            <div class="col-sm-4">
                <input class="form-control datepicker" type="text" name="starttime" required
                       placeholder="开始时间">
            </div>
            <div class="col-sm-4">
                <input class="form-control datepicker" type="text" name="endtime" required
                       placeholder="结束时间">
            </div>

        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">负责人 :</label>

            <div class="col-sm-8">
                <select class="form-control" multiple name="fuzeren">
                    <c:forEach items="${users}" var="user" varStatus="s">
                        <option value="${user.userId}">${user.name}</option>
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
