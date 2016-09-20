package com.foxinmy.weixin4j.server.startup;

import com.foxinmy.weixin4j.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.foxinmy.weixin4j.server.dispatcher.BeanFactory;
import com.foxinmy.weixin4j.server.dispatcher.DefaultMessageMatcher;
import com.foxinmy.weixin4j.server.dispatcher.WeixinMessageDispatcher;
import com.foxinmy.weixin4j.server.dispatcher.WeixinMessageKey;
import com.foxinmy.weixin4j.server.dispatcher.WeixinMessageMatcher;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.server.interceptor.WeixinMessageInterceptor;
import com.foxinmy.weixin4j.server.request.WeixinMessage;
import com.foxinmy.weixin4j.server.socket.WeixinServerInitializer;
import com.foxinmy.weixin4j.server.util.AesToken;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 微信netty服务启动程序
 *
 * @author jy
 * @className WeixinServerBootstrap
 * @date 2014年10月12日
 * @see com.foxinmy.weixin4j.server.dispatcher.WeixinMessageMatcher
 * @see com.foxinmy.weixin4j.server.handler.WeixinMessageHandler
 * @see com.foxinmy.weixin4j.server.interceptor.WeixinMessageInterceptor
 * @see com.foxinmy.weixin4j.server.dispatcher.WeixinMessageDispatcher
 * @see com.foxinmy.weixin4j.server.dispatcher.BeanFactory
 * @since JDK 1.6
 */
public final class WeixinServerBootstrap {
    private final InternalLogger logger = InternalLoggerFactory
            .getInstance(getClass());

    public static void main(String[] args) {

        String paths[] = {"applicationContext.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
//        for (String bean : ctx.getBeanDefinitionNames()) {
//            System.out.println(bean);
//        }
        // 针对文本消息回复
        SuiteMessageHandler suiteMessageHandler = (SuiteMessageHandler) ctx.getBean("suiteMessageHandler");
        MenuEventHandler menuEventHandler = (MenuEventHandler) ctx.getBean("menuEventHandler");
        TextMessageHandler textMessageHandler = (TextMessageHandler) ctx.getBean("textMessageHandler");
        ScribeEventHandler scribeEventHandler = (ScribeEventHandler) ctx.getBean("scribeEventHandler");
        try {
//            new WeixinServerBootstrap("wpx", "tj3e700c876e37c5a5", "6xRKdwfrFqbQGqu1qzUFwb6oUrjbke2h9nC19MLhqKP").addHandler(
//                    messageHandler, menuEventHandler, DebugMessageHandler.global).startup();
            AesToken wpx = new AesToken("tj3e700c876e37c5a5", "wpx", "6xRKdwfrFqbQGqu1qzUFwb6oUrjbke2h9nC19MLhqKP");
            AesToken qlxz = new AesToken("tjf5217ebac93f0b28", "qlxz", "KLg3IqHKdvqDWNrNPAuoWlfU1jqBS7NAEr3fUzTHzsa");
            new WeixinServerBootstrap(wpx, qlxz).addHandler(
                    suiteMessageHandler, menuEventHandler, scribeEventHandler,textMessageHandler, DebugMessageHandler.global).startup();
        } catch (WeixinException e) {
            e.printStackTrace();
        }
    }


    /**
     * boss线程数,默认设置为cpu的核数
     */
    public final static int DEFAULT_BOSSTHREADS;
    /**
     * worker线程数,默认设置为DEFAULT_BOSSTHREADS * 4
     */
    public final static int DEFAULT_WORKERTHREADS;
    /**
     * 服务启动的默认端口
     */
    public final static int DEFAULT_SERVERPORT = 30000;
    /**
     * 消息分发器
     */
    private WeixinMessageDispatcher messageDispatcher;
    /**
     * 消息处理器
     */
    private List<WeixinMessageHandler> messageHandlerList;
    /**
     * 消息拦截器
     */
    private List<WeixinMessageInterceptor> messageInterceptorList;

    /**
     * aes and token
     */
    private final Map<String, AesToken> aesTokenMap;

    static {
        DEFAULT_BOSSTHREADS = Runtime.getRuntime().availableProcessors();
        DEFAULT_WORKERTHREADS = DEFAULT_BOSSTHREADS * 4;
    }

    /**
     * 明文模式
     *
     * @param token 开发者token
     */
    public WeixinServerBootstrap(String token) {
        this(null, token, null);
    }

    /**
     * 明文模式 & 兼容模式 & 密文模式
     *
     * @param weixinId 公众号的应用ID(appid/corpid) 密文&兼容模式下需要填写
     * @param token    开发者填写的token 无论哪种模式都需要填写
     * @param aesKey   消息加密的密钥 密文&兼容模式下需要填写
     */
    public WeixinServerBootstrap(String weixinId, String token, String aesKey) {
        this(new AesToken(weixinId, token, aesKey));
    }

    /**
     * 多个公众号的支持
     * <p>
     * <font color="red">请注意：需在服务接收事件的URL中附加一个名为wexin_id的参数,其值请填写公众号的appid/
     * corpid</font>
     * <p>
     *
     * @return
     */
    public WeixinServerBootstrap(AesToken... aesToken) {
        this(new DefaultMessageMatcher(), aesToken);
    }

    /**
     * 多个公众号的支持
     * <p>
     * <font color="red">请注意：需在服务接收事件的URL中附加一个名为wexin_id的参数,其值请填写公众号的appid/
     * corpid</font>
     * <p>
     *
     * @param messageMatcher 消息匹配器
     * @param aesTokens      公众号信息
     * @return
     */
    public WeixinServerBootstrap(WeixinMessageMatcher messageMatcher,
                                 AesToken... aesTokens) {
        if (messageMatcher == null) {
            throw new IllegalArgumentException("MessageMatcher not be null");
        }
        if (aesTokens == null) {
            throw new IllegalArgumentException("AesToken not be null");
        }
        this.aesTokenMap = new HashMap<String, AesToken>();
        for (AesToken aesToken : aesTokens) {
            this.aesTokenMap.put(aesToken.getWeixinId(), aesToken);
        }
        this.aesTokenMap.put(null, aesTokens[0]);
        this.messageHandlerList = new ArrayList<WeixinMessageHandler>();
        this.messageInterceptorList = new ArrayList<WeixinMessageInterceptor>();
        this.messageDispatcher = new WeixinMessageDispatcher(messageMatcher);
    }

    /**
     * 默认端口启动服务
     */
    public void startup() throws WeixinException {
        startup(DEFAULT_SERVERPORT);
    }

    /**
     * 指定端口启动服务
     */
    public void startup(int serverPort) throws WeixinException {
        startup(DEFAULT_BOSSTHREADS, DEFAULT_WORKERTHREADS, serverPort);
    }

    /**
     * 接受参数启动服务
     *
     * @param bossThreads   boss线程数
     * @param workerThreads worker线程数
     * @param serverPort    服务启动端口
     * @return
     * @throws WeixinException
     */
    public void startup(int bossThreads, int workerThreads, final int serverPort)
            throws WeixinException {
        messageDispatcher.setMessageHandlerList(messageHandlerList);
        messageDispatcher.setMessageInterceptorList(messageInterceptorList);

        EventLoopGroup bossGroup = new NioEventLoopGroup(bossThreads);
        EventLoopGroup workerGroup = new NioEventLoopGroup(workerThreads);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 1024);
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler())
                    .childHandler(
                            new WeixinServerInitializer(aesTokenMap,
                                    messageDispatcher));
            Channel ch = b.bind(serverPort)
                    .addListener(new FutureListener<Void>() {
                        @Override
                        public void operationComplete(Future<Void> future)
                                throws Exception {
                            if (future.isSuccess()) {
                                logger.info("weixin4j server startup OK:{}",
                                        serverPort);
                            } else {
                                logger.info("weixin4j server startup FAIL:{}",
                                        serverPort);
                            }
                        }
                    }).sync().channel();
            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            throw new WeixinException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * 添加一个或者多个消息处理器
     *
     * @param messageHandler 消息处理器
     * @return
     */
    public WeixinServerBootstrap addHandler(
            WeixinMessageHandler... messageHandler) {
        if (messageHandler == null) {
            throw new IllegalArgumentException("messageHandler not be null");
        }
        messageHandlerList.addAll(Arrays.asList(messageHandler));
        return this;
    }

    /**
     * 插入一个或多个消息拦截器
     *
     * @param messageInterceptor 消息拦截器
     * @return
     */
    public WeixinServerBootstrap addInterceptor(
            WeixinMessageInterceptor... messageInterceptor) {
        if (messageInterceptor == null) {
            throw new IllegalArgumentException("messageInterceptor not be null");
        }
        messageInterceptorList.addAll(Arrays.asList(messageInterceptor));
        return this;
    }

    /**
     * 按照包名去添加消息处理器
     *
     * @param messageHandlerPackages 消息处理器所在的包名
     * @return
     */
    public WeixinServerBootstrap handlerPackagesToScan(
            String... messageHandlerPackages) {
        if (messageHandlerPackages == null) {
            throw new IllegalArgumentException(
                    "messageHandlerPackages not be null");
        }
        messageDispatcher.setMessageHandlerPackages(messageHandlerPackages);
        return this;
    }

    /**
     * 按照包名去添加消息拦截器
     *
     * @param messageInterceptorPackages 消息拦截器所在的包名
     * @return
     */
    public WeixinServerBootstrap interceptorPackagesToScan(
            String... messageInterceptorPackages) {
        if (messageInterceptorPackages == null) {
            throw new IllegalArgumentException(
                    "messageInterceptorPackages not be null");
        }
        messageDispatcher
                .setMessageInterceptorPackages(messageInterceptorPackages);
        return this;
    }

    /**
     * 声明处理器跟拦截器类实例化的构造工厂,否则通过Class.newInstance的方式构造
     *
     * @param beanFactory Bean构造工厂
     * @return
     */
    public WeixinServerBootstrap resolveBeanFactory(BeanFactory beanFactory) {
        messageDispatcher.setBeanFactory(beanFactory);
        return this;
    }

    /**
     * 注册消息类型
     *
     * @param messageKey   消息key
     * @param messageClass 消息类
     * @return
     */
    public WeixinServerBootstrap registMessageClass(
            WeixinMessageKey messageKey,
            Class<? extends WeixinMessage> messageClass) {
        messageDispatcher.registMessageClass(messageKey, messageClass);
        return this;
    }

    /**
     * 打开总是响应开关,如未匹配到MessageHandler时回复空白消息
     */
    public WeixinServerBootstrap openAlwaysResponse() {
        messageDispatcher.openAlwaysResponse();
        return this;
    }

    public final static String VERSION = "1.1.7";
}