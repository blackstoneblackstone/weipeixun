package com.foxinmy.weixin4j.server.util;

import com.foxinmy.weixin4j.exception.WeixinException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihb on 10/21/15.
 */
public class XMLUtil {

    public static Map<Class<?>, Unmarshaller> messageUnmarshaller = new HashMap<Class<?>, Unmarshaller>();


    public static <M> M messageRead(String message,
                             Class<M> clazz) throws WeixinException {
        if (clazz == null) {
            return null;
        }
        try {
            Source source = new StreamSource(new ByteArrayInputStream(
                    message.getBytes()));
            JAXBElement<M> jaxbElement = getUnmarshaller(clazz).unmarshal(
                    source, clazz);
            return jaxbElement.getValue();
        } catch (JAXBException e) {
            throw new WeixinException(e);
        }
    }


    /**
     * xml消息转换器
     *
     * @param clazz 消息类型
     * @return 消息转换器
     * @throws WeixinException
     */
    public static Unmarshaller getUnmarshaller(Class<?> clazz)
            throws WeixinException {
        Unmarshaller unmarshaller = messageUnmarshaller.get(clazz);
        if (unmarshaller == null) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
                unmarshaller = jaxbContext.createUnmarshaller();
                messageUnmarshaller.put(clazz, unmarshaller);
            } catch (JAXBException e) {
                throw new WeixinException(e);
            }
        }
        return unmarshaller;
    }
}
