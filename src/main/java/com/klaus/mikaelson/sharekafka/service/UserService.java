/**
* 文件名：UserService.java
*
* 版本信息：
* 日期：2018年10月9日 下午4:03:57
* Copyright 版权所有 @Zhuiyi Inc 2018
*/
package com.klaus.mikaelson.sharekafka.service;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.klaus.mikaelson.sharekafka.model.mongo.User;
import com.klaus.mikaelson.sharekafka.mongo.repository.UserRepository;
import com.mongodb.client.MongoCollection;

/**
 * 此类描述的是：
 * 
 * @author: klauszhou@wezhuiyi.com
 * @version: 2018年10月9日 下午4:03:57
 */
@Service("userService")
public class UserService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserRepository userRepository;

	public MongoCollection<Document> createCollection(String collectionName) {
		if (mongoTemplate.collectionExists(collectionName)) {
			return mongoTemplate.getCollection(collectionName);
		}
		return mongoTemplate.createCollection(collectionName);
	}

	public boolean collectionExists(String collectionName) {
		return mongoTemplate.collectionExists(collectionName);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User saveUserOrUpdate(User user) {
		return userRepository.save(user);
	}

	public Iterable<User> findById(Long userId) {
		return userRepository.findAllById(Arrays.asList(userId));
	}

	public void bulkOpsForUser(String collectionName) {

		// 这里的BulkMode.UNORDERED是个枚举，，，collectionName是mongo的集合名
		BulkOperations ops = mongoTemplate.bulkOps(BulkMode.UNORDERED, collectionName);
//		for (;;) {

		// update我还没有研究这就不讲了，嘿嘿
		Update update = new Update();
		User updateUser = new User();
		ops.updateOne(Query.query(Criteria.where("id").is(updateUser.getId())), update);

		// 我用的insert方法
		// 注意此处的obj必须是一个DBObject，可以是json对象也可以的bson对象，entity没有试过
		User user = new User();
		ops.insert(user);
//		}
		// 循环插完以后批量执行提交一下ok！
		ops.execute();

	}

}
