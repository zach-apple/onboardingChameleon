package com.orasi;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.utils.FrameHandler;
import com.orasi.utils.TestEnvironment;

public class Sandbox extends TestEnvironment {
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
        setPageURL("http://orasi.github.io/Chameleon/sites/unitTests/orasi/utils/frameHandler.html");
        testStart("TestFrame");
    }

    @AfterTest(groups = { "regression", "utils", "dev" })
    public void close(ITestContext testResults) {
        endTest("TestFrame", testResults);
    }

    @SuppressWarnings("deprecation")
    @Test
    public void findAndSwitchToFrameFromOutsideFrame() {

        FrameHandler.findAndSwitchToFrame(getDriver(), "menu_page");
        Assert.assertTrue("Link was not found in 'menu_page'",
                getDriver().findElement(By.id("googleLink")).isDisplayed());
    }
}