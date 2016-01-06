<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!--header-->
<jsp:include page="../header.jsp"></jsp:include>
<!--header-->

<!--menu-->
<jsp:include page="../menu.jsp"></jsp:include>
<!--menu-->
<!--content-->
<div id="content">
    <!--breadcrumbs-->
    <div id="content-header">
        <div id="breadcrumb">
            <a href="<%=basePath%>/platform/index" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
            <a href="<%=basePath%>/platform/project" title="Go to project" class="tip-bottom">资源管理</a>
            <a href="<%=basePath%>/platform/addressbook" title="Go to project" class="tip-bottom">通讯录</a>
        </div>
    </div>
    <!--End-breadcrumbs-->

    <!--Action boxes-->
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span2">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-file"></i> </span>
                        <h5>部门</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <ul id="department_tree"></ul>
                    </div>
                </div>
            </div>
            <div class="span8">
                <div class="top_btn">
                    <button class="btn btn-primary" id="synAddress"><i class="icon-plus"></i>同步通讯录</button>
                    <button class="btn btn-success" id="addUser"><i class="icon-plus"></i>增加用户</button>
                </div>
                <!--End-Action boxes-->
                <div class="widget-box">
                    <table id="departmentMembers"></table>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="<%=basePath%>/js/jquery.easyui.min.js"></script>
<script>

    $(function () {
        var department_tree_url = "<%=basePath%>/platform/addressbook/departmentTree";
        var departmentid = 1;
        var department_tree = $('#department_tree')
                .tree(
                {
                    url: department_tree_url,
                    method: 'get',
                    lines: false,
                    onContextMenu: function (e, node) {
                        tree = $(this);//预先定义好，以后调用
                        departmentid = node.id;
                        e.preventDefault();
                        $(this).tree('select', node.target);
                        $('#menu').menu('show', {
                            left: e.pageX,
                            top: e.pageY
                        });
                    },
                    onClick: function (node) {
                        departmentid = node.id;
                        $("#departmentMembers").datagrid('reload', {partyId: departmentid});
                    },
//                    onBeforeLoad : function(node, param) {
//                        if (department_tree_url) {//只有刷新页面才会执行这个方法
//                            parent.$.messager.progress({
//                                title : '提示',
//                                text : '数据处理中，请稍后....'
//                            });
//                        }
//                    },
                    onLoadSuccess: function (node, data) {
                        parent.$.messager.progress('close');
                    }
                });
        var departmentMembers_url = '<%=basePath%>/platform/addressbook/getUserByPartId';
        $('#departmentMembers').datagrid({
            title: '部门成员',
            iconCls: 'icon-save',
            method: 'get',
            autoRowHeight: false,
            rownumbers: 10,
            fitColumns: true,
            url: departmentMembers_url,
            idField: 'userid',
            height: 500,
            fit: false,
            columns: [[
                {
                    title: '头像', field: 'avatar', width: 80, formatter: function (value, rowData, rowIndex) {
                    return '<img src="' + value + '" style="width:50px;"/>';
                }
                },
                {title: '账号', field: 'userid', width: 80, sortable: true},
                {title: '姓名', field: 'name', width: 80},
                {title: '手机号', field: 'mobile', width: 80}
            ]],
            pagination: true,
            toolbar: [{
                id: 'btnadd',
                text: 'Add',
                iconCls: 'icon-add',
                handler: function () {
                    $('#btnsave').linkbutton('enable');
                    alert('add')
                }
            }, {
                id: 'btncut',
                text: 'Cut',
                iconCls: 'icon-cut',
                handler: function () {
                    $('#btnsave').linkbutton('enable');
                    alert('cut')
                }
            }, '-', {
                id: 'btnsave',
                text: 'Save',
                disabled: true,
                iconCls: 'icon-save',
                handler: function () {
                    $('#btnsave').linkbutton('disable');
                    alert('save')
                }
            }]
        });
        var p = $('#departmentMembers').datagrid('getPager');
        $(p).pagination({
            onBeforeRefresh: function () {
                alert('before refresh');
            }
        });
        $("#synAddress").click(function () {

        });
        //增加用户
        $("#addUser").click(function () {
            alert('faf');
            var url = "<%=basePath%>/platform/addressbook/addJsp?partyId=" + departmentid;
            window.location.href = url;
        });
    });
</script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/easyui.css">
<!--content-->

<!--bottom-->
<jsp:include page="../bottom.jsp"></jsp:include>
<!--bottom-->
