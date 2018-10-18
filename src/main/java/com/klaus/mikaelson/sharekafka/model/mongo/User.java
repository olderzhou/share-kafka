/**
* 文件名：User.java
*
* 版本信息：
* 日期：2018年10月9日 下午3:58:21
* Copyright 版权所有 @Zhuiyi Inc 2018
*/
package com.klaus.mikaelson.sharekafka.model.mongo;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
* 此类描述的是：
* @author: klauszhou@wezhuiyi.com
* @version: 2018年10月9日 下午3:58:21
*/


@Data
@Document(collection="user")
public class User {
	
	@Id
	private String id;
	
	private String username;
	
	
	@Indexed
	private String introduction;
	
	private Byte gender;
	
	private int age;
	
	private Date birthday;
	
	private String headUrl;
	
	private String weiboUrl;
	
	private String jianshuUrl;
	
	private String githubUrl;

	private String twitterUrl;
	
	@GeoSpatialIndexed
    private double[] location;
	
	/**
	 * location is stored in GeoJSON format.
	 * {
	 *   "type" : "Point",
	 *   "coordinates" : [ x, y ]
	 * }
	 */
	GeoJsonPoint position;
	
	private Long createTimestamp;
	
    @CreatedDate
    private Date createDate;

    @LastModifiedDate
    private Date lastModifiedDate;
	
	
	

}
