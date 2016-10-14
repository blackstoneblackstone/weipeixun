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
public class TaskDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> getMessageListByCorpid(String corpid,Page page) {
        try {
            String sql = "SELECT * from task where corpid=?  ORDER BY createtime DESC limit ?,?";
            List<Map<String, Object>> results = this.jdbcTemplate.queryForList(sql, corpid,page.getStart(),page.getPageSize());
            return results;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public int save(String title,String desc,String corpid,String type){
        String sql="insert into task (taskid,state,corpid,createtime，type)values(?,?,?,?,?)";
        int i=this.jdbcTemplate.update(sql,title,desc,corpid,new Date().getTime(),type);
        return i;
    }

}
