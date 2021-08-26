package com.jumia.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationLogger {

	private final Logger logger;

	@Autowired
	public ApplicationLogger(final InjectionPoint ip) {
		logger = Logger.getLogger(ip.getMember().getDeclaringClass().getName());
	}

	public void log(Level level, String message) {
		logger.log(level, message);
	}

	public void logException(Exception exception) {
		StringWriter stack = new StringWriter();
		exception.printStackTrace(new PrintWriter(stack));
		logger.log(Level.SEVERE, exception.getMessage(), stack);
	}
	
	public void logException(Exception exception , String message) {
		StringWriter stack = new StringWriter();
		exception.printStackTrace(new PrintWriter(stack));
		logger.log(Level.SEVERE, message, stack);
	}
}
