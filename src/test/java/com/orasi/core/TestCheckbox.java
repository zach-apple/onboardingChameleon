package com.orasi.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.Checkbox;
import com.orasi.utils.TestEnvironment;


public class TestCheckbox extends TestEnvironment{
    WebDriver driver = null;
    
    @BeforeTest(groups ={"regression", "interfaces", "checkbox", "dev"})
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
	testStart("TestCheckbox");
    }
    
    @AfterTest(groups ={"regression", "interfaces", "checkbox", "dev"})
    public void close(){
	endTest("TestCheckbox");
    }

      
    @Test(groups ={"regression", "interfaces", "checkbox"}, dependsOnMethods="jsToggle")
    public void check(){
	Checkbox checkbox= getDriver().findCheckbox(By.name("checkbox"));
	checkbox.check();
	Assert.assertTrue(checkbox.isChecked());
    }
    
    @Test(groups ={"regression", "interfaces", "checkbox"}, dependsOnMethods="uncheck")
    public void checkValidate(){
	Checkbox checkbox= getDriver().findCheckbox(By.name("checkbox"));
	checkbox.check();
	Assert.assertTrue(checkbox.checkValidate(getDriver()));
    }
      
    @Test(groups ={"regression", "interfaces", "checkbox"})
    public void isChecked(){
	Checkbox checkbox= getDriver().findCheckbox(By.name("checkbox"));
	Assert.assertFalse(checkbox.isChecked());
    }
    
    @Test(groups ={"regression", "interfaces", "checkbox"}, dependsOnMethods="toggle")
    public void jsToggle(){
	Checkbox checkbox= getDriver().findCheckbox(By.name("checkbox"));
	checkbox.jsToggle(getDriver());
	Assert.assertFalse(checkbox.isChecked());
    }
    
    @Test(groups ={"regression", "interfaces", "checkbox"}, dependsOnMethods="isChecked")
    public void toggle(){
	Checkbox checkbox= getDriver().findCheckbox(By.name("checkbox"));
	checkbox.toggle();
	Assert.assertTrue(checkbox.isChecked());
    }
    
    @Test(groups ={"regression", "interfaces", "checkbox"}, dependsOnMethods="check")
    public void uncheck(){
	Checkbox checkbox= getDriver().findCheckbox(By.name("checkbox"));
	checkbox.uncheck();
	Assert.assertFalse(checkbox.isChecked());
    }
    
    @Test(groups ={"regression", "interfaces", "checkbox"}, dependsOnMethods="checkValidate")
    public void uncheckValidate(){
	Checkbox checkbox= getDriver().findCheckbox(By.name("checkbox"));
	checkbox.uncheck();
	Assert.assertTrue(checkbox.uncheckValidate(getDriver()));
    }


}
