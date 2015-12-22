package com.orasi.core.interfaces;

import com.orasi.core.by.angular.FindByNG;
import com.orasi.core.interfaces.impl.internal.ElementFactory;
import com.orasi.utils.TestEnvironment;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;


public class TestElementInternalHandlers extends TestEnvironment{
	@FindBy(id="click")	private Button btn1;
	@FindBys({@FindBy(id = "click"), @FindBy(id = "click")})	private Button btn2;
	@FindByNG(ngButtonText="click")	private Button btn3;
	@FindAll({@FindBy(tagName = "input"), @FindBy(tagName= "input")})	private Button btn4;

    @BeforeTest(groups ={"regression", "interfaces", "internal", "dev"})
    @Parameters({ "runLocation", "browserUnderTest", "browserVersion",
	    "operatingSystem", "environment" })
    public void setup(@Optional String runLocation, String browserUnderTest,
	    String browserVersion, String operatingSystem, String environment) {
	setApplicationUnderTest("Test App");
	setBrowserUnderTest(browserUnderTest);
	setBrowserVersion(browserVersion);
	setOperatingSystem(operatingSystem);
	setRunLocation(runLocation);
	setPageURL("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/core/interfaces/button.html");	
	setTestEnvironment(environment);
	testStart("TestButton");
	}
    
    @AfterTest(groups ={"regression", "interfaces", "internal", "dev"})
    public void close(ITestContext testResults){
	endTest("TestAlert", testResults);
    }

    @Test(groups ={"regression", "interfaces", "button"})
    public void factory(){
		ElementFactory.initElements(driver, this);
		btn1.getWrappedElement();
		btn1.click();
    }
    

}
