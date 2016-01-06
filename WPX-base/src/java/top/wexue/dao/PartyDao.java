package top.wexue.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 7/26/15.
 */
@Service
public class PartyDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> getPartysByParentId(int partyId,String corpid) {
        String sql = "SELECT * from auth_department where parentid=? and corpid=?";
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, partyId,corpid);
        return result;
    }
//    public Map<String, Object> getPartyById(String id) {
//        String sql = "SELECT * from party where id=?";
//        Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, id);
//        return result;
//    }

//    public int save(PartyForm party, String corpid) {
//        String sql = "insert into party (place,icon,id,partytype,partyname,starttime,endtime,partydesc,expectperson,projectid,createtime,corpid) values (?,?,?,?,?,?,?,?,?,?,?,?)";
//        int result = this.jdbcTemplate.update(sql, party.getPlace(), party.getIcon(), party.getId(), party.getType(), party.getName(), party.getStarttime(), party.getEndtime(), party.getDesc(), party.getExpectperson(), party.getProjectid(), BaseMethod.getCurrentTime(), corpid);
//        return result;
//    }
//
//    public int delete(String projectid) {
//        try {
//            String sql = "DELETE from project_user where projectid=?";
//            int result = this.jdbcTemplate.update(sql, projectid);
//            return result;
//        } catch (EmptyResultDataAccessException e) {
//            return 0;
//        }
//    }
//
//    public Map<String, Object> getFuzerenById(String id) {
//        String sql = "SELECT au from party_leader cl,auth_user au where cl.partyid=? and cl.userid=au.userid";
//        Map<String, Object> result = this.jdbcTemplate.queryForMap(sql, id);
//        return result;
//    }

}
