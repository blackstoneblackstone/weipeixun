package top.wexue.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 7/26/15.
 */
@Service
public class CourseUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> getUserListByProjectId(String projectId) {
        String sql = "SELECT auth_user.* from project_user,auth_user where project_user.userid=auth_user.userid AND projectid=?";
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, projectId);
        return result;
    }
    public List<Map<String, Object>> getUserListByCourseId(String courseid) {
        String sql = "SELECT auth_user.* from course_user,auth_user where course_user.userid=auth_user.userid AND courseid=?";
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, courseid);
        return result;
    }

    public int setUser(String courseId, String userId) {
        String sql = "insert into course_user (courseid,userid) values (?,?)";
        int result = this.jdbcTemplate.update(sql, courseId, userId);
        return result;
    }
    public int setLeader(String courseId, String userId) {
        String sql = "insert into course_leader (courseid,userid) values (?,?)";
        int result = this.jdbcTemplate.update(sql, courseId, userId);
        return result;
    }

    public int setSpeaker(String courseId, String userId) {
        String sql = "insert into course_speaker (courseid,speakerid) values (?,?)";
        int result = this.jdbcTemplate.update(sql, courseId, userId);
        return result;
    }


    public int deleteUser(String courseid) {
        try {
            String sql = "DELETE from course_user where courseid=?";
            int result = this.jdbcTemplate.update(sql, courseid);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
    public int deleteLeader(String courseid) {
        try {
            String sql = "DELETE from course_leader where courseid=?";
            int result = this.jdbcTemplate.update(sql, courseid);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
    public int deleteSpeaker(String courseid) {
        try {
            String sql = "DELETE from course_speaker where courseid=?";
            int result = this.jdbcTemplate.update(sql, courseid);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
}
