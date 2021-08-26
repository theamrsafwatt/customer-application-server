package com.jumia.helper;

import com.jumia.utils.ApplicationLogger;

import org.mockito.Mock;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;

public abstract class BaseTest {

    @Mock
    private ApplicationLogger loggerMock;

    protected void setupTestLogger() {
        Logger logger = Logger.getLogger("TEST_CLASS");

        doAnswer(invocation -> {
            logger.log(invocation.getArgument(0, Level.class), invocation.getArgument(1, String.class));
            return null;
        }).when(loggerMock).log(any(), anyString());

        doAnswer(invocation -> {
            StringWriter stack = new StringWriter();
            invocation.getArgument(0, Exception.class).printStackTrace(new PrintWriter(stack));
            logger.log(Level.SEVERE, stack.toString());
            return null;
        }).when(loggerMock).logException(any());

        doAnswer(invocation -> {
            logger.log(Level.SEVERE, invocation.getArgument(1, String.class),invocation.getArgument(0, Exception.class));
            return null;
        }).when(loggerMock).logException(any(), anyString());
    }
}