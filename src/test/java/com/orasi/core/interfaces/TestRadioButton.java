package com.orasi.core.interfaces;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.RadioGroup;
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
	setPageURL("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/core/interfaces/radioGroup.html");
	testStart("TestRadiogroup");
    }

    
    @AfterTest(groups ={"regression", "interfaces", "radiogroup", "dev"})
    public void close(ITestContext testResults){
	endTest("TestAlert", testResults);
    }

      
    @Test(groups ={"regression", "interfaces", "radiogroup"})
    public void getNumberOfOptions(){

	RadioGroup radiogroup = getDriver().findRadioGroup(By.id("radioForm"));
	Assert.assertTrue(radiogroup.getNumberOfOptions() == 2 );
    }
    
   /* @Test(groups ={"regression", "interfaces", "radiogroup"})
    public void getNumberOfRadioButtons(){
	RadioGroup radiogroup = getDriver().findRadioGroup(By.id("radioForm"));
	Assert.assertTrue(radiogroup.getNumberOfRadioButtons() == 2 );
    }
*/
    @Test(groups ={"regression", "interfaces", "radiogroup"})
    public void getSelectedIndex(){
	RadioGroup radiogroup = getDriver().findRadioGroup(By.id("radioForm"));
	Assert.assertTrue(radiogroup.getSelectedIndex() == 1 );
    }
    
    @Test(groups ={"regression", "interfaces", "radiogroup"})
    public void getSelectedOption(){
	RadioGroup radiogroup = getDriver().findRadioGroup(By.id("radioForm"));
	Assert.assertTrue(radiogroup.getSelectedOption().equals("female") );
    }
    
    @Test(groups ={"regression", "interfaces", "radiogroup"}, dependsOnMethods="getSelectedIndex")
    public void selectByIndex(){
	RadioGroup radiogroup = getDriver().findRadioGroup(By.id("radioForm"));
	radiogroup.selectByIndex(1);
	Assert.assertTrue(radiogroup.getSelectedIndex() == 1 );
    }
    
    @Test(groups ={"regression", "interfaces", "radiogroup"}, dependsOnMethods="selectByIndex")
    public void selectByOption(){
	RadioGroup radiogroup = getDriver().findRadioGroup(By.id("radioForm"));
	radiogroup.selectByOption("male");
	Assert.assertTrue(radiogroup.getSelectedIndex() == 0 );
    }
    
    @Test(groups ={"regression", "interfaces", "radiogroup"})
    public void getAllOptions(){
	RadioGroup radiogroup = getDriver().findRadioGroup(By.id("radioForm"));
	List<String> group = radiogroup.getAllOptions();
	Assert.assertTrue(group.get(0).equals("male"));
	Assert.assertTrue(group.get(1).equals("female"));
    }
}
