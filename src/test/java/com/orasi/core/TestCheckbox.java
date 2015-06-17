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

import com.orasi.core.interfaces.Checkbox;
import com.orasi.core.interfaces.impl.CheckboxImpl;
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
	setPageURL("file:" +getClass().getClassLoader().getResource("sites/htmlTest/testApp.html").getPath());
	testStart("TestCheckbox");
    }
    
    @AfterTest(groups ={"regression", "interfaces", "checkbox", "dev"})
    public void close(){
	endTest("TestCheckbox");
    }

      
    @Test(groups ={"regression", "interfaces", "checkbox"}, dependsOnMethods="jsToggle")
    public void check(){
	Checkbox checkbox= new CheckboxImpl(getDriver().findElement(By.name("Checkbox")));
	checkbox.check();
	Assert.assertTrue(checkbox.isChecked());
    }
    
    @Test(groups ={"regression", "interfaces", "checkbox"}, dependsOnMethods="uncheck")
    public void checkValidate(){
	Checkbox checkbox= new CheckboxImpl(getDriver().findElement(By.name("Checkbox")));
	checkbox.check();
	Assert.assertTrue(checkbox.checkValidate(getDriver()));
    }
      
    @Test(groups ={"regression", "interfaces", "checkbox"})
    public void isChecked(){
	Checkbox checkbox= new CheckboxImpl(getDriver().findElement(By.name("Checkbox")));
	Assert.assertFalse(checkbox.isChecked());
    }
    
    @Test(groups ={"regression", "interfaces", "checkbox"}, dependsOnMethods="toggle")
    public void jsToggle(){
	Checkbox checkbox= new CheckboxImpl(getDriver().findElement(By.name("Checkbox")));
	checkbox.jsToggle(getDriver());
	Assert.assertFalse(checkbox.isChecked());
    }
    
    @Test(groups ={"regression", "interfaces", "checkbox"}, dependsOnMethods="isChecked")
    public void toggle(){
	Checkbox checkbox= new CheckboxImpl(getDriver().findElement(By.name("Checkbox")));
	checkbox.toggle();
	Assert.assertTrue(checkbox.isChecked());
    }
    
    @Test(groups ={"regression", "interfaces", "checkbox"}, dependsOnMethods="check")
    public void uncheck(){
	Checkbox checkbox= new CheckboxImpl(getDriver().findElement(By.name("Checkbox")));
	checkbox.uncheck();
	Assert.assertFalse(checkbox.isChecked());
    }
    
    @Test(groups ={"regression", "interfaces", "checkbox"}, dependsOnMethods="checkValidate")
    public void uncheckValidate(){
	Checkbox checkbox= new CheckboxImpl(getDriver().findElement(By.name("Checkbox")));
	checkbox.uncheck();
	Assert.assertTrue(checkbox.uncheckValidate(getDriver()));
    }


}
