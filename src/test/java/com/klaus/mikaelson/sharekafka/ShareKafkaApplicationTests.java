package com.klaus.mikaelson.sharekafka;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.GeoResults;
import org.springframework.test.context.junit4.SpringRunner;

import com.klaus.mikaelson.sharekafka.base.R;
import com.klaus.mikaelson.sharekafka.model.Emp;
import com.klaus.mikaelson.sharekafka.model.mongo.User;
import com.klaus.mikaelson.sharekafka.req.UserReq;
import com.klaus.mikaelson.sharekafka.service.EmpService;
import com.klaus.mikaelson.sharekafka.service.UserService;

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
	private UserService userService;

	@Test
	public void testUserResqQuer() {

		UserReq userReq = new UserReq();
		userReq.setAge(25);
		userReq.setUsername("klaus8.974969486680035");
		R result = userService.findUserList(userReq);
		log.info("result is {}", result);

		assertEquals(true, (int) result.get("code") == 0);
	}

	@Test
	public void testNeoQuery() {
		// 经度\纬度
		double[] bjAli = new double[] { 116.492644, 40.006313 };
		double[] szAli = new double[] { 113.950723, 22.558888 };
		double[] shAli = new double[] { 121.387616, 31.213301 };
		double[] hzAli = new double[] { 120.033345, 30.286398 };
		Arrays.asList(bjAli, szAli, shAli, hzAli).stream().forEach(d -> {
			GeoResults<User> results = userService.nearRadian(d);
			log.info("results is : {}",results);
		});
		
		log.info("near2 : {}", userService.near2(szAli));
		log.info("near3 : {}", userService.near3(szAli));
		log.info("near4 : {}", userService.near4(szAli));
		
		
		

	}

	@Test
	public void testUserService() {
		Random random = new Random();

		List<User> users = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			User user = new User();

			// 经度
			double longitude = 0;
			// 纬度
			double latitude = 0;
			DecimalFormat df = new DecimalFormat("0.000000");
			longitude = 100 + random.nextInt(30) + Double.parseDouble(df.format(Math.random()));
			latitude = 20 + random.nextInt(10) + Double.parseDouble(df.format(Math.random()));
			user.setLocation(new double[] { longitude, latitude });
			user.setUsername("klaus" + random.nextInt(100));
			user.setAge(random.nextInt(100));
			user.setCreateTimestamp(System.currentTimeMillis());
			users.add(user);
		}
		userService.saveMany(users);

		Iterable<User> u = userService.findAll();

		for (User us : u) {
			log.info("user is : {}", us);
		}

		assertEquals(true, u.iterator().hasNext());
	}

	@Test
	public void testSaveEmp() {
		for (int i = 0; i < 10; i++) {
			Emp entity = new Emp();
			entity.setDid(0);
			entity.setEname("周老大" + Math.random() * 10);
			entity.setHire(new Date());
			entity.setSar(1203102.3f);
			entity.setSex("male");
			entity = empService.saveOrUpdate(entity);

//			Emp enEmp = empService.findById(entity.getEid());
//			log.info("entity is :{} , enEmp is :{}", entity.getEid(), enEmp.getEid());

		}
		assertNotEquals(true, empService.countAll() == 0);
	}

	@Test
	public void testUpdateEmp() {
		Emp emp = empService.findById(255716899122315264L);
		emp.setSar(2334234.324f);
		empService.saveOrUpdate(emp);
		assertEquals(true, 2334234.324f == emp.getSar());

	}

}
