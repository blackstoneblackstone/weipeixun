package top.wexue.wpx.controller;

import com.foxinmy.weixin4j.qy.model.User;
import com.foxinmy.weixin4j.util.URLEncodingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wexue.base.dao.AuthUserDao;
import top.wexue.base.dao.CourseDao;
import top.wexue.base.dao.PortalDao;
import top.wexue.base.dao.ProjectDao;
import top.wexue.base.entity.TUser;
import top.wexue.base.model.Page;
import top.wexue.base.repository.UserRepository;
import top.wexue.base.utils.MD5Util;
import top.wexue.wpx.api.CommonAPI;
import top.wexue.wpx.api.QyAPI;
import top.wexue.wpx.api.WpxAPI;
import top.wexue.wpx.model.SessionInfo;
import top.wexue.wpx.utils.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 10/14/15.
 * 培训课程
 */
@Controller
@RequestMapping("/pxkc")
public class PxkcController {
    @Autowired
    WpxAPI wpxAPI;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private AuthUserDao authUserDao;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PortalDao webTemplateDao;
    @Autowired
    private CommonAPI commonAPI;


    @RequestMapping(value = "/publicCourse/{corpId}", method = RequestMethod.GET)
    public String publicCourseJsp(HttpServletRequest request, @PathVariable String corpId) throws UnsupportedEncodingException {
        String currentUrl = request.getRequestURL().toString();
        String result = commonAPI.userCreator(request, corpId, currentUrl);
        if (result.contains("redirect")) {
            return result;
        } else {
            TUser user = userRepository.getOne(result);
            request.setAttribute("user", user);
            return "wx/pxkc/publicCourse";
        }
    }

    @RequestMapping(value = "/requiredCourseJsp", method = RequestMethod.GET)
    public String requiredCourseJsp(String code, String state, HttpServletRequest request) {

        return "wx/pxkc/requiredCourse";
    }

    @RequestMapping(value = "/requiredCourseDetailJsp", method = RequestMethod.GET)
    public String requiredCourseDetailJsp(String courseid, String userid, HttpServletRequest request) {
        SessionInfo sessionInfo = WebUtils.getSessionInfo(request);
        Map<String, Object> course = courseDao.getCourseById(courseid);
        Map<String, Object> user = authUserDao.getUserById(userid, sessionInfo.getCorpid());
        request.setAttribute("course", course);
        request.setAttribute("user", user);
        return "wx/pxkc/requiredCourseDetail";

//        User user = null;
//        if (code == null || "".equals(code)) {
//            boolean have = true;
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null && cookies.length > 0) {
//                for (int i = 0; i < cookies.length; i++) {
//                    if (("userid").equals(cookies[i].getName())) {
//                        String value = cookies[i].getValue();
//                        try {
//                            user = wpxAPI.getUserById(value, corpId);
//                            have = false;
//                        } catch (WeixinException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//            if (have) {
//                String redirectUri = "http://www.wexue.top/WPX-web/pxkc/portalWebJsp?corpId=" + corpId;
//                String Url = wpxAPI.getAuthUserInfoUrl(redirectUri, "portalWeb", corpId);
//                try {
//                    response.sendRedirect(Url);
//                    return;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        } else {
//            user = wpxAPI.getCurrentUser(code, corpId);
//            Cookie cookie = new Cookie("userid", user.getUserId());
//            response.addCookie(cookie);
//        }
//        request.setAttribute("user", user);
//        try {
//            request.getRequestDispatcher("/WEB-INF/view/weixin/pxkc/portalWeb.jsp").forward(request,response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @RequestMapping(value = "/publicCourseDetailJsp", method = RequestMethod.GET)
    public String publicCourseDetailJsp(String courseid, HttpServletRequest request) {
        Map<String, Object> course = courseDao.getCourseById(courseid);
        SessionInfo sessionInfo = WebUtils.getSessionInfo(request);
        int result = courseDao.isCourse(sessionInfo.getUserId(), courseid);
        course.put("state", result);
        sessionInfo.getUserId();
        request.setAttribute("course", course);
        request.setAttribute("user", sessionInfo);
        return "wx/pxkc/publicCourseDetail";
    }

    @RequestMapping(value = "/projectDetailJsp", method = RequestMethod.GET)
    public String projectDetailJsp(String projectid, HttpServletRequest request) {
        Map<String, Object> project = projectDao.getProjectById(projectid);
        SessionInfo sessionInfo = WebUtils.getSessionInfo(request);
        request.setAttribute("project", project);
        request.setAttribute("user", sessionInfo);
        return "wx/pxkc/projectDetail";
    }

    @RequestMapping(value = "/requireCourseList", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> requireCourseList(Page page, HttpServletRequest request) {
        SessionInfo sessionInfo = WebUtils.getSessionInfo(request);
        List<Map<String, Object>> courses = courseDao.getRequireCourses(sessionInfo.getCorpid(), page);
        return courses;
    }

    @RequestMapping(value = "/publicCourseList", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> publicCourseList(Page page, HttpServletRequest request) {
        SessionInfo sessionInfo = WebUtils.getSessionInfo(request);
        List<Map<String, Object>> newCourse = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> courses = courseDao.getPublicCourses(sessionInfo.getCorpid(), page);
        for (Map<String, Object> m : courses) {
            int result = courseDao.isCourse(sessionInfo.getUserId(), m.get("id").toString());
            m.put("state", result);
            newCourse.add(m);
        }
        return newCourse;
    }

    @RequestMapping(value = "/projectList", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> projectList(Page page, HttpServletRequest request) {
        SessionInfo sessionInfo = WebUtils.getSessionInfo(request);
        List<Map<String, Object>> projects = projectDao.getProjectListByCorpid(sessionInfo.getCorpid(), page);
        return projects;
    }

    @RequestMapping(value = "/orderCourse", method = RequestMethod.GET)
    @ResponseBody
    public int orderCourse(String courseid, HttpServletRequest request) {
        SessionInfo sessionInfo = WebUtils.getSessionInfo(request);
        int result = courseDao.orderCourse(sessionInfo.getUserId(), courseid);
        return result;
    }


}
