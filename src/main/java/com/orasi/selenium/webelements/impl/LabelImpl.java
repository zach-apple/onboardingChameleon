package com.orasi.selenium.webelements.impl;

import org.openqa.selenium.By;

import com.orasi.selenium.OrasiDriver;
import com.orasi.selenium.webelements.Label;

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