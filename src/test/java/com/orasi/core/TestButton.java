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
import com.orasi.utils.Sleeper;
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
	setPageURL("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/core/interfaces/testsite.html");	
	setTestEnvironment(environment);
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
	Assert.assertTrue(getDriver().findElement(By.id("count")).getText().equals("VIP count: 6"));
    }
    
    @Test(groups ={"regression", "interfaces", "button"}, dependsOnMethods="click")
    public void jsClick(){
	Button button= new ButtonImpl(getDriver().findElement(By.id("Clear")));
	button.jsClick(getDriver());
	Assert.assertTrue(getDriver().findElement(By.id("count")).getText().equals("VIP count: 0"));
    }
}
