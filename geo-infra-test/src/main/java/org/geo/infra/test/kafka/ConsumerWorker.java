package org.geo.infra.test.kafka;

public class ConsumerWorker implements Runnable {

    private ConsumerRecord<String, String> consumerRecord;

	public ConsumerWorker(ConsumerRecord record) {
	      this.consumerRecord = record;
	}

	public void run() {
	   ConsumerMessageBO  consumerMessageBO= JSONObject.parseObject(consumerRecord.value(),ConsumerMessageBO.class);
	   consumerMessageBO.setOffset(consumerRecord.offset());
	   consumerMessageBO.setPartition(consumerRecord.partition());
	   for(MessageContainer messageContainer: PropertyFactory.consumerProperty.getMessageContainers()){
	       if(consumerRecord.topic().equals(messageContainer.getTopic())){
	           messageContainer.getMessageHandle().onMessage(consumerMessageBO);
	       }
	   }
	
	}
}