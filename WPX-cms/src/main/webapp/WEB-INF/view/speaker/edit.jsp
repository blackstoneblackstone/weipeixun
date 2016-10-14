<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<form action="<%=basePath%>/platform/speaker/add" method="post" class="form-horizontal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">新增讲师</h4>
    </div>
    <div class="modal-body">
        <div class="form-group">
            <label class="col-sm-3 control-label">ID :</label>

            <div class="col-sm-8">
                <input type="text" name="id" value="${speaker.id}" required class="form-control" style="display: none"/>
                ${speaker.id}
            </div>
        </div>
        <div class="form-group">
            <label for="inputName" class="col-sm-3 control-label">讲师名 :</label>

            <div class="col-sm-8">
                <input class="form-control" value="${speaker.sname}" type="text" name="sname" id="inputName" required>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">头像</label>

            <div class="col-sm-8">
                <input type="file" id="file" name="file"/>
                <input type="text" id="courseIcon" value="${speaker.avator}" name="avator" class="hidden"/>

                <div id="wait_loading" style="padding: 50px 0 0 0;display:none;">
                    <div style="width: 103px;margin: 0 auto;"><img id="iconImg"
                                                                   src="<%=basePath%>/img/loading.gif"/>
                    </div>
                </div>
                <input type="button" id="iconUp" class="btn btn-success" value="上传"/>
            </div>
        </div>

        <div class="form-group">
            <label for="inputDesc" class="col-sm-3 control-label">简介 :</label>

            <div class="col-sm-8">
                        <textarea rows="3" class="form-control" type="text" name="sdesc" id="inputDesc" required
                                >${speaker.sdesc}</textarea>
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="submit" class="btn btn-primary">保存</button>
    </div>
</form>
