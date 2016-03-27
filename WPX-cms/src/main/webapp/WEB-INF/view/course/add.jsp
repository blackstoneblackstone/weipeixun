<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<form action="<%=basePath%>/platform/course/add" method="post" class="form-horizontal">
    <!--content-->
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">
            <c:if test="${type==1}">
                新增公开课程
            </c:if>
            <c:if test="${type==2}">
                新增必修课程
            </c:if>
        </h4>
    </div>
    <div class="modal-body">
        <div class="form-group">
            <label class="col-sm-3 control-label">类型 :</label>

            <div class="col-sm-8">
                <input class="form-control" type="text" name="type" value="${type}" required/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">项目 <label class="label label-danger">必填</label>:</label>

            <div class="col-sm-8">
                <select class="form-control" name="projectid">
                    <option value="0">未选择</option>
                    <c:forEach items="${projects}" var="project" varStatus="s">
                        <option value="${project.id}">${project.proname}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">课程图标</label>

            <div class="col-sm-8">
                <input type="file" id="file" name="file"/>
                <input type="text" id="courseIcon" name="icon" class="hidden"/>

                <div id="wait_loading" style="padding: 50px 0 0 0;display:none;">
                    <div style="width: 103px;margin: 0 auto;"><img id="iconImg"
                                                                   src="<%=basePath%>/img/loading.gif"/>
                    </div>
                </div>
                <input type="button" id="iconUp" class="btn btn-success" value="上传"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">课程名 :</label>

            <div class="col-sm-8">
                <input type="text" name="name" required class="form-control" placeholder="如何写一份漂亮的文案。。。"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">课程简介 :</label>

            <div class="col-sm-8">
                <textarea class="form-control" name="desc" required placeholder="这是干什么的，详细介绍下！"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">起止时间 :</label>

            <div class="col-sm-8">
                <input name="starttime" type="date" data-date-format="yyyy-mm-dd"
                       class="form-control">到
                <input name="endtime" type="date" data-date-format="yyyy-mm-dd"
                       class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">主讲人 :</label>

            <div class="col-sm-8">
                <select class="form-control" multiple name="zhujiangren">
                    <c:forEach items="${users}" var="user" varStatus="s">
                        <option value="${user.userId}">${user.name}</option>
                    </c:forEach>
                </select>
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
        <div class="form-group">
            <label class="col-sm-3 control-label">参与人 :</label>

            <div class="col-sm-8">

                <select class="form-control" multiple name="canyuren">
                    <c:forEach items="${users}" var="user" varStatus="s">
                        <option value="${user.userId}">${user.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <c:if test="${type==1}">
            <div class="form-group">
                <label class="col-sm-3 control-label">预计参与人数 :</label>

                <div class="col-sm-8">
                    <input type="number" name="expectperson" class="form-control" placeholder="有可能有多少人参与课程"/>
                </div>
            </div>
        </c:if>
        <div class="form-group">
            <label class="col-sm-3 control-label">上课地点 :</label>

            <div class="col-sm-8">
                <input type="text" name="place" class="form-control" placeholder="在哪上课"/>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="submit" class="btn btn-primary">保存</button>
    </div>
</form>

<script src="<%=basePath%>/js/ajaxfileupload.js"></script>
<script>
    $(function () {
        $("#iconUp").click(function () {
            ajaxFileUpload();
        });
    });
    function ajaxFileUpload() {
        // 开始上传文件时显示一个图片
        $("#wait_loading").ajaxStart(function () {
            $(this).show();
            // 文件上传完成将图片隐藏起来
        }).ajaxComplete(function () {
            //$(this).hide();
        });
        var elementIds = ["type"]; //flag为id、name属性名
        $.ajaxFileUpload({
            url: '<%=basePath%>/upLoadImage?type=course',
            type: 'post',
            secureuri: false, //一般设置为false
            fileElementId: 'file', // 上传文件的id、name属性名
            dataType: 'text', //返回值类型，一般设置为json、application/json
            elementIds: elementIds, //传递参数到服务器
            success: function (data, status) {
                var reg = /<pre.+?>(.+)<\/pre>/g;
                var result = data.match(reg);
                datas = $.parseJSON(RegExp.$1);
                $("#iconImg").attr("src", datas.obj.fileUrl);
                $("#courseIcon").val(datas.obj.fileUrl);
            },
            error: function (data, status, e) {
                console.log(data);
            }
        });
        //return false;
    }
</script>