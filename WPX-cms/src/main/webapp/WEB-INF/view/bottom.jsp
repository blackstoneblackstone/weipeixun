<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path ;
%>
<!--Footer-part-->

<div class="row-fluid">
    <div id="footer" class="span12"> 2013 &copy; Matrix Admin. Brought to you by <a href="http://themedesigner.in/">Themedesigner.in</a> </div>
</div>
<script src="<%=basePath%>/js/bootstrap-colorpicker.js"></script>
<script src="<%=basePath%>/js/bootstrap-datepicker.js"></script>
<script src="<%=basePath%>/js/bootstrap-wysihtml5.js"></script>
<script src="<%=basePath%>/js/excanvas.min.js"></script>
<script src="<%=basePath%>/js/fullcalendar.min.js"></script>
<script src="<%=basePath%>/js/jquery.dataTables.min.js"></script>
<script src="<%=basePath%>/js/jquery.flot.min.js"></script>
<script src="<%=basePath%>/js/jquery.flot.pie.min.js"></script>
<script src="<%=basePath%>/js/jquery.flot.resize.min.js"></script>
<script src="<%=basePath%>/js/jquery.form.js"></script>
<script src="<%=basePath%>/js/jquery.gritter.min.js"></script>
<script src="<%=basePath%>/js/jquery.peity.min.js"></script>
<script src="<%=basePath%>/js/jquery.ui.custom.js"></script>
<script src="<%=basePath%>/js/jquery.uniform.js"></script>
<script src="<%=basePath%>/js/jquery.validate.js"></script>
<script src="<%=basePath%>/js/jquery.wizard.js"></script>
<script src="<%=basePath%>/js/masked.js"></script>
<script src="<%=basePath%>/js/matrix.js"></script>
<script src="<%=basePath%>/js/matrix.form_common.js"></script>
<script src="<%=basePath%>/js/matrix.calendar.js"></script>
<script src="<%=basePath%>/js/matrix.charts.js"></script>
<script src="<%=basePath%>/js/matrix.chat.js"></script>
<script src="<%=basePath%>/js/matrix.dashboard.js"></script>
<script src="<%=basePath%>/js/matrix.form_validation.js"></script>
<script src="<%=basePath%>/js/matrix.login.js"></script>
<script src="<%=basePath%>/js/matrix.popover.js"></script>
<script src="<%=basePath%>/js/matrix.tables.js"></script>
<script src="<%=basePath%>/js/matrix.wizard.js"></script>
<script src="<%=basePath%>/js/select2.min.js"></script>
<script src="<%=basePath%>/js/wysihtml5-0.3.0.js"></script>
<!--end-Footer-part-->

<script type="text/javascript">
    // This function is called from the pop-up menus to transfer to
    // a different page. Ignore if the value returned is a null string:
    function goPage (newURL) {

        // if url is empty, skip the menu dividers and reset the menu selection to default
        if (newURL != "") {

            // if url is "-", it is this page -- reset the menu:
            if (newURL == "-" ) {
                resetMenu();
            }
            // else, send page to designated URL
            else {
                document.location.href = newURL;
            }
        }
    }

    // resets the menu selection upon entry to this page:
    function resetMenu() {
        document.gomenu.selector.selectedIndex = 2;
    }
</script>
</body>
</html>

