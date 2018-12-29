package com.mq.rabbit;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
/**
* @author jjie
* @proc Topics (按topic发送接收)使用场景：发送端不只按固定的routing key发送消息，而是按字符串“匹配”发送，接收端同样如此。
*/
public class ReceiveLogsTopic {

	private static final String EXCHANGE_NAME = "topic_logs";

	  public static void main(String[] argv) throws Exception {
		  
		String[] bindKeys = {"oyy.orange.oyy", "oyy.rabbit", "lazy.oyy"};
		
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("192.168.100.155");
	    factory.setPort(5672);
	    factory.setUsername("admin");
	    factory.setPassword("admin");
	    
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.exchangeDeclare(EXCHANGE_NAME, "topic");
//	    String queueName = channel.queueDeclare().getQueue();
	    String queueName = "topic_logs";

	    if (bindKeys.length < 1) {
	      System.err.println("未指定可用的绑定路由key");
	      System.exit(1);
	    }

	    for (String bindingKey : bindKeys) {
	      channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
	    }

	    System.out.println(" 消费者等待接收消息...");

	    Consumer consumer = new DefaultConsumer(channel) {
	      @Override
	      public void handleDelivery(String consumerTag, Envelope envelope,
	                                 AMQP.BasicProperties properties, byte[] body) throws IOException {
	        String message = new String(body, "UTF-8");
	        System.out.println(" 消费者接收到消息： '" + envelope.getRoutingKey() + "':'" + message + "'");
	      }
	    };
	    channel.basicConsume(queueName, true, consumer);
	  }
}