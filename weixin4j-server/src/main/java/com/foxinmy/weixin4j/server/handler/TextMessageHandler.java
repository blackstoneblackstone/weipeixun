package com.foxinmy.weixin4j.server.handler;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.server.message.TextMessage;
import com.foxinmy.weixin4j.server.request.WeixinRequest;
import com.foxinmy.weixin4j.server.response.TextResponse;
import com.foxinmy.weixin4j.server.response.WeixinResponse;
import org.springframework.stereotype.Component;

/**
 * 调试消息处理器
 * 
 * @className DebugMessageHandler
 * @author jy
 * @date 2015年5月17日
 * @since JDK 1.7
 * @see
 */
@Component
public class TextMessageHandler extends MessageHandlerAdapter<TextMessage> {

	@Override
	public WeixinResponse doHandle0(WeixinRequest request,
									TextMessage message) throws WeixinException {


		System.out.println("textMessage:>>>>"+message.toString());
		return new TextResponse("感谢您关注微学平台，在这里您可以订阅您需要的课程，关注到公司内部培训项目，调查问卷，课前预习，课间测试，课程签到，一站式解决企业培训需求。");
	}
}
