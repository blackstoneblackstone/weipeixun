package top.wexue.wpx.controller;

import com.foxinmy.weixin4j.qy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wexue.base.dao.CourseDao;
import top.wexue.base.model.Page;
import top.wexue.base.model.SessionInfo;
import top.wexue.wpx.api.WpxAPI;
import top.wexue.wpx.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 10/14/15.
 * 培训课程
 */
@Controller
@RequestMapping("/wdkc")
public class WdkcController {
    @Autowired
    private WpxAPI wpxAPI;
    @Autowired
    private CourseDao courseDao;

    @RequestMapping(value = "/myCourseJsp", method = RequestMethod.GET)
    public String orderCourse(String code, String state, HttpServletRequest request) {
        User user = wpxAPI.getCurrentUser(code, state);
       // System.out.println(user.toString());
//        User user = new User("lihb", "木子君");
//        user.setAvatar("http://img5.imgtn.bdimg.com/it/u=2709469067,3830556490&fm=21&gp=0.jpg");
        WebUtils.setSessionInfo(request, user, state);

        return "wx/wdkc/myCourse";
    }

    @RequestMapping(value = "/myRequiredCourseJsp", method = RequestMethod.GET)
    public String myRequiredCourse(String code, String state, HttpServletRequest request) {
        User user = wpxAPI.getCurrentUser(code, state);
        WebUtils.setSessionInfo(request, user, state);
        return "wx/wdkc/myRequiredCourse";
    }

    @RequestMapping(value = "/myCourseList", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> myOrderCourseList(Page page, HttpServletRequest request) {
        SessionInfo sessionInfo = WebUtils.getSessionInfo(request);
        List<Map<String, Object>> courses = courseDao.getMyCourses(sessionInfo.getCorpid(), sessionInfo.getUserId(), page);
        return courses;
    }

}
