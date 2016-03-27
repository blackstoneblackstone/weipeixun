<%--
  Created by IntelliJ IDEA.
  User: lihb
  Date: 1/9/16
  Time: 11:19 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Bootstrap 2 Form Builder</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

  <link href="/lib/Bootstrap-Form-Builder/css/lib/bootstrap.min.css" rel="stylesheet">
  <link href="/lib/Bootstrap-Form-Builder/css/lib/bootstrap-responsive.min.css" rel="stylesheet">
  <link href="/lib/Bootstrap-Form-Builder/css/custom.css" rel="stylesheet">
  <!--[if lt IE 9]>
  <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

  <link rel="shortcut icon" href="images/favicon.ico">
  <link rel="apple-touch-icon" href="images/apple-touch-icon.png">
  <link rel="apple-touch-icon" sizes="72x72" href="images/apple-touch-icon-72x72.png">
  <link rel="apple-touch-icon" sizes="114x114" href="images/apple-touch-icon-114x114.png">
</head>

<body>
<div class="container">
  <div class="row clearfix">
    <!-- Building Form. -->
    <div class="span6">
      <div class="clearfix">
        <h2>我的问卷</h2>
        <hr>
        <div id="build">
          <form id="target" class="form-horizontal">
          </form>
        </div>
      </div>
    </div>
    <!-- / Building Form. -->

    <!-- Components -->
    <div class="span6">
      <h2>素材</h2>
      <hr>
      <div class="tabbable">
        <ul class="nav nav-tabs" id="formtabs">
          <!-- Tab nav -->
        </ul>
        <form class="form-horizontal" id="components">
          <fieldset>
            <div class="tab-content">
              <!-- Tabs of snippets go here -->
            </div>
          </fieldset>
        </form>
      </div>
    </div>
    <!-- / Components -->

  </div>

</div> <!-- /container -->

<script data-main="/lib/Bootstrap-Form-Builder/js/main-built.js" src="/lib/Bootstrap-Form-Builder/js/lib/require.js" ></script>
</body>
</html>

