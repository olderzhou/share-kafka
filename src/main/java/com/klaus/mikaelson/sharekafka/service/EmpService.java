package com.klaus.mikaelson.sharekafka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.klaus.mikaelson.sharekafka.dao.IEmpDao;
import com.klaus.mikaelson.sharekafka.model.Emp;

@Service
public class EmpService {
	
	
	@Autowired
	private IEmpDao empDao;
	
	
	@CachePut(value = "emp", key = "'emp'.concat(#emp.eid.toString())")
	public Emp saveOrUpdate(Emp emp) {
		return empDao.save(emp);
	}
	
	@Cacheable(value = "emp", key = "'emp'.concat(#eid.toString())")
	public Emp findById(Long eid) {
		return empDao.findById(eid).orElseGet(null);
	}
	
	@CacheEvict(value = "emp", key = "'emp'.concat(#eid.toString())")
	public void deleteById(Long eid) {
		empDao.deleteById(eid);
	}
	
	public List<Emp> findAll() {
		return empDao.findAll();
	}
	
	public Long countAll() {
		return empDao.count();
	}
	
	
	
	
}
