package com.orasi.core.interfaces;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.Textbox;
import com.orasi.utils.TestEnvironment;

public class TestTextbox extends TestEnvironment{
    
    @BeforeTest(groups ={"regression", "interfaces", "textbox", "dev"})
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
	setPageURL("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/core/interfaces/textbox.html");
	testStart("TestTextbox");
    }
    
    @AfterTest(groups ={"regression", "interfaces", "textbox", "dev"})
    public void close(ITestContext testResults){
	endTest("TestAlert", testResults);
    }

    @Test(groups ={"regression", "interfaces", "textbox"})
    public void getText(){
	Textbox textbox= getDriver().findTextbox(By.id("text1"));
	Assert.assertTrue(textbox.getText().equals("Testing methods"));
    }
    
    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="getText")
    public void set(){
	Textbox textbox= getDriver().findTextbox(By.id("text1"));
	textbox.set("set");
	Assert.assertTrue(textbox.getAttribute("value").equals("set"));
    }
    
    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="set")
    public void jsSet(){
	Textbox textbox= getDriver().findTextbox(By.id("text1"));
	textbox.scrollAndSet( "setScrollIntoView");
	Assert.assertTrue(textbox.getAttribute("value").equals("setScrollIntoView"));
    }
    
    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="jsSet")
    public void clear(){
	Textbox textbox= getDriver().findTextbox(By.id("text1"));
	textbox.clear();
	Assert.assertTrue(textbox.getAttribute("value").equals(""));
    }
    
    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="clear")
    public void safeSet(){
	Textbox textbox= getDriver().findTextbox(By.id("text1"));
	textbox.safeSet("safeSet");
	Assert.assertTrue(textbox.getAttribute("value").contains("safeSet"));
	
    }
    
    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="safeSet")
    public void setSecure(){
	Textbox textbox= getDriver().findTextbox(By.id("text1"));
	//textbox.clear();
	textbox.setSecure("c2V0U2VjdXJl");
	Assert.assertTrue(textbox.getAttribute("value").contains("safeSetsetSecure"));
	textbox.clear();
    }
    
    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="setSecure")
    public void safeSetSecure(){
	Textbox textbox= getDriver().findTextbox(By.id("text1"));
//	textbox.clear();
	textbox.safeSetSecure("c2V0U2VjdXJl");
	Assert.assertTrue(textbox.getAttribute("value").contains("setSecure"));
	textbox.clear();
    }
  
}
