package com.orasi.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.Label;
import com.orasi.core.interfaces.impl.LabelImpl;
import com.orasi.utils.TestEnvironment;

public class TestLabel extends TestEnvironment{
    
    @BeforeTest(groups ={"regression", "interfaces", "label", "dev"})
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
	setPageURL("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/core/interfaces/label.html");
	testStart("TestLabel");
    }
    
    @AfterTest(groups ={"regression", "interfaces", "label", "dev"})
    public void close(){
	endTest("TestLabel");
    }

      
    @Test(groups ={"regression", "interfaces", "label"})
    public void getFor(){
	Label label= new LabelImpl(getDriver().findElement(By.xpath("//*[@id='radioForm']/label[1]")));
	Assert.assertTrue(label.getFor().equals("genderm"));
    }

}
