package top.wexue.base.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 7/26/15.
 */
@Service
public class PortalDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int updateSchoolNotify(String id, String schoolNotify) {
        try {
            String sql = "UPDATE portal SET schoolnotify=? where id=?";
            int result = this.jdbcTemplate.update(sql, schoolNotify, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public int updateSchoolDesc(String id, String schoolDesc) {
        try {
            String sql = "UPDATE portal SET schooldesc=? where id=?";
            int result = this.jdbcTemplate.update(sql, schoolDesc, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }


    public Map<String, Object> getPortalById(String id) {
        try {
            String sql = "SELECT * from portal where id=?";
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Map<String, Object>> getPortals(String corpid) {
        try {
            String sql = "SELECT * from portal where corpid=?  ORDER BY createtime DESC ";
            List<Map<String, Object>> results = this.jdbcTemplate.queryForList(sql, corpid);
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

    public int save(String id, String wdesc, String model, String modelstyle, String corpid) {
        String sql = "insert into portal (id,wdesc,model,modelstyle,corpid,createtime) values (?,?,?,?,?,?)";
        int result = this.jdbcTemplate.update(sql, id, wdesc, model, modelstyle, corpid, new Date().getTime());
        return result;
    }

    public int delete(String id) {
        try {
            String sql = "DELETE from portal where id=?";
            int result = this.jdbcTemplate.update(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
    public int open(String id,String corpid) {
        try {
            int result1 = this.jdbcTemplate.update("UPDATE portal SET isopen=0 where corpid=?", corpid);
            String sql = "UPDATE portal SET isopen=1 where id=?";
            int result = this.jdbcTemplate.update(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
    public int noOpen(String id) {
        try {
            String sql = "UPDATE portal SET isopen=0 where id=?";
            int result = this.jdbcTemplate.update(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
}
