package top.wexue.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wexue.base.dao.SpeakerDao;
import top.wexue.base.model.Page;
import top.wexue.common.model.SessionInfo;
import top.wexue.base.utils.BaseMethod;
import top.wexue.base.utils.Constants;
import top.wexue.common.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by lihb on 9/5/15.
 */
@Controller
@RequestMapping("/platform/speaker")
public class SpeakerController {
    @Autowired
    private SpeakerDao speakerDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String course(HttpServletRequest request, HttpSession session,Page page) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        request.setAttribute("speakers",speakerDao.getSpeakers(sessionInfo.getCorpid(),page));
        request.setAttribute("prepage", page.getStartPage() - 1);
        request.setAttribute("nextpage", page.getStartPage() + 1);
        return "/speaker/show";
    }
    @RequestMapping(value = "/addJsp", method = RequestMethod.GET)
    public String addJsp(HttpSession session, HttpServletRequest request) {
        return "/speaker/add";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(String sname, String sdesc, String avator, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        String id = BaseMethod.createUUID(Constants.EntityType.COURSE);
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        speakerDao.save(id, sname, sdesc, avator,sessionInfo.getCorpid());
        try {
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
            response.sendRedirect(basePath + "/platform/speaker");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/editJsp", method = RequestMethod.GET)
    public String editJsp(String id, HttpServletRequest request) {

        Map<String, Object> speaker = speakerDao.getSpeakerById(id);
        request.setAttribute("speaker", speaker);
        return "/speaker/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void edit(String id,String sname,String sdesc,String avator,HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        speakerDao.delete(id);
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        speakerDao.save(id, sname, sdesc, avator,sessionInfo.getCorpid());
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
    public Result delete(String id, HttpServletRequest request, HttpServletResponse response) {
        speakerDao.delete(id);
        Result result=new Result();
        result.setSuccess(true);
        return  result;
    }
}
