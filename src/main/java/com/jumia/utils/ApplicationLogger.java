package com.jumia.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Amr Elbassiouni
 *
 */
@Component
public class ApplicationLogger {

	private final Logger logger;

	@Autowired
	public ApplicationLogger(final InjectionPoint ip) {
		logger = Logger.getLogger(ip.getMember().getDeclaringClass().getName());
	}

	/**
	 * @param level
	 * @param message
	 */
	public void log(Level level, String message) {
		logger.log(level, message);
	}

	/**
	 * @param exception
	 */
	public void logException(Exception exception) {
		StringWriter stack = new StringWriter();
		exception.printStackTrace(new PrintWriter(stack));
		logger.log(Level.SEVERE, exception.getMessage(), stack);
	}
	
	/**
	 * @param exception
	 * @param message
	 */
	public void logException(Exception exception , String message) {
		StringWriter stack = new StringWriter();
		exception.printStackTrace(new PrintWriter(stack));
		logger.log(Level.SEVERE, message, stack);
	}
}
