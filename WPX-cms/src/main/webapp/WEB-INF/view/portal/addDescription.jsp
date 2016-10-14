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
    <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-4">
            <div class="panel panel-success ">
                <div class="panel-heading center">
                    效果预览
                </div>
                <div class="panel-body" id="viewHtml" style="min-height: 400px;">
                    ${portal.schooldesc}
                </div>
                <div class="panel-footer center">
                    <button id="viewSave" class="btn btn-success btn-sm">保存</button>
                    <span id="viewTip"></span>
                </div>
            </div>
        </div>
        <div class="col-sm-7">
            <script id="editor" type="text/plain" style="width:90%;height:340px;"></script>
        </div>

    </div>
    <div class="row">
        <div class="col-sm-12">
            <hr>
        </div>
    </div>
</div>
<script>
    window.UEDITOR_HOME_URL = "<%=basePath%>/lib/ueditor/";
</script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>/lib/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>/lib/ueditor/ueditor.all.min.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="<%=basePath%>/lib/ueditor/lang/zh-cn/zh-cn.js"></script>

<jsp:include page="../bottom.jsp"></jsp:include>
<script>
    var ue = UE.getEditor('editor');
    $(function () {
        $("#viewSave").click(function () {
            if (!ue.hasContents()) {
                showTip("没有编辑任何值", "failure");
                return;
            }
            var cont = ue.getContent();
            $.ajax({
                url: "<%=basePath%>/platform/portal/updateSchoolDesc",
                type: "post",
                data: {
                    id: "${id}",
                    schooldesc: cont
                },
                success: function (data) {
                    if (data.success) {
                        showTip(data.msg, "success");
                    } else {
                        showTip(data.msg, "failure");
                    }
                    $("#viewTip").text(new Date().toLocaleString());
                }
            });
        });
        setInterval(function () {
            $("#viewHtml").html(ue.getContent());
        }, 1000);
        insertHtml();
    });
    function insertHtml() {
        ue.addListener('ready', function (editor) {
            ue.execCommand('insertHtml', '${portal.schooldesc}');
        });
    }
</script>