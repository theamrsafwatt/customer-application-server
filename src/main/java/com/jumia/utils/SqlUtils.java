package com.jumia.utils;

import org.springframework.stereotype.Component;

@Component
public class SqlUtils {

	public String formMatchQueryString(String queryParam) {
		if(queryParam != null) {
			return "%" + queryParam + "%";
		} else {
			return queryParam;
		}
	}
}