package top.wexue.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.wexue.common.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihb on 11/5/15.
 */
@Controller
public class FileController {
    @RequestMapping(value = "/upLoadImage", method = RequestMethod.POST)
    @ResponseBody
    public Result upLoadImage( MultipartFile file,String type){
        Map<String,String> map=new HashMap<String,String>(1);
        String fileName = type+"-"+new Date().getTime()+".png";
        String path="/opt/weipeixun/data/images/";
//        String path="/Users/lihb/Pictures/pic/";
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Result result=new Result();
        map.put("fileName",fileName);
        map.put("fileUrl", "http://www.wexue.top:20000/images/"+fileName);
        result.setSuccess(true);
        result.setObj(map);
        return  result;
    }



}
