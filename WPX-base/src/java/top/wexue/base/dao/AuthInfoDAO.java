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
 * Created by lihb on 10/24/15.
 */
@Service
public class AuthInfoDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(OUserInfo oUserInfo, String suiteId) {
        System.out.println("oUserInfo" + oUserInfo.toString());
        String Sql = "insert into auth_info(id,corpid,agentid,iname,square_logo_url,round_logo_url,appid,api_group,suiteid)values (?,?,?,?,?,?,?,?,?)";
        List<OUserInfo.AgentItem> agentItems = oUserInfo.getAuthInfo().getAgentItems();
        CorpInfo corpInfo = oUserInfo.getCorpInfo();
        int i = 0;
        if (agentItems != null) {
            for (OUserInfo.AgentItem agentItem : agentItems) {
                String id = BaseMethod.createUUID(Constants.EntityType.AUTHINFO);
                String agS = "";
                if (agentItem.getApiGroup() != null) {
                    for (String ag : agentItem.getApiGroup()) {
                        agS = agS + ag + ";";
                    }
                }
                i = i + jdbcTemplate.update(Sql, id, corpInfo.getCorpId(), agentItem.getAgentId(), agentItem.getName(), agentItem.getSquareLogoUrl(), agentItem.getRoundLogoUrl(), agentItem.getAppId(), agS, suiteId);
            }
        }
        return i;
    }

    public Map<String, Object> getAuthInfoByAppId(String corpid, String appid) {
        String Sql = "select * from auth_info where corpid=? and appid=?";
        return jdbcTemplate.queryForMap(Sql, corpid, appid);
    }

    public Map<String, Object> getAuthInfoByAgentId(String corpid, int agentid) {
        String Sql = "select * from auth_info where corpid=? and agentid=?";
        return jdbcTemplate.queryForMap(Sql, corpid, agentid);
    }

    public List<Map<String, Object>> getAuthInfosByCorpid(String corpid) {
        String Sql = "select * from auth_info where corpid=?";
        return jdbcTemplate.queryForList(Sql, corpid);
    }

    public int delete(OUserInfo oUserInfo, String suiteId) {
        String Sql = "DELETE FROM auth_info WHERE suiteid=? AND appid=? AND corpid=?";
        List<OUserInfo.AgentItem> agentItems = oUserInfo.getAuthInfo().getAgentItems();
        CorpInfo corpInfo = oUserInfo.getCorpInfo();
        int i = 0;
        if (agentItems != null) {
            for (OUserInfo.AgentItem agentItem : agentItems) {
                i = i + jdbcTemplate.update(Sql, suiteId, agentItem.getAppId(), corpInfo.getCorpId());
            }
        }
        return i;
    }


}
