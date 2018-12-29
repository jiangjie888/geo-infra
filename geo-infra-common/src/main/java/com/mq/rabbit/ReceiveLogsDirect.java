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
* @proc Routing (按路线发送接收)使用场景：发送端按routing key发送消息，不同的接收端按不同的routing key接收消息。
*/
public class ReceiveLogsDirect {

  private static final String EXCHANGE_NAME = "direct_logs";

  public static void main(String[] argv) throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("192.168.100.155");
    factory.setPort(5672);
    factory.setUsername("admin");
    factory.setPassword("admin");
    
    final Connection connection = factory.newConnection();
    final Channel channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE_NAME, "direct");
    String queueName = channel.queueDeclare().getQueue();
    
    if (argv.length < 1){
      System.err.println("Usage: ReceiveLogsDirect [info] [warning] [error]");
      System.exit(1);
    }
    
    for(String severity : argv){    
      channel.queueBind(queueName, EXCHANGE_NAME, severity);
    }
    
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    //QueueingConsumer consumer = new QueueingConsumer(channel);
    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope,
                                 AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body, "UTF-8");
        String routingKey = envelope.getRoutingKey();
        String contentType = properties.getContentType();
        long deliveryTag = envelope.getDeliveryTag();
        System.out.println(" 消费者接收到消息： '" + envelope.getRoutingKey() + "':'" + message + "'");
        channel.basicAck(deliveryTag, false);
      }
    };
	
    
    channel.basicConsume(queueName, true, consumer);
    
    /*while (true) {
    	DefaultConsumer.Delivery delivery = consumer.nextDelivery();
      String message = new String(delivery.getBody());
      String routingKey = delivery.getEnvelope().getRoutingKey();

      System.out.println(" [x] Received '" + routingKey + "':'" + message + "'");   
    }*/
  }
}