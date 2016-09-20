package com.foxinmy.weixin4j.server.handler;

import com.foxinmy.weixin4j.cache.RedisCacheStorager;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.qy.api.NotifyApi;
import com.foxinmy.weixin4j.qy.api.SuiteApi;
import com.foxinmy.weixin4j.qy.message.NotifyMessage;
import com.foxinmy.weixin4j.qy.model.CorpInfo;
import com.foxinmy.weixin4j.qy.model.IdParameter;
import com.foxinmy.weixin4j.qy.model.OUserInfo;
import com.foxinmy.weixin4j.qy.model.User;
import com.foxinmy.weixin4j.qy.token.WeixinTokenCreator;
import com.foxinmy.weixin4j.server.qy.suite.SuiteEventType;
import com.foxinmy.weixin4j.server.qy.suite.SuiteMessage;
import com.foxinmy.weixin4j.server.request.WeixinMessage;
import com.foxinmy.weixin4j.server.request.WeixinRequest;
import com.foxinmy.weixin4j.server.response.TextResponse;
import com.foxinmy.weixin4j.server.response.WeixinResponse;
import com.foxinmy.weixin4j.server.util.XMLUtil;
import com.foxinmy.weixin4j.token.TicketManager;
import com.foxinmy.weixin4j.token.TokenManager;
import com.foxinmy.weixin4j.tuple.Text;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import top.wexue.base.entity.TAgentInfo;
import top.wexue.base.entity.TCorpInfo;
import top.wexue.base.repository.AgentInfoRepository;
import top.wexue.base.repository.CorpInfoRepository;
import top.wexue.base.repository.UserRepository;

import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * 调试消息处理器
 *
 * @author jy
 * @className DebugMessageHandler
 * @date 2015年5月17日
 * @see
 * @since JDK 1.7
 * **********************************************************************
 * 示例请求：
 * http://localhost:30000?weixin_id=tjf5217ebac93f0b28&msg_signature=843aa5b022262c215d3f25cc3b17b98480c30694&timestamp=1472401725&nonce=815682332
 * <xml>
 * <SuiteId><![CDATA[tjf5217ebac93f0b28]]></SuiteId>
 * <AuthCode><![CDATA[qLecdjJJByvI5zZ8wVxSuytDDYQZc4VFJfhsBlF2f3VEnUdgf0_uyBdNno32ZA5TRXiTMw7LurcMfKmK92tMUQ]]></AuthCode>
 * <InfoType><![CDATA[create_auth]]></InfoType>
 * <TimeStamp>1472401715</TimeStamp>
 * </xml>
 * **********************************************************************
 */
@Component
public class SuiteMessageHandler implements WeixinMessageHandler {

    private final InternalLogger logger = InternalLoggerFactory
            .getInstance(getClass());
    @Autowired
    RedisCacheStorager<Token> redisCacheStorager;
    @Autowired
    Properties weixin;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CorpInfoRepository corpInfoRepository;
    @Autowired
    AgentInfoRepository agentInfoRepository;

    @Override
    public boolean canHandle(WeixinRequest request, WeixinMessage message, Set<String> nodeNames) throws WeixinException {
        for (String node : nodeNames) {
            if (node.equals("suiteid")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public WeixinResponse doHandle(WeixinRequest request, WeixinMessage message, Set<String> nodeNames) throws WeixinException {
        SuiteMessage messageTrue = XMLUtil.messageRead(request.getOriginalContent(), SuiteMessage.class);
        SuiteEventType suiteEventType = SuiteEventType.valueOf(messageTrue.getEventType());
        boolean result = true;
        switch (suiteEventType) {
            case suite_ticket:
                result = suiteTicketHandle(messageTrue);
                break;
            case change_auth:
                changeAuthHandle(messageTrue);
                break;
            case cancel_auth:
                cancelAuthHandle(messageTrue);
                break;
            case create_auth:
                createAuthHandle(messageTrue);
                break;
        }
        if (result) {
            return new TextResponse("success");
        } else {
            return new TextResponse("error");
        }

    }

    @Override
    public int weight() {
        return 0;
    }


    private boolean suiteTicketHandle(SuiteMessage message) throws WeixinException {
        try {
            //缓存ticket
            TicketManager ticketManager = new TicketManager(message.getSuiteId(), weixin.getProperty(message.getSuiteId() + "_secret"), redisCacheStorager);
            ticketManager.cachingTicket(message.getSuiteTicket());
        } catch (com.foxinmy.weixin4j.exception.WeixinException e) {
            logger.error("suiteTicket 缓存错误,报错日志:{}", e.getMessage());
            return false;
        }
        return true;
    }

    private void changeAuthHandle(SuiteMessage message) {
        sendNotify("更改授权》" + message.getAuthCorpId());
    }

    private void cancelAuthHandle(SuiteMessage message) {
        sendNotify("取消授权》" + message.getAuthCorpId());
    }

    /**
     * 异步执行
     *
     * @param message
     * @return
     */
    @Async
    private void createAuthHandle(SuiteMessage message) {
        try {
            //缓存永久授权码
            TicketManager ticketManager = new TicketManager(message.getSuiteId(), weixin.getProperty(message.getSuiteId() + "_secret"), redisCacheStorager);
            SuiteApi suiteApi = new SuiteApi(ticketManager);
            OUserInfo oUserInfo = suiteApi.exchangeAuthInfo(message.getAuthCode());

            //获取授权企业号信息
            CorpInfo corpInfo = oUserInfo.getCorpInfo();
            TCorpInfo tCorpInfo = new TCorpInfo();
            BeanUtils.copyProperties(corpInfo, tCorpInfo);
            tCorpInfo.setPermanentCode(suiteApi.getPreCodeManager().getAccessToken());
            tCorpInfo.setSuiteid(message.getSuiteId());
            corpInfoRepository.saveAndFlush(tCorpInfo);

            //User user = oUserInfo.getAdminInfo();
            //获取应用信息
            List<OUserInfo.AgentItem> agentItems = oUserInfo.getAuthInfo().getAgentItems();
            for (OUserInfo.AgentItem agentItem : agentItems) {
                TAgentInfo agentInfo = new TAgentInfo();
                BeanUtils.copyProperties(agentItem, agentInfo);
                agentInfo.setSuitId(message.getSuiteId());
                agentInfo.setCorpId(message.getAuthCorpId());
                agentInfoRepository.saveAndFlush(agentInfo);
            }
            sendNotify("创建授权:用户信息》" + agentItems.toString());
        } catch (WeixinException e) {
            sendNotify("创建授权:错误 》CorpId:" +message.getAuthCorpId()+":"+e.getLocalizedMessage() );
            logger.error("createAuth 错误,报错日志:{}", e.getLocalizedMessage());
        }
    }

    private void sendNotify(String msg) {
        try {
            TokenManager tokenManager = new TokenManager(new WeixinTokenCreator(weixin.getProperty("admin_corp_id"), weixin.getProperty("admin_secret")), redisCacheStorager);
            NotifyApi notifyApi = new NotifyApi(tokenManager);
            IdParameter idParameter = new IdParameter();
            idParameter.putUserIds("muzijun");
            Text text = new Text(msg);
            NotifyMessage notifyMessage = new NotifyMessage(0, text, idParameter, false);
            notifyApi.sendNotifyMessage(notifyMessage);
        } catch (WeixinException e) {
            logger.error("发送通知失败了");
        }
    }
}
