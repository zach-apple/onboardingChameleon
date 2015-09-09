package com.orasi.utils;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestAlertHandler extends TestEnvironment{
    @BeforeTest(groups ={"regression", "utils", "dev"})
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
	setPageURL("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/utils/alertHandler.html");
	testStart("TestAlert");
    }
    
    @AfterTest(groups ={"regression", "utils", "dev"})
    public void close(){
	endTest("TestAlert");
    }
    
    @Test(groups ={"regression", "utils", "dev"})
    public void isAlertPresent(){
	Assert.assertTrue(AlertHandler.isAlertPresent(getDriver(), 1));
    }
    
    @Test(groups ={"regression", "utils", "dev"}, dependsOnMethods="isAlertPresent")
    public void handleAllAlerts(){
	AlertHandler.handleAllAlerts(getDriver(), 1);
	Assert.assertFalse(AlertHandler.isAlertPresent(getDriver(), 1));
    }
    
    @Test(groups ={"regression", "utils", "dev"}, dependsOnMethods="handleAllAlerts")
    public void handleAlert(){
	getDriver().findElement(By.id("button")).click();
	AlertHandler.handleAlert(getDriver(), 1);
	Assert.assertFalse(AlertHandler.isAlertPresent(getDriver(), 1));
    }
}
