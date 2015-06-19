package com.orasi.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.Listbox;
import com.orasi.core.interfaces.impl.ListboxImpl;
import com.orasi.utils.TestEnvironment;

public class TestListbox extends TestEnvironment{
    WebDriver driver = null;
    private String multiSelectXpath = "//*[@id='page']/div[2]/div/select";
    private String listboxXpath = "//*[@id='para1']/select";
    @BeforeTest(groups ={"regression", "interfaces", "listbox", "dev"})
    @Parameters({ "runLocation", "browserUnderTest", "browserVersion",
	    "operatingSystem", "environment" })
    public void setup(@Optional String runLocation, String browserUnderTest,
	    String browserVersion, String operatingSystem, String environment) {
	setApplicationUnderTest("Test Site");
	setBrowserUnderTest(browserUnderTest);
	setBrowserVersion(browserVersion);
	setOperatingSystem(operatingSystem);
	setRunLocation(runLocation);
	setTestEnvironment(environment);
	setPageURL("http://www.allthingsdiscussed.com/More/How-to-create-a-list-box-in-HTML");
	testStart("TestListbox");
    }
    
    @AfterTest(groups ={"regression", "interfaces", "listbox", "dev"})
    public void close(){
	endTest("TestListbox");
    }

      
    @Test(groups ={"regression", "interfaces", "listbox"})
    public void isMultiple(){
	Listbox listbox= new ListboxImpl(getDriver().findElement(By.xpath(multiSelectXpath)));
	Assert.assertTrue(listbox.isMultiple());
    }
    
    @Test(groups ={"regression", "interfaces", "listbox"})
    public void select(){
	Listbox listbox= new ListboxImpl(getDriver().findElement(By.xpath(listboxXpath)));
	listbox.select("Mastercard");
	Assert.assertTrue(listbox.getFirstSelectedOption().getText().equals("Mastercard"));
    }
    
    @Test(groups ={"regression", "interfaces", "listbox"}, dependsOnMethods="select")
    public void getAllSelectedOptions(){
	Listbox listbox= new ListboxImpl(getDriver().findElement(By.xpath(listboxXpath)));
	Assert.assertTrue(listbox.getAllSelectedOptions().get(0).getText().equals("Mastercard"));
    }
    
    @Test(groups ={"regression", "interfaces", "listbox"}, dependsOnMethods="getAllSelectedOptions")
    public void isSelected(){
	Listbox listbox= new ListboxImpl(getDriver().findElement(By.xpath(listboxXpath)));
	Assert.assertTrue(listbox.isSelected("Mastercard"));
    }
    
    @Test(groups ={"regression", "interfaces", "listbox"}, dependsOnMethods="isSelected")
    public void deselectByVisibleText(){
	Listbox listbox= new ListboxImpl(getDriver().findElement(By.xpath(multiSelectXpath)));
	listbox.select("American Express");
	Assert.assertTrue(listbox.isSelected("American Express"));
	listbox.deselectByVisibleText("American Express");
	Assert.assertFalse(listbox.isSelected("American Express"));
    }
    
    @Test(groups ={"regression", "interfaces", "listbox"}, dependsOnMethods="deselectByVisibleText")
    public void deselectAll(){
	Listbox listbox= new ListboxImpl(getDriver().findElement(By.xpath(multiSelectXpath)));
	listbox.select("Mastercard");
	listbox.deselectAll();
	Assert.assertNull(listbox.getFirstSelectedOption());
    }
    
}
