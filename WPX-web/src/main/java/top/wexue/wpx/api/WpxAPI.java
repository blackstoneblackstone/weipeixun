package top.wexue.wpx.api;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.JsonResult;
import com.foxinmy.weixin4j.model.Button;
import com.foxinmy.weixin4j.qy.api.MenuApi;
import com.foxinmy.weixin4j.qy.api.OauthApi;
import com.foxinmy.weixin4j.qy.api.SuiteApi;
import com.foxinmy.weixin4j.qy.api.UserApi;
import com.foxinmy.weixin4j.qy.model.AgentSetter;
import com.foxinmy.weixin4j.qy.model.OUserInfo;
import com.foxinmy.weixin4j.qy.model.User;
import com.foxinmy.weixin4j.qy.suite.SuitePerCodeHolder;
import com.foxinmy.weixin4j.qy.suite.SuiteTicketHolder;
import com.foxinmy.weixin4j.token.RedisTokenStorager;
import com.foxinmy.weixin4j.token.TokenHolder;
import com.foxinmy.weixin4j.type.ButtonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wexue.dao.AuthCorpInfoDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihb on 10/23/15.
 */
@Service
public class WpxAPI {
    private String suiteId = "tj3e700c876e37c5a5";
    private String suiteSecret = "jNxEH7UABaHtrwOhGG2jBpUfU3x_EISNJTAGjjpYSGwFBmSSYZTb-Q9xsQgpyP4E";

    private String serverId = "tjf5217ebac93f0b28";
    private String serverSecret = "eaBQNTXz0HkFn21iTUhuI--fBwc9wRtDn5Z3vohzYehBvgKoOghhkEn_9Cih8C80";

    public String getSuiteId() {
        return suiteId;
    }


    public SuiteApi suiteApi;
    public SuiteApi serverApi;
    public OauthApi oauthApi;
    @Autowired
    AuthCorpInfoDAO authCorpInfoDAO;

    public WpxAPI() {
        RedisTokenStorager redisTokenStorager = new RedisTokenStorager();
        suiteApi = new SuiteApi(new SuiteTicketHolder(suiteId, suiteSecret, redisTokenStorager));
        serverApi = new SuiteApi(new SuiteTicketHolder(serverId, serverSecret, redisTokenStorager));
        oauthApi = new OauthApi();
    }

    public String getSuiteAuthorizeURL() throws WeixinException {
        TokenHolder tokenHolder = suiteApi.getPreCodeHolder();
        String suiteAuthorizeURL = oauthApi.getSuiteAuthorizeURL(suiteId, tokenHolder.getAccessToken(), "http://www.wexue.top/auth/getauthcode", "suite");
        return suiteAuthorizeURL;
    }

    public String getServerAuthorizeURL() throws WeixinException {
        TokenHolder tokenHolder = serverApi.getPreCodeHolder();
        String suiteAuthorizeURL = oauthApi.getSuiteAuthorizeURL(serverId, tokenHolder.getAccessToken(), "http://www.wexue.top/auth/getauthcode", "server");
        return suiteAuthorizeURL;
    }

    public OUserInfo exchangeSuitePermanentCode(String authcode) throws WeixinException {
        OUserInfo oUserInfo = suiteApi.exchangePermanentCode(authcode);
        return oUserInfo;
    }

    public OUserInfo exchangeServerPermanentCode(String authcode) throws WeixinException {
        OUserInfo oUserInfo = serverApi.exchangePermanentCode(authcode);
        return oUserInfo;
    }

    public String getPerCode(String authCorpId) throws WeixinException {
        SuitePerCodeHolder suitePerCodeHolder = suiteApi.getPerCodeHolder(authCorpId);
        return suitePerCodeHolder.getPermanentCode();
    }

    public String createMenu(String authCorid, OUserInfo oUserInfo) throws WeixinException {
        TokenHolder tokenHolder = suiteApi.getTokenSuiteHolder(authCorid);
        MenuApi menuApi = new MenuApi(tokenHolder);
        String msg = "agentItems:>>";
        for (OUserInfo.AgentItem agentItem : oUserInfo.getAuthInfo().getAgentItems()) {
            if (agentItem.getAppId() == 1) {
                List<Button> buttons = new ArrayList<Button>();
                //http://www.wexue.top/pxkc/authUser?corpId=wxf54e1b5e0b62fa96&state=wxf54e1b5e0b62fa96
                //Button xueyuan = new Button("学院门户", "http://www.wexue.top/WPX-web/pxkc/portalWebJsp?corpId="+oUserInfo.getCorpInfo().getCorpId(), ButtonType.view);
                //Button xueyuan = new Button("学院门户", "http://www.wexue.top/pxkc/portalWebJsp?corpId="+oUserInfo.getCorpInfo().getCorpId(), ButtonType.view);
                Button gongkai = new Button("公开课", "http://www.wexue.top/auth/authUser?state=public&corpId=" + oUserInfo.getCorpInfo().getCorpId(), ButtonType.view);
                Button bixiu = new Button("必修课", "http://www.wexue.top/auth/authUser?state=require&corpId=" + oUserInfo.getCorpInfo().getCorpId(), ButtonType.view);
                //buttons.add(xueyuan);
                buttons.add(gongkai);
                buttons.add(bixiu);
                JsonResult jsonResult = menuApi.createMenu(buttons, agentItem.getAgentId());
                msg = msg + agentItem.getAppId() + ":" + jsonResult.getDesc() + ";";
                //设置信息
                AgentSetter agentSetter = new AgentSetter(agentItem.getAgentId());
                agentSetter.setRedirectDomain("www.wexue.top");
                agentSetter.setReportEnter(true);
                agentSetter.setReportUser(true);
                suiteApi.setAgent(authCorid, agentSetter);
            }
            if (agentItem.getAppId() == 2) {
                List<Button> buttons = new ArrayList<Button>();
                Button xueyuan = new Button("课程签到", "scancode_push", ButtonType.scancode_push);
                Button gongkai = new Button("我的课程", "http://www.wexue.top/auth/authUser?state=mycourse&corpId=" + oUserInfo.getCorpInfo().getCorpId(), ButtonType.view);
//               Button bixiu = new Button("我的必修课", "http://www.wexue.top/auth/authUser?state=myrequired&corpId="+oUserInfo.getCorpInfo().getCorpId(), ButtonType.view);
                buttons.add(xueyuan);
                buttons.add(gongkai);
//                buttons.add(bixiu);
                JsonResult jsonResult = menuApi.createMenu(buttons, agentItem.getAgentId());
                msg = msg + agentItem.getAppId() + ":" + jsonResult.getDesc() + ";";

                //设置信息
                AgentSetter agentSetter = new AgentSetter(agentItem.getAgentId());
                agentSetter.setRedirectDomain("www.wexue.top");
                agentSetter.setReportEnter(true);
                agentSetter.setReportUser(true);
                suiteApi.setAgent(authCorid, agentSetter);
            }
//            if (agentItem.getAppId()==3) {
//                //设置信息
//                AgentSetter agentSetter=new AgentSetter(agentItem.getAgentId());
//                agentSetter.setRedirectDomain("www.wexue.top");
//                agentSetter.setReportEnter(true);
//                agentSetter.setReportUser(true);
//                suiteApi.setAgent(authCorid,agentSetter);
//            }
//            if (agentItem.getAppId()==3) {
//                List<Button> buttons = new ArrayList<Button>();
//                Button xueyuan = new Button("新建群聊", "http://www.wexue.top/qlxz/newChatJsp?corpId="+oUserInfo.getCorpInfo().getCorpId(), ButtonType.view);
//                Button gongkai = new Button("我的群聊", "http://www.wexue.top/qlxz/myChatJsp?corpId="+oUserInfo.getCorpInfo().getCorpId(), ButtonType.view);
//                Button bixiu = new Button("学习小组", "http://www.wexue.top/qlxz/studyGroupJsp?corpId="+oUserInfo.getCorpInfo().getCorpId(), ButtonType.view);
//                buttons.add(xueyuan);
//                buttons.add(gongkai);
//                buttons.add(bixiu);
//                JsonResult jsonResult=menuApi.createMenu(buttons,agentItem.getAgentId());
//                msg=msg+agentItem.getAppId()+":"+jsonResult.getDesc()+";";
//
//                //设置信息
//                AgentSetter agentSetter=new AgentSetter(agentItem.getAgentId());
//                agentSetter.setRedirectDomain("www.wexue.top");
//                agentSetter.setReportEnter(true);
//                agentSetter.setReportUser(true);
//                suiteApi.setAgent(authCorid,agentSetter);
//            }
        }
        return msg;
    }

    public String getAuthUserInfoUrl(String redirectUri, String state, String corpId) {
        return oauthApi.getUserAuthorizeURL(corpId, redirectUri, state);
    }

    public User getCurrentUser(String code, String corpid) {
        TokenHolder tokenHolder = suiteApi.getTokenSuiteHolder(corpid);
        UserApi userApi = new UserApi(tokenHolder);
        try {
            return userApi.getUserByCode(code);
        } catch (WeixinException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(String userId, String corpid) throws WeixinException {
        TokenHolder tokenHolder = suiteApi.getTokenSuiteHolder(corpid);
        UserApi userApi = new UserApi(tokenHolder);
        return userApi.getUser(userId);
    }
}
