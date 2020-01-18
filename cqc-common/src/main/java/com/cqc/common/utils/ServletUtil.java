/*   
 * Copyright (c) 2018-2040 Founder Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package com.cqc.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: ServletUtil
 * @Description: Servlet 工具类
 * @author Dragon Li
 * 
 */
public class ServletUtil {
	/**
	 * @Title: getHttpServletRequest
	 * @Description: 获取Http请求
	 * @author Dragon Li
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	/**
	 * @Description: 获取Http请求
	 * @author Dragon Li
	 * @return
	 */
	public static ServletWebRequest servletWebRequest() {
		return new ServletWebRequest(getHttpServletRequest(), getHttpServletResponse());

	}

	/**
	 * @Title: getHttpServletResponse
	 * @Description: 获取Http返回
	 * @author Dragon Li
	 * @date 2018年7月18日 下午4:15:15
	 * @return
	 */
	public static HttpServletResponse getHttpServletResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	/**
	 * @Title: getParameter
	 * @Description: 获取请求参数
	 * @author Dragon Li
	 * @param name
	 * @return
	 */
	public static String getParameter(String name) {
		return getHttpServletRequest().getParameter(name);
	}

	/**
	 * @Title: getHeader
	 * @Description: 获取请求头部信息
	 * @author Dragon Li
	 * @param name
	 * @return
	 */
	public static String getHeader(String name) {
		return getHttpServletRequest().getHeader(name);
	}

}
