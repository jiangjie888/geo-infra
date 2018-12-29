package org.geo.infra.test.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import kafka.javaapi.producer.Producer;


public class Kafka {

	@SuppressWarnings("deprecation")
    public static void main( String[] args ) throws InterruptedException{
    	System.out.println( "Hello kafka!" );
    	
    	
	    Properties props = new Properties();
		 props.put("bootstrap.servers", bootstrap.servers);
		 props.put("acks", "all"); //ack是判别请求是否为完整的条件（就是是判断是不是成功发送了）。我们指定了“all”将会阻塞消息，这种设置性能最低，但是是最可靠的。
		 props.put("retries", 0);  //如果请求失败，生产者会自动重试，我们指定是0次，如果启用重试，则会有重复消息的可能性。
		 props.put("batch.size", 0);
		 props.put("linger.ms", 1);
		 props.put("buffer.memory", 33554432);
		 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 //(生产者)缓存每个分区未发送消息。缓存的大小是通过 batch.size 配置指定的。值较大的话将会产生更大的批。并需要更多的内存（因为每个“活跃”的分区都有1个缓冲区）。
		KafkaProducer<String, String> producer = new KafkaProducer<String,String>(props);
		ProducerRecord<String,String> record = new ProducerRecord<String,String>(message.getTopic().getName(), message.getMessageId(), JSONObject.toJSONString(message));
		RecordMetadata recordMetadata = producer.send(record).get();
		
		
/*		ProducerRecord<byte[],byte[]> record = new ProducerRecord<byte[],byte[]>("the-topic", key, value);
		 producer.send(myRecord,
		               new Callback() {
		                   public void onCompletion(RecordMetadata metadata, Exception e) {
		                       if(e != null)
		                         
		                       System.out.println("获取消息发送结果");
		                   }
		               });*/
    	
    }
}
