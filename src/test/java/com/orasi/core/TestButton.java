package com.orasi.core;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.Button;
import com.orasi.utils.TestEnvironment;


public class TestButton extends TestEnvironment{
    
    @BeforeTest(groups ={"regression", "interfaces", "button", "dev"})
    @Parameters({ "runLocation", "browserUnderTest", "browserVersion",
	    "operatingSystem", "environment" })
    public void setup(@Optional String runLocation, String browserUnderTest,
	    String browserVersion, String operatingSystem, String environment) {
	setApplicationUnderTest("Test App");
	setBrowserUnderTest(browserUnderTest);
	setBrowserVersion(browserVersion);
	setOperatingSystem(operatingSystem);
	setRunLocation(runLocation);
	setPageURL("http://www.cs.tut.fi/~jkorpela/www/testel.html");	
	setTestEnvironment(environment);
	testStart("TestButton");
	}
    
    @AfterTest(groups ={"regression", "interfaces", "button", "dev"})
    public void close(){
	endTest("TestButton");
    }

    @Test(groups ={"regression", "interfaces", "button"})
    public void click(){
	Button button= getDriver().findButton(By.id("click"));
	button.click();
	Assert.assertTrue(getDriver().findElement(By.id("f1")).getAttribute("value").equals("Default text."));
    }
    
    @Test(groups ={"regression", "interfaces", "button"}, dependsOnMethods="click")
    public void jsClick(){
	Button button= getDriver().findButton(By.id("jsClick"));
	button.jsClick(getDriver());
	Assert.assertTrue(getDriver().findButton(By.id("testJsClick")).getText().equals("Successful"));
    }
}
