package com.orasi.core.interfaces.impl.internal;

import static com.orasi.utils.TestReporter.logTrace;

import java.lang.reflect.Field;

import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.orasi.core.by.angular.AngularElementLocator;
import com.orasi.core.by.angular.FindByNG;
import com.orasi.exception.automation.NullDriverException;
import com.orasi.utils.OrasiDriver;

public class CustomElementLocatorFactory implements ElementLocatorFactory {
    private final OrasiDriver driver;

    public CustomElementLocatorFactory(final OrasiDriver driver) {
        if(driver == null)
            throw new NullDriverException();
        this.driver = driver;
    }

    @Override
    public ElementLocator createLocator(final Field field) {
        logTrace("Entering CustomElementLocatorFactory#createLocator");
        if (field.isAnnotationPresent(FindByNG.class)) {
            logTrace("Attempting to create Angular Element Locator");
            AngularElementLocator element = new AngularElementLocator(driver, field);
            logTrace("Successfully created Angular Element Locator");
            logTrace("Exiting CustomElementLocatorFactory#createLocator");
            return element;
        } else {
            logTrace("Attempting to create Default Element Locator");
            DefaultElementLocator element = new DefaultElementLocator(driver.getWebDriver(), field);
            logTrace("Successfully created Default Element Locator");
            logTrace("Exiting CustomElementLocatorFactory#createLocator");
            return element;
        }
    }
}