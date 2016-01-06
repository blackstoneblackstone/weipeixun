package top.wexue.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import top.wexue.model.User;
import top.wexue.utils.BaseMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 7/26/15.
 */
@Service
public class AuthUserDao {
    String TABLE = "auth_user";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getUserById(String id) {
        try {
            String sql = "SELECT * from auth_user where userid=?";
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Map<String, Object> getUserByName(String name) {
        String sql = "SELECT * from auth_user where username=?";
        try {
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, name);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Map<String, Object>> getUserByParty(int partyId, int page, String corpid) {
        String sql = "SELECT * from auth_user au,department_user du where du.authuserid=au.userid and du.departmentid=? and au.corpid=? limit ?, 10";
        try {
            List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, partyId, corpid, (page - 1) * 10);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean isHaveUserId(String userId) {
        String sql = "select count(userid) from "+TABLE+" where userid=?";
        int re = this.jdbcTemplate.queryForInt(sql, userId);
        if (re > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int save(User user, String corpid) {
        String sql = "insert into auth_user (userid,name,position,mobile,gender,email,weixinid,status,corpid,createtime,updatetime) values (?,?,?,?,?,?,?,?,?,?,?)";
        int result = this.jdbcTemplate.update(sql, user.getUserid(), user.getName(), user.getPosition(), user.getMobile(), user.getGender(), user.getEmail(), user.getWeixinid(), user.getStatus(), corpid, BaseMethod.getCurrentTime(), BaseMethod.getCurrentTime());
        return result;
    }
}
