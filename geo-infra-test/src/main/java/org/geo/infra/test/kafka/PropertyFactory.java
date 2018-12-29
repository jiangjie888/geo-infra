package org.geo.infra.test.kafka;

public class PropertyFactory {

    public  static ProducerProperty producerProperty;

    public  static ConsumerProperty consumerProperty;


    public ProducerProperty getProducerProperty() {
        return producerProperty;
    }

    public void setProducerProperty(ProducerProperty producerProperty) {
        this.producerProperty = producerProperty;
    }

    public ConsumerProperty getConsumerProperty() {
        return consumerProperty;
    }

    public void setConsumerProperty(ConsumerProperty consumerProperty) {
        this.consumerProperty = consumerProperty;
    }


    ConsumerHandler consumer=null;

    @PostConstruct
    public  void startListerConsumer(){
        consumer= new ConsumerListener(consumerProperty).startListen();
    }

    @PreDestroy
    public void shutDown(){
    if(consumer!=null){
        consumer.shutdown();
    }
    }
}