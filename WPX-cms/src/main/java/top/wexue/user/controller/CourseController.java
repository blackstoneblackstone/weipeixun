package top.wexue.user.controller;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.qy.message.NotifyMessage;
import com.foxinmy.weixin4j.qy.model.ChatInfo;
import com.foxinmy.weixin4j.qy.model.IdParameter;
import com.foxinmy.weixin4j.qy.model.User;
import com.foxinmy.weixin4j.qy.type.UserStatus;
import com.foxinmy.weixin4j.tuple.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wexue.common.model.Result;
import top.wexue.common.service.WeixinAPI;
import top.wexue.base.dao.*;
import top.wexue.base.model.Page;
import top.wexue.user.utils.WebUtils;
import top.wexue.base.utils.BaseMethod;
import top.wexue.base.utils.Constants;
import top.wexue.base.model.Course;
import top.wexue.common.model.SessionInfo;

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
    private ProjectDao projectDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private GroupUserDao groupUserDao;
    @Autowired
    private CourseUserDao courseUserDao;
    @Autowired
    private ResearchDao researchDao;
    @Autowired
    private ResearchCourseDao researchCourseDao;
    @Autowired
    private AuthInfoDAO authInfoDAO;
    private WeixinAPI weixinAPI;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String course(HttpServletRequest request, String projectid, HttpSession session, Page page) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        if (BaseMethod.notEmpty(projectid)) {
            request.setAttribute("courses", courseDao.getCoursesByProId(projectid, sessionInfo.getCorpid(), page));
            request.setAttribute("projectid", projectid);
        } else {
            request.setAttribute("courses", courseDao.getCourses(sessionInfo.getCorpid(), page));
        }
        request.setAttribute("prepage", page.getStartPage() - 1);
        request.setAttribute("nextpage", page.getStartPage() + 1);
        return "/course/show";
    }

    @RequestMapping(value = "/editJsp", method = RequestMethod.GET)
    public String editJsp(String id, HttpServletRequest request) {
        Map<String, Object> course = courseDao.getCourseById(id);
        request.setAttribute("course", course);
        return "/course/edit";
    }

    @RequestMapping(value = "/sendResearchJsp", method = RequestMethod.GET)
    public String sendResearchJsp(String id, HttpServletRequest request, HttpSession session, Page page) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        List<Map<String, Object>> researchs = researchDao.getResearchs(sessionInfo.getCorpid(), page);
        request.setAttribute("researchs", researchs);
        request.setAttribute("courseid", id);
        return "/course/sendResearch";
    }

    @RequestMapping(value = "/sendResearch", method = RequestMethod.GET)
    @ResponseBody
    public Result sendResearch(String courseid, String researchid, HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        Map<String, Object> research = researchDao.getResearchById(researchid);
        List<Map<String, Object>> courseUsers = courseUserDao.getUserListByCourseId(courseid);
        News news = new News();
        Map<String, Object> agent = authInfoDAO.getAuthInfoByAppId(sessionInfo.getCorpid(), BaseMethod.WDKCAPPID);
        IdParameter idParameter = new IdParameter();
        List<String> userIds = new ArrayList<String>();
        for (Map<String, Object> courseUser : courseUsers) {
            userIds.add(courseUser.get("userid").toString());
        }
        idParameter.setUserIds(userIds);
        NotifyMessage notify = new NotifyMessage(Integer.valueOf(agent.get("agentid").toString()), news, idParameter, false);
        news.addArticle(research.get("rname").toString(), research.get("rdesc").toString(), "http://www.wexue.top:20000/images/survey-default.jpg", "http://www.wexue.top/research/survey?corpid=" + sessionInfo.getCorpid() + "&id=" + researchid);
        //news.addArticle("title2", "desc2", "picUrl2", "url2");
        Result result = new Result();
        if (userIds == null || userIds.isEmpty()) {
            result.setSuccess(false);
            result.setMsg("发送失败，该课程还没有人订阅呢");
        } else {
            IdParameter jo = null;
            try {
                jo = weixinAPI.sendNotifyMessage(notify);
            } catch (WeixinException e1) {
                result.setSuccess(false);
                result.setSysErrorMsg();
            }

            //记录使用情况
            researchCourseDao.add(courseid, researchid);
            //标记已使用
            researchDao.isUsed(researchid);
            result.setSuccess(true);
            result.setMsg("发送成功，" + jo.toString());
        }
        return result;
    }

    @RequestMapping(value = "/createStudyGroupJsp", method = RequestMethod.GET)
    public String createStudyGroupJsp(String id, HttpServletRequest request, HttpSession session, Page page) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        List<Map<String, Object>> users = courseDao.getUsersByCourseid(sessionInfo.getCorpid(), id);
        Map<String, Object> course = courseDao.getCourseById(id);
        request.setAttribute("courseid", id);
        request.setAttribute("users", users);
        request.setAttribute("coursename", course.get("coursename").toString());
        return "/course/createStudyGroup";
    }

    @RequestMapping(value = "/createBigStudyGroup", method = RequestMethod.GET)
    @ResponseBody
    public Result createStudyGroupJsp(String courseid, HttpServletRequest request, HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        Result result = new Result();
        return result;
    }

    @RequestMapping(value = "/createSmallStudyGroup", method = RequestMethod.POST)
    @ResponseBody
    public Result createStudyGroupJsp(String courseid, String gname, String gdesc, String[] gusers, HttpSession session) {
        Result result = new Result();
        if (!(gusers != null && gusers.length > 3)) {
            result.setSuccess(false);
            result.setMsg("人数太少，至少三个人才能聊吧");
            return result;
        }
        try {
            SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
            ChatInfo chatInfo = new ChatInfo(gname, sessionInfo.getUserId(), gusers);
            String chatS = weixinAPI.createChat(chatInfo);
            Map<String, Object> course = courseDao.getCourseById(courseid);
            weixinAPI.sendGroupTextChatMessage(chatS, sessionInfo.getUserId(), "这里是【" + course.get("coursename").toString() + "】的学习小组，大家尽量讨论课程相关问题欧,本小组由天天微学提供技术。");
            weixinAPI.sendGroupTextChatMessage(chatS, sessionInfo.getUserId(), gdesc);
            result.setSuccess(true);
            result.setMsg("学习小组创建成功");
            //保存小组
            for (String guser : gusers) {
                groupUserDao.save(guser, chatS);
            }
            groupDao.save(chatS, gname, gdesc, courseid, null, sessionInfo.getCorpid());
        } catch (WeixinException e) {
            result.setSuccess(false);
            result.setSysErrorMsg();
            System.out.println(e.getLocalizedMessage());
        }
        return result;
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
    public String addJsp(HttpSession session, int type, Page page, HttpServletRequest request, String proId) {

        List<User> users = new ArrayList<User>(0);
        try {
            users = weixinAPI.listUser(1, false, UserStatus.BOTH, true);
        } catch (WeixinException e) {
            e.printStackTrace();
        }
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        List<Map<String, Object>> projects = projectDao.getProjectListByCorpid(sessionInfo.getCorpid(), page);
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
    @ResponseBody
    public Result delete(String id, HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        courseDao.delete(id);
        courseUserDao.deleteUser(id);
        courseUserDao.deleteSpeaker(id);
        courseUserDao.deleteLeader(id);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/courseByproId", method = RequestMethod.GET)
    @ResponseBody
    public Result courseByproId(String proid, HttpServletRequest request) {
        Result result = new Result();
        SessionInfo sessionInfo = WebUtils.getSessionInfo(request);
        List<Map<String, Object>> courses = courseDao.getCoursesByProId(proid, sessionInfo.getCorpid());
        result.setObj(courses);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/fuzerenById", method = RequestMethod.GET)
    @ResponseBody
    public Result fuzerenById(String id, HttpServletRequest request) {
        SessionInfo sessionInfo = WebUtils.getSessionInfo(request);
        Result result = new Result();
        List<Map<String, Object>> courses = courseDao.getCoursesByProId(id, sessionInfo.getCorpid());
        result.setObj(courses);
        result.setSuccess(true);
        return result;
    }
}
