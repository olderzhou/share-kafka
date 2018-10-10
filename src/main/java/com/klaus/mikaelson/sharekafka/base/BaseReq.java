/**
* 文件名：BaseReq.java
*
* 版本信息：
* 日期：2018年10月10日 上午10:23:54
* Copyright 版权所有 @Zhuiyi Inc 2018
*/
package com.klaus.mikaelson.sharekafka.base;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import lombok.Data;

/**
 * 此类描述的是：
 * 
 * @author: klauszhou@wezhuiyi.com
 * @version: 2018年10月10日 上午10:23:54
 */
@Data
public class BaseReq {

	private int pageIndex = 0;

	private int pageSize = 20;

	private String sort;

	private boolean isAsc;

	public PageRequest getPageReq() {
		// 不排序
		if (StringUtils.isEmpty(this.sort)) {
			return PageRequest.of(this.pageIndex, this.pageSize);
		}
		// 需要排序，通用分页只支持单字段
		return PageRequest.of(this.pageIndex, this.pageSize,
				new Sort(this.isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, this.sort));
	}
}
