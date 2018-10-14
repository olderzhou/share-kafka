package com.klaus.mikaelson.sharekafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.klaus.mikaelson.sharekafka.kafka.KafkaSender;
import com.klaus.mikaelson.sharekafka.model.Emp;
import com.spring4all.swagger.EnableSwagger2Doc;

import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableSwagger2Doc
@EnableScheduling
@EnableJpaAuditing
@EnableJpaRepositories("com.klaus.mikaelson.sharekafka.dao")
@EnableAutoConfiguration(exclude= {MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
@Slf4j
public class ShareKafkaApplication {

	
	@Autowired
	private KafkaSender sender;

	@Autowired
	private KafkaAdmin  admin;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ShareKafkaApplication.class, args);
		Long workId = System.currentTimeMillis() % 1024L;
		DefaultKeyGenerator.setWorkerId(workId);
		log.info("SpringApplication started ...., workId is : {}", workId);
		

	}
	

//	@Scheduled(cron = "0/30 * * * * *")
//	@Scheduled(cron = "0 0/1 * * * *")
	public void produceData() {
		log.info("admin config is :{}", admin.getConfig());
		log.info("send message started");
		long start = System.currentTimeMillis();
		DefaultKeyGenerator key = new DefaultKeyGenerator();
		int i=0;
		for(i = 0 ;i<100000;i++) {
			Emp emp = new Emp();
			emp.setDid(i);
			emp.setEid(key.generateKey().longValue());
			sender.send("foo1",emp);
//			sender.sendAync("testJson1",emp);
		}
		log.info("send {} message finished,time use :{} ms",i,System.currentTimeMillis() - start );
	}

}
