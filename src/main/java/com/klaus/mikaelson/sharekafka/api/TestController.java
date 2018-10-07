package com.klaus.mikaelson.sharekafka.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.klaus.mikaelson.sharekafka.model.Emp;
import com.klaus.mikaelson.sharekafka.service.EmpService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController("/api/test")
public class TestController {
	
	@Value("${spring.profiles.active}")
	private String profile;
	
	@GetMapping("/status")
	public String info1() {
		return "ok";
	}
	@GetMapping("/info")
	public String info2() {
		return "active profile is :"+profile;
	}
	@GetMapping("item/{id}")
	public String status(@PathVariable(name="id",required=true) String id) {
		return "ok";
	}
	
	@Autowired EmpService empService;
	
	@GetMapping("/emp")
	public Emp save() {
		
		Emp entity = new Emp();
		
		entity.setDid(0);
		entity.setEname("klaus");
		entity.setHire(new Date());
		entity.setSar(1203102.3f);
		entity.setSex("male");
		log.info("entity is :{}",entity);
		return empService.saveOrUpdate(entity);
	}
}
