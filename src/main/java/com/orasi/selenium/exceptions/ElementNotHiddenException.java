package com.orasi.selenium.exceptions;

import com.orasi.selenium.OrasiDriver;
import com.orasi.selenium.WebException;

public class ElementNotHiddenException extends WebException {

    private static final long serialVersionUID = 1865273000586352087L;

    public ElementNotHiddenException(String message) {
        super(message);
    }

    public ElementNotHiddenException(String message, OrasiDriver driver) {
        super(message, driver);
    }
}
