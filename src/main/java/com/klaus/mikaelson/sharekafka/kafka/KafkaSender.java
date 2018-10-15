package com.klaus.mikaelson.sharekafka.kafka;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.klaus.mikaelson.sharekafka.model.Emp;

import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class KafkaSender {

//    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
    
    //ReplyingKafkaTemplate<K, V, R>
    private ReplyingKafkaTemplate<String, Object, String> replyingKafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send(String topic, Emp message) {
        message.setEname("klaus elena damon");
//        log.info("+++++++++++++++++++++  message = {}", gson.toJson(message));
        kafkaTemplate.send(topic, String.valueOf(message.getEid()), message);
    }
    
    
    /**
     * 向topic中发送消息
     */
    public void send (String topic, List<Emp> msgs) {
        msgs.forEach(msg -> kafkaTemplate.send(topic, msg));
    }
    
    /**
     * 向topic中发送消息
     * @throws ExecutionException 
     * @throws InterruptedException 
     */
    public void  sendAndReceive(ProducerRecord<String, Object> record) throws InterruptedException, ExecutionException {
    	RequestReplyFuture<String, Object, String> replyFuture = replyingKafkaTemplate.sendAndReceive(record);
    	SendResult<String, Object> sendResult = replyFuture.getSendFuture().get();
    	log.info("Sent ok: {}", gson.toJson(sendResult.getRecordMetadata()));
        ConsumerRecord<String, String> consumerRecord = replyFuture.get();
        log.info("Return value: {}", consumerRecord.value());
    	
    }
    
    public void sendAync(String topic, Emp emp) {
    	ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, String.valueOf(emp.getEid()), emp);
    	future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
    		
    		@Override
    		public void onSuccess(SendResult<String, Object> result) {
    			log.info("RecordMetadata is : {}",gson.toJson(result.getRecordMetadata()));
    		}
    		
    		@Override
    		public void onFailure(Throwable ex) {
    			log.error("send failure ,cause by :{}",ex);
    		}
    		
    	});
    	
    }
    
    

}