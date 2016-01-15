package com.orasi.utils;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.by.angular.ByNG;
import com.orasi.core.interfaces.Textbox;

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
	setPageURL("http://google.com");
	testStart("TestAlert");
    }
    @Test
    public void test(){
	driver.executeJavaScript("window.open('http://bluesourcestaging.herokuapp.com', 'BlueSource', 'height=800,width=800');");
	WindowHandler.waitUntilWindowExistsWithTitle(driver, "BlueSource");
	System.out.println(driver.getTitle());
	driver.close();
	driver.quit();
    }
}
