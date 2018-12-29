package com.mq.rabbit;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;


/**
* @author jjie
* @proc Routing (按路线发送接收)使用场景：发送端按routing key发送消息，不同的接收端按不同的routing key接收消息。
*/
public class EmitLogDirect {

  private static final String EXCHANGE_NAME = "direct_logs";

  public static void main(String[] argv) throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("192.168.100.155");
    factory.setPort(5672);
    factory.setUsername("admin");
    factory.setPassword("admin");
    
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE_NAME, "direct");

    String severity = getSeverity(argv);
    String message = getMessage(argv);

    channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
    System.out.println(" [x] Sent '" + severity + "':'" + message + "'");

    channel.close();
    connection.close();
  }
  
  private static String getSeverity(String[] strings){
    if (strings.length < 1)
            return "info";
    return strings[0];
  }

  private static String getMessage(String[] strings){ 
    if (strings.length < 2)
            return "Hello World!";
    return joinStrings(strings, " ", 1);
  }
  
  private static String joinStrings(String[] strings, String delimiter, int startIndex) {
    int length = strings.length;
    if (length == 0 ) return "";
    if (length < startIndex ) return "";
    StringBuilder words = new StringBuilder(strings[startIndex]);
    for (int i = startIndex + 1; i < length; i++) {
        words.append(delimiter).append(strings[i]);
    }
    return words.toString();
  }
}