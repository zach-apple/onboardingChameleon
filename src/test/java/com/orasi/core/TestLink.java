package com.orasi.core;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.Link;
import com.orasi.utils.TestEnvironment;


public class TestLink extends TestEnvironment {
    
    @BeforeTest(groups ={"regression", "interfaces", "link", "dev"})
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
	testStart("TestLink");
    }
    
    @AfterTest(groups ={"regression", "interfaces", "link", "dev"})
    public void close(){
	endTest("TestLink");
    }

    @Test(groups ={"regression", "interfaces", "link"}, dependsOnMethods="getURL")
    public void click(){
	Link link= getDriver().findLink(By.xpath("//a[@href='testLinks.html']"));
	link.click();
	Assert.assertTrue(getDriver().findElement(By.xpath("//a[@href='link.html']")).syncVisible(getDriver()));
	}
    
    @Test(groups ={"regression", "interfaces", "link"}, dependsOnMethods="click")
    public void jsClick(){
	Link link= getDriver().findLink(By.xpath("//a[@href='link.html']"));
	link.jsClick(getDriver());
	Assert.assertTrue(getDriver().findLink(By.xpath("//a[@href='testLinks.html']")).syncVisible(getDriver()));
    }
    
    @Test(groups ={"regression", "interfaces", "link"})
    public void getURL(){
	Link link= getDriver().findLink(By.xpath("//a[@href='testLinks.html']"));
	Assert.assertTrue(link.getURL().contains("testLinks.html"));
    }
}
