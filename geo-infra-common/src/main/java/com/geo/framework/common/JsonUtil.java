package com.geo.framework.common;

/*import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
*/
/**
 * @author 
 *
 */
public class JsonUtil {

	/**
     * 记录日志信息
     *//*
    private static Logger logger = LogManager.getLogger(JsonUtil.class);

    *//**
     * 用于处理json字符串的静态对象
     *//*
    private static ObjectMapper objectMapper = new ObjectMapper();

    *//**
     * 默认构造器
     *//*
    private JsonUtil() {
    }

    *//**
     * 对Object的对象进行编码
     *
     * @param object 编码对象
     * @return 一个编码后的字符串
     * @throws Exception
     *//*
    public static String convertToXml(Object object) throws Exception {
        return convertToXml(object, "UTF-8");
    }

    *//**
     * 对object的对象转化成XML字符串
     *
     * @param object 转化对象
     * @param encoding 编码方式
     * @return 一个对象格式的XML字符串
     * @throws Exception
     *//*
    public static String convertToXml(Object object, String encoding) throws JAXBException{
        String result = "";
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            StringWriter writer = new StringWriter();
            marshaller.marshal(object, writer);
            result = writer.toString();
        } catch (JAXBException e) {
            logger.error("对象转xml字符串的错误日志-->>"+e.getMessage(), e);
        }
        logger.debug("生成对象转XML字符串的调试日志-->>"+result);
        return result;
    }

    *//**
     * xml字符串转换成JavaBean实体
     *
     * @param xml   XML字符串
     * @param clazz 字节码类型
     * @param <T>   转换的泛型
     * @return 一个转化的实体对象
     *//*
    public static <T> T converyToJavaBean(String xml, Class<T> clazz) throws JAXBException{
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            logger.error("xml转实体bean的错误日志-->>"+e.getMessage(), e);
        }
        return t;
    }

    *//**
     * 对象转换为json字符串
     *
     * @param object 转化的实体对象
     * @return 装换后的json字符串
     *//*
    public static synchronized String objectToJson(Object object) throws IOException {
        Writer writer = null;
        String json = "";
        try {
            writer = new StringWriter();
            objectMapper.writeValue(writer, object);
            json = writer.toString();
        } catch (IOException e) {
            logger.error("实体对象转JSON字符串的错误日志-->>"+e.getMessage(),e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                logger.error("实体对象转JSON字符串关闭writer的错误日志-->>"+e.getMessage(),e);
            }
        }
        logger.debug("生成实体转JSON字符串的调试日志-->>"+json);
        return json;
    }

    *//**
     * json字符转换成对象
     *
     * @param json  json字符串
     * @param clazz 实体对象的class
     * @param <T>   泛型实体
     * @return 返回转换后的实体对象
     *//*
    public static synchronized <T> T jsonToObject(String json, Class<T> clazz) throws IOException {
        T object = null;
        try {
            object = objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error("JSON字符串转实体bean的错误日志-->>"+e.getMessage(),e);
        }
        return object;
    }*/


}
