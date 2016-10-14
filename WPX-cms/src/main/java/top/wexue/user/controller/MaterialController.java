package top.wexue.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.wexue.base.dao.MaterialDao;
import top.wexue.base.model.Page;
import top.wexue.common.model.SessionInfo;
import top.wexue.base.utils.BaseMethod;
import top.wexue.base.utils.Constants;
import top.wexue.common.model.Result;
import top.wexue.common.service.WeixinAPI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 9/5/15.
 */
@Controller
@RequestMapping("/platform/material")
public class MaterialController {
    @Autowired
    MaterialDao materialDao;
    WeixinAPI weixinAPI;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String material(HttpServletRequest request, HttpSession session, Page page) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
        List<Map<String, Object>> materials = materialDao.getMaterialListByCorpid(sessionInfo.getCorpid(), page);
        request.setAttribute("materials", materials);
        request.setAttribute("prepage", page.getStartPage() - 1);
        request.setAttribute("nextpage", page.getStartPage() + 1);
        return "/material/show";
    }

    @RequestMapping(value = "/addJsp", method = RequestMethod.GET)
    public String addJsp() {
        return "material/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(HttpSession session, String mname, MultipartFile file) {
        Result result = new Result();
        try {
            String fileId = BaseMethod.createUUID(Constants.EntityType.FILE);
            SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.Config.SESSION_USER_NAME);
            int index = file.getOriginalFilename().indexOf(".");
            if (index == -1) {
                result.setSuccess(false);
                result.setMsg("文件格式不正确");
                return result;
            }
            String[] filenames = (file.getOriginalFilename()).split("\\.");
//            String[] filenames = "《营销管理》案例教学-中国版第十三版-案例清单-李默-20160226-2015P2班.xls".split("\\.");
            String fileName = fileId + "." + filenames[filenames.length - 1];
            String filePath = "/files/" + fileName;
            String path = "/opt/weipeixun/data/files/";
            //String path = "/Users/lihb/project/uploadFile/";
            File targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            //保存
            file.transferTo(targetFile);
            materialDao.save(fileId, mname, filePath, sessionInfo.getCorpid());
            result.setSuccess(true);
            result.setMsg("保存成功");
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setSysErrorMsg();
            return result;
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(String id) {
        int r = materialDao.delete(id);
        Result result = new Result();
        if (r == 1) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
            result.setSysErrorMsg();
        }

        return result;
    }
}
