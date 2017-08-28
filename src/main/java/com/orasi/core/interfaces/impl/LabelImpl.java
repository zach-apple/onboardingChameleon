package com.orasi.core.interfaces.impl;

import org.openqa.selenium.By;

import com.orasi.core.interfaces.Label;
import com.orasi.utils.OrasiDriver;

/**
 * Wraps a label on a html form with some behavior.
 */
public class LabelImpl extends ElementImpl implements Label {
    /**
     * Creates an Element for a given WebElement.
     *
     * @param element
     *            element to wrap up
     */

    public LabelImpl(OrasiDriver driver, By by) {
        super(driver, by);
    }

    @Override
    public String getFor() {
        return getWrappedElement().getAttribute("for");
    }
}