<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>

<div class="form-horizontal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">新增问题</h4>
    </div>
    <div class="modal-body">
        <div>
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#blank" aria-controls="home" role="tab"
                                                          data-toggle="tab">填空题</a></li>
                <li role="presentation"><a href="#radio" aria-controls="profile" role="tab"
                                           data-toggle="tab">单选题</a></li>
                <li role="presentation"><a href="#check" aria-controls="messages" role="tab"
                                           data-toggle="tab">多选题</a>
                </li>
                <li role="presentation"><a href="#resume" aria-controls="settings" role="tab"
                                           data-toggle="tab">简答题</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="blank">
                    <div class="form-group">
                        <label for="blankDesc" class="col-sm-3 control-label">问题:</label>

                        <div class="col-sm-8">
                            <input class="form-control" type="text" name="name" id="blankName" required
                                   placeholder="问题描述。。。">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="blankDesc" class="col-sm-3 control-label">问题补充:</label>

                        <div class="col-sm-8">
                            <input class="form-control" type="text" name="name" id="blankDesc"
                                   placeholder="补充下。。。">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button id="blankSubmit" type="button" data-dismiss="modal" class="btn btn-primary">添加</button>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="radio">
                    <div id="radioContent">
                        <div class="form-group">
                            <label for="radioName" class="col-sm-3 control-label">问题:</label>

                            <div class="col-sm-8">
                                <input class="form-control" type="text" name="name" id="radioName" required
                                       placeholder="问题描述。。。">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="radioDesc" class="col-sm-3 control-label">问题补充:</label>

                            <div class="col-sm-8">
                                <input class="form-control" type="text" name="name" id="radioDesc"
                                       placeholder="补充下。。。">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">选项:</label>

                            <div class="col-sm-6">
                                <input class="form-control" type="text" name="radioItem" placeholder="补充下。。。">
                            </div>
                            <div class="col-sm-2">
                                <button id="radioAdd" class="btn btn-success"><span
                                        class="glyphicon glyphicon-plus"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button id="radioSubmit" type="button" data-dismiss="modal" class="btn btn-primary">添加</button>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="check">
                    <div id="checkContent">
                        <div class="form-group">
                            <label for="radioName" class="col-sm-3 control-label">问题:</label>

                            <div class="col-sm-8">
                                <input class="form-control" type="text" name="name" id="checkName" required
                                       placeholder="问题描述。。。">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="radioDesc" class="col-sm-3 control-label">问题补充:</label>

                            <div class="col-sm-8">
                                <input class="form-control" type="text" name="name" id="checkDesc"
                                       placeholder="补充下。。。">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">选项:</label>

                            <div class="col-sm-6">
                                <input class="form-control" type="text" name="checkItem" placeholder="补充下。。。">
                            </div>
                            <div class="col-sm-2">
                                <button id="checkAdd" class="btn btn-success"><span
                                        class="glyphicon glyphicon-plus"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button id="checkSubmit" type="button" data-dismiss="modal" class="btn btn-primary">添加</button>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="resume">
                    <div class="form-group">
                        <label for="radioName" class="col-sm-3 control-label">问题:</label>

                        <div class="col-sm-8">
                            <input class="form-control" type="text" name="name" id="resumeName" required
                                   placeholder="问题描述。。。">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="radioDesc" class="col-sm-3 control-label">问题补充:</label>

                        <div class="col-sm-8">
                            <input class="form-control" type="text" name="name" id="resumeDesc"
                                   placeholder="补充下。。。">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" id="resumeSubmit" data-dismiss="modal" class="btn btn-primary">添加</button>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <%--<div class="modal-footer">--%>
    <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
    <%--<button type="submit" class="btn btn-primary">添加</button>--%>
    <%--</div>--%>
    <script>
        $(function () {
            $("#radioAdd").click(function () {
                $("#radioContent").append('<div class="form-group">' +
                '<label class="col-sm-3 control-label"></label>' +
                '<div class="col-sm-6">' +
                '<input class="form-control" type="text" name="radioItem" placeholder="加个选项">' +
                '</div>' +
                '<div class="col-sm-2">' +
                '<button class="radioDelete btn btn-danger"><span class="glyphicon glyphicon-minus"></span></button>' +
                '</div>' +
                '</div>');
                $(".radioDelete").click(function () {
                    $(this).parent().parent().remove();
                });
            });

            $("#checkAdd").click(function () {
                $("#checkContent").append('<div class="form-group">' +
                '<label class="col-sm-3 control-label"></label>' +
                '<div class="col-sm-6">' +
                '<input class="form-control" type="text" name="checkItem" placeholder="加个选项">' +
                '</div>' +
                '<div class="col-sm-2">' +
                '<button class="checkDelete btn btn-danger"><span class="glyphicon glyphicon-minus"></span></button>' +
                '</div>' +
                '</div>');
                $(".checkDelete").click(function () {
                    $(this).parent().parent().remove();
                });
            });


            $("#blankSubmit").click(function () {
                index++;
                $("#questionList").append('<tr><td>' +
                index +
                '</td> ' +
                '<td>' +
                $("#blankName").val() +
                '</td>' +
                '<td>' +
                $("#blankDesc").val() +
                '</td>' +
                '<td>' +
                '填空题</td>' +
                '<td>' +
                '<button data-id="' + index + '" data-loading-text="删除中..." class="delete btn btn-xs btn-danger">删除' +
                '</button>' +
                '<span class="quJson">' + '{"id":'+index+',"question":"' + $("#blankName").val() + '", "qdesc": "' + $("#blankDesc").val() + '", "type": "blank"}' +
                '</span></td></tr>');
                $(".delete").click(function () {
                    $(this).parent().parent().remove();
                });
            });

            $("#radioSubmit").click(function () {
                index++;
                var quJ = {"id":index,"question": $("#radioName").val(), "qdesc": $("#radioDesc").val(), "type": "radio"};
                var item = [];
                $("input[name='radioItem']").each(function () {
                    item.push($(this).val());
                });
                quJ.item = item;


                $("#questionList").append('<tr><td>' +
                index +
                '</td> ' +
                '<td>' +
                $("#radioName").val() +
                '</td>' +
                '<td>' +
                $("#radioDesc").val() +
                '</td>' +
                '<td>' +
                '单选题</td>' +
                '<td>' +
                '<button data-id="' + index + '" data-loading-text="删除中..." class="delete btn btn-xs btn-danger">删除' +
                '</button>' +
                '<span class="quJson">' + JSON.stringify(quJ) +
                '</span></td></tr>');
                $(".delete").click(function () {
                    $(this).parent().parent().remove();
                });

            });
            $("#checkSubmit").click(function () {
                index++;
                var quJ = {"id":index,"question": $("#checkName").val(), "qdesc": $("#checkDesc").val(), "type": "check"};
                var item = [];
                $("input[name='checkItem']").each(function () {
                    item.push($(this).val());
                });
                quJ.item = item;

                $("#questionList").append('<tr><td>' +
                index +
                '</td> ' +
                '<td>' +
                $("#checkName").val() +
                '</td>' +
                '<td>' +
                $("#checkDesc").val() +
                '</td>' +
                '<td>' +
                '多选题</td>' +
                '<td>' +
                '<button data-id="' + index + '" data-loading-text="删除中..." class="delete btn btn-xs btn-danger">删除' +
                '</button>' +
                '<span class="quJson">' + JSON.stringify(quJ) +
                '</span></td></tr>');
                $(".delete").click(function () {
                    $(this).parent().parent().remove();
                });

            });
            $("#resumeSubmit").click(function () {
                index++;
                $("#questionList").append('<tr><td>' +
                index +
                '</td> ' +
                '<td>' +
                $("#resumeName").val() +
                '</td>' +
                '<td>' +
                $("#resumeDesc").val() +
                '</td>' +
                '<td>' +
                '简答题</td>' +
                '<td>' +
                '<button data-id="' + index + '" data-loading-text="删除中..." class="delete btn btn-xs btn-danger">删除' +
                '</button>' +
                '<span class="quJson">' + '{"id":'+index+',"question":"' + $("#resumeName").val() + '", "qdesc": "' + $("#resumeDesc").val() + '", "type": "resume"}' +
                '</span></td></tr>');
                $(".delete").click(function () {
                    $(this).parent().parent().remove();
                });
            });
        });
    </script>
</div>
