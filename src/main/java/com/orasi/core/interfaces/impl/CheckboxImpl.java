package com.orasi.core.interfaces.impl;

import static com.orasi.utils.TestReporter.interfaceLog;
import static com.orasi.utils.TestReporter.logTrace;

import org.openqa.selenium.By;

import com.orasi.core.interfaces.Checkbox;
import com.orasi.utils.OrasiDriver;

/**
 * Wrapper class like Select that wraps basic checkbox functionality.
 */
public class CheckboxImpl extends ElementImpl implements Checkbox {
    /**
     * Wraps a WebElement with checkbox functionality.
     *
     * @param element
     *            to wrap up
     */

    public CheckboxImpl(OrasiDriver driver, By by) {
        super(driver, by);
    }

    @Override
    public void toggle() {
        logTrace("Entering CheckboxImpl#toggle");
        getWrappedElement().click();
        logTrace("Exiting CheckboxImpl#toggle");
    }

    @Override
    public void jsToggle() {
        logTrace("Entering CheckboxImpl#jsToggle");
        getWrappedDriver().executeJavaScript("if( document.createEvent ) {var click_ev = document.createEvent('MouseEvents'); click_ev.initEvent('click', true , true )"
                + ";arguments[0].dispatchEvent(click_ev);} else { arguments[0].click();}", element);
        logTrace("Exiting CheckboxImpl#jsToggle");
    }

    @Override
    public void check() {
        logTrace("Entering CheckboxImpl#check");
        if (!isChecked()) {
            try {
                toggle();
            } catch (RuntimeException rte) {
                interfaceLog(" Checking the Checkbox [ <b>" + getElementLocatorInfo() + " </b>]", true);
                logTrace("Exiting CheckboxImpl#uncheck");
                throw rte;
            }
            interfaceLog(" Checking the Checkbox [ <b>" + getElementLocatorInfo() + " </b>]");
        }
        logTrace("Exiting CheckboxImpl#check");
    }

    @Override
    public void uncheck() {
        logTrace("Entering CheckboxImpl#uncheck");
        if (isChecked()) {
            try {
                toggle();
            } catch (RuntimeException rte) {
                interfaceLog(" Unchecking the Checkbox [ <b>" + getElementLocatorInfo() + " </b>]", true);
                logTrace("Exiting CheckboxImpl#uncheck");
                throw rte;
            }

            interfaceLog(" Unchecking the Checkbox [ <b>" + getElementLocatorInfo() + " </b>]");
        }
        logTrace("Exiting CheckboxImpl#uncheck");
    }

    @Override
    public boolean isChecked() {
        logTrace("Entering CheckboxImpl#isChecked");
        boolean checked = getWrappedElement().isSelected();
        logTrace("Exiting CheckboxImpl#isChecked");
        return checked;
    }
}