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
	setPageURL("http://www.cs.tut.fi/~jkorpela/www/testel.html");
	testStart("TestLabel");
    }
    
    @AfterTest(groups ={"regression", "interfaces", "label", "dev"})
    public void close(){
	endTest("TestLabel");
    }

      
    @Test(groups ={"regression", "interfaces", "label"})
    public void getFor(){
	Label label= new LabelImpl(getDriver().findElement(By.xpath("//form/div[4]/label")));
	Assert.assertTrue(label.getFor().equals("f1"));
    }

}
