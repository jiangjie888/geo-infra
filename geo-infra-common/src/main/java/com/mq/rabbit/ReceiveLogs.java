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
* @proc Publish/Subscribe  使用场景：发布、订阅模式，发送端发送广播消息，多个接收端接收。
* 接收端：
	1、声明名为“logs”的exchange的，方式为"fanout"，和发送端一样。
	2、channel.queueDeclare().getQueue();该语句得到一个随机名称的Queue，该queue的类型为non-durable、exclusive、auto-delete的，将该queue绑定到上面的exchange上接收消息。
	3、注意binding queue的时候，channel.queueBind()的第三个参数Routing key为空，即所有的消息都接收。如果这个值不为空，在exchange type为“fanout”方式下该值被忽略！
*/

public class ReceiveLogs {
	private static final String EXCHANGE_NAME = "logs";

	  public static void main(String[] argv) throws Exception {
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("192.168.100.155");
	    factory.setPort(5672);
	    factory.setUsername("admin");
	    factory.setPassword("admin");
	    
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    
	    /**
	     * 消费者 ———— 声明交换器、声明队列、绑定交换器与队列
	     */
	    channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
	    String queueName = channel.queueDeclare().getQueue();
	    channel.queueBind(queueName, EXCHANGE_NAME, "");

	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

	    Consumer consumer = new DefaultConsumer(channel) {
	    
	    /**
	     * 该方法在消费者获取到消息处理消息时，会执行
	     */
	      @Override
	      public void handleDelivery(String consumerTag, Envelope envelope,
	                                 AMQP.BasicProperties properties, byte[] body) throws IOException {
	        String message = new String(body, "UTF-8");
	        System.out.println(" [x] Received '" + message + "'");
	      }
	    };
	    channel.basicConsume(queueName, true, consumer);
	  }
}