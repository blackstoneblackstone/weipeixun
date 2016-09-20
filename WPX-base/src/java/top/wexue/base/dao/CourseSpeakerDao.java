package top.wexue.base.dao;

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
public class CourseSpeakerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> getUserListByProjectId(String projectId) {
        String sql = "SELECT * from project_user where projectid=?";
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, projectId);
        return result;
    }

    public int setUser(String courseId, String userId) {
        String sql = "insert into course_user (courseid,speaker) values (?,?)";
        int result = this.jdbcTemplate.update(sql, courseId, userId);
        return result;
    }
    public int setLeader(String courseId, String userId) {
        String sql = "insert into course_leader (courseid,speaker) values (?,?)";
        int result = this.jdbcTemplate.update(sql, courseId, userId);
        return result;
    }

    public int setSpeaker(String courseId, String userId) {
        String sql = "insert into course_speaker (courseid,speaker) values (?,?)";
        int result = this.jdbcTemplate.update(sql, courseId, userId);
        return result;
    }
    public  Map<String, Object> getSpeakerByCourseId(String courseId) {
        String sql = "SELECT * from course_speaker where courseid=?";
        Map<String, Object> result= this.jdbcTemplate.queryForMap(sql, courseId);
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
            String sql = "DELETE from project_speaker where courseid=?";
            int result = this.jdbcTemplate.update(sql, courseid);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
}
