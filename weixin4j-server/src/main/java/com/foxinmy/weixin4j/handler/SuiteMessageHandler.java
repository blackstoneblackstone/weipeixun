package com.foxinmy.weixin4j.handler;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.qy.suite.SuiteTicketHolder;
import com.foxinmy.weixin4j.qy.suite.WeixinSuiteMessage;
import com.foxinmy.weixin4j.request.WeixinMessage;
import com.foxinmy.weixin4j.request.WeixinRequest;
import com.foxinmy.weixin4j.response.TextResponse;
import com.foxinmy.weixin4j.response.WeixinResponse;
import com.foxinmy.weixin4j.token.RedisTokenStorager;
import com.foxinmy.weixin4j.util.XMLUtil;
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
public class SuiteMessageHandler implements WeixinMessageHandler {



	@Override
	public boolean canHandle(WeixinRequest request, WeixinMessage message, Set<String> nodeNames) throws WeixinException {
		for(String node:nodeNames){
			if(node.equals("suiteid")){
				return true;
			}
		}
		return false;
	}

	@Override
	public WeixinResponse doHandle(WeixinRequest request, WeixinMessage message, Set<String> nodeNames) throws WeixinException {
		WeixinSuiteMessage messageTrue= XMLUtil.messageRead(request.getOriginalContent(), WeixinSuiteMessage.class);
		SuiteTicketHolder suiteTicketHolder=new SuiteTicketHolder(messageTrue.getSuiteId(),"jNxEH7UABaHtrwOhGG2jBpUfU3x_EISNJTAGjjpYSGwFBmSSYZTb-Q9xsQgpyP4E",new RedisTokenStorager("123.57.237.121", 6379, "wexuetop"));
		suiteTicketHolder.cachingTicket(messageTrue.getSuiteTicket());
		System.out.println("SuiteMessage:>>>>" + message.toString());
		return new TextResponse("success");
	}

	@Override
	public int weight() {
		return 0;
	}
}
