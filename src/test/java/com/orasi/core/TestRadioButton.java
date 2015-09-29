package com.orasi.core;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.RadioGroup;
import com.orasi.core.interfaces.impl.RadioGroupImpl;
import com.orasi.utils.TestEnvironment;

public class TestRadioButton extends TestEnvironment{
    private String xpath = "//form/fieldset[1]";
    @BeforeTest(groups ={"regression", "interfaces", "radiogroup", "dev"})
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
	testStart("TestRadiogroup");
    }

    
    @AfterTest(groups ={"regression", "interfaces", "radiogroup", "dev"})
    public void close(){
	endTest("TestRadiogroup");
    }

      
    @Test(groups ={"regression", "interfaces", "radiogroup"})
    public void getNumberOfOptions(){
	RadioGroup radiogroup = new RadioGroupImpl(getDriver().findElement(By.xpath(xpath)));
	Assert.assertTrue(radiogroup.getNumberOfOptions() == 2 );
    }
    
    @Test(groups ={"regression", "interfaces", "radiogroup"})
    public void getNumberOfRadioButtons(){
	RadioGroup radiogroup = new RadioGroupImpl(getDriver().findElement(By.xpath(xpath)));
	Assert.assertTrue(radiogroup.getNumberOfRadioButtons() == 2 );
    }

    @Test(groups ={"regression", "interfaces", "radiogroup"})
    public void getSelectedIndex(){
	RadioGroup radiogroup = new RadioGroupImpl(getDriver().findElement(By.xpath(xpath)));
	Assert.assertTrue(radiogroup.getSelectedIndex() == 1 );
    }
    
    @Test(groups ={"regression", "interfaces", "radiogroup"})
    public void getSelectedOption(){
	RadioGroup radiogroup = new RadioGroupImpl(getDriver().findElement(By.xpath(xpath)));
	Assert.assertTrue(radiogroup.getSelectedOption().equals("2") );
    }
    
    @Test(groups ={"regression", "interfaces", "radiogroup"}, dependsOnMethods="getSelectedIndex")
    public void selectByIndex(){
	RadioGroup radiogroup = new RadioGroupImpl(getDriver().findElement(By.xpath(xpath)));
	radiogroup.selectByIndex(0);
	Assert.assertTrue(radiogroup.getSelectedIndex() == 0 );
    }
    
    @Test(groups ={"regression", "interfaces", "radiogroup"}, dependsOnMethods="selectByIndex")
    public void selectByOption(){
	RadioGroup radiogroup = new RadioGroupImpl(getDriver().findElement(By.xpath(xpath)));
	radiogroup.selectByOption("2");
	Assert.assertTrue(radiogroup.getSelectedIndex() == 1 );
    }
    
    @Test(groups ={"regression", "interfaces", "radiogroup"})
    public void getAllOptions(){
	RadioGroup radiogroup = new RadioGroupImpl(getDriver().findElement(By.xpath(xpath)));
	List<String> group = radiogroup.getAllOptions();
	Assert.assertTrue(group.get(0).equals("1"));
	Assert.assertTrue(group.get(1).equals("2"));
    }
}
