package com.mq.rabbit;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

/**
* @author jjie
* @proc Publish/Subscribe  使用场景：发布、订阅模式，发送端发送广播消息，多个接收端接收。
*/
public class EmitLog {

  private static final String EXCHANGE_NAME = "logs";

  public static void main(String[] argv) throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("192.168.100.155");
    factory.setPort(5672);
    factory.setUsername("admin");
    factory.setPassword("admin");
    
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

    String message = getMessage(argv);

    channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
    System.out.println(" [x] Sent '" + message + "'");

    channel.close();
    connection.close();
  }
  
  private static String getMessage(String[] strings){
    if (strings.length < 1)
            return "info: Hello World!";
    return joinStrings(strings, " ");
  }
  
  private static String joinStrings(String[] strings, String delimiter) {
    int length = strings.length;
    if (length == 0) return "";
    StringBuilder words = new StringBuilder(strings[0]);
    for (int i = 1; i < length; i++) {
        words.append(delimiter).append(strings[i]);
    }
    return words.toString();
  }
}