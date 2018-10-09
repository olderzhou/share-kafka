package com.klaus.mikaelson.sharekafka.kafka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.klaus.mikaelson.sharekafka.model.Emp;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;

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

}