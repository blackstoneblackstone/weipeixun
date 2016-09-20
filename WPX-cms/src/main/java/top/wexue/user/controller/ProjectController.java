package top.wexue.user.controller;

import com.foxinmy.weixin4j.qy.model.ChatInfo;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wexue.common.model.Result;
import top.wexue.common.service.WeixinAPI;
import top.wexue.base.dao.GroupDao;
import top.wexue.base.dao.GroupUserDao;
import top.wexue.base.model.Page;
import top.wexue.base.utils.BaseMethod;
import top.wexue.base.utils.Constants;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.qy.model.User;
import com.foxinmy.weixin4j.qy.type.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.wexue.base.dao.ProjectDao;
import top.wexue.base.dao.ProjectLeaderDao;
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
@RequestMapping("/platform/project")
public class ProjectController {
    @Autowired
    ProjectDao projectDao;
    @Autowired
    ProjectLeaderDao projectLeaderDao;
    @Autowired
    private GroupDao groupDao;
    WeixinAPI weixinAPI;
    @Autowired
    private GroupUserDao groupUserDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String project(HttpServletRequest request, HttpSession session,Page page) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        List<Map<String, Object>> projects = projectDao.getProjectListByCorpid(sessionInfo.getCorpid(),page);
        request.setAttribute("projects", projects);
        List<User> users = new ArrayList<User>(0);
        try {
            users = weixinAPI.listUser(1, false, UserStatus.BOTH, true);
        } catch (WeixinException e) {
            e.printStackTrace();
        }
        request.setAttribute("users", users);
        request.setAttribute("prepage",page.getStartPage()-1);
        request.setAttribute("nextpage",page.getStartPage()+1);
        return "/project/show";
    }

    @RequestMapping(value = "/editJsp", method = RequestMethod.GET)
    public String editJsp(String id, HttpServletRequest request) {

        Map<String, Object> project = projectDao.getProjectById(id);
        request.setAttribute("project", project);
        return "project/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void edit(HttpSession session, HttpServletRequest request, HttpServletResponse response, String id, String name, String desc, String starttime, String endtime, String[] fuzeren) {

        projectDao.delete(id);
        projectLeaderDao.delete(id);
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        projectDao.save(id, name, desc, starttime, endtime, sessionInfo.getCorpid());
        if (fuzeren != null) {
            for (String f : fuzeren) {
                projectLeaderDao.save(id, f);
            }
        }
        try {
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
            response.sendRedirect(basePath + "/platform/project");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/createStudyGroupJsp", method = RequestMethod.GET)
    public String createStudyGroupJsp(String projectid, HttpServletRequest request) {
        request.setAttribute("projectid", projectid);
        return "project/createStudyGroup";
    }

    @RequestMapping(value = "/addJsp", method = RequestMethod.GET)
    public String addJsp(HttpSession session, HttpServletRequest request) {
        List<User> users = new ArrayList<User>(0);
        try {
            users = weixinAPI.listUser(1, false, UserStatus.BOTH, true);
        } catch (WeixinException e) {
            e.printStackTrace();
        }
        request.setAttribute("users", users);
        return "project/add";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(HttpSession session, HttpServletRequest request, HttpServletResponse response, String name, String desc, String starttime, String endtime, String[] fuzeren) {
        String proId = BaseMethod.createUUID(Constants.EntityType.PROJECT);
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        projectDao.save(proId, name, desc, starttime, endtime, sessionInfo.getCorpid());
        if (fuzeren != null) {
            for (String f : fuzeren) {
                projectLeaderDao.save(proId, f);
            }
        }
        try {
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
            response.sendRedirect(basePath + "/platform/project");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/createStudyGroup", method = RequestMethod.POST)
    @ResponseBody
    public Result add(String gname, String gdesc,String projectid, String[] gusers, HttpSession session) {
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
            weixinAPI.sendGroupTextChatMessage(chatS,sessionInfo.getUserId(),gdesc);
            result.setSuccess(true);
            result.setMsg("学习小组创建成功");
            //保存小组
            for (String guser : gusers) {
                groupUserDao.save(guser, chatS);
            }
            groupDao.save(chatS, gname, gdesc, projectid, null, sessionInfo.getCorpid());
        } catch (WeixinException e) {
            result.setSuccess(false);
            result.setMsg(e.getLocalizedMessage());
        }
        return result;
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(String id, HttpServletRequest request, HttpServletResponse response) {
        projectDao.delete(id);
        projectLeaderDao.delete(id);
        Result result=new Result();
        result.setSuccess(true);
        return  result;
    }
}
