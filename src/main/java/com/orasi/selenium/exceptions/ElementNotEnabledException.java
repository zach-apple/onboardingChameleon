package com.orasi.selenium.exceptions;

import com.orasi.selenium.OrasiDriver;
import com.orasi.selenium.WebException;

public class ElementNotEnabledException extends WebException {

    private static final long serialVersionUID = 6579447002670243452L;

    public ElementNotEnabledException(String message) {
        super(message);
    }

    public ElementNotEnabledException(String message, OrasiDriver driver) {
        super(message, driver);
    }
}
