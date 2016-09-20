package top.wexue.base.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by lihb on 7/26/15.
 */
@Service
public class QYDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getQYById(String id) {
        try {
            String sql = "SELECT * from qy where id=?";
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Map<String, Object> getQYByUserId(String userId) {
        try {
            String sql = "SELECT a.name,a.corpid,a.secret from qy a,user_qy b where a.id=b.qyid and b.userid=?";
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, userId);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Map<String, Object> getQYByName(String name) {
        String sql = "SELECT * from qy where qyname=?";
        try {
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, name);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int save(String name, String pwd, String tel) {
        long start = new Date().getTime();
        String sql = "insert into qy (qyname,password,tel,starttime,endtime) values (?,?,?,?,?)";
        //30天有效期
        int result = this.jdbcTemplate.update(sql, name, pwd, tel, start, start + 24 * 60 * 60 * 1000 * 30);
        return result;
    }
}
