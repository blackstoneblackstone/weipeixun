<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!--header-->
<jsp:include page="header.jsp"></jsp:include>
<script src="<%=basePath%>/js/echarts.min.js"></script>
<!--header-->
<!--content-->
<div class="content">
    <div class="jumbotron">
        <h3>实时报表</h3>
        <div class="row">
            <div class="col-lg-5">
                  <div id="peoples" style="height: 100px;">

                  </div>
            </div>

            <div class="col-lg-5">

            </div>
        </div>
    </div>
</div>

<script>

    var option = {
        animationDuration:5000,
        color:['#e96a60','#00e8bd'],
        radar: {
            radius: '50%',
            center:['50%','60%'],
            // shape: 'circle',
            indicator: [
                { name: '微信', max: 6500},
                { name: 'iOSAPP', max: 16000},
                { name: 'AndroidAPP', max: 30000},
                { name: '手机portal', max: 38000},
                { name: 'PCportal', max: 52000},
            ]
        },
        series: [{
            type: 'radar',
            data : [
                {
                    value : [4300, 10000, 28000, 35000, 50000, 19000],
                    name : '室内'
                },
                {
                    value : [5000, 14000, 28000, 31000, 42000, 21000],
                    name : '室外'
                }
            ]
        }]
    };
    var myChart = echarts.init(document.getElementById('peoples'));
    myChart.setOption(option);

</script>
<!--content-->

<!--bottom-->
<jsp:include page="bottom.jsp"></jsp:include>
<!--bottom-->
