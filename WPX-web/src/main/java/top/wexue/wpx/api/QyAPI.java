package top.wexue.wpx.api;

import com.foxinmy.weixin4j.cache.RedisCacheStorager;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.qy.api.*;
import com.foxinmy.weixin4j.qy.model.User;
import com.foxinmy.weixin4j.qy.suite.WeixinSuiteTokenCreator;
import com.foxinmy.weixin4j.qy.suite.WeixinTokenSuiteCreator;
import com.foxinmy.weixin4j.token.PerTicketManager;
import com.foxinmy.weixin4j.token.TicketManager;
import com.foxinmy.weixin4j.token.TokenManager;
import org.springframework.stereotype.Service;

/**
 * Created by lihb on 11/8/16.
 */
public class QyAPI {
    /**
     * 授权API
     */
    private OauthApi oauthApi;
    /**
     * 媒体素材API
     */
    private MediaApi mediaApi;
    /**
     * 菜单API
     */
    private MenuApi menuApi;
    /**
     * 消息服务API
     */
    private NotifyApi notifyApi;
    /**
     * 部门API
     */
    private PartyApi partyApi;
    /**
     * 成员API
     */
    private UserApi userApi;
    /**
     * 标签API
     */
    private TagApi tagApi;
    /**
     * 辅助API
     */
    private HelperApi helperApi;
    /**
     * 应用API
     */
    private AgentApi agentApi;
    /**
     * 批量操作API
     */
    private BatchApi batchApi;
    /**
     * 微信配置
     */
    private String SUIT_ID;
    private String SUIT_SECRET;
    /**
     * token存储
     */
    private RedisCacheStorager<Token> redisCacheStorager;

    public QyAPI(String authCorpID, RedisCacheStorager<Token> redisCacheStorager, String suitId, String suitSecret) {
        this.redisCacheStorager = redisCacheStorager;
        this.SUIT_ID = suitId;
        this.SUIT_SECRET = suitSecret;
        this.init(authCorpID);
    }

    private void init(String corpID) {

        //获取ticket
        TicketManager ticketManager = new TicketManager(SUIT_ID, SUIT_SECRET, redisCacheStorager);
        //获取suitetoken
        WeixinSuiteTokenCreator weixinSuiteTokenCreator = new WeixinSuiteTokenCreator(ticketManager);
        TokenManager suiteToken = new TokenManager(weixinSuiteTokenCreator, redisCacheStorager);
        //获取永久授权码
        PerTicketManager perTicketManager = new PerTicketManager(corpID, SUIT_ID, SUIT_SECRET, redisCacheStorager);
        //获取ACCESS_TOKEN
        WeixinTokenSuiteCreator weixinTokenSuiteCreator = new WeixinTokenSuiteCreator(perTicketManager, suiteToken);
        TokenManager tokenManager = new TokenManager(weixinTokenSuiteCreator, redisCacheStorager);

        this.partyApi = new PartyApi(tokenManager);
        this.userApi = new UserApi(tokenManager);
        this.tagApi = new TagApi(tokenManager);
        this.helperApi = new HelperApi(tokenManager);
        this.agentApi = new AgentApi(tokenManager);
        this.batchApi = new BatchApi(tokenManager);
        this.notifyApi = new NotifyApi(tokenManager);
        this.menuApi = new MenuApi(tokenManager);
        this.mediaApi = new MediaApi(tokenManager);
    }


    public User getCurrentUser(String code, String corpid) {
        try {
            return userApi.getUserByCode(code);
        } catch (WeixinException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCurrentUserId(String code) throws WeixinException {
        String[] ss = userApi.getUserIdByCode(code);
        return ss[0];
    }


}
