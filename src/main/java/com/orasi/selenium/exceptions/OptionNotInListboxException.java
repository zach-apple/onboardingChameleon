package com.orasi.selenium.exceptions;

import com.orasi.selenium.OrasiDriver;
import com.orasi.selenium.WebException;

public class OptionNotInListboxException extends WebException {

    private static final long serialVersionUID = 4926417034544326093L;

    public OptionNotInListboxException(String message) {
        super(message);
    }

    public OptionNotInListboxException(String message, OrasiDriver driver) {
        super(message, driver);
    }
}
