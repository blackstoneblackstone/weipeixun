package top.wexue.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import top.wexue.common.service.WeixinAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.wexue.dao.SchoolDao;
import top.wexue.model.SessionInfo;
import top.wexue.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by lihb on 9/5/15.
 */
@Controller
@RequestMapping("/platform")
public class IndexController {
    @Autowired
    SchoolDao schoolDao;
    @Autowired
    WeixinAPI weixinAPI;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "platform";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpSession session, HttpServletRequest request) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        Map<String, Object> school = schoolDao.getSchoolBycorpid(sessionInfo.getCorpid());
        String schooldesc = "";
        String schoolnotify = "";
        if (school != null) {
            schooldesc = school.get("schooldesc").toString();
            schoolnotify = school.get("schoolnotify").toString();

        }
        request.setAttribute("schooldesc", schooldesc);
        request.setAttribute("schoolnotify", schoolnotify);
        return "home";
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


    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(int type) {
        if (type == 403) {
            return "error403";
        }
        if (type == 404) {
            return "error404";
        }
        if (type == 405) {
            return "error405";
        }
        return "error500";
    }
}
