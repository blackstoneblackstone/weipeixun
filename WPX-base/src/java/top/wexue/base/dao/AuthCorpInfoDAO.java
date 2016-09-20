package top.wexue.base.dao;

import com.foxinmy.weixin4j.qy.model.CorpInfo;
import com.foxinmy.weixin4j.qy.model.OUserInfo;
import com.foxinmy.weixin4j.qy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by lihb on 10/24/15.
 */
@Service
public class AuthCorpInfoDAO {
    String suiteid = "tj3e700c876e37c5a5";
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int insert(OUserInfo oUserInfo, String permanent_code, String suiteId) {
        String Sql = "insert into auth_corp_info(permanent_code,corpid,corp_name,corp_type,corp_round_logo_url,corp_square_logo_url,corp_user_max,corp_agent_max,corp_wxqrcode,email,mobile,suiteid)values (?,?,?,?,?,?,?,?,?,?,?,?)";
        CorpInfo corpInfo = oUserInfo.getCorpInfo();
        User user = oUserInfo.getAdminInfo();
        return jdbcTemplate.update(Sql, permanent_code, corpInfo.getCorpId(), corpInfo.getCorpName(), corpInfo.getCorpType(), corpInfo.getRoundLogoUrl(), corpInfo.getSquareLogoUrl(), corpInfo.getUserMax(), corpInfo.getAgentMax(), corpInfo.getWxQrCode(), user.getEmail(), user.getMobile(), suiteId);
    }

    public Map<String, Object> getCorpById(String id) {
        String Sql = "select * from auth_corp_info where corpid=?";
        return jdbcTemplate.queryForMap(Sql, id);
    }

    public int delete(String suiteId, String corpid) {
        String Sql = "DELETE  FROM auth_corp_info WHERE suiteid=? AND corpid=?";
        return jdbcTemplate.update(Sql, suiteId, corpid);
    }

}
