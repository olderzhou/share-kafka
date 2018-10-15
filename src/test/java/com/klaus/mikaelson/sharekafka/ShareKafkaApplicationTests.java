package com.klaus.mikaelson.sharekafka;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.klaus.mikaelson.sharekafka.model.Emp;
import com.klaus.mikaelson.sharekafka.service.EmpService;

import io.shardingsphere.orchestration.config.OrchestrationConfiguration;
import io.shardingsphere.orchestration.reg.api.RegistryCenterConfiguration;
import io.shardingsphere.shardingjdbc.orchestration.spring.boot.orchestration.SpringBootOrchestrationConfigurationProperties;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ShareKafkaApplicationTests {

//	@Autowired
	private EmpService empService;

//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;
//
//	@Autowired
//	private StringRedisTemplate stringRedisTemplate;
	
	
	
	
	@Autowired
	private SpringBootOrchestrationConfigurationProperties orchestrationProperties;
	
//	@Autowired
	OrchestrationConfiguration orchestrationConfig;
	
	
//	@Autowired
	RegistryCenterConfiguration regCenterConfig;
	
	
	
	
	@Test 
	public void testSpringBootOrchestrationConfigurationProperties() {
		
		Gson gson = new Gson();
		
		log.info("orchestrationProperties is {}", gson.toJson(orchestrationProperties));
		
	}
	
	
//	@Test 
	public void testOrchestrationConfiguration() {
		
		log.info("orchestrationConfig is {}", orchestrationConfig);
		
	}
	
	
//	@Test 
	public void testRegistryCenterConfiguration() {
		
		log.info("regCenterConfig is {}", regCenterConfig);
		
	}
	

//	@Test
	public void testSaveEmp() {
		for (int i=0;i<10;i++) {
			Emp entity = new Emp();
			entity.setDid(0);
			entity.setEname("周老大"+Math.random()*10);
			entity.setHire(new Date());
			entity.setSar(1203102.3f);
			entity.setSex("male");
			entity = empService.saveOrUpdate(entity);
			
			Emp enEmp = empService.findById(entity.getEid());
			log.info("entity is :{} , enEmp is :{}", entity.getEid(), enEmp.getEid());
			
		}
		assertNotEquals(true, empService.countAll() == 0);
	}
	
	
//	@Test
	public void testUpdateEmp() {
		Emp emp = empService.findById(255716899122315264L);
		emp.setSar(2334234.324f);
		empService.saveOrUpdate(emp);
		assertEquals(true, 2334234.324f == emp.getSar());
		
	}
	
	
	
	

}
