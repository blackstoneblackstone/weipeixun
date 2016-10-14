/**
 * Created by lihb on 2/6/16.
 */
$(function () {
    $('.tool').tooltip();
    $("#Modal").on("hidden.bs.modal", function() {
        $(this).removeData("bs.modal");
    });
    String.prototype.format = function(args) {
        if (arguments.length>0) {
            var result = this;
            if (arguments.length == 1 && typeof (args) == "object") {
                for (var key in args) {
                    var reg=new RegExp ("({"+key+"})","g");
                    result = result.replace(reg, args[key]);
                }
            }
            else {
                for (var i = 0; i < arguments.length; i++) {
                    if(arguments[i]==undefined)
                    {
                        return "";
                    }
                    else
                    {
                        var reg=new RegExp ("({["+i+"]})","g");
                        result = result.replace(reg, arguments[i]);
                    }
                }
            }
            return result;
        }
        else {
            return this;
        }
    }
});

function showTip(tipText,state) {
    var tip= $('#tip');
    tip.text(tipText);
    tip.removeClass("hidden");
    if(state=="failure"){
        tip.addClass("alert-danger");
    }
    if(state=="success"){
        tip.addClass("alert-success");
    }
    var time = setTimeout(function () {
        tip.addClass("hidden");
        tip.removeClass("alert-success");
        tip.removeClass("alert-danger");
    }, 3000);
}