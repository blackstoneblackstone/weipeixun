package top.wexue.user.controller;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.qy.model.User;
import com.foxinmy.weixin4j.qy.type.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wexue.common.model.Result;
import top.wexue.common.service.WeixinAPI;
import top.wexue.model.Page;
import top.wexue.user.utils.WebUtils;
import top.wexue.utils.BaseMethod;
import top.wexue.utils.Constants;
import top.wexue.dao.CourseDao;
import top.wexue.dao.CourseUserDao;
import top.wexue.dao.ProjectDao;
import top.wexue.model.Course;
import top.wexue.model.SessionInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 9/5/15.
 */
@Controller
@RequestMapping("/platform/course")
public class CourseController {
    @Autowired
    ProjectDao projectDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    CourseUserDao courseUserDao;
    @Autowired
    WeixinAPI weixinAPI;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String course(HttpServletRequest request, HttpSession session,Page page) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        List<Map<String, Object>> projects = projectDao.getProjectListByCorpid(sessionInfo.getCorpid(),page);
        request.setAttribute("projects", projects);
        return "/course/show";
    }

    @RequestMapping(value = "/courseByProId", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> courseByProId(String proId,String corpid) {
        List<Map<String, Object>> courses = courseDao.getCoursesByProId(proId,corpid);
        return courses;
    }

    @RequestMapping(value = "/editJsp", method = RequestMethod.GET)
    public String editJsp(String id, HttpServletRequest request) {
        Map<String, Object> course = courseDao.getCourseById(id);
        request.setAttribute("course", course);
        return "/course/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void edit(HttpSession session, HttpServletRequest request, HttpServletResponse response, Course course) {
        String id = course.getId();
        courseDao.delete(id);
        courseUserDao.deleteUser(id);
        courseUserDao.deleteSpeaker(id);
        courseUserDao.deleteLeader(id);

        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        courseDao.save(course, sessionInfo.getCorpid());
        if (course.getFuzeren() != null) {
            for (String f : course.getFuzeren()) {
                courseUserDao.setLeader(id, f);
            }
        }
        if (course.getCanyuren() != null) {
            for (String f : course.getCanyuren()) {
                courseUserDao.setUser(id, f);
            }
        }
        if (course.getZhujiangren() != null) {
            for (String f : course.getZhujiangren()) {
                courseUserDao.setSpeaker(id, f);
            }
        }
        try {
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
            response.sendRedirect(basePath + "/platform/course");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addJsp", method = RequestMethod.GET)
    public String addJsp(HttpSession session, int type,Page page, HttpServletRequest request, String proId) {

        List<User> users = new ArrayList<User>(0);
        try {
            users = weixinAPI.listUser(1, false, UserStatus.BOTH, true);
        } catch (WeixinException e) {
            e.printStackTrace();
        }
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        List<Map<String, Object>> projects = projectDao.getProjectListByCorpid(sessionInfo.getCorpid(),page);
        request.setAttribute("type", type);
        request.setAttribute("proId", proId);
        request.setAttribute("projects", projects);
        request.setAttribute("users", users);
        return "course/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(HttpSession session, HttpServletRequest request, HttpServletResponse response, Course course) {
        String courseId = BaseMethod.createUUID(Constants.EntityType.COURSE);
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        course.setId(courseId);
        courseDao.save(course, sessionInfo.getCorpid());
        if (course.getFuzeren() != null) {
            for (String f : course.getFuzeren()) {
                courseUserDao.setLeader(courseId, f);
            }
        }
        if (course.getCanyuren() != null) {
            for (String f : course.getCanyuren()) {
                courseUserDao.setUser(courseId, f);
            }
        }
        if (course.getZhujiangren() != null) {
            for (String f : course.getZhujiangren()) {
                courseUserDao.setSpeaker(courseId, f);
            }
        }
        try {
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
            response.sendRedirect(basePath + "/platform/course");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(String id, HttpServletRequest request, HttpServletResponse response) {

        courseDao.delete(id);
        courseUserDao.deleteUser(id);
        courseUserDao.deleteSpeaker(id);
        courseUserDao.deleteLeader(id);

        try {
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
            response.sendRedirect(basePath + "/platform/course");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/courseByproId", method = RequestMethod.GET)
    @ResponseBody
    public Result courseByproId(String proid,HttpServletRequest request) {
        Result result = new Result();
        SessionInfo sessionInfo=WebUtils.getSessionInfo(request);
        List<Map<String, Object>> courses = courseDao.getCoursesByProId(proid,sessionInfo.getCorpid());
        result.setObj(courses);
        result.setSuccess(true);
        return result;
    }
    @RequestMapping(value = "/fuzerenById", method = RequestMethod.GET)
    @ResponseBody
    public Result fuzerenById(String id,HttpServletRequest request) {
        SessionInfo sessionInfo=WebUtils.getSessionInfo(request);
        Result result = new Result();
        List<Map<String, Object>> courses = courseDao.getCoursesByProId(id,sessionInfo.getCorpid());
        result.setObj(courses);
        result.setSuccess(true);
        return result;
    }
}
