/**
* 文件名：UserRepository.java
*
* 版本信息：
* 日期：2018年10月9日 下午3:57:19
* Copyright 版权所有 @Zhuiyi Inc 2018
*/
package com.klaus.mikaelson.sharekafka.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.klaus.mikaelson.sharekafka.model.mongo.User;

/**
* 此类描述的是：
* @author: klauszhou@wezhuiyi.com
* @version: 2018年10月9日 下午3:57:19
*/
public interface UserRepository extends MongoRepository<User, String>{

}
