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
import top.wexue.base.dao.ProjectLeaderDao;
import top.wexue.base.dao.ResearchDao;
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
@RequestMapping("/platform/research")
public class ResearchController {
    @Autowired
    private ResearchDao researchDao;
    @Autowired
    ProjectLeaderDao projectLeaderDao;
    WeixinAPI weixinAPI;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String research(HttpServletRequest request, String courseid, HttpSession session, Page page) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        List<Map<String, Object>> researchs = new ArrayList<Map<String, Object>>();
        if (BaseMethod.notEmpty(courseid)) {
            researchs = researchDao.getResearchsByCourseId(courseid, sessionInfo.getCorpid(), page);
        } else {
            researchs = researchDao.getResearchs(sessionInfo.getCorpid(), page);
        }
        request.setAttribute("researchs", researchs);
        request.setAttribute("courseid", courseid);
        return "/research/show";
    }

    @RequestMapping(value = "/addSurveyJsp", method = RequestMethod.GET)
    public String addSurveyJsp(String courseid, HttpServletRequest request) {
        request.setAttribute("courseid", courseid);
        return "/research/addSurvey";
    }

    @RequestMapping(value = "/addQuestionJsp", method = RequestMethod.GET)
    public String addQuestonJsp(HttpServletRequest request) {

        return "/research/addQuestion";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void edit(HttpSession session, HttpServletRequest request, HttpServletResponse response, String id, String name, String desc, String starttime, String endtime, String[] fuzeren) {

        researchDao.delete(id);
        projectLeaderDao.delete(id);
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
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
    @ResponseBody
    public Result add(HttpSession session, String rname,String rdesc, String rdata, String rtype) {
        String id = BaseMethod.createUUID(Constants.EntityType.PROJECT);
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        researchDao.save(id,rname,rdesc, rdata, rtype, sessionInfo.getCorpid());
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(String id, HttpServletRequest request, HttpServletResponse response) {

        researchDao.delete(id);
        try {
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
            response.sendRedirect(basePath + "/platform/research");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
