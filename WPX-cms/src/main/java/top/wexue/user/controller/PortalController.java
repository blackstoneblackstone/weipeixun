package top.wexue.user.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wexue.common.model.Result;
import top.wexue.base.dao.PortalDao;
import top.wexue.base.dao.ProjectDao;
import top.wexue.base.dao.ProjectLeaderDao;
import top.wexue.base.dao.SchoolDao;
import top.wexue.common.model.SessionInfo;
import top.wexue.base.utils.BaseMethod;
import top.wexue.base.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 9/5/15.
 */
@Controller
@RequestMapping("/platform/portal")
public class PortalController {
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private PortalDao portalDao;
    @Autowired
    private SchoolDao schoolDao;
    @Autowired
    private ProjectLeaderDao projectLeaderDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String portal(HttpServletRequest request, HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
//        Map<String, Object> school=schoolDao.getSchoolBycorpid(sessionInfo.getCorpid());
//        String schooldesc="";
//        String schoolnotify="";
//        if(school!=null){
//            schooldesc= school.get("schooldesc").toString();
//            schoolnotify= school.get("schoolnotify").toString();
//
//        }
//        request.setAttribute("schooldesc", schooldesc);
//        request.setAttribute("schoolnotify", schoolnotify);
        List<Map<String, Object>> portals = portalDao.getPortals(sessionInfo.getCorpid());
        request.setAttribute("portals", portals);
        return "/portal/show";
    }

    @RequestMapping(value = "/tpl", method = RequestMethod.GET)
    public String editJsp(String id, String json, HttpServletRequest request) {
        JSONObject a = new JSONObject(json);
        JSONArray functions = a.getJSONArray("functions");
        for (int i = 0; i < functions.length(); i++) {
            String fun = functions.getString(i);
            if ("desc".equals(fun)) {
                request.setAttribute("desc", true);
            }
            if ("publicCourse".equals(fun)) {
                request.setAttribute("publicCourse", true);
            }
            if ("requireCourse".equals(fun)) {
                request.setAttribute("requireCourse", true);
            }
            if ("teacher".equals(fun)) {
                request.setAttribute("teacher", true);
            }
            if ("courseNotify".equals(fun)) {
                request.setAttribute("courseNotify", true);
            }
            if ("courseCalendar".equals(fun)) {
                request.setAttribute("courseCalendar", true);
            }
        }
        return "portal/templates/" + id;
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

    @RequestMapping(value = "/addJsp", method = RequestMethod.GET)
    public String addJsp(HttpSession session, HttpServletRequest request) {
        return "portal/add";
    }

    @RequestMapping(value = "/addDescriptionJsp", method = RequestMethod.GET)
    public String addDescriptionJsp(String id, HttpServletRequest request) {
        request.setAttribute("id", id);
        Map<String, Object> portal = portalDao.getPortalById(id);
        request.setAttribute("portal", portal);
        return "portal/addDescription";
    }

    @RequestMapping(value = "/addNotifyJsp", method = RequestMethod.GET)
    public String addNotifyJsp(String id, HttpServletRequest request) {
        request.setAttribute("id", id);
        Map<String, Object> portal = portalDao.getPortalById(id);
        request.setAttribute("portal", portal);
        return "portal/addNotify";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public Result add(HttpSession session, String wdesc, String[] model, String modelstyle) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        Result result = new Result();
        String id = BaseMethod.createUUID(Constants.EntityType.PORTAL);
        int resultInt = portalDao.save(id, wdesc, BaseMethod.join(model), modelstyle, sessionInfo.getCorpid());
        if (resultInt == 1) {
            result.setSuccess(true);
            result.setMsg("添加成功");
        } else {
            result.setSuccess(false);
            result.setSysErrorMsg();
        }
        return result;
    }

    @RequestMapping(value = "/updateSchoolNotify", method = RequestMethod.POST)
    @ResponseBody
    public Result updateSchoolNotify(String schoolnotify, String id) {
        Result result = new Result();
        int resultInt = portalDao.updateSchoolNotify(id, schoolnotify);
        if (resultInt == 1) {
            result.setSuccess(true);
            result.setMsg("添加成功");
        } else {
            result.setSuccess(false);
            result.setSysErrorMsg();
        }
        return result;
    }

    @RequestMapping(value = "/updateSchoolDesc", method = RequestMethod.POST)
    @ResponseBody
    public Result updateSchoolDesc(String schooldesc, String id) {
        Result result = new Result();
        int resultInt = portalDao.updateSchoolDesc(id, schooldesc);
        if (resultInt == 1) {
            result.setSuccess(true);
            result.setMsg("添加成功");
        } else {
            result.setSuccess(false);
            result.setSysErrorMsg();
        }
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(HttpSession session, String schooldesc, String schoolnotify) {
        schooldesc = schooldesc.replace("<html><head>", "<html><head><meta name=\"viewport\"" +
                "          content=\"width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>");
        schoolnotify = schoolnotify.replace("<html><head>", "<html><head><meta name=\"viewport\"" +
                "          content=\"width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>");
        String schoolId = BaseMethod.createUUID(Constants.EntityType.PROJECT);
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        schoolDao.save(schoolId, sessionInfo.getCorpid(), schooldesc, schoolnotify);
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/saveindex", method = RequestMethod.POST)
    @ResponseBody
    public Result saveindex(HttpSession session, String schoolindex) {
        String schoolId = BaseMethod.createUUID(Constants.EntityType.PROJECT);
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        schoolDao.saveIndex(schoolId, sessionInfo.getCorpid(), schoolindex);
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/saveNotify", method = RequestMethod.POST)
    public void saveNotify(HttpSession session, HttpServletRequest request, HttpServletResponse response, String name, String desc, String starttime, String endtime, String[] fuzeren) {
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

    @RequestMapping(value = "/open", method = RequestMethod.GET)
    @ResponseBody
    public Result open(String id,HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        Result result = new Result();
        int reint = portalDao.open(id,sessionInfo.getCorpid());
        if (reint == 1) {
            result.setSuccess(true);
            result.setMsg("已开启，您可以预览");
        } else {
            result.setSuccess(false);
            result.setSysErrorMsg();
        }
        return result;
    }

    @RequestMapping(value = "/noOpen", method = RequestMethod.GET)
    @ResponseBody
    public Result noOpen(String id) {
        Result result = new Result();
        int reint = portalDao.noOpen(id);
        if (reint == 1) {
            result.setSuccess(true);
            result.setMsg("已禁用");
        } else {
            result.setSuccess(false);
            result.setSysErrorMsg();
        }
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(String id) {
        Result result = new Result();
        int reint = portalDao.delete(id);
        if (reint == 1) {
            result.setSuccess(true);
            result.setMsg("删除成功");
        } else {
            result.setSuccess(false);
            result.setSysErrorMsg();
        }
        return result;
    }
}
