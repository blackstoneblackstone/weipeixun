package top.wexue.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import top.wexue.model.Page;
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

    public Map<String, Object> getUserById(String id,String corpid) {
        try {
            String sql = "SELECT * from auth_user where userid=? AND corpid=?";
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, id,corpid);
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

    public List<Map<String, Object>> getUserByPartyId(String partyId, Page page, String corpid) {
        String sql = "SELECT * from auth_user au,department_user du where du.authuserid=au.userid and du.departmentid=? and au.corpid=? limit ?, ?";
        try {
            List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, partyId, corpid, (page.getStartPage() - 1) * page.getPageSize(), page.getPageSize());
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Map<String, Object>> getUsersByCorpId(String corpid) {
        String sql = "SELECT * from auth_user  where corpid=? ";
        try {
            List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql,  corpid);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public List<Map<String, Object>> getUsersByDepId(String depId, String corpid) {
        String sql = "SELECT * from auth_user au,department_user du where du.authuserid=au.userid and du.departmentid=? and au.corpid=? AND du.corpid=?";
        try {
            List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, depId, corpid,corpid);
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
    public int delete(String userid) {
        try {
            String sql = "DELETE from auth_user where userid=?";
            int result = this.jdbcTemplate.update(sql, userid);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public int save(User user, String corpid) {
        String sql = "insert into auth_user (userid,username,position,mobile,gender,email,weixinid,corpid,createtime,updatetime) values (?,?,?,?,?,?,?,?,?,?)";
        int result = this.jdbcTemplate.update(sql, user.getUserid(), user.getName(), user.getPosition(), user.getMobile(), user.getGender(), user.getEmail(), user.getWeixinid(), corpid, BaseMethod.getCurrentTime(), BaseMethod.getCurrentTime());
        return result;
    }
    public int update(User user, String corpid,String id) {
        String sql = "UPDATE auth_user SET userid=?,username=?,position=?,mobile=?,gender=?,email=?,weixinid=?,updatetime=? WHERE userid=? AND corpid=?";
        int result = this.jdbcTemplate.update(sql, user.getUserid(), user.getName(), user.getPosition(), user.getMobile(), user.getGender(), user.getEmail(), user.getWeixinid(),BaseMethod.getCurrentTime(),id,corpid);
        return result;
    }
}
