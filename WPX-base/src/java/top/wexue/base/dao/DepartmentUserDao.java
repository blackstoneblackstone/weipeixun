package top.wexue.base.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 7/26/15.
 */
@Repository
public class DepartmentUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    String TABLE = "department_user";

    public List<Map<String, Object>> getLeaderListByProjectId(String projectId) {
        String sql = "SELECT * from project_leader where projectid=?";
        //30天有效期
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, projectId);
        return result;
    }

    public int save(Integer departmentid, String userId, String corpid) {
        String sql = "insert into " + TABLE + " (departmentid,authuserid,corpid) values (?,?,?)";
        int result = this.jdbcTemplate.update(sql, departmentid, userId, corpid);
        return result;
    }

    public int delete(Integer departmentid, String authuserid, String corpid) {
        try {
            String sql = "DELETE from " + TABLE + " where departmentid=? AND authuserid=? AND corpid=?";
            int result = this.jdbcTemplate.update(sql, departmentid, authuserid, corpid);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public int update(Integer departmentid, String authuserid, String corpid) {
        String sql = "SELECT * from " + TABLE + " where departmentid=? AND authuserid=? AND corpid=?";
        List result = this.jdbcTemplate.queryForList(sql, departmentid, authuserid, corpid);
        int result1 = 0;
        if (result == null && result.size() == 0) {
            String sql1 = "insert into " + TABLE + " (departmentid,authuserid,corpid) values (?,?,?)";
            result1 = this.jdbcTemplate.update(sql1, departmentid, authuserid, corpid);
        }
        return result1;
    }
}
