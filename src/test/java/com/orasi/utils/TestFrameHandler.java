package com.orasi.utils;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestFrameHandler extends TestEnvironment{
    @BeforeTest(groups ={"regression", "utils", "dev", "framehandler"})
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
	setDefaultTestTimeout(3);
	setPageURL("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/utils/frameHandler.html");
	testStart("TestFrame");
    }
    
    @AfterTest(groups ={"regression", "utils", "dev", "framehandler"})
    public void close(){
	endTest("TestFrame");
    }
    
    @Test(groups ={"regression", "utils", "dev", "framehandler"})
    public void findAndSwitchToFrameFromOutsideFrame(){
	String test = FrameHandler.getCurrentFrameName(getDriver());
	FrameHandler.findAndSwitchToFrame(getDriver(), "menu_page" );
	Assert.assertTrue(getDriver().findElement(By.id("googleLink")).isDisplayed());
    }
    
    @Test(groups ={"regression", "utils", "dev", "framehandler"}, dependsOnMethods="testGetCurrentFrameName")
    public void findAndSwitchToFrameFromInsideDiffFrame(){
	FrameHandler.findAndSwitchToFrame(getDriver(), "main_page" );
	Assert.assertTrue(getDriver().findElement(By.id("button")).isDisplayed());
    }
    
    @Test(groups ={"regression", "utils", "dev", "framehandler"}, dependsOnMethods="findAndSwitchToFrameFromOutsideFrame")
    public void testGetCurrentFrameName(){
	//FrameHandler.findAndSwitchToFrame(getDriver(), "menu_page" );
	Assert.assertTrue(FrameHandler.getCurrentFrameName(getDriver()).equals("menu_page"));
    }
    
   
}
