package com.jumia.utils;

import org.springframework.stereotype.Component;

/**
 * @author Amr Elbassiouni
 *
 */
@Component
public class SqlUtils {

	/**
	 * Form match query string
	 * @param queryParam
	 * @return String
	 */
	public String formMatchQueryString(String queryParam) {
		if(queryParam != null) {
			return "%" + queryParam + "%";
		} else {
			return queryParam;
		}
	}
}