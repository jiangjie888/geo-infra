package org.geo.infra.test.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangwentao on 2017/5/18.
 */
public class ConsumerHandler {
    private final KafkaConsumer<String, String> consumer;
    private ExecutorService executors;

    public ConsumerHandler(ConsumerProperty  consumerProperty, List<String> topics) {
        Properties props = new Properties();
        props.put("bootstrap.servers", consumerProperty.getBrokerList());
        props.put("group.id", consumerProperty.getGroupId());
        props.put("enable.auto.commit", consumerProperty.getEnableAutoCommit());
        props.put("auto.commit.interval.ms", consumerProperty.getAutoCommitInterval());
        props.put("session.timeout.ms", consumerProperty.getSessionTimeout());
        props.put("key.deserializer", consumerProperty.getKeySerializer());
        props.put("value.deserializer", consumerProperty.getValueSerializer());
        consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(topics);
    }

    public void execute(int workerNum) {
        executors = new ThreadPoolExecutor(workerNum, workerNum, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue(1000), new ThreadPoolExecutor.CallerRunsPolicy());
        Thread t = new Thread(new Runnable(){//启动一个子线程来监听kafka消息
            public void run(){
       while (true) {
        ConsumerRecords<String, String> records = consumer.poll(200);
        for (final ConsumerRecord record : records) {
            System.out.println("监听到kafka消息。。。。。。");
            executors.submit(new ConsumerWorker(record));
        }
      }
            }});
        t.start();

    }

    public void shutdown() {
        if (consumer != null) {
            consumer.close();
        }
        if (executors != null) {
            executors.shutdown();
        }
        try {
            if (!executors.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("Timeout.... Ignore for this case");
            }
        } catch (InterruptedException ignored) {
            System.out.println("Other thread interrupted this shutdown, ignore for this case.");
            Thread.currentThread().interrupt();
        }
    }
}