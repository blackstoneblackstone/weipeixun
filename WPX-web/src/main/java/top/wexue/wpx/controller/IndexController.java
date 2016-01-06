package top.wexue.wpx.controller;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.qy.model.OUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wexue.wpx.api.WpxAPI;
import top.wexue.wpx.dao.AuthCorpInfoDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by lihb on 10/20/15.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {

        return "/pc/index";
    }


}
