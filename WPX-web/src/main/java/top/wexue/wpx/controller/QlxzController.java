package top.wexue.wpx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lihb on 10/14/15.
 * 培训课程
 */
@Controller
@RequestMapping("/qlxz")
public class QlxzController {

    @RequestMapping(value = "/newChatJsp", method = RequestMethod.GET)
    public String newChat(@PathVariable String corpid) {
        return "/wdkc/newChat";


    }

    @RequestMapping(value = "/myChatJsp", method = RequestMethod.GET)
    public String myChat(@PathVariable String corpid) {

        return "/wdkc/myChat";

    }

    @RequestMapping(value = "/studyGroupJsp", method = RequestMethod.GET)
    public String studyGroup(@PathVariable String corpid) {

        return "/wdkc/studyGroup";

    }

}
