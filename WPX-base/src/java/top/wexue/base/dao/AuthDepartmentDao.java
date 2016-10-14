package top.wexue.base.dao;

import com.foxinmy.weixin4j.qy.model.CorpInfo;
import com.foxinmy.weixin4j.qy.model.OUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import top.wexue.base.utils.BaseMethod;
import top.wexue.base.utils.Constants;

import java.util.List;
import java.util.Map;

/**
 * Created by lihb on 7/26/15.
 */
@Service
public class AuthDepartmentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    String TABLE = "auth_department";

    public List<Map<String, Object>> getDepartments(String corpid) {
        String sql = "SELECT * from " + TABLE + " where corpid=?";
        List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, corpid);
        return result;
    }

    public int insert(OUserInfo oUserInfo, String suiteId) {
        String sql = "insert into " + TABLE + " (id,departmentid,corpid,departmentname,parentid,orderby,suiteid) values (?,?,?,?,?,?,?)";
        List<OUserInfo.DepartItem> departItems = oUserInfo.getAuthInfo().getDepartItems();
        int result = 0;
        for (OUserInfo.DepartItem departItem : departItems) {
            String id = BaseMethod.createUUID(Constants.EntityType.AUTHDEPARTMENT);
            result = result + this.jdbcTemplate.update(sql, id, departItem.getId(),oUserInfo.getCorpInfo().getCorpId(), departItem.getName(), departItem.getParentId(), departItem.getOrder(),suiteId);
        }
        return result;
    }

    public int delete(OUserInfo oUserInfo, String suiteId) {
        String Sql = "DELETE FROM auth_info WHERE suiteid=? AND appid=? AND corpid=?";
        List<OUserInfo.AgentItem> agentItems = oUserInfo.getAgentInfo();
        CorpInfo corpInfo = oUserInfo.getCorpInfo();
        int i=0;
        for (OUserInfo.AgentItem agentItem : agentItems) {
            i=i+jdbcTemplate.update(Sql, suiteId,agentItem.getAppId(),corpInfo.getCorpId());
        }
        return i;
    }
}
