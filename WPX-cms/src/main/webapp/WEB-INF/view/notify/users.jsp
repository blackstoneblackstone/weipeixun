<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<script src="<%=basePath%>/lib/JQuery_z_tree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=basePath%>/lib/JQuery_z_tree/js/jquery.ztree.excheck-3.5.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>/lib/JQuery_z_tree/css/zTreeStyle/zTreeStyle.css">
<form action="<%=basePath%>/platform/portal/updateSchoolNotify" method="post" id="notifyForm" class="form-horizontal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <h4 class="model-title" id="myModalLabel">选择发送人</h4>
    </div>
    <div class="modal-body">
        <div class="form-group">
            <label class="col-sm-3 control-label">已选择:</label>

            <div class="col-sm-8 alert alert-success">
                <div id="selectUsers" style="display: inline-table">

                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">全部部门:</label>

            <div class="col-sm-8 alert alert-info">
                <div class="ztree" id="departmentTree"></div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">部门成员:

            </label>


            <div class="col-sm-8 alert alert-info" id="usersList">

            </div>
        </div>


    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="saveSubmit" type="button" data-loading-text="保存中..." class="btn btn-primary">保存</button>
    </div>
</form>
<style>
    .users-item {
        margin-left: 10px;
        cursor: pointer;
    }

    .users-item span {
        margin-left: 5px;
        color: #000000;
    }

    .users-item img {
        height: 20px;
        margin-right: 5px;
    }
</style>
<script>
    var selectedUsers = [];
    $(function () {
        var setting = {
            check: {
                enable: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick,
                onClick: clickEvent
            }
        };
        $.ajax({
            url: "<%=basePath%>/platform/addressbook/departmentTree",
            type: "get",
            success: function (data) {
                if (data != null && data.length != 0) {
                    $.fn.zTree.init($("#departmentTree"), setting, data);
                } else {
                    $("#departmentTree").text("空");
                }
            }
        });
    });
    function beforeClick(treeId, treeNode, clickFlag) {
        return true;
    }
    function clickEvent(event, treeId, treeNode, clickFlag) {

        showUsers(treeNode.id);
    }
    function showUsers(depId) {
        $.ajax({
            url: "<%=basePath%>/platform/addressbook/getUsersByDepId",
            type: "get",
            data: {
                "depId": depId
            },
            success: function (data) {
                $("#usersList").html("");
                if (data != null && data.length != 0) {
                    $("#usersList").html('<label onclick="allSlected('+data+'   )" class="btn btn-success btn-xs">全选</label>');
                    for (var i = 0; i < data.length; i++) {
                        $("#usersList").append('<label class="label label-info users-item" onclick="addUserItem(\'' + data[i].userid + '\',\'' + data[i].username + '\',\'' + data[i].avatar + '\')"><img src="' + data[i].avatar + '">' + data[i].username + '</label>');
                    }
                } else {
                    $("#usersList").text("空");
                }
            }
        });
    }

    function addUserItem(id, name, avatar) {
        if (selectedUsers.indexOf({"id": id}) != -1) {
            return;
        }
        selectedUsers.push({"id": id});
        var temp = '<label class="label label-success  users-item left" onclick="removeUserItem(this,{id})"><img src="{avatar}">{name}<span>x</span></label>';
        var result = temp.format({
            "id": id,
            "name": name,
            "avatar": avatar
        });
        $("#selectUsers").append(result);
    }
    function allSlected(data) {

        for (var i = 0; i < data.length; i++) {
            if (selectedUsers.indexOf({"id": id}) != -1) {
                break;
            }
            selectedUsers.push(data[i]);
            addUserItem(data[i].id, data[i].name, data[i].avatar);
        }
    }

</script>


<%--// function removeUserItem(elem, id) {--%>
<%--// elem.parentNode.removeChild(elem);--%>
<%--// var index = selectedUsers.indexOf(id);--%>
<%--// selectedUsers.splice(index, 1);--%>
<%--// console.log(selectedUsers);--%>
<%--// }--%>


