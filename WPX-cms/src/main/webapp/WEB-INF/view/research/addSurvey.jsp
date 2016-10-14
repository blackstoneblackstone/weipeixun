<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<jsp:include page="../header.jsp"></jsp:include>
<script>
    var questionJson = [];
    var index = 0;
</script>

<div class="content">
    <label class="label label-default">调查问卷</label>
    <c:if test="${!empty courseid}">
        <button onclick="javascript:window.location.href='<%=basePath%>/platform/course'"
                id="btn-next" class="btn btn-mini btn-warning">
            <i class="glyphicon glyphicon-chevron-left"></i> 返回
        </button>
    </c:if>
    <button type="button" class="btn btn-default" href="<%=basePath%>/platform/research/addQuestionJsp"
            data-toggle="modal" data-target="#Modal">
        <i class="glyphicon glyphicon-plus"></i> 添加问题
    </button>

    <hr>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>序号</th>
            <th>问题</th>
            <th>问题补充</th>
            <th>类型</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="questionList">

        </tbody>
    </table>
    <div class="modal-footer">
        <button class="btn btn-primary"
                data-toggle="modal" data-target="#myModalResume">提交
        </button>

    </div>

</div>
<div class="modal fade" id="myModalResume" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content form-horizontal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">编辑问卷</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="resumeName" class="col-sm-3 control-label">标题:</label>

                    <div class="col-sm-8">
                        <input class="form-control" type="text" name="resumeName" id="resumeName"
                               placeholder="标题。。。" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="resumeDesc" class="col-sm-3 control-label">摘要:</label>

                    <div class="col-sm-8">
                        <input class="form-control" type="text" name="resumeDesc" id="resumeDesc"
                               placeholder="摘要。。。" required>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="surveySubmit" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        var quJ = [];

        $("#menu li:eq(4)").addClass("active");
//        $("#menu li:eq(4) span").text("调查问卷");
        $("#surveySubmit").click(function () {
            if ($("#resumeName").val() == "") {
                alert("请填个标题");
                return;
            }
            if ($("#resumeDesc").val() == "") {
                alert("请填个摘要");
                return;
            }
            $(".quJson").each(function () {
                quJ.push(JSON.parse($(this).text()));
            });
            $.ajax(
                    {
                        url: "<%=basePath%>/platform/research/add",
                        type: "post",
                        data: {
                            "rname": $("#resumeName").val(),
                            "rdesc": $("#resumeDesc").val(),
                            "rdata": JSON.stringify(quJ),
                            "rtype": "research"
                        },
                        success: function (data) {
                            window.location.href = "<%=basePath%>/platform/research";
                        }

                    }
            );
        });
    });
</script>
<jsp:include page="../bottom.jsp"></jsp:include>