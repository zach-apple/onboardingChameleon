package com.orasi.utils;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.*;

public class TestAlertHandler extends TestEnvironment {
	@BeforeTest(groups = { "regression", "utils", "dev", "framehandler" })
	@Parameters({ "runLocation", "browserUnderTest", "browserVersion", "operatingSystem", "environment" })
	public void setup(@Optional String runLocation, String browserUnderTest, String browserVersion,
			String operatingSystem, String environment) {
		setApplicationUnderTest("Test Site");
		setBrowserUnderTest(browserUnderTest);
		setBrowserVersion(browserVersion);
		setOperatingSystem(operatingSystem);
		setRunLocation(runLocation);
		setTestEnvironment(environment);
		if(getBrowserUnderTest().toLowerCase().equals("html") || getBrowserUnderTest().isEmpty() ) throw new SkipException("Test not valid for HTMLUnitDriver");

		setDefaultTestTimeout(3);
		setPageURL("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/utils/alertHandler.html");
		testStart("TestAlert");
	}

	@AfterTest(groups = { "regression", "utils", "dev" })
	public void close(ITestContext testResults) {
		endTest("TestAlert", testResults);
	}

	@Test(groups ={"regression", "interfaces", "textbox"})
	public void constructorWithElement(){
		Assert.assertNotNull((new AlertHandler()));
	}

	@Test(groups = { "regression", "utils", "dev", "alertHandler" })
	public void isAlertPresent() {
		Assert.assertTrue("Alert was not present", AlertHandler.isAlertPresent(driver,3));
	}

	@Test(groups = { "regression", "utils", "dev", "alertHandler" },
		  dependsOnMethods = "isAlertPresent")
	public void handleAllAlerts() {
		AlertHandler.handleAllAlerts(driver,3);
		Assert.assertFalse("Alert was not handled", AlertHandler.isAlertPresent(driver,3));
	}

	@Test(groups = { "regression", "utils", "dev", "alertHandler" },
			dependsOnMethods = "handleAllAlerts")
	public void handleAlert() {
		driver.findElement(By.id("button")).click();
		AlertHandler.handleAlert(driver,3);
		Assert.assertFalse("Alert was not handled", AlertHandler.isAlertPresent(driver,3));
	}


}
