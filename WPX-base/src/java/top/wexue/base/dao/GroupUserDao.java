package top.wexue.base.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import top.wexue.base.model.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 7/26/15.
 */
@Service
public class GroupUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> getGroups(String courseid,String corpid, Page page) {
        String sql = "SELECT * from group_user where courseid=? ";
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, corpid, (page.getStartPage() - 1) * page.getPageSize(), page.getPageSize());
        return result;
    }

    public List<Map<String, Object>> getUsersByGroupId(String groupid) {
        String sql = "SELECT auth_user.* from group_user,auth_user where group_user.userid=auth_user.userid AND group_user.groupid=?";
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, groupid);
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

    public List<Map<String, Object>> getPublicCourses(String corpId, Page page) {
        String sql = "SELECT * from course where corpid=? and coursetype=1  order by starttime DESC limit ?,?";
        System.out.println((page.getStartPage() - 1) * page.getPageSize() + ">>>" + page.getPageSize());
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, corpId, (page.getStartPage() - 1) * page.getPageSize(), page.getPageSize());
        return result;
    }

    public Map<String, Object> getCourseById(String id) {
        String sql = "SELECT c.*,s.name teacher from course c,speaker s,course_speaker cs where c.id=? and c.id=cs.courseid and cs.speakerid=s.id";
        Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, id);
        return result;
    }


    public int save(String userid, String groupid) {
        String sql = "insert into group_user (userid,groupid) values (?,?)";
        int result = this.jdbcTemplate.update(sql, userid, groupid);
        return result;
    }

    public int delete(String id) {
        try {
            String sql = "DELETE from group_user where groupid=?";
            int result = this.jdbcTemplate.update(sql, id);
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
