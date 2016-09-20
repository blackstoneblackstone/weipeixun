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
public class ProjectLeaderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> getLeaderListByProjectId(String projectId) {
        String sql = "SELECT * from project_leader where projectid=?";
        //30天有效期
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, projectId);
        return result;
    }
    public int save(String projectId, String userId) {
        String sql = "insert into project_leader (projectid,userid) values (?,?)";
        //30天有效期
        int result = this.jdbcTemplate.update(sql, projectId, userId);
        return result;
    }
    public int delete(String projectid) {
        try {
            String sql = "DELETE from project_leader where projectid=?";
            int result=this.jdbcTemplate.update(sql, projectid);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
}
