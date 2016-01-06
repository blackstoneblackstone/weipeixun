package top.wexue.user.utils;

import com.foxinmy.weixin4j.qy.model.User;
import top.wexue.model.SessionInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lihb on 1/1/16.
 */
public class WebUtils {
    public static String SESSION_KEY = "userinfo";

    public static SessionInfo getSessionInfo(HttpServletRequest request) {
        return (SessionInfo) request.getSession().getAttribute(SESSION_KEY);
    }

    public static void setSessionInfo(HttpServletRequest request, User user,String corpId) {
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setUserId(user.getUserId());
        sessionInfo.setUseravator(user.getAvatar());
        sessionInfo.setUsername(user.getName());
        sessionInfo.setCorpid(corpId);
        request.getSession().setAttribute(SESSION_KEY, sessionInfo);
    }
}
