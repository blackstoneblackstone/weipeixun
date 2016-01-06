<%--
  Created by IntelliJ IDEA.
  User: lihb
  Date: 1/2/16
  Time: 10:21 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<script src="/js/lib/amazeui/js/jquery.min.js"></script>
<script src="/js/lib/amazeui/js/amazeui.js"></script>
<script src="/js/lib/amazeui/js/handlebars.min.js"></script>
<script src="/js/lib/amazeui/js/amazeui.widgets.helper.js"></script>
<script src="/js/wx/common.js"></script>
<div id="toast" style="display: none;">
    <div class="weui_mask_transparent"></div>
    <div class="weui_toast">
        <i class="weui_icon_toast"></i>
        <p class="weui_toast_content" id="toast_content"></p>
    </div>
</div>