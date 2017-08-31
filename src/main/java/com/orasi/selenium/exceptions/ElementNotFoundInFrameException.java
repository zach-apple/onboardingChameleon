package com.orasi.selenium.exceptions;

import com.orasi.selenium.OrasiDriver;
import com.orasi.selenium.WebException;

public class ElementNotFoundInFrameException extends WebException {
    private static final long serialVersionUID = 624614577584686540L;

    public ElementNotFoundInFrameException(String message) {
        super(message);
    }

    public ElementNotFoundInFrameException(String message, OrasiDriver driver) {
        super(message, driver);
    }
}
