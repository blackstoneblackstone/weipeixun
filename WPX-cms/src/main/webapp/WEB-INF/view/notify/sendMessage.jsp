<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<jsp:include page="../header.jsp"></jsp:include>
<style>

    .user-tree {
        width: 300px;
    }

    .wechat-preview {
        min-height: 450px;
    }

    .title-img {
        height: 50px;
        margin-top: 20px;
        margin-left: 20px;
    }

    .text-view {
        background-color: #e4f0ce;
        border: solid #79a337 1px;
        min-width: 20px;
        min-height: 30px;
        margin-top: 30px;
    }
</style>
<script src="<%=basePath%>/lib/JQuery_z_tree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=basePath%>/lib/JQuery_z_tree/js/jquery.ztree.excheck-3.5.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>/lib/JQuery_z_tree/css/zTreeStyle/zTreeStyle.css">
<div class="content">

    <div class="panel panel-info" id="sendView">
        <div class="panel-heading">发消息</div>
        <div class="panel-body">
            <div class="col-sm-8">
                <div class="panel panel-danger">
                    <div class="panel-heading"><img src="${app.square_logo_url}"
                                                    style="height:30px;margin-right: 10px;">${app.iname}</div>
                    <div class="panel-body">
                        <div>
                            <label class="label label-success">发送给：</label>

                            <div class="input-group">
                                <input type="text" class="form-control" aria-label="...">

                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-warning"
                                            href="<%=basePath%>/platform/notify/usersJsp"
                                            data-toggle="modal" data-target="#Modal">选择
                                    </button>

                                </div>
                            </div>
                        </div>
                        <hr>
                        <ul class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active"><a href="#text" aria-controls="text"
                                                                      role="tab"
                                                                      data-toggle="tab">文字</a></li>
                            <li role="presentation"><a href="#news" aria-controls="news" role="tab"
                                                       data-toggle="tab">图文</a></li>
                            <li role="presentation"><a href="#pic" aria-controls="pic" role="tab"
                                                       data-toggle="tab">图片</a></li>
                            <li role="presentation"><a href="#file" aria-controls="file" role="tab"
                                                       data-toggle="tab">文件</a></li>
                            <li role="presentation"><a href="#video" aria-controls="video" role="tab"
                                                       data-toggle="tab">视频</a></li>
                            <li role="presentation"><a href="#vioce" aria-controls="vioce" role="tab"
                                                       data-toggle="tab">语音</a></li>
                        </ul>
                        <%--课程大组--%>
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane active" id="text">
                                <div class="form-group">
                                    <textarea rows="10" class="form-control"></textarea>

                                </div>
                                <div class="right">
                                    <button class="btn btn-success">
                                        发送
                                    </button>
                                    <button class="btn btn-info">
                                        保存
                                    </button>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="news">
                                <div class="form-group">
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="pic">
                                <div class="form-group">
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="file">
                                <div class="form-group">
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="video">
                                <div class="form-group">
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="vioce">
                                <div class="form-group">
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="panel panel-primary">
                    <div class="panel-heading"><img src="<%=basePath%>/img/weixinlogo.png"
                                                    style="height:30px;margin-right: 10px;">预览
                    </div>
                    <div class="panel-body wechat-preview">
                        <div class="col-sm-3">
                            <img class="title-img" src="${app.square_logo_url}">
                        </div>
                        <div class="col-sm-8">
                            <div class="preview text-view alert alert-success">
                                <span id="text-view">

                                </span>
                            </div>
                            <div class="preview news-view">

                            </div>
                            <div class="preview pic-view">

                            </div>
                            <div class="preview file-view">

                            </div>
                            <div class="preview video-view">

                            </div>
                            <div class="preview vioce-view">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>
<script>
    $(function () {

    });

</script>
<jsp:include page="../bottom.jsp"></jsp:include>