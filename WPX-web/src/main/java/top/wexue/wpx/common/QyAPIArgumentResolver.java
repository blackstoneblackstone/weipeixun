package top.wexue.wpx.common;

import com.foxinmy.weixin4j.cache.RedisCacheStorager;
import com.foxinmy.weixin4j.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import top.wexue.base.utils.Constants;
import top.wexue.wpx.api.QyAPI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lihb on 8/31/16.
 */
public class QyAPIArgumentResolver implements WebArgumentResolver {
    /**
     * 微信配置
     */
    @Value("${suite_id}")
    private String SUIT_ID;
    @Value("${suite_secret}")
    private String SUIT_SECRET;
    @Autowired
    RedisCacheStorager<Token> redisCacheStorager;

    @Override
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) {
        if (methodParameter.getParameterType() != null
                && methodParameter.getParameterType().equals(QyAPI.class)) {
            // 判断controller方法参数有没有写当前用户，如果有，这里返回即可，通常我们从session里面取出来
            HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
            HttpSession session = request.getSession();
            Object qyAPI = session.getAttribute(Constants.Config.QY_API_NAME);
            if (qyAPI != null && qyAPI instanceof QyAPI) {
                return qyAPI;
            } else {
                String corpId = request.getParameter("corpId");
                return new QyAPI(corpId, redisCacheStorager, SUIT_ID, SUIT_SECRET);
            }
        }
        return UNRESOLVED;
    }
}
