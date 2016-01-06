package com.foxinmy.weixin4j.qy.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.qy.api.NotifyApi;
import com.foxinmy.weixin4j.qy.message.NotifyMessage;
import com.foxinmy.weixin4j.tuple.File;
import com.foxinmy.weixin4j.tuple.Image;
import com.foxinmy.weixin4j.tuple.MpNews;
import com.foxinmy.weixin4j.tuple.News;
import com.foxinmy.weixin4j.tuple.Text;
import com.foxinmy.weixin4j.tuple.Video;
import com.foxinmy.weixin4j.tuple.Voice;

/**
 * 客服消息测试
 * 
 * @className NotifyTest
 * @author jy.hu
 * @date 2014年4月10日
 * @since JDK 1.7
 * @see
 */
public class NotifyTest extends TokenTest {

	private NotifyApi notifyApi;

	@Before
	public void init() {
		notifyApi = new NotifyApi(tokenHolder);
	}

	@Test
	public void text() throws WeixinException {
		NotifyMessage notify = new NotifyMessage(64, new Text("您有新的课程"));
		System.out.println(notifyApi.sendNotifyMessage(notify));
	}

	@Test
	public void image() throws WeixinException {
		NotifyMessage notify = new NotifyMessage(0, new Image("123"));
		System.out.println(notifyApi.sendNotifyMessage(notify));
	}

	@Test
	public void voice() throws WeixinException {
		NotifyMessage notify = new NotifyMessage(0, new Voice("123"));
		System.out.println(notifyApi.sendNotifyMessage(notify));
	}

	@Test
	public void video() throws WeixinException {
		NotifyMessage notify = new NotifyMessage(0, new Video("mediaId",
				"title", "desc"));
		System.out.println(notifyApi.sendNotifyMessage(notify));
	}

	@Test
	public void file() throws WeixinException {
		File file = new File("file");
		NotifyMessage notify = new NotifyMessage(0, file);
		System.out.println(notifyApi.sendNotifyMessage(notify));
	}

	@Test
	public void news() throws WeixinException {
		News news = new News();
		NotifyMessage notify = new NotifyMessage(0, news);
		news.addArticle("课程通知", "您有新的课程\n\n\n名称：<a href=http://www.baidu.com style=color:red>成功人士的10000个习惯</a>\n时间：2015-10-10\n地点：公司c1会议室\n讲师：李老师", "", "http://www.wexue.top");
		//news.addArticle("title2", "desc2", "picUrl2", "url2");
		System.out.println(notifyApi.sendNotifyMessage(notify));
	}

	@Test
	public void mpnews() throws WeixinException {
		MpNews news = new MpNews();
		NotifyMessage notify = new NotifyMessage(0, news);
		news.addArticle("thumbMediaId1", "title1", "content1");
		news.addArticle("thumbMediaId2", "title1", "content2");
		System.out.println(notifyApi.sendNotifyMessage(notify));
	}

	@Test
	public void send1() throws WeixinException {
		Text text = new Text("this is a text");
		JSONObject result = notifyApi.sendNotifyMessage(new NotifyMessage(2,
				text));
		Assert.assertEquals(0, result.getIntValue("errcode"));
	}
}
