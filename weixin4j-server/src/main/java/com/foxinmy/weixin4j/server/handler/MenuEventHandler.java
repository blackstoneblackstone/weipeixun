package com.foxinmy.weixin4j.server.handler;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.server.message.event.MenuEventMessage;
import com.foxinmy.weixin4j.server.request.WeixinMessage;
import com.foxinmy.weixin4j.server.request.WeixinRequest;
import com.foxinmy.weixin4j.server.response.TextResponse;
import com.foxinmy.weixin4j.server.response.WeixinResponse;
import org.springframework.stereotype.Component;

import java.util.Set;

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
public class MenuEventHandler extends EventHandlerAdapter<MenuEventMessage> {

	@Override
	public WeixinResponse doHandle0(WeixinRequest request,
									MenuEventMessage message) throws WeixinException {


		return new TextResponse("");
	}

	@Override
	public boolean canHandle(WeixinRequest request, WeixinMessage message, Set<String> nodeNames) throws WeixinException {
		return false;
	}

	@Override
	public WeixinResponse doHandle(WeixinRequest request, WeixinMessage message, Set<String> nodeNames) throws WeixinException {
		return null;
	}

	@Override
	public int weight() {
		return 0;
	}
}
