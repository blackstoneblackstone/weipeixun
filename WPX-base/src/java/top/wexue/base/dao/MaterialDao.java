package top.wexue.base.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import top.wexue.base.model.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 7/26/15.
 */
@Service
public class MaterialDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getMaterialById(String id) {
        try {
            String sql = "SELECT * from material where id=?";
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Map<String, Object>> getMaterialListByCorpid(String corpid,Page page) {
        try {
            String sql = "SELECT * from material where corpid=?  ORDER BY createtime DESC limit ?,?";
            List<Map<String, Object>> results = this.jdbcTemplate.queryForList(sql, corpid,page.getStart(),page.getPageSize());
            return results;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Map<String, Object> getMaterialByName(String name) {
        String sql = "SELECT * from Material where Materialname=?";
        try {
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, name);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int save(String id,String mname,String path,String corpid) {
        String sql = "insert into material (id,mname,path,corpid,createtime) values (?,?,?,?,?)";
        int result = this.jdbcTemplate.update(sql, id,mname,path,corpid,new Date().getTime());
        return result;
    }

    public int delete(String id) {
        try {
            String sql = "DELETE from material where id=?";
            int result=this.jdbcTemplate.update(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
}
