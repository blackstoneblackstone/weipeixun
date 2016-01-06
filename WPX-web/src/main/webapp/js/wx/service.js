$.publicData = {
    publicCourseData: function (contentdata) {
        var dtd = $.Deferred();
        var url = "/pxkc/publicCourseList";
        var success = function (data) {
            //适配
            //desc: "新员工入职培训"
            //detaillink: "http://localhost:8080/pxkc/publicCourseDetailJsp?courseid=COURSE22243cf3-7216-431b-91d1-1e394f58fc80&userid=lihb"
            //img: "http://www.ekaola.com/assets/package/cover/corp_phpyD9QiQ1448334050.png"
            //orderlink: "http://www.sina.com"
            //teacher: "李老师"
            //
            //corpid: "wxf54e1b5e0b62fa96"
            //coursedesc: "sfaf"
            //coursename: "文本回复"
            //coursetype: 2
            //createtime: "1446215608450"
            //endtime: "2015-10-06"
            //expectperson: 232
            //factperson: 0
            //icon: "http://www.wexue.top:20000/images/course-default.png"
            //id: "COURSEa49c99f1-d7ef-4e9f-8066-b4ab8ee52b30"
            //place: null
            //projectid: "PROf1d8fa59-53eb-4684-8bf0-8042a7fb8d1f"
            //starttime: "2015-09-28"
            for (var i = 0; i < data.length; i++) {
                var o = {
                    desc: "<h2 class='am-slider-title'>" + data[i].coursename + "</h2><p>" + data[i].coursedesc + "</p>",
                    img: data[i].icon,
                    time: data[i].starttime.replace(/-/g, "/") + "-" + data[i].endtime.replace(/-/g, "/").replace("2015/", ""),
                    detaillink: "/pxkc/publicCourseDetailJsp?courseid=" + data[i].id,
                    orderbtn: "onclick=ordercourse('" + data[i].id + "')"
                };
                console.log(data[i].state);
                if (data[i].state == 0) {
                    o.state1 = "1";
                } else {
                    o.state2 = "1";
                }
                contentdata.push(o);
            }
            console.log(contentdata);
            dtd.resolve();
        }
        ajaxGet(url, success);
        return dtd.promise();
    },
    projectData: function (contentdata) {
        var dtd = $.Deferred();
        var url = "/pxkc/projectList";
        var success = function (data) {
            //"link": "http://www.baidu.com", // 链接
            //    "thumb": "", // 缩略图
            //    "desc": '<div class="am-slider-content"><h2 class="am-slider-title">新员工入职培训</h2><p>新员工入职必备课程</p></div><a class="am-slider-more">了解更多</a>',// 附加信息，支持DOM，为高级定制提供DOM接口
            //    "img": "http://s.amazeui.org/media/i/demos/bing-1.jpg"
            //corpid: "wxf54e1b5e0b62fa96"
            //createtime: "1446119963872"
            //endtime: "2015-10-06"
            //id: "PROf1d8fa59-53eb-4684-8bf0-8042a7fb8d1f"
            //prodesc: "fasfa"
            //proname: "fsafas"
            //starttime: "2015-10-05"
            for (var i = 0; i < data.length; i++) {
                var o = {
                    desc: '<div class="am-slider-content"><h2 class="am-slider-title"><span class="am-badge am-badge-success am-radius">项目</span>&nbsp;' + data[i].proname + '</h2><p>' + data[i].prodesc + '</p></div><a class="am-slider-more">了解更多</a>',
                    img: "http://www.wexue.top:20000/images/sky-2.jpg",
                    link: "/pxkc/projectDetailJsp?projectid=" + data[i].id
                };
                contentdata.push(o);
            }
            dtd.resolve();
        }
        ajaxGet(url, success);
        return dtd.promise();
    }
}


$.myCourseData = {
    myCourseData: function (contentdata) {
        var dtd = $.Deferred();
        var url = "/wdkc/myCourseList";
        var success = function (data) {
            //适配
            //type:"red",
            //    month: "8",
            //    day: "3",
            //    time: "2:00",
            //    title: "哈哈哈哈哈",
            //    teachername: "李老师",
            //    teacherdesc: "李老四这牛逼"

            //corpid: "wxf54e1b5e0b62fa96"
            //coursedesc: "sfaf"
            //coursename: "文本回复"
            //coursetype: 2
            //createtime: "1446215608450"
            //endtime: "2015-10-06"
            //expectperson: 232
            //factperson: 0
            //icon: "http://www.wexue.top:20000/images/course-default.png"
            //id: "COURSEa49c99f1-d7ef-4e9f-8066-b4ab8ee52b30"
            //place: null
            //projectid: "PROf1d8fa59-53eb-4684-8bf0-8042a7fb8d1f"
            //starttime: "2015-09-28"
            for (var i = 0; i < data.length; i++) {
                var o = {
                    title: data[i].coursename,
                    day: data[i].starttime.split("-")[2],
                    month: data[i].starttime.split("-")[1],
                    people: data[i].factperson,
                    teachername:data[i].teachername,
                    teacherdesc:data[i].teacherdesc,
                    teacheravator:data[i].teacheravator,
                    link: "/pxkc/publicCourseDetailJsp?courseid=" + data[i].id
                };
                if (data[i].coursetype == 2) {
                    o.type = "red";
                    o.require=true;
                } else {
                    o.public=true;
                    o.type = "green";
                }
                contentdata.push(o);
            }
            dtd.resolve();
        }
        ajaxGet(url, success);
        return dtd.promise();
    }
}

