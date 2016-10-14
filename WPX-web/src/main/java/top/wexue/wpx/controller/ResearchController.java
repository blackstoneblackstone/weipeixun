package top.wexue.wpx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wexue.base.dao.AnswerDao;
import top.wexue.base.dao.ResearchDao;
import top.wexue.base.dao.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by lihb on 10/14/15.
 * 培训课程
 */
@Controller
@RequestMapping("/research")
public class ResearchController {
    @Autowired
    private ResearchDao researchDao;
    @Autowired
    private AnswerDao answerDao;

    @RequestMapping(value = "/survey", method = RequestMethod.GET)
    public String index(String id,String userid,String corpid,HttpServletRequest request) {
        Map<String, Object> survey = researchDao.getResearchById(id);
        request.setAttribute("survey",survey);
        request.setAttribute("userid",userid);
        request.setAttribute("researchid",id);
        request.setAttribute("corpid",corpid);
        return "/wx/research/survey";
    }
    @RequestMapping(value = "/answerSubmit", method = RequestMethod.POST)
    @ResponseBody
    public String answerSubmit(String userid,String corpid,String answer,String researchid,HttpServletRequest request) {
        int result = answerDao.save(userid,researchid,corpid,answer);
        if(result==1){
            return "success";
        }else{
            return "failure";
        }

    }


}
