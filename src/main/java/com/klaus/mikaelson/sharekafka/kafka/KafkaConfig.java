package com.klaus.mikaelson.sharekafka.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer.AckMode;

@EnableKafka
@Configuration
//KafkaAutoConfiguration 查看自动配置
public class KafkaConfig {
	/**
	 * 生产者工厂
	 */
	@Autowired
	private ProducerFactory<String, Object> producerFactory;

	/**
	 * 消费者工厂
	 */
	@Autowired
	private ConsumerFactory<String, Object> consumerFactory;

	/**
	 * 生产者模板
	 */
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory);
	}

	/**
	 * 消费者批量工厂
	 */
	@Bean
	public KafkaListenerContainerFactory<?> batchFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		factory.setBatchListener(true);
		factory.getContainerProperties().setAckMode(AckMode.MANUAL);
		return factory;
	}

}
