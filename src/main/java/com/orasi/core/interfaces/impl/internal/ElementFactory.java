package com.orasi.core.interfaces.impl.internal;

import static com.orasi.utils.TestReporter.logTrace;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import com.orasi.exception.automation.PageInitialization;
import com.orasi.utils.OrasiDriver;

/**
 * Element factory for wrapped elements. Similar to {@link org.openqa.selenium.support.PageFactory}
 */
public class ElementFactory {

    /**
     * See {@link org.openqa.selenium.support.PageFactory#initElements(org.openqa.selenium.WebDriver driver, Class)}
     */
    public static <T> T initElements(OrasiDriver driver, Class<T> pageClassToProxy) {
        logTrace("Entering ElementFactory#initElements");
        logTrace("Creating Page Object");
        T page = instantiatePage(driver, pageClassToProxy);
        logTrace("Successfully created Page Object");
        final OrasiDriver driverRef = driver;
        logTrace("Initialize Page Elements");
        PageFactory.initElements(new ElementDecorator(new CustomElementLocatorFactory(driverRef)), page);
        logTrace("Successfully created Page Elements");
        logTrace("Exiting ElementFactory#initElements");
        return page;
    }

    /**
     * See {@link org.openqa.selenium.support.PageFactory#initElements(org.openqa.selenium.support.pagefactory.FieldDecorator, Object)}
     */
    public static void initElements(OrasiDriver driver, Object page) {
        logTrace("Entering ElementFactory#initElements");
        final OrasiDriver driverRef = driver;
        logTrace("Initialize Page Elements");
        PageFactory.initElements(new ElementDecorator(new CustomElementLocatorFactory(driverRef), driverRef), page);
        logTrace("Successfully created Page Elements");
        logTrace("Exiting ElementFactory#initElements");
    }

    /**
     * see {@link org.openqa.selenium.support.PageFactory#initElements(org.openqa.selenium.support.pagefactory.ElementLocatorFactory, Object)}
     */
    public static void initElements(CustomElementLocatorFactory factory, Object page) {
        logTrace("Entering ElementFactory#initElements");
        final CustomElementLocatorFactory factoryRef = factory;
        logTrace("Initialize Page Elements");
        PageFactory.initElements(new ElementDecorator(factoryRef), page);
        logTrace("Successfully created Page Elements");
        logTrace("Exiting ElementFactory#initElements");
    }

    /**
     * see {@link org.openqa.selenium.support.PageFactory#initElements(org.openqa.selenium.support.pagefactory.ElementLocatorFactory, Object)}
     */
    public static void initElements(FieldDecorator decorator, Object page) {
        logTrace("Entering ElementFactory#initElements");
        logTrace("Initialize Page Elements");
        PageFactory.initElements(decorator, page);
        logTrace("Successfully created Page Elements");
        logTrace("Exiting ElementFactory#initElements");
    }

    /**
     * Copy of {@link org.openqa.selenium.support.PageFactory#instantiatePage(org.openqa.selenium.WebDriver, Class)}
     */
    private static <T> T instantiatePage(WebDriver driver, Class<T> pageClassToProxy) {
        logTrace("Entering ElementFactory#instantiatePage");
        try {
            try {
                logTrace("Create Constructor of Page object");
                Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
                logTrace("Successfully created Constructor");

                logTrace("Create new instance of Page object");
                T instance = constructor.newInstance(driver);
                logTrace("Successfully created new Page instance");
                logTrace("Exiting ElementFactory#instantiatePage");
                return instance;
            } catch (NoSuchMethodException e) {
                try {
                    logTrace("Entering ElementFactory#instantiatePage");
                    return pageClassToProxy.newInstance();
                } catch (InstantiationException ie) {
                    throw new PageInitialization("Failed to create instance of: " + pageClassToProxy.getName(), driver);
                }
            }
        } catch (InstantiationException e) {
            throw new PageInitialization("Failed to create instance of: " + pageClassToProxy.getName(), driver);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}