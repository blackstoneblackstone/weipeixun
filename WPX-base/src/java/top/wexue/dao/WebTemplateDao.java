package top.wexue.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import top.wexue.model.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 7/26/15.
 */
@Service
public class WebTemplateDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getProjectById(String id) {
        try {
            String sql = "SELECT * from web_template where id=?";
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public Map<String, Object> getTemplateByCorpId(String corpid) {
        try {
            String sql = "SELECT * from web_template where corpid=?";
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, corpid);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Map<String, Object>> getProjectListByCorpid(String corpid,Page page) {
        try {
            String sql = "SELECT * from web_template where corpid=?  ORDER BY createtime DESC limit ?,?";
            List<Map<String, Object>> results = this.jdbcTemplate.queryForList(sql, corpid,page.getStart(),page.getPageSize());
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

    public int save(String id,String name, String desc, String starttime,String endtime,String corpid) {
        String sql = "insert into web_template (id,proname,prodesc,starttime,endtime,corpid,createtime) values (?,?,?,?,?,?,?)";
        //30天有效期
        int result = this.jdbcTemplate.update(sql, id,name, desc, starttime, endtime,corpid,new Date().getTime());
        return result;
    }

    public int delete(String id) {
        try {
            String sql = "DELETE from web_template where id=?";
            int result=this.jdbcTemplate.update(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
}
