package top.wexue.wpx.controller;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.qy.model.OUserInfo;
import com.foxinmy.weixin4j.qy.model.User;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wexue.wpx.api.WpxAPI;
import top.wexue.wpx.dao.AuthCorpInfoDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by lihb on 10/20/15.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    WpxAPI wpxAPI;
    @Autowired
    AuthCorpInfoDAO authCorpInfoDAO;

    @RequestMapping(value = "/suite", method = RequestMethod.GET)
    public String Auth(HttpServletRequest request) {
        try {
            String suiteAuthorizeURL = wpxAPI.getSuiteAuthorizeURL();
            System.out.println("suiteAuthorizeURL:" + suiteAuthorizeURL);
            request.setAttribute("authurl", suiteAuthorizeURL);
        } catch (WeixinException e) {
            e.printStackTrace();
        }
        return "/pc/auth";
    }

    @RequestMapping(value = "/server", method = RequestMethod.GET)
    public String pxkcAuth(HttpServletRequest request) {
        try {
            String suiteAuthorizeURL = wpxAPI.getServerAuthorizeURL();
            System.out.println("ServerAuthorizeURL:" + suiteAuthorizeURL);
            request.setAttribute("authurl", suiteAuthorizeURL);
        } catch (WeixinException e) {
            e.printStackTrace();
        }
        return "/pc/auth";
    }

    @RequestMapping(value = "/getauthcode", method = RequestMethod.GET)
    @ResponseBody
    public void pxkcAuth(String auth_code, String expires_in, String state,HttpServletResponse response) {
        int re = 0;
        String msg = "";
        try {
            OUserInfo oUserInfo=null;
            if("suite".equals(state)){
                oUserInfo = wpxAPI.exchangeSuitePermanentCode(auth_code);
            }
            if("server".equals(state)){
                oUserInfo = wpxAPI.exchangeServerPermanentCode(auth_code);
            }
            Map<String ,Object> map=authCorpInfoDAO.getCorpById(oUserInfo.getCorpInfo().getCorpId());
            if(map==null){
                re = authCorpInfoDAO.insert(oUserInfo, wpxAPI.getPerCode(), wpxAPI.getSuiteId());
            }
            msg = wpxAPI.createMenu(oUserInfo.getCorpInfo().getCorpId(), oUserInfo);
            response.sendRedirect("http://www.wexue.top:8080/registJsp?corpid=" + oUserInfo.getCorpInfo().getCorpId() + "&userheader=" + URLEncoder.encode(oUserInfo.getCorpInfo().getSquareLogoUrl(), "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/authUser", method = RequestMethod.GET)
    public void authUser(String corpId, String state, HttpServletResponse response, HttpServletRequest request) {
        String redirectUri="";
        if("webportal".equals(state)){
            redirectUri = "http://www.wexue.top/pxkc/portalWebJsp";
        }
        if("public".equals(state)){
            redirectUri = "http://www.wexue.top/pxkc/publicCourseJsp";
        }
        if("require".equals(state)){
            redirectUri = "http://www.wexue.top/pxkc/requiredCourseJsp";
        }
        if("mycourse".equals(state)){
            redirectUri = "http://www.wexue.top/wdkc/myCourseJsp";
        }
        if("ordercourse".equals(state)){
            redirectUri = "http://www.wexue.top/wdkc/orderCourseJsp";
        }
        String Url = wpxAPI.getAuthUserInfoUrl(redirectUri, corpId, corpId);
        try {
            response.sendRedirect(Url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
