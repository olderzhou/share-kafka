package com.klaus.mikaelson.sharekafka.kafka;

import java.util.List;
import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.klaus.mikaelson.sharekafka.model.Emp;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaReceiver {

	@KafkaListener(topics = { "zhisheng" })
	public void listen(ConsumerRecord<?, ?> record) {
		log.info("----------------------start receive message-----------------------");
		Optional<?> kafkaMessage = Optional.ofNullable(record.value());

		if (kafkaMessage.isPresent()) {

			Object message = kafkaMessage.get();

			log.info("----------------- record =" + record);
			log.info("------------------ message =" + message);
		}

	}

	@KafkaListener(id = "baz", topics = "zhisheng", containerFactory = "kafkaManualAckListenerContainerFactory")
	public void listen(String data, Acknowledgment ack) {
		log.info("baz listen data is :{}",data);
		ack.acknowledge();
	}
	
	@KafkaListener(id = "listCRs", topics = "zhisheng", containerFactory = "batchFactory")
	public void listen1(List<ConsumerRecord<Integer, String>> list) {
		for(ConsumerRecord<Integer, String> i: list) {
			log.info("listCRs listen1 data is :<{},{}>",i.key(),i.value());
		}
	}

	@KafkaListener(id = "listCRsAck", topics = "zhisheng", containerFactory = "batchFactory")
	public void listen2(List<ConsumerRecord<Integer, String>> list, Acknowledgment ack) {
		for(ConsumerRecord<Integer, String> i: list) {
			log.info("listCRsAck listen2 data is :<{},{}>",i.key(),i.value());
		}
		ack.acknowledge();
	}
	
	@KafkaListener(id = "list", topics = "zhisheng", containerFactory = "batchFactory")
	public void listen(List<String> list) {
		for(String i:list) {
			log.info("listen data is :{}",i);
		}
	}
	
	
	@KafkaListener(id = "listMsg", topics = "zhisheng", containerFactory = "batchFactory")
	public void listen14(List<Emp> list) {
		for(Emp i:list) {
			log.info("listen14 data is :{}",i);
		}
	}

	@KafkaListener(id = "listMsgAck", topics = "zhisheng", containerFactory = "batchFactory")
	public void listen15(List<Emp> list, Acknowledgment ack) {
		for(Emp i:list) {
			log.info("listen15 data is :{}",i);
		}
		ack.acknowledge();
	}
	
	
	
}