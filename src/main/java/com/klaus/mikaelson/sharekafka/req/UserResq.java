/**
* 文件名：UserResq.java
*
* 版本信息：
* 日期：2018年10月10日 上午11:02:31
* Copyright 版权所有 @Zhuiyi Inc 2018
*/
package com.klaus.mikaelson.sharekafka.req;

import java.util.List;

import com.klaus.mikaelson.sharekafka.model.mongo.User;

import lombok.Data;

/**
* 此类描述的是：
* @author: klauszhou@wezhuiyi.com
* @version: 2018年10月10日 上午11:02:31
*/
@Data
public class UserResq {
	private Long total;
	
	private List<User> users;
	
	

}
