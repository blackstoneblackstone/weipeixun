package top.wexue.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import top.wexue.common.service.WeixinAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lihb on 9/5/15.
 */
@Controller
@RequestMapping("/platform")
public class IndexController {
    @Autowired
    WeixinAPI weixinAPI;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "platform";
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/research", method = RequestMethod.GET)
    public String research() {
        return "research";
    }

    @RequestMapping(value = "/exam", method = RequestMethod.GET)
    public String exam() {
        return "exam";
    }

    @RequestMapping(value = "/studyGroup", method = RequestMethod.GET)
    public String studyGroup() {
        return "studyGroup";
    }

    @RequestMapping(value = "statistics", method = RequestMethod.GET)
    public String statistics() {
        return "statistics";
    }

    @RequestMapping(value = "resource", method = RequestMethod.GET)
    public String resource() {
        return "resource";
    }

    @RequestMapping(value = "/addressbook", method = RequestMethod.GET)
    public String addressBook(HttpServletRequest request,HttpSession session) {
        return "addressbook/show";
    }

}
