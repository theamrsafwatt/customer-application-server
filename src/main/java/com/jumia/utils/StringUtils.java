package com.jumia.utils;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

/**
 * @author Amr Elbassiouni
 *
 */
@Component
public class StringUtils {

	/**
	 * Validate string against regex
	 * @param inputString
	 * @param regex
	 * @return boolean
	 */
	public boolean validateStringAgainstRegex(String inputString, String regex) {
		final Pattern pattern = Pattern.compile(regex);
	    return pattern.matcher(inputString).matches();
	}
}