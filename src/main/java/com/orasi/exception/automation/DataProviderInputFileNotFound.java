package com.orasi.exception.automation;

import com.orasi.exception.AutomationException;

public class DataProviderInputFileNotFound extends AutomationException{
    private static final long serialVersionUID = 8734638785785664287L;

    public DataProviderInputFileNotFound(String message) {
	super(message);
    }
}
