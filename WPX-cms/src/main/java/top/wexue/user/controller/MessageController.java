package top.wexue.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.wexue.common.service.WeixinAPI;
import top.wexue.base.dao.*;
import top.wexue.base.model.Page;
import top.wexue.common.model.SessionInfo;
import top.wexue.base.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 9/5/15.
 */
@Controller
@RequestMapping("/platform/message")
public class MessageController {
    @Autowired
    MessageDao messageDao;
    WeixinAPI weixinAPI;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String message(HttpServletRequest request, HttpSession session,Page page) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        List<Map<String, Object>> messages = messageDao.getMessageListByCorpid(sessionInfo.getCorpid(), page);
        request.setAttribute("messages", messages);
        request.setAttribute("prepage",page.getStartPage()-1);
        request.setAttribute("nextpage", page.getStartPage() + 1);
        return "/message/show";
    }


}
