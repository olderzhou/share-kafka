package com.klaus.mikaelson.sharekafka.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.klaus.mikaelson.sharekafka.model.Emp;

import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class KafkaReceiver {

//	@KafkaListener(id = "listCRs", topics = "zhisheng", containerFactory = "batchFactory")
//	public void listen1(List<ConsumerRecord<String, String>> list) {
//		for(ConsumerRecord<String, String> i: list) {
//			log.info("listCRs listen1 data is :<{},{}>",i.key(),i.value());
//		}
//		log.info("listCRs listen1 finished");
//	}

//	@KafkaListener(id = "listCRsAck", topics = "zhisheng", containerFactory = "batchFactory")
//	public void listen2(List<ConsumerRecord<String, String>> list, Acknowledgment ack) {
//		for(ConsumerRecord<String, String> i: list) {
//			log.info("listCRsAck listen2 data is :<{},{}>",i.key(),i.value());
//		}
//		log.info("listCRs listen1 finished");
//		ack.acknowledge();
//	}
	
//	@KafkaListener(id = "list", topics = "zhisheng", containerFactory = "batchFactory")
//	public void listen(List<String> list) {
//		for(String i:list) {
//			log.info("listen data is :{}",i);
//		}
//		log.info("list listen finished");
//	}
	
//	@KafkaListener(id = "listMsgSingle1", topics = "testJson")
//	public void listen12(Emp message) {
//		log.info("listen12 data is :{}",message);
//		log.info("list listMsgSingle1 finished");
//	}
	
//	@KafkaListener(id = "listMsgSingle2", topics = "testJson")
//	public void listen13(ConsumerRecord<String, String> record) {
//		log.info("listen13 data is :<{},{}>", record.key(), record.value());
//		log.info("listMsgSingle2 listen13 finished");
//	}
	
//	@KafkaListener(id = "listMsg", topics = "testJson", containerFactory = "batchFactory")
//	public void listen14(List<Emp> list) {
//		for(Emp i:list) {
//			log.info("listen14 data is :{}",i);
//		}
//		log.info("list listen finished");
//	}
	
//	@KafkaListener(id = "listMsgAck", topics = "testJson")
//	public void listen15(ConsumerRecord<String, String> record, Acknowledgment ack) {
//		log.info("listen15 data is :{}, {}", record.key(), record.value());
//		ack.acknowledge();
//		log.info("list listen finished");
//	}

//	@KafkaListener(id = "listMsgAck", topics = "testJson", containerFactory = "batchFactory")
	public void listen15(List<Emp> list,
			@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<Integer> keys,
	        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
	        @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
	        @Header(KafkaHeaders.OFFSET) List<Long> offsets, Acknowledgment ack) {
		log.info("list listen start, keys: {}, partitions: {}, topics: {}, offsets: {}", keys, partitions, topics, offsets);
		
		for(Emp i:list) {
			log.info("listen15 data is :{}",i);
		}
		ack.acknowledge();
		log.info("list listen finished");
	}
	
    /**
     * 监听topic3和topic4,单条消费
     */
//    @KafkaListener(topics = {"testJson", "testJson1"})
//    public void listen2(ConsumerRecord<String, Object> record , Acknowledgment ack) {
//        consumer(record);
//		ack.acknowledge();
//		log.info("list listen finished");
//    }

    /**
     * 单条消费
     */
    public void consumer(ConsumerRecord<String, Object> record) {
    	
    	if(record.value() instanceof Emp) {
    		log.info("主题:{}, 内容: {}", record.topic(), record.value());
    	}
    	
    }

    /**
     * 批量消费
     */
    public void batchConsumer(List<ConsumerRecord<String, Object>> records) {
        records.forEach(record -> consumer(record));
    }

	
}