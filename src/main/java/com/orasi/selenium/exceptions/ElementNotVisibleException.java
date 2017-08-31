package com.orasi.selenium.exceptions;

import com.orasi.selenium.OrasiDriver;
import com.orasi.selenium.WebException;

public class ElementNotVisibleException extends WebException {
    private static final long serialVersionUID = 7724792038612608062L;

    public ElementNotVisibleException(String message) {
        super(message);
    }

    public ElementNotVisibleException(String message, OrasiDriver driver) {
        super(message, driver);
    }
}
