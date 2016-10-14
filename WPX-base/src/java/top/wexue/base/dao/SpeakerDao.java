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
public class SpeakerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> getSpeakers(String corpid, Page page) {
        String sql = "SELECT * from speaker where corpid=? ORDER BY createtime DESC limit ?,?";
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, corpid, (page.getStartPage() - 1) * page.getPageSize(), page.getPageSize());
        return result;
    }

    public Map<String, Object> getSpeakerById(String id) {
        String sql = "SELECT * from speaker where id=?";
        Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, id);
        return result;
    }

    public int save(String id, String sname, String sdesc, String avator, String corpid) {
        String sql = "insert into speaker (id,sname,sdesc,avator,corpid,createtime) values (?,?,?,?,?,?)";
        int result = this.jdbcTemplate.update(sql, id, sname, sdesc, avator, corpid, BaseMethod.getCurrentTime());
        return result;
    }

    public int delete(String id) {
        try {
            String sql = "DELETE from speaker where id=?";
            int result = this.jdbcTemplate.update(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }


}
