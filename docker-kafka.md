# docker-kafka-install

docker-compose.yml

version: '3'

services:
  zoo1:
    image: wurstmeister/zookeeper
    restart: unless-stopped
    hostname: zoo1
    ports:
      - "2181:2181"
    container_name: zookeeper

  # kafka version: 1.1.0
  # scala version: 2.12
  kafka1:
    image: wurstmeister/kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_HOST_NAME: 192.168.0.3
      KAFKA_ZOOKEEPER_CONNECT: "zoo1:2181"
      KAFKA_BROKER_ID: 1
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_CREATE_TOPICS: "stream-in:1:1,stream-out:1:1"
    depends_on:
      - zoo1
    container_name: kafka
    
    
    
    
    
    
#可能遇到的问题

1. 消息格式为对象的时候，需要设置
spring:
  kafka:
    consumer:
      properties:
        spring:
          json:
            trusted:
              packages: com.klaus.mikaelson.sharekafka.model
否则将无法识别对象类型，从而序列化失败


2.No Acknowledgment available as an argument, the listener container must have a MANUAL Ackmode to populate the Acknowledgment

Acknowledgment只有在spring.kafka.consumer.enable-auto-commit=false的情况下才会生效，此时需要设置ackMode类型为MANUAL  或 MANUAL_IMMEDIATE 
添加配置如下：
spring:
  kafka:
    listener:
      ack-mode: MANUAL  #or  MANUAL_IMMEDIATE
      
当需要使用批量处理的 时候，则batchFactory也必须配置ackMode

	@Bean
	public KafkaListenerContainerFactory<?> batchFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		factory.setBatchListener(true);
		factory.getContainerProperties().setAckMode(AckMode.MANUAL);
		return factory;
	}







