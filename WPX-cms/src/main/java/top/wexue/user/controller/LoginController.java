package top.wexue.user.controller;

import com.foxinmy.weixin4j.cache.RedisCacheStorager;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wexue.common.model.Result;
import top.wexue.base.dao.AuthCorpInfoDAO;
import top.wexue.base.utils.BaseMethod;
import top.wexue.base.utils.Constants;
import top.wexue.common.service.WeixinAPI;
import top.wexue.user.utils.IpUtil;
import top.wexue.base.utils.MD5Util;
import top.wexue.base.dao.LoginUserDao;
import top.wexue.common.model.SessionInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;


/**
 * @author lihb
 * @version V1.3.1
 * @Description 个人信息控制器（登陆）
 * @date 2014-1-8 下午3:36:28
 */

@Controller
public class LoginController {


    /**
     * 微信配置
     */
    @Value("${suite_id}")
    private String SUIT_ID;
    @Value("${suite_secret}")
    private String SUIT_SECRET;
    @Value("${chat_id}")
    private String CHAT_ID;
    @Value("${chat_secret}")
    private String CHAT_SECRET;
    @Value("${corp_id}")
    private String CORP_ID;
    @Value("${provider_secret}")
    private String PROVIDER_SECRET;

    @Autowired
    RedisCacheStorager<Token> redisCacheStorager;

    @Autowired
    private LoginUserDao loginUserDao;
    @Autowired
    private AuthCorpInfoDAO authCorpInfoDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomeJsp() {
        return "platform";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginJSP(HttpServletRequest request, String handlerUrl) {
        request.setAttribute("handlerUrl", handlerUrl);
        return "login";
    }

    @RequestMapping(value = "/loginBak", method = RequestMethod.GET)
    public String loginBakJsp() {
        return "loginBak";
    }

    /*
    *企业号授权登陆
    *http://www.wexue.top/qyAuth?auth_code=ce075635d1b3e3249c7f2869468e859d&state=&expires_in=600
    */
    @RequestMapping(value = "/qyauth", method = RequestMethod.GET)
    public String qyauth(String auth_code, HttpServletRequest request, HttpSession session, String state, String expires_in) {
//        try {
//            OUserInfo oUserInfo = weixinAPI.getOUserInfoByCode(auth_code);
        SessionInfo sessionInfo = new SessionInfo();

//            sessionInfo.setIp(IpUtil.getIpAddr(request));
//            sessionInfo.setUsername(oUserInfo.getAdminInfo().getName());
//            sessionInfo.setId(String.valueOf(new Date().getTime()));
//            sessionInfo.setUserId(oUserInfo.getAdminInfo().getUserId());
//            sessionInfo.setCorpid(oUserInfo.getCorpInfo().getCorpId());
//            sessionInfo.setQyname(oUserInfo.getCorpInfo().getCorpName());
//            sessionInfo.setQyheader(oUserInfo.getCorpInfo().getRoundLogoUrl());
//            WeixinAPI weixinAPI=new WeixinAPI(oUserInfo.getCorpInfo().getCorpId(),redisCacheStorager,SUIT_ID,SUIT_SECRET,CHAT_ID,CHAT_SECRET,CORP_ID,PROVIDER_SECRET);

        //初始化各种API
        WeixinAPI weixinAPI = new WeixinAPI("wxf54e1b5e0b62fa96", redisCacheStorager, SUIT_ID, SUIT_SECRET, CHAT_ID, CHAT_SECRET, CORP_ID, PROVIDER_SECRET);
        sessionInfo.setWeixinApI(weixinAPI);
        sessionInfo.setUserId("muzijun");
        sessionInfo.setCorpid("wxf54e1b5e0b62fa96");
        sessionInfo.setId(BaseMethod.createUUID("SE"));
        session.setAttribute(Constants.Config.SESSION_USER_NAME, sessionInfo);

//            weixinAPI.initWeixinAPI(oUserInfo.getCorpInfo().getCorpId());
//        } catch (WeixinException e) {
//            return "platform";
//        }
        return "platform";
    }

    /**
     * 用户登录
     *
     * @param session
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Result login(String username, String password, HttpSession session, HttpServletRequest request) {
        Result result = new Result();
        try {
            Map<String, Object> user = loginUserDao.getUserByName(username);
            Map<String, Object> corp = authCorpInfoDao.getCorpById(user.get("corpid").toString());
            if (user != null) {
                if ((MD5Util.md5(password)).equals(user.get("password"))) {
                    SessionInfo sessionInfo = new SessionInfo();
                    sessionInfo.setIp(IpUtil.getIpAddr(request));
                    sessionInfo.setUsername(username);
                    sessionInfo.setId(user.get("id").toString());
                    sessionInfo.setUserId(user.get("userid").toString());
                    sessionInfo.setCorpid(user.get("corpid").toString());
                    sessionInfo.setQyname(corp.get("corp_name").toString());
                    sessionInfo.setQyheader(corp.get("corp_square_logo_url").toString());
                    session.setAttribute(Constants.Config.SESSION_USER_NAME, sessionInfo);
                    result.setObj(sessionInfo);
                    result.setSuccess(true);
                    result.setMsg(Constants.Message.LOGIN_SUCCESS);
                } else {
                    result.setSuccess(false);
                    result.setMsg(Constants.Message.LOGIN_PWD_ERROR);
                }
            } else {
                result.setSuccess(false);
                result.setMsg(Constants.Message.LOGIN_NO_USERNAME);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMsg(Constants.Message.LOGIN_FAILURE + e.getLocalizedMessage());
        }
        return result;
    }

    /**
     * 用户退出
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public void logout(HttpSession session, HttpServletResponse response) {
        session.removeAttribute(Constants.Config.SESSION_USER_NAME);
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/registJsp", method = RequestMethod.GET)
    public String registJsp(HttpServletRequest request, @RequestParam(value = "userheader", required = true) String userheader, @RequestParam(value = "corpid", required = true) String corpid) {
        try {

            request.setAttribute("userheader", URLDecoder.decode(userheader, "utf-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        request.setAttribute("corpid", corpid);
        return "regist";
    }

    /**
     * 用户注册
     *
     * @return
     */
    @RequestMapping(value = "/user/reg", method = RequestMethod.POST)
    @ResponseBody
    public Result reg(HttpServletResponse response, String rusername, String rpassword, String rpasswordr, String tel, String corpid) {
        Result result = new Result();
        Map<String, Object> user = loginUserDao.getUserByName(rusername);
        String id = BaseMethod.createUUID(Constants.EntityType.LOGIN_USER);
        if (user == null) {
            int i = loginUserDao.save(id, rusername, MD5Util.md5(rpassword), tel, corpid);
            if (i == 1) {
                result.setSuccess(true);
                result.setMsg(Constants.Message.REG_SUCCESS);
            } else {
                result.setSuccess(false);
                result.setMsg(Constants.Message.REG_FAILURE);
            }
        } else {
            result.setSuccess(false);
            result.setMsg(Constants.Message.REG_EXIST_USERNAME);
        }
        return result;
    }
}
