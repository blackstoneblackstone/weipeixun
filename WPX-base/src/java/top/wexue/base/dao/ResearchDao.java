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
public class ResearchDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getResearchById(String id) {
        try {
            String sql = "SELECT * from research where id=?";
            Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Map<String, Object>> getResearchs(String corpid, Page page) {
        try {
            String sql = "SELECT * from research where corpid=?  ORDER BY createtime DESC limit ?,?";
            List<Map<String, Object>> results = this.jdbcTemplate.queryForList(sql, corpid, page.getStart(), page.getPageSize());
            return results;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Map<String, Object>> getResearchsByCourseId(String courseid, String corpid, Page page) {
        try {
            String sql = "SELECT * from research,research_course where research.id=research_course.researchid AND research.corpid=? AND research_course.courseid=? ORDER BY research.createtime DESC limit ?,?";
            List<Map<String, Object>> results = this.jdbcTemplate.queryForList(sql, corpid, courseid, page.getStart(), page.getPageSize());
            return results;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int save(String id, String rname, String rdesc, String rdata, String rtype, String corpid) {
        String sql = "insert into research (id,rname,rdesc,rdata,createtime,corpid,rtype) values (?,?,?,?,?,?,?)";
        int result = this.jdbcTemplate.update(sql, id, rname, rdesc, rdata, new Date().getTime(), corpid, rtype);
        return result;
    }

    public int delete(String id) {
        try {
            String sql = "DELETE from research where id=?";
            int result = this.jdbcTemplate.update(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
    public int isUsed(String id) {
        try {
            String sql = "UPDATE research SET isuse=1 where id=?";
            int result = this.jdbcTemplate.update(sql, id);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
}
