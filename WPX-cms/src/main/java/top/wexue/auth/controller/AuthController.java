package top.wexue.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.weixin4j.cache.RedisCacheStorager;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.qy.api.UserApi;
import com.foxinmy.weixin4j.qy.model.User;
import com.foxinmy.weixin4j.qy.token.WeixinTokenCreator;
import com.foxinmy.weixin4j.token.TokenCreator;
import com.foxinmy.weixin4j.token.TokenManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import top.wexue.auth.model.LoginCodeCreator;
import top.wexue.auth.model.LoginCodeManager;
import top.wexue.base.utils.BaseMethod;
import top.wexue.common.model.Result;
import top.wexue.common.service.WeixinAPI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by lihb on 9/5/15.
 */
@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @Autowired
    RedisCacheStorager redisCacheStorager;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String redirect_uri,String appId, HttpServletRequest request) {
        //判断appId是否有,判断是否是信任地址
        String lc = BaseMethod.createUUID("LC");
        request.setAttribute("redirect_uri", redirect_uri);
        request.setAttribute("lc", lc);
        return "auth/login";
    }

    @RequestMapping(value = "/login/mobile", method = RequestMethod.GET)
    public void mobile(String lc, HttpServletRequest request, HttpServletResponse response) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        try {
            response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2f23249cbbc08dd1&redirect_uri=" + URLEncoder.encode(basePath + "/auth/login/mobile/code/" + lc, "UTF-8") + "&response_type=code&scope=snsapi_base&state=app1#wechat_redirect");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/login/mobile/code/{lc}", method = RequestMethod.GET)
    public String mobileCode(@PathVariable String lc, String code,HttpServletRequest request) {
        if (code != null && !"".equals(code)) {
            TokenCreator tokenCreator = new WeixinTokenCreator("wx2f23249cbbc08dd1", "7vkgdR7PNpGZflDEq389jku3TCoBsCSy8ehtPAZccIFOv3lL2uEHPoRXV_912Prs");
            TokenManager tokenManager = new TokenManager(tokenCreator, redisCacheStorager);
            UserApi userApi = new UserApi(tokenManager);
            //添加user到redis
            try {
                User user = userApi.getUserByCode(code);
                LoginCodeCreator loginCodeCreator = new LoginCodeCreator("lc", user);
                LoginCodeManager loginCodeManager = new LoginCodeManager(loginCodeCreator, redisCacheStorager);
                try {
                    loginCodeManager.refreshCache();
                } catch (WeixinException e) {
                    log.error(e.getMessage());
                }
                request.setAttribute("lc",lc);
            } catch (WeixinException e) {
                log.error(e.getMessage());
            }
        }
        return "auth/mobile-login";
    }

    @RequestMapping(value = "/login/mobile/confirm", method = RequestMethod.GET)
    @ResponseBody
    public Result mobileConfirm(String lc) {
        new LoginWebSocketHandler().sendMessageToUsers(new TextMessage(lc));
        return new Result(true,lc);
    }

//    @RequestMapping(value = "/login/code", method = RequestMethod.GET)
//    @ResponseBody
//    public String loginCode(String state) {
//        LoginCodeCreator loginCodeCreator = new LoginCodeCreator(state);
//        LoginCodeManager loginCodeManager = new LoginCodeManager(loginCodeCreator, redisCacheStorager);
//        String authCode = "-";
//        try {
//            authCode = loginCodeManager.getAuthCode();
//        } catch (WeixinException e) {
//            log.error("It is error when login code get.detail info: {}", e.getMessage());
//        }
//        return authCode;
//    }


}
