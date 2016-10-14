<%--
  Created by IntelliJ IDEA.
  User: lihb
  Date: 2/14/16
  Time: 2:58 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp"></jsp:include>
<title>调研测试</title>
</head>
<style>
    body {
        background-color: #f2f2f2;
    }
</style>
<body>
<form class="am-form" id="formData">
    <img src="http://www.wexue.top:20000/images/survey-default.jpg" style="width: 100%;margin-bottom: 10px;">
    <article class="am-article">
        <div class="am-article-hd">
            <h1 class="am-article-title">${survey.rname}</h1>

            <%--<p class="am-article-meta">${survey.rdesc}</p>--%>
        </div>

        <div class="am-article-bd">
            <p class="am-article-lead">${survey.rdesc}</p>
            <fieldset>
                <div id="formBody">

                </div>
            </fieldset>
        </div>
    </article>
</form>
<jsp:include page="../footer.jsp"></jsp:include>
</body>

<script>
    //  ["{\"question\":\"爱好？\",\"qdesc\":\"\",\"type\":\"check\",\"item\":[\"唱歌\",\"跳舞\"]}",
    //    "{\"question\":\"性别\",\"qdesc\":\"\",\"type\":\"radio\",\"item\":[\"男\",\"女\"]}",
    //    "{\"question\":\"你的名字？\", \"qdesc\": \"\", \"type\": \"blank\"}",
    //    "{\"question\":\"个人简介？\", \"qdesc\": \"\", \"type\": \"resume\"}"]
    $(function () {
        var data = ${survey.rdata};
        for (var i = 0; i < data.length; i++) {
            var type = data[i].type;
            if (type == "check") {
//            <div class="am-form-group">
//                <label>你的爱好</label>
//                <label class="am-checkbox-inline">
//                <input type="checkbox" value="option1"> 选我
//                </label>
//                <label class="am-checkbox-inline">
//                <input type="checkbox" value="option2"> 同时可以选我
//                </label>
//                <label class="am-checkbox-inline">
//                <input type="checkbox" value="option3"> 还可以选我
//                </label>
//                </div>
                var htmlD = '<div class="am-form-group"><label>' + data[i].question + '</label><br>';
                var itemD = data[i].item;
                for (var j = 0; j < itemD.length; j++) {
                    htmlD = htmlD + ' <label class="am-checkbox-inline"><input name="' + data[i].id + '" type="checkbox" value="' + itemD[j] + '">' + itemD[j] + ' </label>';
                }
                htmlD = htmlD + '</div>';
                $("#formBody").append(htmlD);
            }
            if (type == "radio") {
                var htmlD = '<div class="am-form-group"><label>' + data[i].question + '</label><br>';
                var itemD = data[i].item;
                for (var j = 0; j < itemD.length; j++) {
                    htmlD = htmlD + ' <label class="am-radio-inline"><input name="' + data[i].id + '" type="radio" value="' + itemD[j] + '">' + itemD[j] + ' </label>';
                }
                htmlD = htmlD + '</div>';
                $("#formBody").append(htmlD);
            }
            if (type == "blank") {
                var htmlD = '<div class="am-form-group"><label>' + data[i].question + '</label> <input type="text" name="' + data[i].id + '" placeholder="' + data[i].qdesc + '"></div>';
                $("#formBody").append(htmlD);
            }
            if (type == "resume") {
                var htmlD = '<div class="am-form-group"><label>' + data[i].question + '</label> <textarea rows="5" name="' + data[i].id + '" placeholder="' + data[i].qdesc + '"></textarea></div>';
                $("#formBody").append(htmlD);
            }

        }
        $("#formBody").append('<p><button id="answerSubmit" type="button" class="am-btn am-btn-success">提交</button></p>');
        $("#answerSubmit").click(function () {
            var answers = $("#formData").serializeArray();
            $.ajax({
                url: "/research/answerSubmit",
                data: {
                    userid: "${userid}",
                    researchid: "${researchid}",
                    corpid: "${corpid}",
                    answer: JSON.stringify(answers)
                },
                type: "post",
                success: function (data) {
                    if (data == "success") {
                        $("#formData").remove();
                        $("body").html('<div style="text-align: center;height:100px;width:100%">提交成功，感谢您的参与</div>');
                    }
                }
            });
        });
    });

</script>
</html>
