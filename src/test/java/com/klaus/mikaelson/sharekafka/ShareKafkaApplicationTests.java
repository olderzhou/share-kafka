package com.klaus.mikaelson.sharekafka;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.klaus.mikaelson.sharekafka.model.Emp;
import com.klaus.mikaelson.sharekafka.service.EmpService;

import io.shardingsphere.api.config.ShardingRuleConfiguration;
import io.shardingsphere.orchestration.internal.OrchestrationFacade;
import io.shardingsphere.orchestration.internal.config.ConfigurationNode;
import io.shardingsphere.orchestration.internal.yaml.converter.DataSourceConverter;
import io.shardingsphere.orchestration.internal.yaml.converter.ShardingConfigurationConverter;
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
	
	
	@Test 
	public void testConfigurationService() {
		OrchestrationFacade orchestrationFacade = null;
		RegistryCenter regCenter = null;
		RegistryCenterConfiguration registryCenterConfiguration = null;
		try {
			orchestrationFacade = new OrchestrationFacade(orchestrationProperties.getOrchestrationConfiguration());
			registryCenterConfiguration = orchestrationProperties.getOrchestrationConfiguration().getRegCenterConfig();
			regCenter = new ZookeeperRegistryCenter((ZookeeperConfiguration) registryCenterConfiguration);
			ConfigurationNode configNode =  new ConfigurationNode(orchestrationProperties.getName());
			
			String dataSource = regCenter.get(configNode.getFullPath(ConfigurationNode.DATA_SOURCE_NODE_PATH));
			log.info("{} is: {}", configNode.getFullPath(ConfigurationNode.DATA_SOURCE_NODE_PATH), dataSource);
			
			String shardingRule = regCenter.get(configNode.getFullPath(ConfigurationNode.SHARDING_RULE_NODE_PATH));
			log.info("{} is: {}", configNode.getFullPath(ConfigurationNode.SHARDING_RULE_NODE_PATH), shardingRule);
			
			String shardingConfig = regCenter.get(configNode.getFullPath(ConfigurationNode.SHARDING_CONFIG_MAP_NODE_PATH));
			log.info("{} is: {}", configNode.getFullPath(ConfigurationNode.SHARDING_CONFIG_MAP_NODE_PATH), shardingConfig);
			
			String shardingProps = regCenter.get(configNode.getFullPath(ConfigurationNode.SHARDING_PROPS_NODE_PATH));
			log.info("{} is: {}", configNode.getFullPath(ConfigurationNode.SHARDING_PROPS_NODE_PATH), shardingProps);
			
			Map<String, DataSource> dataSourceMap = DataSourceConverter.dataSourceMapFromYaml(dataSource) ;
			ShardingRuleConfiguration masterSlaveRuleConfig = ShardingConfigurationConverter.shardingRuleConfigFromYaml(shardingRule);
			Map<String, Object> shardingConfigMaps = ShardingConfigurationConverter.configMapFromYaml(shardingConfig);
			Properties  shardingProperties =  ShardingConfigurationConverter.propertiesFromYaml(shardingProps);
			
			orchestrationFacade.init(dataSourceMap, masterSlaveRuleConfig, shardingConfigMaps, shardingProperties);
			
		} catch (Exception e) {
			
			log.error("Exception is {}",e);
		} finally {
			try {
				regCenter.close();
				orchestrationFacade.close();
			} catch (Exception e) {
				log.error("Resource release exception :{}",e);
			}
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
