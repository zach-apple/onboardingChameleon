package com.orasi.core.by.angular.internal;

import org.openqa.selenium.WebDriverException;

/*
 * Original Code from https://github.com/paul-hammant/ngWebDriver
 */

public class VariableNotInScopeException extends WebDriverException {
    private static final long serialVersionUID = 1L;

    public VariableNotInScopeException(String msg) {
        super(msg);
    }
}
