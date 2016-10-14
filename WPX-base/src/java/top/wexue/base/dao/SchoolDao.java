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
public class SchoolDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getSchoolBycorpid(String corpid) {
        try {
            String sql = "SELECT * from portal where corpid=? and isopen=1";
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, corpid);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Map<String, Object>> getProjectListByCorpid(String corpid,Page page) {
        try {
            String sql = "SELECT * from project where corpid=?  ORDER BY createtime DESC limit ?,?";
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

    public int save(String id,String corpid,String schooldesc,String schoolnotify) {
        Map<String, Object> index= getSchoolBycorpid(corpid);
        String schoolindex="暂未配置";
        if(index!=null){
            delete(corpid);
            schoolindex=index.get("schoolindex").toString();
        }
        String sql = "insert into school (id,schooldesc,schoolnotify,schoolindex,corpid,createtime) values (?,?,?,?,?,?)";
        int result = this.jdbcTemplate.update(sql, id,schooldesc,schoolnotify,schoolindex,corpid,new Date().getTime());
        return result;
    }
    public int saveIndex(String id,String corpid,String schoolindex) {
        Map<String, Object> index= getSchoolBycorpid(corpid);
        String sql ="insert into school (id,schooldesc,schoolnotify,schoolindex,corpid,createtime) values (?,?,?,?,?,?)";
        if(index!=null){
            sql="update school set schoolindex=? where corpid=?";
            int result =this.jdbcTemplate.update(sql,schoolindex,corpid);
            return result;
        }else{
            int result = this.jdbcTemplate.update(sql, id,"暂未配置","暂未配置",schoolindex,corpid,new Date().getTime());
            return result;
        }


    }

    public int delete(String corpid) {
        try {
            String sql = "DELETE from school where corpid=?";
            int result=this.jdbcTemplate.update(sql, corpid);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
}
