/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2017 - 2018
 */

package com.klaus.mikaelson.sharekafka.constants;


import org.apache.http.HttpStatus;

import lombok.Getter;

/**
* @author klaus mikaelson
*/
@Getter
public enum ResultCode {
	/**
	 *  success
	 */
	OK(0,"success"),
	ERROR_400(HttpStatus.SC_BAD_REQUEST,"不好意思，错误请求"),
	ERROR_401(HttpStatus.SC_UNAUTHORIZED,"不好意思，缺少认证"),
	ERROR_402(HttpStatus.SC_PAYMENT_REQUIRED,"不好意思，缺少凭证"),
	ERROR_403(HttpStatus.SC_FORBIDDEN,"不好意思，禁止访问"),
	ERROR_404(HttpStatus.SC_NOT_FOUND ,"不好意思，资源找不到了"),
	ERROR_405(HttpStatus.SC_METHOD_NOT_ALLOWED ,"不好意思，请求方法错误"),
	ERROR_406(HttpStatus.SC_NOT_ACCEPTABLE ,"不好意思，请求不接受"),
	ERROR_407(HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED ,"不好意思，需要代理认证"),
	ERROR_408(HttpStatus.SC_REQUEST_TIMEOUT ,"不好意思，请求超时"),
	
	ERROR_500(HttpStatus.SC_INTERNAL_SERVER_ERROR,"不好意思，发生异常，处理不过来啦，快去联系追一科技/@info@weizhuiyi.com/@"),
	ERROR_502(HttpStatus.SC_BAD_GATEWAY,"网关异常"),
	ERROR_503(HttpStatus.SC_SERVICE_UNAVAILABLE,"服务不可用"),
	ERROR_504(HttpStatus.SC_GATEWAY_TIMEOUT,"网关超时")
	;
	
	private int code;
	private String msg;
	
	ResultCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
