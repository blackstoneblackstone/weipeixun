package top.wexue.wpx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lihb on 10/20/15.
 */
@Controller
@RequestMapping("/app")
public class AppController {

    @RequestMapping(value = "/weipeixun", method = RequestMethod.GET)
    public String weipeixun() {

        return "/pc/weipeixun";
    }

    @RequestMapping(value = "/pxkc", method = RequestMethod.GET)
    public String pxkc() {

        return "/pc/pxkc";
    }

    @RequestMapping(value = "/wdkc", method = RequestMethod.GET)
    public String wdkc() {

        return "/pc/wdkc";
    }

    @RequestMapping(value = "/qlxz", method = RequestMethod.GET)
    public String qlxz() {

        return "/pc/qlxz";
    }
}