package top.wexue.wpx.api;

import com.foxinmy.weixin4j.cache.RedisCacheStorager;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Button;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.qy.api.MenuApi;
import com.foxinmy.weixin4j.qy.api.OauthApi;
import com.foxinmy.weixin4j.qy.api.SuiteApi;
import com.foxinmy.weixin4j.qy.api.UserApi;
import com.foxinmy.weixin4j.qy.model.AgentSetter;
import com.foxinmy.weixin4j.qy.model.OUserInfo;
import com.foxinmy.weixin4j.qy.model.User;
import com.foxinmy.weixin4j.token.PerTicketManager;
import com.foxinmy.weixin4j.token.TicketManager;
import com.foxinmy.weixin4j.type.ButtonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.wexue.base.dao.AuthCorpInfoDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihb on 10/23/15.
 */
@Service
public class WpxAPI {
    @Value("${suit_id}")
    private String SUIT_ID;
    @Value("${suit_secret}")
    private String SUIT_SECRET;
    @Value("${chat_id}")
    private String CHAT_ID;
    @Value("${chat_secret}")
    private String CHAT_SECRET;
    @Autowired
    private RedisCacheStorager<Token> redisCacheStorager;
    @Value("${server_web_url}")
    private String webUrl;
    @Value("${qy_redirect_domain}")
    private String redirectDomain;
    @Value("${auth_redirect_url}")
    private String authedirectUrl;

    public SuiteApi suiteApi;
    public SuiteApi chatApi;
    public OauthApi oauthApi;
    @Autowired
    AuthCorpInfoDAO authCorpInfoDAO;

    public WpxAPI() {
        TicketManager suitTicketManager = new TicketManager(SUIT_ID, SUIT_SECRET, redisCacheStorager);
        this.suiteApi = new SuiteApi(suitTicketManager);
        TicketManager serverTicketManager = new TicketManager(CHAT_ID, CHAT_SECRET, redisCacheStorager);
        this.chatApi = new SuiteApi(serverTicketManager);
        this.oauthApi = new OauthApi();
    }

    /**
     * 获取授权路径，用于获取当前用户信息
     * @return
     * @throws WeixinException
     */
    public String getSuiteAuthorizeURL() throws WeixinException {
        String suiteAuthorizeURL = oauthApi.getThirdAuthorizationURL(authedirectUrl,"");
        return suiteAuthorizeURL;
    }

    /**
     * 获取会话授权的地址 用于获取用户信息
     * @return
     * @throws WeixinException
     */
    public String getChateAuthorizeURL() throws WeixinException {
        return "";
    }

    public OUserInfo exchangeSuitePermanentCode(String authcode) throws WeixinException {
        return null;
    }

    public OUserInfo exchangeServerPermanentCode(String authcode) throws WeixinException {
        return null;
    }

    public String getPerCode(String authCorpId) throws WeixinException {
        return null;
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
                Button gongkai = new Button("公开课", webUrl + "/auth/authUser?state=public&corpId=" + oUserInfo.getCorpInfo().getCorpId(), ButtonType.view);
                Button bixiu = new Button("必修课", webUrl + "/auth/authUser?state=require&corpId=" + oUserInfo.getCorpInfo().getCorpId(), ButtonType.view);
                //buttons.add(xueyuan);
                buttons.add(gongkai);
                buttons.add(bixiu);
                JsonResult jsonResult = menuApi.createMenu(buttons, agentItem.getAgentId());
                msg = msg + agentItem.getAppId() + ":" + jsonResult.getDesc() + ";";
                //设置信息
                AgentSetter agentSetter = new AgentSetter(agentItem.getAgentId());
                agentSetter.setRedirectDomain(redirectDomain);
                agentSetter.setReportEnter(true);
                agentSetter.setReportUser(true);
                suiteApi.setAgent(authCorid, agentSetter);
            }
            if (agentItem.getAppId() == 2) {
                List<Button> buttons = new ArrayList<Button>();
                Button xueyuan = new Button("课程签到", "scancode_push", ButtonType.scancode_push);
                Button gongkai = new Button("我的课程", webUrl + "/auth/authUser?state=mycourse&corpId=" + oUserInfo.getCorpInfo().getCorpId(), ButtonType.view);
//               Button bixiu = new Button("我的必修课", "http://www.wexue.top/auth/authUser?state=myrequired&corpId="+oUserInfo.getCorpInfo().getCorpId(), ButtonType.view);
                buttons.add(xueyuan);
                buttons.add(gongkai);
//                buttons.add(bixiu);
                JsonResult jsonResult = menuApi.createMenu(buttons, agentItem.getAgentId());
                msg = msg + agentItem.getAppId() + ":" + jsonResult.getDesc() + ";";

                //设置信息
                AgentSetter agentSetter = new AgentSetter(agentItem.getAgentId());
                agentSetter.setRedirectDomain(redirectDomain);
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
