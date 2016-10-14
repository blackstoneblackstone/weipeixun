package top.wexue.user.controller;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.qy.model.ChatInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wexue.common.model.Result;
import top.wexue.common.service.WeixinAPI;
import top.wexue.base.dao.*;
import top.wexue.base.model.Page;
import top.wexue.common.model.SessionInfo;
import top.wexue.base.utils.BaseMethod;
import top.wexue.base.utils.Constants;

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
@RequestMapping("/platform/group")
public class GroupController {
    @Autowired
    private GroupUserDao groupUserDao;
    @Autowired
    private CourseUserDao courseUserDao;
    @Autowired
    private GroupDao groupDao;
    private WeixinAPI weixinAPI;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String group(String courseid, HttpServletRequest request, HttpSession session, Page page) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        if (BaseMethod.notEmpty(courseid)) {
            request.setAttribute("groups", groupDao.getGroupsByCourseId(courseid, sessionInfo.getCorpid(), page));
        } else {
            request.setAttribute("groups", groupDao.getGroups(sessionInfo.getCorpid(), page));
        }
        request.setAttribute("prepage", page.getStartPage() - 1);
        request.setAttribute("nextpage", page.getStartPage() + 1);
        return "/group/show";
    }

    @RequestMapping(value = "/addJsp", method = RequestMethod.GET)
    public String addJsp() {

        return "/group/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(String gname, String gdesc, String[] gusers, HttpSession session) {
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
            weixinAPI.sendGroupTextChatMessage(chatS, sessionInfo.getUserId(), gdesc);
            result.setSuccess(true);
            result.setMsg("学习小组创建成功");
            //保存小组
            for (String guser : gusers) {
                groupUserDao.save(guser, chatS);
            }
            groupDao.save(chatS, gname, gdesc, null, null, sessionInfo.getCorpid());
        } catch (WeixinException e) {
            result.setSuccess(false);
            result.setMsg(e.getLocalizedMessage());
        }
        return result;
    }

    @RequestMapping(value = "/viewuser", method = RequestMethod.GET)
    @ResponseBody
    public Result viewuser(String groupid) {
        Result result = new Result();
        List<Map<String, Object>> users = groupDao.getUsersByGroupId(groupid);
        result.setObj(users);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/editJsp", method = RequestMethod.GET)
    public String editJsp(String id, HttpServletRequest request) {
        Map<String, Object> group = groupDao.getGroupById(id);
        List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> gusers = groupUserDao.getUsersByGroupId(id);
        if (group.get("courseid") != null && BaseMethod.notEmpty(group.get("courseid").toString())) {
            users = courseUserDao.getUserListByCourseId(group.get("courseid").toString());
        }
        if (group.get("projectid") != null && BaseMethod.notEmpty(group.get("projectid").toString())) {
            users = courseUserDao.getUserListByProjectId(group.get("courseid").toString());
        }
        users.removeAll(gusers);
        request.setAttribute("users", users);
        request.setAttribute("gusers", gusers);
        request.setAttribute("group", group);
        return "/group/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void edit(String id, String gname, String gdesc, String[] guser, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        groupDao.delete(id);
        groupUserDao.delete(id);
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        groupDao.save(id, gname, gdesc, null, null, sessionInfo.getCorpid());
        for (String user : guser) {
            if (!"0".equals(user)) {
                groupUserDao.save(user, id);
            }
        }
        try {
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
            response.sendRedirect(basePath + "/platform/group");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(String id, HttpSession session) {
        Result result = new Result();
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        try {
            weixinAPI.sendGroupTextChatMessage(id, sessionInfo.getUserId(), "这个小组解散了，大家有序退出。");
            groupDao.delete(id);
            groupUserDao.delete(id);
            weixinAPI.quitChat(id, sessionInfo.getUserId());
            result.setSuccess(true);
            result.setMsg("已经退出会话。");
        } catch (WeixinException e) {
            result.setSysErrorMsg();
            result.setSuccess(false);
            logger.error(e.getLocalizedMessage());
        }
        return result;
    }
}
