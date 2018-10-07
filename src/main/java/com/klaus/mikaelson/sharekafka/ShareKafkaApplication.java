package com.klaus.mikaelson.sharekafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.spring4all.swagger.EnableSwagger2Doc;

import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableSwagger2Doc
@EnableKafka
@EnableScheduling
@EnableJpaAuditing
@EnableJpaRepositories("com.klaus.mikaelson.sharekafka.dao")
@Slf4j
public class ShareKafkaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ShareKafkaApplication.class, args);
		Long workId = System.currentTimeMillis()%1024L;
		DefaultKeyGenerator.setWorkerId(workId);
		log.info("SpringApplication started ...., workId is : {}", workId);
		
		
	    }
		
		

}
