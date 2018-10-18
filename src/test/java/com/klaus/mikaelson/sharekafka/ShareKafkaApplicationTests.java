package com.klaus.mikaelson.sharekafka;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.klaus.mikaelson.sharekafka.model.Emp;
import com.klaus.mikaelson.sharekafka.service.EmpService;

import io.shardingsphere.orchestration.internal.OrchestrationFacade;
import io.shardingsphere.orchestration.internal.config.ConfigurationService;
import io.shardingsphere.orchestration.reg.api.RegistryCenter;
import io.shardingsphere.orchestration.reg.api.RegistryCenterConfiguration;
import io.shardingsphere.orchestration.reg.zookeeper.ZookeeperConfiguration;
import io.shardingsphere.orchestration.reg.zookeeper.ZookeeperRegistryCenter;
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
	RegistryCenterConfiguration regCenterConfig;
	
	
	
	@Test 
	public void testConfigurationService() {
		try {
			Gson gson = new Gson();
//			log.info("********************\n orchestrationProperties is {}", orchestrationProperties);
//			log.info("********************\n OrchestrationConfiguration is {}", orchestrationProperties.getOrchestrationConfiguration());
			
			OrchestrationFacade orchestrationFacade = new OrchestrationFacade(orchestrationProperties.getOrchestrationConfiguration());
			
//			log.info("********************\n orchestrationFacade is {}", orchestrationFacade);
			
			RegistryCenterConfiguration registryCenterConfiguration = orchestrationProperties.getOrchestrationConfiguration().getRegCenterConfig();
			
			RegistryCenter regCenter = new ZookeeperRegistryCenter((ZookeeperConfiguration) registryCenterConfiguration);
			
//			log.info("********************\n regCenter is {}", regCenter);
			
			ConfigurationService configService = orchestrationFacade.getConfigService();
			
//			log.info("********************\n configService is {}", configService);
			
			
			log.info("************************************************************************************************");
			
			Map<String, DataSource> dataSources = configService.loadDataSourceMap() ;
			dataSources.forEach((k, v) -> System.out.println("key:value = " + k + ":" + v));
			log.info("DataSourceMap is :{}", configService.loadDataSourceMap());
			
			log.info("MasterSlaveConfigMap is:{}", configService.loadMasterSlaveConfigMap());
			
			
			log.info("MasterSlaveRuleConfiguration is :{}", configService.loadMasterSlaveRuleConfiguration() );
			
			log.info("YamlServerConfiguration is :{}", configService.loadYamlServerConfiguration());
			
			log.info("MasterSlaveProperties is :{}", configService.loadMasterSlaveProperties());
			
			
			
			
			orchestrationFacade.close();
		} catch (Exception e) {
			
			log.error("Exception is {}",e);
		}
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
