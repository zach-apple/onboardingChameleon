package com.orasi.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.Button;
import com.orasi.core.interfaces.impl.ButtonImpl;
import com.orasi.core.interfaces.impl.ElementImpl;
import com.orasi.utils.TestEnvironment;


public class TestButton extends TestEnvironment{
    
    @BeforeTest(groups ={"regression", "interfaces", "button", "dev"})
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
	setPageURL("file:" +getClass().getClassLoader().getResource("sites/htmlTest/testApp.html").getPath());
	testStart("TestButton");
    }
    
    @AfterTest(groups ={"regression", "interfaces", "button", "dev"})
    public void close(){
	endTest("TestButton");
    }

    @Test(groups ={"regression", "interfaces", "button"})
    public void click(){
	Button button= new ButtonImpl(getDriver().findElement(By.id("Load")));
	button.click();
	Assert.assertTrue(new ElementImpl(getDriver().findElement(By.id("count"))).getText().equals("VIP count: 6"));
	new ButtonImpl(driver.findElement(By.id("Clear"))).click();
	Assert.assertTrue(new ElementImpl(getDriver().findElement(By.id("count"))).getText().equals("VIP count: 0"));
    }
    
    @Test(groups ={"regression", "interfaces", "button"})
    public void jsClick(){
	Button button= new ButtonImpl(getDriver().findElement(By.id("Load")));
	button.jsClick(getDriver());
	Assert.assertTrue(new ElementImpl(getDriver().findElement(By.id("count"))).getText().equals("VIP count: 6"));
	new ButtonImpl(driver.findElement(By.id("Clear"))).jsClick(getDriver());
	Assert.assertTrue(new ElementImpl(getDriver().findElement(By.id("count"))).getText().equals("VIP count: 0"));
    }
}
