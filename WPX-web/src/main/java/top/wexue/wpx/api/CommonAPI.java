package top.wexue.wpx.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wexue.base.utils.MD5Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by lihb on 11/8/16.
 */
@Service
public class CommonAPI {
    @Autowired
    private WpxAPI wpxAPI;

    public String userCreator(HttpServletRequest request, String corpId, String sourceUrl) throws UnsupportedEncodingException {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        String authRedirectUrl = basePath + "/auth/userCookie";
        String cookieKey = MD5Util.md5(corpId + "userId");
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookieKey.equals(cookie.getName()) && cookie.getValue() != null && cookie.getValue() != "") {
                    return cookie.getValue();
                }
            }
        }
        return "redirect:" + wpxAPI.getAuthUserInfoUrl(authRedirectUrl, String.format("%s@%s",corpId, URLEncoder.encode(sourceUrl,"utf-8")),corpId);
    }

}
