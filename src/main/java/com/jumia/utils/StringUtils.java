package com.jumia.utils;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class StringUtils {

	public boolean validateStringAgainstRegex(String inputString, String regex) {
		final Pattern pattern = Pattern.compile(regex);
	    return pattern.matcher(inputString).matches();
	}
}