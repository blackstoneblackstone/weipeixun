package top.wexue.base.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import top.wexue.base.model.Page;
import top.wexue.base.utils.BaseMethod;
import top.wexue.base.model.Course;

import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 7/26/15.
 */
@Service
public class CourseDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> getCoursesByProId(String projectId, String corpid, Page page) {
        String sql = "SELECT * from course where projectid=? and corpid=? ORDER BY starttime DESC limit ?,?";
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, projectId, corpid, (page.getStartPage() - 1) * page.getPageSize(), page.getPageSize());
        return result;
    }

    public List<Map<String, Object>> getCoursesByProId(String projectId, String corpid) {
        String sql = "SELECT * from course where projectid=? and corpid=? ";
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, projectId, corpid);
        return result;
    }

    public List<Map<String, Object>> getCourses(String corpid, Page page) {
        String sql = "SELECT * from course where corpid=? ORDER BY starttime DESC limit ?,?";
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, corpid, (page.getStartPage() - 1) * page.getPageSize(), page.getPageSize());
        return result;
    }

    public List<Map<String, Object>> getRequireCourses(String corpId, Page page) {
        String sql = "SELECT * from course where corpid=? and coursetype=2  order by starttime DESC limit ?,?";
//        System.out.println((page.getStartPage()-1)*page.getPageSize()+">>>"+page.getPageSize());
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, corpId, (page.getStartPage() - 1) * page.getPageSize(), page.getPageSize());
        return result;
    }

    public List<Map<String, Object>> getMyRequireCourses(String corpId, Page page) {
        String sql = "SELECT * from course where corpid=? and coursetype=2  order by starttime DESC limit ?,?";
//        System.out.println((page.getStartPage()-1)*page.getPageSize()+">>>"+page.getPageSize());
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, corpId, (page.getStartPage() - 1) * page.getPageSize(), page.getPageSize());
        return result;
    }

    public List<Map<String, Object>> getMyCourses(String corpId, String userId, Page page) {
        String sql = "SELECT * from allcourse where corpid=? and userid=? order by starttime DESC limit ?,?";
//        System.out.println((page.getStartPage()-1)*page.getPageSize()+">>>"+page.getPageSize());
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, corpId, userId, (page.getStartPage() - 1) * page.getPageSize(), page.getPageSize());
        return result;
    }

    public List<Map<String, Object>> getUsersByCourseid(String corpId, String courseid) {
        String sql = "SELECT * from auth_user,course_user where auth_user.userid=course_user.userid AND corpid=? and course_user.courseid=?";
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, corpId, courseid);
        return result;
    }


    public List<Map<String, Object>> getPublicCourses(String corpId, Page page) {
        String sql = "SELECT * from course where corpid=? and coursetype=1  order by starttime DESC limit ?,?";
        System.out.println((page.getStartPage() - 1) * page.getPageSize() + ">>>" + page.getPageSize());
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, corpId, (page.getStartPage() - 1) * page.getPageSize(), page.getPageSize());
        return result;
    }

    public Map<String, Object> getCourseById(String id) {
        String sql = "SELECT * from course where id=?";
        Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, id);
        return result;
    }

    public int isCourse(String userId, String courseId) {
        String sql = "SELECT count(userid) from course_user where userid= ? and courseid=?";
        int result = this.jdbcTemplate.queryForInt(sql, userId, courseId);
        return result;
    }

    public int orderCourse(String userId, String courseId) {
        String sql = "insert into course_user (courseid,userid) VALUES (?,?)";
        int result = 0;
        try {
            result = this.jdbcTemplate.update(sql, courseId, userId);
        } catch (Exception e) {

        }
        return result;
    }

    public int save(Course course, String corpid) {
        String sql = "insert into course (place,icon,id,coursetype,coursename,starttime,endtime,coursedesc,expectperson,projectid,createtime,corpid) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        int result = this.jdbcTemplate.update(sql, course.getPlace(), course.getIcon(), course.getId(), course.getType(), course.getName(), course.getStarttime(), course.getEndtime(), course.getDesc(), course.getExpectperson(), course.getProjectid(), BaseMethod.getCurrentTime(), corpid);
        return result;
    }

    public int delete(String projectid) {
        try {
            String sql = "DELETE from course where id=?";
            int result = this.jdbcTemplate.update(sql, projectid);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public Map<String, Object> getFuzerenById(String id) {
        String sql = "SELECT au from course_leader cl,auth_user au where cl.courseid=? and cl.userid=au.userid";
        Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, id);
        return result;
    }

}
