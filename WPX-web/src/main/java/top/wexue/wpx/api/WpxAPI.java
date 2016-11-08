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
import com.foxinmy.weixin4j.qy.type.URLConsts;
import com.foxinmy.weixin4j.token.PerTicketManager;
import com.foxinmy.weixin4j.token.TicketManager;
import com.foxinmy.weixin4j.type.ButtonType;
import com.foxinmy.weixin4j.util.Consts;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.wexue.base.dao.AuthCorpInfoDAO;
import top.wexue.base.repository.CorpInfoRepository;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihb on 10/23/15.
 */
@Service
public class WpxAPI {

    @Value("${suite_id}")
    private String SUIT_ID;

    @Value("${suite_secret}")
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
    @Value("${suite_oauth_redirect_uri}")
    private String authedirectUrl;

    public SuiteApi suiteApi;
    public SuiteApi chatApi;
    public OauthApi oauthApi;
    @Autowired
    CorpInfoRepository corpInfoRepository;

    public WpxAPI() {

    }

    @PostConstruct
    public void init() {
        TicketManager suitTicketManager = new TicketManager(SUIT_ID, SUIT_SECRET, redisCacheStorager);
        this.suiteApi = new SuiteApi(suitTicketManager);
        TicketManager serverTicketManager = new TicketManager(CHAT_ID, CHAT_SECRET, redisCacheStorager);
        this.chatApi = new SuiteApi(serverTicketManager);
    }

    public String getSUIT_ID() {
        return SUIT_ID;
    }

    public String getCHAT_ID() {
        return CHAT_ID;
    }

    /**
     * 应用套件授权 <font
     * color="red">需先缓存ticket，在授权完成之后需要调用SuiteApi#exchangeAuthInfo方法
     * ,否则无法缓存token相关导致后续的组件接口调用失败</font>
     */
    public String getSuiteAuthorizationURL(String redirectUri,
                                           String state) throws WeixinException {
        try {
            return String.format(URLConsts.SUITE_OAUTH_URL, SUIT_ID,
                    suiteApi.getPreCodeManager().getAccessToken(),
                    URLEncoder.encode(redirectUri, Consts.UTF_8.name()), state);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 获取会话授权的地址 用于获取用户信息
     *
     * @return
     * @throws WeixinException
     */
    public String getChatAuthorizationURL(String redirectUri,
                                          String state) throws WeixinException {
        try {
            return String.format(URLConsts.SUITE_OAUTH_URL, CHAT_ID,
                    suiteApi.getPreCodeManager().getAccessToken(),
                    URLEncoder.encode(redirectUri, Consts.UTF_8.name()), state);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
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

    public String getAuthUserInfoUrl(String redirectUri, String state,String corpId) {
        this.oauthApi = new OauthApi();
        String authUrl=oauthApi.getUserAuthorizationURL(redirectUri,state,corpId);
        return authUrl;
    }



    public User getUserById(String userId, String corpid) throws WeixinException {
//        TokenHolder tokenHolder = suiteApi.getTokenSuiteHolder(corpid);
//        UserApi userApi = new UserApi(tokenHolder);
        return null;
    }
}
