package com.orasi.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orasi.core.by.angular.FindByNG;
import com.orasi.core.interfaces.Button;
import com.orasi.core.interfaces.impl.internal.ElementFactory;
import com.orasi.utils.debugging.Highlight;

public class sandbox extends TestEnvironment{
    private OrasiDriver driver = null;
        @FindByNG(ngButtonText="Retrieve password") private Button button;
    	//@FindBy(id="form_submit") private Button button;
        @BeforeMethod( alwaysRun=true)
       public void setup() {
           // setPageURL("https://the-internet.herokuapp.com/forgot_password");
            setPageURL("https://the-internet.herokuapp.com/tables");
    		setApplicationUnderTest("Toyota");
    		setBrowserUnderTest("chrome");
    		setBrowserVersion("");
    		setOperatingSystem("windows");
    		setRunLocation("Local");
    		setTestEnvironment("");
    		setThreadDriver(true);
    		testName="Sandbox";
    		testStart(testName);
    		Sleeper.sleep(1500);
    		driver = getDriver();
    		Highlight.setDebugMode(true);
        }
        
        @Test
        public void main(){
        	TestReporter.setDebugLevel(3);
        	ElementFactory.initElements(driver,this);
        	button.highlight();
        }
        
        @AfterMethod
        public void cleanup(ITestContext testResults){
            endTest(testName, testResults);
        }
}