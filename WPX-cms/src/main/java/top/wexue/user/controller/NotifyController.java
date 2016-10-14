package top.wexue.user.controller;

import com.foxinmy.weixin4j.exception.WeixinException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wexue.common.service.WeixinAPI;
import top.wexue.base.dao.AuthInfoDAO;
import top.wexue.base.dao.NotifyDao;
import top.wexue.base.dao.PartyDao;
import top.wexue.base.model.Page;
import top.wexue.common.model.SessionInfo;
import top.wexue.base.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lihb
 * @version V1.3.1
 * @Description 发送消息控制器
 * @date 2014-1-8 下午3:36:28
 */

@Controller
@RequestMapping("/platform/notify")
public class NotifyController {
    @Autowired
    private NotifyDao notifyDao;
    @Autowired
    private AuthInfoDAO authInfoDAO;
    @Autowired
    private PartyDao partyDao;
    private WeixinAPI weixinAPI;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpSession session, Page page) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        List<Map<String, Object>> notifys = notifyDao.getNotifys(sessionInfo.getCorpid(), page);
        request.setAttribute("notifys", notifys);
        return "/notify/show";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        List<Map<String, Object>> apps = authInfoDAO.getAuthInfosByCorpid(sessionInfo.getCorpid());
        request.setAttribute("apps", apps);
        return "/notify/add";
    }

    @RequestMapping(value = "usersJsp", method = RequestMethod.GET)
    public String usersJsp(HttpServletRequest request, HttpSession session) {
        return "/notify/users";
    }

//    @RequestMapping(value = "sendMessage", method = RequestMethod.GET)
//    public String sendMessage(HttpServletRequest request, HttpSession session, int agentid) {
//        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
////        Map<String, Object> app = authInfoDAO.getAuthInfoByAgentId(sessionInfo.getCorpid(), agentid);
////        request.setAttribute("app", app);
//        String rUrl = "error404";
//        try {
//            String url = weixinAPI.loginSendMsg(sessionInfo.getCorpid(), agentid);
//            request.setAttribute("url",url);
//            rUrl = "wechatPage";
//        } catch (WeixinException e) {
//            e.printStackTrace();
//        }
//        return rUrl;
//    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> users(HttpSession session) {
        List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        List<Map<String, Object>> parties = partyDao.getPartysByCorpid(sessionInfo.getCorpid());
        try {
            for (int i = 0; i < parties.size(); i++) {
                Map<String, Object> party = parties.get(i);
                Map<String, Object> jsonObject = new HashMap<String, Object>();
                jsonObject.put("id", party.get("departmentid"));
                jsonObject.put("name", party.get("departmentname"));
                jsonObject.put("open", true);
                jsonObject.put("pId", party.get("parentid"));
                users.add(jsonObject);
            }
            return users;
        } catch (Exception e) {
            return users;
        }

    }


}
