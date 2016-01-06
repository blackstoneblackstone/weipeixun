package top.wexue.user.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wexue.common.model.Result;
import top.wexue.common.service.WeixinAPI;
import top.wexue.utils.Constants;
import top.wexue.dao.AuthUserDao;
import top.wexue.dao.DepartmentUserDao;
import top.wexue.dao.PartyDao;
import top.wexue.model.SessionInfo;
import top.wexue.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @author lihb
 * @version V1.3.1
 * @Description 个人信息控制器（登陆）
 * @date 2014-1-8 下午3:36:28
 */

@Controller
@RequestMapping("/platform/addressbook")
public class AddressBookController {

    @Autowired
    private WeixinAPI weixinAPI;
    @Autowired
    private PartyDao partyDao;
    @Autowired
    private AuthUserDao authUserDao;
    @Autowired
    private DepartmentUserDao departmentUserDao;

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public String getUserByPartId(HttpSession session) {

        return "/addressbook/tree";
    }

    /**
     * 获得部门用户
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserByPartId", method = RequestMethod.GET)
    public List<Map<String, Object>> getUserByPartId(@RequestParam(required = true, value = "partyId") int partyId, @RequestParam(required = true, value = "page") int page, HttpSession session) {
        if (partyId == 0) {
            partyId = 1;
        }
        if (page == 0) {
            page = 1;
        }
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        return authUserDao.getUserByParty(partyId, page, sessionInfo.getCorpid());
    }


    @RequestMapping(value = "/departmentTree", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray addressBookTree(HttpSession session) {
        JSONArray jsonArray = new JSONArray();
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        jsonArray = partiesTree(0, sessionInfo.getCorpid());
        return jsonArray;
    }

    private JSONArray partiesTree(int id, String corpId) {
        List<Map<String, Object>> parties = partyDao.getPartysByParentId(id, corpId);
        JSONArray jsonArray = new JSONArray();
        try {
            for (int i = 0; i < parties.size(); i++) {
                Map<String, Object> party = parties.get(i);
                int tempId = Integer.valueOf(party.get("id").toString());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", tempId);
                jsonObject.put("text", party.get("departmentname").toString());
                jsonObject.put("iconCls", "icon-th");
                jsonObject.put("children", partiesTree(tempId, corpId));
                jsonArray.add(jsonObject);
            }
        } catch (Exception e) {
        }
        return jsonArray;
    }

    @RequestMapping(value = "/addJsp", method = RequestMethod.GET)
    public String addJsp(HttpServletRequest request, @RequestParam(value = "partyId", required = false) String partyId) {
        if (partyId == null) {
            partyId = "1";
        }
        request.setAttribute("partyId", partyId);
        return "/addressbook/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(HttpSession session, HttpServletRequest request, HttpServletResponse response, User user) {
        user.setStatus("4");
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        authUserDao.save(user, sessionInfo.getCorpid());
        departmentUserDao.save(user.getDepartmentid(), user.getUserid());
        try {
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
            response.sendRedirect(basePath + "/platform/addressbook");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/isUserId", method = RequestMethod.GET)
    @ResponseBody
    public Result isUserId(String userId) {
        Result result = new Result();
        if (authUserDao.isHaveUserId(userId)) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }
        return result;
    }
}
