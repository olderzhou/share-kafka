/**
* 文件名：UserService.java
*
* 版本信息：
* 日期：2018年10月9日 下午4:03:57
* Copyright 版权所有 @Zhuiyi Inc 2018
*/
package com.klaus.mikaelson.sharekafka.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.klaus.mikaelson.sharekafka.base.R;
import com.klaus.mikaelson.sharekafka.constants.BizConstant;
import com.klaus.mikaelson.sharekafka.model.mongo.User;
import com.klaus.mikaelson.sharekafka.mongo.repository.UserRepository;
import com.klaus.mikaelson.sharekafka.req.UserReq;
import com.klaus.mikaelson.sharekafka.req.UserResq;
import com.mongodb.client.MongoCollection;

import lombok.extern.slf4j.Slf4j;

/**
 * 此类描述的是：
 * 
 * @author: klauszhou@wezhuiyi.com
 * @version: 2018年10月9日 下午4:03:57
 */
@Service("userService")
@Slf4j
public class UserService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserRepository userRepository;

	public MongoCollection<Document> createCollection() {

		if (mongoTemplate.collectionExists(BizConstant.CollectionNameEnum.COLLECTION_USER.getName())) {
			return mongoTemplate.getCollection(BizConstant.CollectionNameEnum.COLLECTION_USER.getName());
		}
		return mongoTemplate.createCollection(BizConstant.CollectionNameEnum.COLLECTION_USER.getName());
	}

	public boolean collectionExists() {
		return mongoTemplate.collectionExists(BizConstant.CollectionNameEnum.COLLECTION_USER.getName());
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User saveUserOrUpdate(User user) {

		return userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public void deleteById(String userId) {
		userRepository.deleteById(userId);
	}

	public void deleteByIds(List<String> userIds) {
		userRepository.deleteAll(userRepository.findAllById(userIds));
	}

	public void deleteAll() {
		userRepository.deleteAll();
	}

	public Iterable<User> findByIds(List<String> userIds) {
		return userRepository.findAllById(userIds);
	}

	public void bulkOpsForUser() {

		// 这里的BulkMode.UNORDERED是个枚举，，，collectionName是mongo的集合名
		BulkOperations ops = mongoTemplate.bulkOps(BulkMode.UNORDERED,
				BizConstant.CollectionNameEnum.COLLECTION_USER.getName());
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

	public R findUserList(UserReq userReq) {
		// 获取所有字段
		Field[] declaredFields = UserReq.class.getDeclaredFields();
		Criteria queryBuilder = new Criteria();
		// 循环设置查询条件
		try {
			for (Field field : declaredFields) {
				// 不为空设置进去查询条件 这里是查总记录数
				field.setAccessible(true);// 设置反射可以得到私有字段
				String name = field.getName();
				Object object = field.get(userReq);
				if(object != null) {
					if ("username".equals(name)) {
						// 模糊查询
						queryBuilder.and(name).regex(Pattern.compile("^.*" + String.valueOf(object) + ".*$"));
					} else {
						queryBuilder.and(name).is(object);
					}
				}
			}
		} catch (Exception e) {
			log.error("findUserList error : {}", e);
		}
		// 总记录数
		long count = mongoTemplate.count(Query.query(queryBuilder),
				BizConstant.CollectionNameEnum.COLLECTION_USER.getName());

		List<User> find = mongoTemplate.find(
				Query.query(queryBuilder).skip(userReq.getPageIndex() * userReq.getPageSize())
						.limit(userReq.getPageSize()).with(new Sort(Direction.DESC, userReq.getSort())),
				User.class, BizConstant.CollectionNameEnum.COLLECTION_USER.getName());

		UserResq userResq = new UserResq();
		userResq.setTotal(count);
		userResq.setUsers(find);
		return R.ok().put("data", userResq);
	}

}
