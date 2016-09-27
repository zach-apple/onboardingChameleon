package com.orasi.utils;

import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orasi.utils.debugging.Highlight;

public class sandbox extends TestEnvironment{
    private OrasiDriver driver = null;
        
        @BeforeMethod( alwaysRun=true)
       public void setup() {
            setPageURL("http://toyota-oss:changeit@origin.staging.toyota.com/local-specials");
    		setApplicationUnderTest("Toyota");
    		setBrowserUnderTest("firefox");
    		setBrowserVersion("");
    		setOperatingSystem("windows");
    		setRunLocation("Local");
    		setTestEnvironment("");
    		setThreadDriver(true);
    		testName="Sandbox";
    		testStart(testName);
    		driver = getDriver();
    		Highlight.setDebugMode(true);
        }
        
        @Test
        public void main(){

           driver.findTextbox(By.className("tcom-zipcode-changer-input")).syncEnabled();
           driver.findTextbox(By.className("tcom-zipcode-changer-input")).set("27409");
           driver.findButton(By.className("tcom-submit")).click();

           driver.findElement(By.className("tcom-filter-group-header")).syncEnabled();
           driver.findCheckbox(By.xpath("//div[@data-path='Cars & Minivan']/.//input[@value='camry']/..")).syncVisible();
           driver.findCheckbox(By.xpath("//div[@data-path='Cars & Minivan']/.//input[@value='camry']/..")).jsClick();
           

           driver.findCheckbox(By.xpath("//div[@data-path='model-year']/.//input[@value='2016']/..")).syncVisible();
           driver.findCheckbox(By.xpath("//div[@data-path='model-year']/.//input[@value='2016']/..")).click();
           System.out.println();
        }
        
        @AfterMethod
        public void cleanup(ITestContext testResults){
            endTest(testName, testResults);
        }
}