package org.geo.infra.test.kafka;

public class ConsumerProperty {

    private String brokerList;

    private String groupId;

    private  String enableAutoCommit="true";

    private String autoCommitInterval="1000";

    private String sessionTimeout="30000";

    private String keySerializer="org.apache.kafka.common.serialization.StringDeserializer";

    private String valueSerializer="org.apache.kafka.common.serialization.StringDeserializer";
    /**
     * topic以及消费的实现类
     */
    private List<MessageContainer> messageContainers;