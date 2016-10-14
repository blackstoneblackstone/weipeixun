package com.foxinmy.weixin4j.server.handler;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.server.qy.event.ScribeEventMessage;
import com.foxinmy.weixin4j.server.request.WeixinRequest;
import com.foxinmy.weixin4j.server.response.TextResponse;
import com.foxinmy.weixin4j.server.response.WeixinResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

/**
 * 调试消息处理器
 *
 * @author jy
 * @className DebugMessageHandler
 * @date 2015年5月17日
 * @see <xml><ToUserName><![CDATA[wxf54e1b5e0b62fa96]]></ToUserName>
 * <FromUserName><![CDATA[gao]]></FromUserName>
 * <CreateTime>1472719148</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <AgentID>116</AgentID>
 * <Event><![CDATA[subscribe]]></Event>
 * <EventKey><![CDATA[]]></EventKey>
 * </xml>
 * @since JDK 1.7
 */
@Log4j
@Component
public class ScribeEventHandler extends EventHandlerAdapter<ScribeEventMessage> {

    @Override
    public boolean canHandle0(WeixinRequest request, ScribeEventMessage message) throws WeixinException {

        return super.canHandle0(request, message);
    }

    @Override
    public WeixinResponse doHandle0(WeixinRequest request, ScribeEventMessage message) throws WeixinException {
        if ("muzijun".equals(message.getFromUserName())) {
            return new TextResponse("欢迎您的关注");
        }
        return new TextResponse("");
    }


    @Override
    public int weight() {
        return 0;
    }
}
