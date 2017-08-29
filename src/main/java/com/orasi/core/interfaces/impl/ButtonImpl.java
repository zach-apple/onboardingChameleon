package com.orasi.core.interfaces.impl;

import static com.orasi.utils.TestReporter.interfaceLog;
import static com.orasi.utils.TestReporter.logFailure;
import static com.orasi.utils.TestReporter.logTrace;

import org.openqa.selenium.By;

import com.orasi.core.by.angular.ByNG;
import com.orasi.core.interfaces.Button;
import com.orasi.exception.AutomationException;
import com.orasi.utils.OrasiDriver;

/**
 * Wraps a label on a html form with some behavior.
 */
public class ButtonImpl extends ElementImpl implements Button {
    /**
     * Creates a Element for a given WebElement.
     *
     * @param element
     *            - element to wrap up
     */

    public ButtonImpl(OrasiDriver driver, By by) {
        super(driver, by);
    }

    public ButtonImpl(OrasiDriver driver, ByNG by) {
        super(driver, by);
    }

    @Override
    public void click() {
        logTrace("Entering ButtonImpl#click");
        try {
            logTrace("Attempting to invoke method [ Click ] on element [ " + by.toString() + " ] ");
            getWrappedElement().click();
        } catch (RuntimeException rte) {
            interfaceLog("Clicked Button [ <b>" + getElementLocatorInfo() + "</b>]", true);
            throw rte;
        }

        interfaceLog("Clicked Button [ <b>" + getElementLocatorInfo() + "</b>]");
        logTrace("Successfully invoked method [ Click ] on element [ " + by.toString() + " ] ");
        logTrace("Exiting ButtonImpl#click");
    }

    @Override
    public void jsClick() {
        logTrace("Entering ButtonImpl#jsClick");
        try {
            logTrace("Attempting to executed [ jsClick ] on element [ " + by.toString() + " ] ");
            getWrappedDriver().executeJavaScript("arguments[0].click();", getWrappedElement());
            logTrace("Successfully executed [ jsClick ] on element [ " + by.toString() + " ] ");
        } catch (RuntimeException rte) {
            logFailure("Clicked Button [ <b>" + getElementLocatorInfo() + "</b>]");
            logTrace("Failed to execute [ jsClick ] on element [ " + by.toString() + " ] ");
            logTrace("Exiting ButtonImpl#jsClick");
            throw new AutomationException(rte.getMessage(), driver);
        }
        interfaceLog("Clicked Button [ <b>" + getElementLocatorInfo() + "</b>]");
        logTrace("Exiting ButtonImpl#jsClick");

    }
}