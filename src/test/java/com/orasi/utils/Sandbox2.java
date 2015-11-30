package com.orasi.utils;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.angular.ByAngular;
import com.orasi.core.by.angular.ByNG;
import com.orasi.core.interfaces.Textbox;
import com.orasi.core.interfaces.impl.ElementImpl;

public class Sandbox2 extends TestEnvironment{

    @BeforeTest(groups ={"regression", "utils", "dev"})
    @Parameters({ "runLocation", "browserUnderTest", "browserVersion",
	    "operatingSystem", "environment" })
    public void setup() {
	setApplicationUnderTest("Test Site");
	setBrowserUnderTest("firefox");
	setBrowserVersion("");
	setOperatingSystem("windows");
	setRunLocation("local");
	setTestEnvironment("");
	setPageURL("http://bluesourcestaging.herokuapp.com");
	testStart("TestAlert");
    }
    @Test
    public void test(){
	TestReporter.setPrintToConsole(true);
	System.out.println(driver.getDriverCapability().browserName());
	System.out.println(driver.getDriverCapability().browserVersion());
	System.out.println(driver.getDriverCapability().platformOS());
	
	Textbox username = driver.findTextbox(By.id("employee_username"));
	username.set("company.admin");
	
	//new ElementImpl(getDriver().findElement(By.id("employee_username"))).sendKeys("TESTING");
	driver.findTextbox(By.name("employee[password]")).syncEnabled();
	driver.findTextbox(By.name("employee[password]")).set("test");
	
	driver.findButton(ByNG.buttonText("Login")).click();
	
	driver.quit();
    }
}
