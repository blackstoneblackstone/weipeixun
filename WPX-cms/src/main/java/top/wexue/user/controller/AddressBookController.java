package top.wexue.user.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.qy.model.BatchResult;
import com.foxinmy.weixin4j.qy.model.Callback;
import com.foxinmy.weixin4j.qy.model.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.wexue.common.model.Result;
import top.wexue.common.service.WeixinAPI;
import top.wexue.dao.AuthUserDao;
import top.wexue.dao.DepartmentUserDao;
import top.wexue.dao.PartyDao;
import top.wexue.dao.TaskDao;
import top.wexue.model.Page;
import top.wexue.model.SessionInfo;
import top.wexue.model.User;
import top.wexue.utils.BaseMethod;
import top.wexue.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    @Autowired
    private TaskDao taskDao;

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public String getUserByPartId(HttpSession session) {

        return "/addressbook/tree";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String addressBook(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//        try {
//            BatchResult p = weixinAPI.getBatchResult("EbvUl017_-Z0ehEC1TWp2QSNfRMVBuf1--_Gq3A5ebM");
//            request.setAttribute("pTask", p.getStatus());
//        } catch (WeixinException e) {
//            request.setAttribute("pTask", e.getLocalizedMessage());
//        }
//        try {
//            BatchResult u = weixinAPI.getBatchResult("syi3qNlVDm7xkngacjq_ExULazMz3TNqqI-6m_BkgrI");
//            request.setAttribute("uTask", u.getStatus());
//        } catch (WeixinException eu) {
//            request.setAttribute("uTask", eu.getLocalizedMessage());
//        }
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        String rUrl = "error404";
        try {
            String url = weixinAPI.loginAddressBook(sessionInfo.getCorpid());
            request.setAttribute("url", url);
            rUrl = "wechatPage";
        } catch (WeixinException e) {
            e.printStackTrace();
        }
        return rUrl;
    }

    /**
     * 获得部门用户
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserByPartId", method = RequestMethod.GET)
    public List<Map<String, Object>> getUserByPartId(String partyId, Page page, HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        return authUserDao.getUserByPartyId(partyId, page, sessionInfo.getCorpid());
    }

    /**
     * 获得部门用户
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUsersByDepId", method = RequestMethod.GET)
    public List<Map<String, Object>> getUsersByDepId(String depId, HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        return authUserDao.getUsersByDepId(depId, sessionInfo.getCorpid());
    }


    @RequestMapping(value = "/departmentTree", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray addressBookTree(HttpSession session) {
        JSONArray jsonArray = new JSONArray();
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        List<Map<String, Object>> parties = partyDao.getPartysByCorpid(sessionInfo.getCorpid());
        try {
            for (int i = 0; i < parties.size(); i++) {
                Map<String, Object> party = parties.get(i);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", party.get("departmentid"));
                jsonObject.put("name", party.get("departmentname"));
                jsonObject.put("open", true);
                jsonObject.put("pId", party.get("parentid"));
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (Exception e) {
            return jsonArray;
        }

    }

    private JSONArray partiesTree(int id, String corpId) {
//        [
//        {id: 1, pId: 0, name: "随意勾选 1", open: true},
//        {id: 11, pId: 1, name: "随意勾选 1-1", open: true},
//        {id: 111, pId: 11, name: "随意勾选 1-1-1"},
//        {id: 112, pId: 11, name: "随意勾选 1-1-2"},
//        {id: 12, pId: 1, name: "随意勾选 1-2", open: true},
//        {id: 121, pId: 12, name: "随意勾选 1-2-1"},
//        {id: 122, pId: 12, name: "随意勾选 1-2-2"},
//        {id: 2, pId: 0, name: "随意勾选 2", checked: true, open: true},
//        {id: 21, pId: 2, name: "随意勾选 2-1"},
//        {id: 22, pId: 2, name: "随意勾选 2-2", open: true},
//        {id: 221, pId: 22, name: "随意勾选 2-2-1", checked: true},
//        {id: 222, pId: 22, name: "随意勾选 2-2-2"},
//        {id: 23, pId: 2, name: "随意勾选 2-3"}
//        ]
        List<Map<String, Object>> parties = partyDao.getPartysByParentId(id, corpId);
        JSONArray jsonArray = new JSONArray();
        try {
            for (int i = 0; i < parties.size(); i++) {
                Map<String, Object> party = parties.get(i);
                int tempId = Integer.valueOf(party.get("id").toString());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", tempId);
                jsonObject.put("name", party.get("departmentname"));
                jsonObject.put("open", false);
                jsonObject.put("pId", party.get("parentid"));
                jsonArray.add(jsonObject);
            }
        } catch (Exception e) {
        }
        return jsonArray;
    }

    @RequestMapping(value = "/addUserJsp", method = RequestMethod.GET)
    public String addJsp() {
        return "/addressbook/addUser";
    }

    @RequestMapping(value = "/addDepJsp", method = RequestMethod.GET)
    public String addDepJsp() {
        return "/addressbook/addDep";
    }

    @RequestMapping(value = "/editJsp", method = RequestMethod.GET)
    public String editJsp(HttpServletRequest request, String id, HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        Map<String, Object> user = authUserDao.getUserById(id, sessionInfo.getCorpid());
        request.setAttribute("user", user);
        return "/addressbook/edit";
    }

    @RequestMapping(value = "/userDelete", method = RequestMethod.GET)
    @ResponseBody
    public Result userDelete(String depId, String userid, HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        Result result = new Result();
        int i = authUserDao.delete(userid);
        i = i + departmentUserDao.delete(depId, userid, sessionInfo.getCorpid());
        if (i >= 1) {
            result.setSuccess(true);
            result.setMsg("删除成功，记得同步通讯录");
        } else {
            result.setSuccess(false);
            result.setSysErrorMsg();
        }
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result edit(HttpSession session, MultipartFile file, String id, HttpServletRequest request, HttpServletResponse response, User user) {
        Result result = new Result();
        user.setStatus("4");
        String avatorId = BaseMethod.createUUID(Constants.EntityType.AVATOR);
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);

        int index = file.getOriginalFilename().indexOf(".");
        if (index == -1) {
            result.setSuccess(false);
            result.setMsg("文件格式不正确,只支持JPG，PNG");
            return result;
        }
        String[] filenames = (file.getOriginalFilename()).split("\\.");
        if (!"jpg".equalsIgnoreCase(filenames[filenames.length - 1]) && !"png".equalsIgnoreCase(filenames[filenames.length - 1])) {
            result.setSuccess(false);
            result.setMsg("文件格式不正确,只支持JPG，PNG");
            return result;
        }
        try {
//            String[] filenames = "《营销管理》案例教学-中国版第十三版-案例清单-李默-20160226-2015P2班.xls".split("\\.");
            String fileName = avatorId + "." + filenames[filenames.length - 1];
            String filePath = "/images/" + fileName;
            //String path = "/opt/weipeixun/data/files/";
            String path = "/Users/lihb/project/uploadFile/";
            File targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            //保存
            file.transferTo(targetFile);
            authUserDao.update(user, sessionInfo.getCorpid(), id);
            String depIds = user.getDepartmentid();
            if (BaseMethod.notEmpty(depIds)) {
                String[] deps = depIds.split(",");
                for (String dep : deps) {
                    departmentUserDao.save(dep, user.getUserid(), sessionInfo.getCorpid());
                }
            } else {
                departmentUserDao.save("1", user.getUserid(), sessionInfo.getCorpid());
            }
            result.setSuccess(true);
            result.setMsg("添加成功");
            return result;
        } catch (IOException e) {
            result.setSuccess(false);
            result.setSysErrorMsg();
            return result;
        }

    }


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public Result addUser(HttpSession session, MultipartFile file, User user) {
        Result result = new Result();
        String avatorId = BaseMethod.createUUID(Constants.EntityType.AVATOR);
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);

        int index = file.getOriginalFilename().indexOf(".");
        if (index == -1) {
            result.setSuccess(false);
            result.setMsg("文件格式不正确,只支持JPG，PNG");
            return result;
        }
        String[] filenames = (file.getOriginalFilename()).split("\\.");
        if (!"jpg".equalsIgnoreCase(filenames[filenames.length - 1]) && !"png".equalsIgnoreCase(filenames[filenames.length - 1])) {
            result.setSuccess(false);
            result.setMsg("文件格式不正确,只支持JPG，PNG");
            return result;
        }

        try {
//            String[] filenames = "《营销管理》案例教学-中国版第十三版-案例清单-李默-20160226-2015P2班.xls".split("\\.");
            String fileName = avatorId + "." + filenames[filenames.length - 1];
            String filePath = "/images/" + fileName;
            //String path = "/opt/weipeixun/data/files/";
            String path = "/Users/lihb/project/uploadFile/";
            File targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            //保存
            file.transferTo(targetFile);
            authUserDao.save(user, sessionInfo.getCorpid());
            String depIds = user.getDepartmentid();
            if (BaseMethod.notEmpty(depIds)) {
                String[] deps = depIds.split(",");
                for (String dep : deps) {
                    departmentUserDao.save(dep, user.getUserid(), sessionInfo.getCorpid());
                }
            } else {
                departmentUserDao.save("1", user.getUserid(), sessionInfo.getCorpid());
            }
            result.setSuccess(true);
            result.setMsg("添加成功");
            return result;
        } catch (IOException e) {
            result.setSuccess(false);
            result.setSysErrorMsg();
            return result;
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

    @RequestMapping(value = "/syncUsersToWechat", method = RequestMethod.GET)
    @ResponseBody
    public Result syncUsersToWechat(HttpSession session) {
        Result result = new Result();
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        List<Map<String, Object>> deps = partyDao.getPartysByCorpid(sessionInfo.getCorpid());
        List<Map<String, Object>> users = authUserDao.getUsersByCorpId(sessionInfo.getCorpid());
        if (deps != null && deps.size() != 0) {
            List<Party> parties = new ArrayList();
            for (Map<String, Object> dep : deps) {
                Party party = new Party(Integer.valueOf(dep.get("departmentid").toString()), dep.get("departmentname").toString(), Integer.valueOf(dep.get("parentid").toString()));
                parties.add(party);
            }
            for (Map<String, Object> user : users) {
                com.foxinmy.weixin4j.qy.model.User userT = new com.foxinmy.weixin4j.qy.model.User(user.get("userid").toString(), user.get("username").toString());
                userT.setEmail(user.get("email").toString());
                userT.setPosition(user.get("position").toString());
                userT.setMobile(user.get("mobile").toString());
                userT.setGender(Integer.valueOf(user.get("gender").toString()));
                userT.setWeixinId(user.get("weixinId").toString());
                userT.setAvatar(user.get("avatar").toString());
            }
            List<com.foxinmy.weixin4j.qy.model.User> userA = new ArrayList();
            try {
                String i = weixinAPI.batchUploadParties(parties);
                String j = weixinAPI.batchUploadUsers(userA);

                String taskidP = weixinAPI.batchReplaceParty(i, null);
                String taskidU = weixinAPI.batchReplaceUser(j, null);
                result.setMsg("部门任务ID" + taskidP + "人员任务ID" + taskidU);
            } catch (WeixinException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @RequestMapping(value = "/syncUsersLocation", method = RequestMethod.GET)
    @ResponseBody
    public Result syncUsersLocation(String userId) {
        Result result = new Result();

        return result;
    }
}
