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
public class LoginUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getUserById(String id) {
        try {
            String sql = "SELECT * from login_user where id=?";
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Map<String, Object> getUserByName(String name) {
        String sql = "SELECT * from login_user where username=?";
        try {
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, name);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int save(String id,String name, String pwd, String tel,String corpid) {
        long start = new Date().getTime();
        String sql = "insert into login_user (id,username,password,tel,starttime,endtime,corpid) values (?,?,?,?,?,?,?)";
        //30天有效期
        int result = this.jdbcTemplate.update(sql,id, name, pwd, tel, start, start + 24 * 60 * 60 * 1000 * 30,corpid);
        return result;
    }
}
