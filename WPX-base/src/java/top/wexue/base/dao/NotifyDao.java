package top.wexue.base.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import top.wexue.base.model.Page;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 7/26/15.
 */
@Service
public class NotifyDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getResearchById(String id) {
        try {
            String sql = "SELECT * from research where id=?";
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Map<String, Object>> getNotifys(String corpid, Page page) {
        try {
            String sql = "SELECT * from notify where corpid=?  ORDER BY createtime DESC limit ?,?";
            List<Map<String, Object>> results = this.jdbcTemplate.queryForList(sql, corpid, page.getStart(), page.getPageSize());
            return results;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Map<String, Object> getProjectByName(String name) {
        String sql = "SELECT * from Project where Projectname=?";
        try {
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, name);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int save(String id, String ndesc, String ntype, String corpid) {
        String sql = "insert into notify (id,ndesc,ntype,createtime,corpid) values (?,?,?,?,?)";
        int result = this.jdbcTemplate.update(sql, id, ndesc, ntype, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), corpid);
        return result;
    }

    public int delete(String id) {
        try {
            String sql = "DELETE from research where id=?";
            int result = this.jdbcTemplate.update(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
}
