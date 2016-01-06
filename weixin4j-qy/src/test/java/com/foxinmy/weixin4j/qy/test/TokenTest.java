package com.foxinmy.weixin4j.qy.test;

import com.foxinmy.weixin4j.token.RedisTokenStorager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.WeixinAccount;
import com.foxinmy.weixin4j.qy.token.WeixinTokenCreator;
import com.foxinmy.weixin4j.token.FileTokenStorager;
import com.foxinmy.weixin4j.token.TokenHolder;
import com.foxinmy.weixin4j.util.Weixin4jConfigUtil;

/**
 * token测试
 *
 * @author jy.hu
 * @className TokenTest
 * @date 2014年4月10日
 * @since JDK 1.7
 */
public class TokenTest {

    protected TokenHolder tokenHolder;

    @Before
    public void setUp() {
        WeixinAccount weixinAccount = Weixin4jConfigUtil.getWeixinAccount();
        tokenHolder = new TokenHolder(new WeixinTokenCreator(
                weixinAccount.getId(), weixinAccount.getSecret()), new RedisTokenStorager()
        );
    }

    @Test
    public void test() throws WeixinException {
        Assert.assertNotNull(tokenHolder.getToken());
        System.out.println(tokenHolder.getToken());
    }
}
