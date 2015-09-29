package com.orasi.core.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.orasi.core.by.angular.AngularElementLocator;
import com.orasi.core.by.angular.FindByNG;
import com.orasi.core.interfaces.impl.internal.CustomElementLocatorFactory;

public class TestCustomElementLocatorFactory {
    @FindBy(id="null")
    private WebElement findBy;
    
    @FindByNG(ngShow="null")
    private WebElement findByNG;
    
    CustomElementLocatorFactory elementFactory = null;
    WebDriver driver = new FirefoxDriver();
    
    @BeforeTest
    private void setup(){
	elementFactory = new CustomElementLocatorFactory(driver);
    }
    
    @AfterTest
    private void teardown(){
	driver.quit();
    }
    
    @Test
    public void createDefaultLocator(){
	ElementLocator locator = getLocatorForElement("findBy");
	Assert.assertTrue( locator instanceof DefaultElementLocator);
    }
    
    
    @Test
    public void createAngularLocator(){
	ElementLocator locator = getLocatorForElement("findByNG");
	Assert.assertTrue( locator instanceof AngularElementLocator);
    }
    
    private ElementLocator getLocatorForElement(String element){
	ElementLocator locator = null;
	try {
	    locator = elementFactory.createLocator(this.getClass().getDeclaredField(element));
	} catch (NoSuchFieldException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SecurityException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	return locator;
    }
}
