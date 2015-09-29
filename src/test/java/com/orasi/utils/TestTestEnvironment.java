package com.orasi.utils;

import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.impl.ElementImpl;

public class TestTestEnvironment {
    private String application = "application";
    private String browserUnderTest = "html"; 
    private String browserVersion = "1";
    private String operatingSystem = "windows";
    private String runLocation = "local";
    private String testingEnvironment = "stage";
    private String testingName = "TestEnvironment";
    private String pageURL = "http://www.cs.tut.fi/~jkorpela/www/testel.html";
    
    @FindBy(id="f1")
    private Element firstname;
    
    @Test(groups="regression")
    public void testEnviroment(){
	TestEnvironment te = new TestEnvironment();
	Assert.assertNotNull(te);	
    }
    
    @Test(groups="regression")
    public void testEnviromentWithStringConstructor(){
   	TestEnvironment te = new TestEnvironment(application, browserUnderTest, browserVersion, operatingSystem, runLocation, testingEnvironment);
   	Assert.assertNotNull(te);
   }
    
    @Test(groups="regression")
    public void testEnviromentWithTestEnvironmentConstructor(){
   	TestEnvironment te = new TestEnvironment();
   	TestEnvironment te2 = new TestEnvironment(te);
   	
   	Assert.assertNotNull(te2);
   }
    
    @Test(groups="regression")
    public void applicationUnderTest(){
   	TestEnvironment te = new TestEnvironment();
   	te.setApplicationUnderTest(application);
   	Assert.assertTrue(te.getApplicationUnderTest().equals(application));
   }
    
    @Test(groups="regression")
    public void browserUnderTest(){
   	TestEnvironment te = new TestEnvironment();
   	te.setBrowserUnderTest(browserUnderTest);
   	Assert.assertTrue(te.getBrowserUnderTest().equals(browserUnderTest));
   }
    
    @Test(groups="regression")
    public void browserVersion(){
   	TestEnvironment te = new TestEnvironment();
   	te.setBrowserVersion(browserVersion);
   	Assert.assertTrue(te.getBrowserVersion().equals(browserVersion));
   }
    
    @Test(groups="regression")
    public void operatingSystem(){
   	TestEnvironment te = new TestEnvironment();
   	te.setOperatingSystem(operatingSystem);
   	Assert.assertTrue(te.getOperatingSystem().equals(operatingSystem));
   }

    @Test(groups="regression")
    public void runLocation(){
   	TestEnvironment te = new TestEnvironment();
   	te.setRunLocation(runLocation);
   	Assert.assertTrue(te.getRunLocation().equals(runLocation));
   }

    @Test(groups="regression")
    public void testEnvironment(){
   	TestEnvironment te = new TestEnvironment();
   	te.setTestEnvironment(testingEnvironment);
   	Assert.assertTrue(te.getTestEnvironment().equals(testingEnvironment));
   }
    
    @Test(groups="regression")
    public void testName(){
   	TestEnvironment te = new TestEnvironment();
   	te.setTestName(testingName);
   	Assert.assertTrue(te.getTestName().equals(testingName));
   }
    
    @Test(groups="regression")
    public void pageURL(){
   	TestEnvironment te = new TestEnvironment();
   	te.setPageURL(pageURL);
   	Assert.assertTrue(te.getPageURL().equals(pageURL));
   }
    
    @Test(groups="regression")
    public void testStart(){
   	TestEnvironment te = new TestEnvironment(application, browserUnderTest, browserVersion, operatingSystem, runLocation, testingEnvironment);
   	te.setPageURL(pageURL);
   	te.testStart(testingName);
   	Assert.assertTrue(te.getDriver().getTitle().equals("Test display of HTML elements"));
   }
    
    @Test(groups="regression", dependsOnMethods="testStart")
    public void pageLoaded(){
   	TestEnvironment te = new TestEnvironment(application, browserUnderTest, browserVersion, operatingSystem, runLocation, testingEnvironment);
   	te.setPageURL(pageURL);
   	te.testStart(testingName);
   	Assert.assertTrue(te.pageLoaded());
   }
    
    @Test(groups="regression", dependsOnMethods="testStart")
    public void pageLoadedWithElement(){
   	TestEnvironment te = new TestEnvironment(application, browserUnderTest, browserVersion, operatingSystem, runLocation, testingEnvironment);
   	te.setPageURL(pageURL);
   	te.testStart(testingName);
   	Assert.assertTrue(te.pageLoaded(this.getClass(), firstname));
   }
    
}
