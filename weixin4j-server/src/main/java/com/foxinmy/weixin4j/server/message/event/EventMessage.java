package com.foxinmy.weixin4j.server.message.event;

import javax.xml.bind.annotation.XmlElement;

import com.foxinmy.weixin4j.server.request.WeixinMessage;
import com.foxinmy.weixin4j.server.type.EventType;
import com.foxinmy.weixin4j.server.type.MessageType;

/**
 * 事件消息基类
 * 
 * @className EventMessage
 * @author jy.hu
 * @date 2014年4月6日
 * @since JDK 1.6
 * @see <a
 *      href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140454&token=&lang=zh_CN">订阅号、服务号的事件推送</a>
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E6%8E%A5%E6%94%B6%E4%BA%8B%E4%BB%B6">企业号的事件消息</a>
 */
public class EventMessage extends WeixinMessage {

	private static final long serialVersionUID = 7703667223814088865L;

	protected EventMessage() {
		// jaxb requried
	}

	public EventMessage(String eventType) {
		super(MessageType.event.name());
		this.eventType = eventType;
	}

	/**
	 * 事件类型
	 * 
	 * @see EventType
	 */
	@XmlElement(name = "Event")
	private String eventType;

	public String getEventType() {
		return eventType;
	}

	@Override
	public String toString() {
		return "eventType=" + eventType + ", " + super.toString();
	}
}
