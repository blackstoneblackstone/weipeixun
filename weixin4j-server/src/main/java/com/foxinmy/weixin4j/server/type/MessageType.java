package com.foxinmy.weixin4j.server.type;


/**
 * 
 * 消息类型
 * 
 * @author jy.hu
 * 
 */
public enum MessageType {
	/**
	 * 文字消息
	 * 
	 * @see com.foxinmy.weixin4j.server.message.TextMessage
	 */
	text,
	/**
	 * 图片消息
	 * 
	 * @see com.foxinmy.weixin4j.server.message.ImageMessage
	 */
	image,
	/**
	 * 语音消息
	 * 
	 * @see com.foxinmy.weixin4j.server.message.VoiceMessage
	 */
	voice,
	/**
	 * 视频消息
	 * 
	 * @see com.foxinmy.weixin4j.server.message.VideoMessage
	 */
	video,
	/**
	 * 小视频消息
	 * 
	 * @see com.foxinmy.weixin4j.server.message.VideoMessage
	 */
	shortvideo,
	/**
	 * 位置消息
	 * 
	 * @see com.foxinmy.weixin4j.server.message.LocationMessage
	 */
	location,
	/**
	 * 链接消息
	 * 
	 * @see com.foxinmy.weixin4j.server.message.LinkMessage
	 */
	link,
	/**
	 * 事件消息
	 * 
	 * @see com.foxinmy.weixin4j.server.message.event.EventMessage
	 */
	event;
}
