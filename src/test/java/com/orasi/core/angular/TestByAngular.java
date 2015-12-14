package com.orasi.core.angular;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.by.angular.ByNG;
import com.orasi.core.by.angular.WaitForAngularRequestsToFinish;
import com.orasi.core.by.angular.internal.ByAngular;
import com.orasi.core.by.angular.internal.ByAngularButtonText;
import com.orasi.core.by.angular.internal.ByAngularController;
import com.orasi.core.by.angular.internal.ByAngularModel;
import com.orasi.core.by.angular.internal.ByAngularRepeater;
import com.orasi.utils.Sleeper;
import com.orasi.utils.TestEnvironment;

public class TestByAngular extends TestEnvironment{
	@BeforeTest(groups ={"regression", "interfaces", "ByAngularModel", "dev"})
    @Parameters({ "runLocation", "browserUnderTest", "browserVersion",
	    "operatingSystem", "environment" })
    public void setup(@Optional String runLocation, String browserUnderTest,
	    String browserVersion, String operatingSystem, String environment) {
		
		setApplicationUnderTest("Test App");
		setBrowserUnderTest(browserUnderTest);
		setBrowserVersion(browserVersion);
		setOperatingSystem(operatingSystem);
		if(getBrowserUnderTest().toLowerCase().equals("html") || getBrowserUnderTest().isEmpty() ) throw new SkipException("Test not valid for HTMLUnitDriver");
		
		setRunLocation(runLocation);
		setPageURL("http://cafetownsend-angular-rails.herokuapp.com/login");	
		setTestEnvironment(environment);
		testStart("TestByAngularModel");
	}
    
    @AfterTest(groups ={"regression", "interfaces", "ByAngularModel", "dev"})
    public void close(ITestContext testResults){
	endTest("TestAlert", testResults);
    }


    @Test(groups ={"regression", "interfaces", "ByAngularModel"})
    public void byAngular(){
    	ByAngular angular = new ByAngular(driver);
    	Assert.assertNotNull(angular);
    }
    
 
    @Test(groups ={"regression", "interfaces", "ByAngularModel"})
    public void byAngularModel(){
    	ByAngularModel model = new ByAngularModel((JavascriptExecutor)driver.getDriver(), "user.name");
    	Assert.assertNotNull(model);
    }
    

    @Test(groups ={"regression", "interfaces", "ByAngularModel"})
    public void byAngularButtonText(){
    	ByAngularButtonText buttonText = new ByAngularButtonText((JavascriptExecutor)driver.getDriver(), "Login");
    	Assert.assertNotNull(buttonText);
    	driver.findTextbox(ByNG.model("user.name")).set("Luke");
    	driver.findTextbox(ByNG.model("user.password")).set("Skywalker");
    	driver.findTextbox(ByNG.buttonText("Login")).click();
    	
    }
    
    
    @Test(groups={"regression", "interfaces", "WaitForAngularRequestsToFinish"}, dependsOnMethods="byAngularButtonText")
    public void waitForAngularRequestsToFinish(){
    	WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish(driver);
    }
    
    @Test(groups ={"regression", "interfaces", "byAngularController"}, dependsOnMethods="byAngularButtonText")
    public void byAngularController(){
    	ByAngularController controller = new ByAngularController((JavascriptExecutor)driver.getDriver(), "HeaderController");
    	Assert.assertNotNull(controller);
    }
    
    
    @Test(groups ={"regression", "interfaces", "byAngularController"}, dependsOnMethods="byAngularButtonText")
    public void byAngularRepeater(){
    	ByAngularRepeater repeat = new ByAngularRepeater((JavascriptExecutor)driver.getDriver(), "employee in employees");
    	Assert.assertNotNull(repeat);
    }
    
    
}
