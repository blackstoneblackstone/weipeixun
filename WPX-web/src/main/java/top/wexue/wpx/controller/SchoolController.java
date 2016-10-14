package top.wexue.wpx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.wexue.base.dao.SchoolDao;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by lihb on 10/20/15.
 */
@Controller
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolDao schoolDao;

    @RequestMapping(value = "/schooldesc", method = RequestMethod.GET)
    public String schooldesc(String corpid, HttpServletRequest request) {
        Map<String, Object> school=schoolDao.getSchoolBycorpid(corpid);
        request.setAttribute("body",school.get("schooldesc"));
        return "/wx/school/schooldesc";
    }

    @RequestMapping(value = "/schoolnotify", method = RequestMethod.GET)
    public String schoolnotify(String corpid, HttpServletRequest request) {
        Map<String, Object>  school=schoolDao.getSchoolBycorpid(corpid);
        request.setAttribute("body",school.get("schoolnotify"));
        return "/wx/school/schoolnotify";
    }
    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public String teacher(String corpid, HttpServletRequest request) {

        return "/wx/school/teacher";
    }
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(String corpId, HttpServletRequest request) {
        Map<String, Object> school=schoolDao.getSchoolBycorpid(corpId);
        request.setAttribute("school",school);
        if(school==null||school.get("modelstyle")==null){
            return "/wx/school/index";
        }
        String[] functions=school.get("model").toString().split(",");
        for (int i = 0; i < functions.length; i++) {
            String fun = functions[i];
            if ("xyjj".equals(fun)) {
                request.setAttribute("desc", true);
            }
            if ("gkk".equals(fun)) {
                request.setAttribute("publicCourse", true);
            }
            if ("bxk".equals(fun)) {
                request.setAttribute("requireCourse", true);
            }
            if ("jsk".equals(fun)) {
                request.setAttribute("teacher", true);
            }
            if ("xygg".equals(fun)) {
                request.setAttribute("courseNotify", true);
            }
            if ("kcrj".equals(fun)) {
                request.setAttribute("courseCalendar", true);
            }
        }
        return "/wx/school/index";
    }

}
