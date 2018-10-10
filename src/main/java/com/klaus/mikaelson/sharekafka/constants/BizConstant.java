/**
* 文件名：BizConstant.java
*
* 版本信息：
* 日期：2018年10月10日 上午9:58:20
* Copyright 版权所有 @Zhuiyi Inc 2018
*/
package com.klaus.mikaelson.sharekafka.constants;

/**
* 此类描述的是：
* @author: klauszhou@wezhuiyi.com
* @version: 2018年10月10日 上午9:58:20
*/
public final class BizConstant {
	
	
	private BizConstant() {
		
	}
	
	
	public static String author = "klaus mikaelson";
	
	
	
	public enum CollectionNameEnum {
		
		COLLECTION_USER("user"),
		COLLECTION_SYS_LOG("sysLog"),
		COLLECTION_ORDER_COMMENT("orderComment"),
		COLLECTION_ORDER("order")
		;
		private String name;
		
		CollectionNameEnum(String name) {
			this.name = name;
		}

		/**
		* name
		*
		* @return the name
		*/
		public String getName() {
			return name;
		}
		
		
		
	}
	

}
