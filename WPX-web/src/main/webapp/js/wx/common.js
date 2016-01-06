/**
 * Created by lihb on 12/14/15.
 */
String.format = function(str) {
    var args = arguments, re = new RegExp("%([1-" + args.length + "])", "g");
    return String(str).replace(
        re,
        function($1, $2) {
            return args[$2];
        }
    );
};
function ajaxGet(url, success) {
    $.ajax({
        url: url,
        dataType: "json",
        error: function () {
            return null;
        },
        success: success
    });
}
function ordercourse(courseid) {
    var url = "/pxkc/orderCourse?courseid="+courseid;
    ajaxGet(url, function (data) {
        if(data==1){
            $("#btn_order").addClass("am-disabled");
            $("#btn_order").html("已预约");
            showToase("预约成功");
        }else{
            showToase("预约失败");
        }
    });
}
function showToase(content){
    var $toast = $('#toast');
    var toastcontent = $('#toast_content');
    toastcontent.html(content);
    if ($toast.css('display') != 'none') {
        return;
    }
    $toast.show();
    setTimeout(function () {
        $toast.hide();
    }, 1000);
}