package com.foxinmy.weixin4j.handler;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.message.TextMessage;
import com.foxinmy.weixin4j.message.event.EventMessage;
import com.foxinmy.weixin4j.message.event.MenuEventMessage;
import com.foxinmy.weixin4j.request.WeixinRequest;
import com.foxinmy.weixin4j.response.TextResponse;
import com.foxinmy.weixin4j.response.WeixinResponse;

/**
 * 调试消息处理器
 * 
 * @className DebugMessageHandler
 * @author jy
 * @date 2015年5月17日
 * @since JDK 1.7
 * @see
 */
public class MenuEventHandler extends EventHandlerAdapter<MenuEventMessage> {

	@Override
	public WeixinResponse doHandle0(WeixinRequest request,
									MenuEventMessage message) throws WeixinException {


		return new TextResponse("");
	}
}
