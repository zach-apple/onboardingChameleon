package com.orasi.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;

import com.saucelabs.saucerest.SauceREST;

/**
 * 
 * @summary Contains the methods to be used within the test class testNG annotated methods.
 * 			This allows the test class to become much cleaner and more organized.
 * @author Jonathan Doll and Waightstill W Avery
 * @date April 7, 2015
 *
 */
public class TestNgTestClassMethods extends TestEnvironment{
	/*
	 * Constructor that invokes the TestEnvironment super class
	 */
	public TestNgTestClassMethods(String application, TestEnvironment te) {
		super(te);
	}

	/**
	 * @summary testNG "before" method (http://testng.org/doc/documentation-main.html#annotations)
	 * 			This will be used to absorb testNG XML parameters and pass them to the TestEnvironment
	 * 			instance that is extended or implemented by all page and test classes.  Also contains 
	 * 			functionality that is used to update SauceLabs tests results in the SauceLabs VM farm
	 * @param test - testNG variable which contains the results of the previously run test
	 * @param driver - the WebDriver used by the test
	 * @return N/A
	 */
	@SuppressWarnings("unchecked")
	public void after_sauceLabs(ITestResult test, WebDriver driver) {
		Map<String, Object> updates = new HashMap<String, Object>();
		updates.put("name", test.getMethod().getMethodName());

		// if is a failure, then take a screenshot
		if (test.getStatus() == ITestResult.FAILURE) {
			new Screenshot().takeScreenShot(test, driver);
			updates.put("passed", false);
		} else {
			updates.put("passed", true);
		}

		JSONArray tags = new JSONArray();
		String[] groups = test.getMethod().getGroups();
		for (int x = 0; x < groups.length; x++) {
			tags.add(groups[x]);
		}
		updates.put("tags", tags);

		if (runLocation.equalsIgnoreCase("remote")) {
			SauceREST client = new SauceREST(
					Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_USERNAME")),
					Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_KEY")));
			client.updateJobInfo(((RemoteWebDriver) driver).getSessionId().toString(), updates);
			System.out.println(client.getJobInfo(((RemoteWebDriver) driver).getSessionId().toString()));
		}

		if (driver != null && driver.getWindowHandles().size() > 0) {
			driver.quit();
		}
	}	
	
	/**
	 * @summary testNG "before" method (http://testng.org/doc/documentation-main.html#annotations)
	 * 			This will be used to absorb testNG XML parameters and pass them to the TestEnvironment
	 * 			instance that is extended or implemented by all page and test classes
	 * @param te
	 */
	public void after(ITestResult test, WebDriver driver) {
		Map<String, Object> updates = new HashMap<String, Object>();
		updates.put("name", test.getMethod().getMethodName());

		// if is a failure, then take a screenshot
		if (test.getStatus() == ITestResult.FAILURE) {
			new Screenshot().takeScreenShot(test, driver);
		}

		if (driver != null && driver.getWindowHandles().size() > 0) {
			driver.quit();
		}
	}

	public WebDriver testStart(String testName, TestEnvironment te) throws InterruptedException,
			IOException {
		// Uncomment the following line to have TestReporter outputs output to
		// the console
		TestReporter.setPrintToConsole(true);

		WebDriver driver = initialize();

		System.out.println(testName);
		this.drivers.put(testName, driver);

		return this.drivers.get(testName);
	}
}
