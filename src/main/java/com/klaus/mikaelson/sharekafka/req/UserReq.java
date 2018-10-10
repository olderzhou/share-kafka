/**
* 文件名：UserReq.java
*
* 版本信息：
* 日期：2018年10月10日 上午10:19:07
* Copyright 版权所有 @Zhuiyi Inc 2018
*/
package com.klaus.mikaelson.sharekafka.req;

import com.klaus.mikaelson.sharekafka.base.BaseReq;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 此类描述的是：
 * 
 * @author: klauszhou@wezhuiyi.com
 * @version: 2018年10月10日 上午10:19:07
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UserReq extends BaseReq {

	private String username;
	private int age;

}
