package top.wexue.base.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import top.wexue.base.model.Page;
import top.wexue.base.utils.BaseMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 7/26/15.
 */
@Service
public class GroupDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> getGroups(String corpid, Page page) {
        String sql = "SELECT * from chatgroup where corpid=? ORDER BY createtime DESC limit ?,?";
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, corpid, (page.getStartPage() - 1) * page.getPageSize(), page.getPageSize());
        return result;
    }

    public List<Map<String, Object>> getGroupsByCourseId(String courseid, String corpid, Page page) {
        String sql = "SELECT * from chatgroup where corpid=? AND courseid=? ORDER BY createtime DESC limit ?,?";
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, corpid, courseid, (page.getStartPage() - 1) * page.getPageSize(), page.getPageSize());
        return result;
    }

    public List<Map<String, Object>> getUsersByGroupId(String groupId) {
        String sql = "SELECT * from auth_user,group_user where group_user.groupId=? AND group_user.userid=auth_user.userid";
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, groupId);
        return result;
    }

    public Map<String, Object> getGroupById(String id) {
        String sql = "SELECT * from chatgroup where id=?";
        Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, id);
        return result;
    }


    public int save(String id, String gname, String gdesc, String courseid, String projectid, String corpid) {
        String sql = "insert into chatgroup (id,gname,gdesc,courseid,projectid,corpid,createtime) values (?,?,?,?,?,?,?)";
        int result = this.jdbcTemplate.update(sql, id, gname, gdesc, courseid, projectid, corpid, BaseMethod.getCurrentTime());
        return result;
    }

    public int delete(String id) {
        try {
            String sql = "DELETE from chatgroup where id=?";
            int result = this.jdbcTemplate.update(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

}
