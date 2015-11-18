package com.orasi.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.Listbox;
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
	setPageURL("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/core/interfaces/listbox.html");
	testStart("TestListbox");
    }
    
    @AfterTest(groups ={"regression", "interfaces", "listbox", "dev"})
    public void close(ITestContext testResults){
	endTest("TestAlert", testResults);
    }

      
    @Test(groups ={"regression", "interfaces", "listbox"})
    public void isMultiple(){
	Listbox listbox= getDriver().findListbox(By.id("multiSelect"));
	Assert.assertTrue(listbox.isMultiple());
    }
    
    @Test(groups ={"regression", "interfaces", "listbox"})
    public void select(){
	Listbox listbox= getDriver().findListbox(By.id("singleSelect"));
	listbox.select("Sports");
	Assert.assertTrue(listbox.getFirstSelectedOption().getText().equals("Sports"));
    }
    
    @Test(groups ={"regression", "interfaces", "listbox"}, dependsOnMethods="select")
    public void getAllSelectedOptions(){
	Listbox listbox= getDriver().findListbox(By.id("singleSelect"));
	Assert.assertTrue(listbox.getAllSelectedOptions().get(0).getText().equals("Sports"));
    }
    
    @Test(groups ={"regression", "interfaces", "listbox"}, dependsOnMethods="getAllSelectedOptions")
    public void isSelected(){
	Listbox listbox= getDriver().findListbox(By.id("singleSelect"));
	Assert.assertTrue(listbox.isSelected("Sports"));
    }
    
    @Test(groups ={"regression", "interfaces", "listbox"}, dependsOnMethods="isSelected")
    public void deselectByVisibleText(){
	Listbox listbox= getDriver().findListbox(By.id("multiSelect"));
	listbox.select("Baseball");
	Assert.assertTrue(listbox.isSelected("Baseball"));
	listbox.deselectByVisibleText("Baseball");
	Assert.assertFalse(listbox.isSelected("Baseball"));
    }
    
    @Test(groups ={"regression", "interfaces", "listbox"}, dependsOnMethods="deselectByVisibleText")
    public void deselectAll(){
	Listbox listbox= getDriver().findListbox(By.id("multiSelect"));
	listbox.select("Basketball");
	listbox.select("Soccer");
	listbox.deselectAll();
	Assert.assertNull(listbox.getFirstSelectedOption());
    }
    
}
