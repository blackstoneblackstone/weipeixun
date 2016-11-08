package top.wexue.wpx.controller;

import com.foxinmy.weixin4j.cache.RedisCacheStorager;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.qy.model.OUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wexue.base.dao.AuthCorpInfoDAO;
import top.wexue.base.dao.AuthDepartmentDao;
import top.wexue.base.dao.AuthInfoDAO;
import top.wexue.base.utils.MD5Util;
import top.wexue.wpx.api.QyAPI;
import top.wexue.wpx.api.WpxAPI;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 10/20/15.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private WpxAPI wpxAPI;
    @Autowired
    private AuthCorpInfoDAO authCorpInfoDAO;
    @Autowired
    private AuthInfoDAO authInfoDAO;
    @Autowired
    private AuthDepartmentDao authDepartmentDao;

    @Value("${server_cms_url}")
    private String cmsUrl;

    @Value("${server_web_url}")
    private String webUrl;

    @Value("${suite_id}")
    private String SUIT_ID;
    @Value("${suite_secret}")
    private String SUIT_SECRET;
    @Autowired
    RedisCacheStorager<Token> redisCacheStorager;


    private String authCodeUrl = "/auth/getauthcode";

    @RequestMapping(value = "/suite", method = RequestMethod.GET)
    public String Auth(HttpServletRequest request) {
        try {
            String suiteAuthorizeURL = wpxAPI.getSuiteAuthorizationURL(authCodeUrl, "web");
            request.setAttribute("authurl", suiteAuthorizeURL);
        } catch (WeixinException e) {
            e.printStackTrace();
        }
        return "/pc/auth";
    }

    @RequestMapping(value = "/server", method = RequestMethod.GET)
    public String pxkcAuth(HttpServletRequest request) {
        try {
            String suiteAuthorizeURL = wpxAPI.getChatAuthorizationURL(authCodeUrl, "web");
            request.setAttribute("authurl", suiteAuthorizeURL);
        } catch (WeixinException e) {
            e.printStackTrace();
        }
        return "/pc/auth";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(String auth_code, HttpServletResponse response, String state, String expires_in) {
        String loginAuthURL = cmsUrl + "/qyauth?auth_code=" + auth_code + "&state=" + state + "&expires_in=" + expires_in;
        try {
            response.sendRedirect(loginAuthURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getauthcode", method = RequestMethod.GET)
    @ResponseBody
    public void pxkcAuth(String auth_code, String expires_in, String state, HttpServletResponse response) {
        int re = 0;
        String msg = "";
        try {
            OUserInfo oUserInfo = null;
            if ("suite".equals(state)) {
                oUserInfo = wpxAPI.exchangeSuitePermanentCode(auth_code);
            }
            if ("server".equals(state)) {
                oUserInfo = wpxAPI.exchangeServerPermanentCode(auth_code);
            }
//            Map<String, Object> map = authCorpInfoDAO.getCorpById(oUserInfo.getCorpInfo().getCorpId());
            authCorpInfoDAO.delete(wpxAPI.getSUIT_ID(), oUserInfo.getCorpInfo().getCorpId());

            re = re + authCorpInfoDAO.insert(oUserInfo, wpxAPI.getPerCode(oUserInfo.getCorpInfo().getCorpId()), wpxAPI.getSUIT_ID());
            authInfoDAO.delete(oUserInfo, wpxAPI.getSUIT_ID());
            re = re + authInfoDAO.insert(oUserInfo, wpxAPI.getSUIT_ID());
            List<Map<String, Object>> des = authDepartmentDao.getDepartments(oUserInfo.getCorpInfo().getCorpId());
            if (des == null || des.size() == 0) {
                re = re + authDepartmentDao.insert(oUserInfo, wpxAPI.getSUIT_ID());
            }
            response.sendRedirect(cmsUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/authUser", method = RequestMethod.GET)
    public void authUser(String corpId, String state, HttpServletResponse response, HttpServletRequest request) {
        String redirectUri = "";
        if ("webportal".equals(state)) {
            redirectUri = webUrl + "/pxkc/portalWebJsp";
        }
        if ("public".equals(state)) {
            redirectUri = webUrl + "/pxkc/publicCourseJsp";
        }
        if ("require".equals(state)) {
            redirectUri = webUrl + "/pxkc/requiredCourseJsp";
        }
        if ("mycourse".equals(state)) {
            redirectUri = webUrl + "/wdkc/myCourseJsp";
        }
        if ("ordercourse".equals(state)) {
            redirectUri = webUrl + "/wdkc/orderCourseJsp";
        }
        String Url = wpxAPI.getAuthUserInfoUrl(redirectUri, corpId, corpId);
        try {
            response.sendRedirect(Url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Auth2.0获取用户信息
     *
     * @param code
     * @param response
     * @param state
     */
    @RequestMapping(value = "/userCookie", method = RequestMethod.GET)
    public String userCookie(String code, HttpServletResponse response, String state) throws UnsupportedEncodingException, WeixinException {
        String corpId = state.split("@")[0];
        String sourceUrl = state.split("@")[1];
        String cookieKey = MD5Util.md5(corpId + "userId");
//        QyAPI qyAPI = new QyAPI(corpId, redisCacheStorager, SUIT_ID, SUIT_SECRET);
//        String userId = qyAPI.getCurrentUserId(code);
        String userId="muzijun";
        Cookie cookie = new Cookie(cookieKey, userId);
        cookie.setMaxAge(3600*24);
        //设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
        cookie.setPath("/");
        response.addCookie(cookie);
        return String.format("%s:%s", "redirect", URLDecoder.decode(sourceUrl, "utf-8"));
    }
}
