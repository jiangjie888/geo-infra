package com.mq.rabbit;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import org.junit.Test;

import com.rabbitmq.client.Channel;

/**
* @author jjie
* @proc 单发送单接收
*/
public class Send {
    
  private final static String QUEUE_NAME = "hello";

  //@Test
  public static void main(String[] argv) throws Exception {
                
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("192.168.100.155");
    factory.setPort(5672);
    factory.setUsername("admin");
    factory.setPassword("admin");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "Hello World!";
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    System.out.println(" [x] Sent '" + message + "'");
    
    channel.close();
    connection.close();
  }
}