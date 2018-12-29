package com.mq.rabbit;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;


 /**
 * @author jjie
 * @proc 单发送多接收   使用场景：一个发送端，多个接收端，如分布式的任务派发。
 * 为了保证消息发送的可靠性，不丢失消息，使消息持久化了。
 * 同时为了防止接收端在处理消息时down掉，只有在消息处理完成后才发送ack消息。
 */
public class NewTask {
  
  private static final String TASK_QUEUE_NAME = "task_queue";


  public static void main(String[] argv) throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("192.168.100.155");
    factory.setPort(5672);
    factory.setUsername("admin");
    factory.setPassword("admin");
    
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    
    channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
    
    String message = getMessage(argv);
    
    channel.basicPublish( "", TASK_QUEUE_NAME, 
                MessageProperties.PERSISTENT_TEXT_PLAIN,
                message.getBytes());
    System.out.println(" [x] Sent '" + message + "'");
    
    channel.close();
    connection.close();
  }
    
  private static String getMessage(String[] strings){
    if (strings.length < 1)
      return "Hello World!";
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