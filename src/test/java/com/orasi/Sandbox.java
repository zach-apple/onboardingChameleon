package com.orasi;

import org.testng.annotations.Test;

import com.orasi.utils.date.DateTimeConversion;

public class Sandbox {
    /*
     * @BeforeTest(groups = { "regression", "utils", "dev", "framehandler" })
     *
     * @Parameters({ "runLocation", "browserUnderTest", "browserVersion", "operatingSystem", "environment" })
     * public void setup(@Optional String runLocation, String browserUnderTest, String browserVersion,
     * String operatingSystem, String environment) {
     * setApplicationUnderTest("Test Site");
     * setBrowserUnderTest(browserUnderTest);
     * setBrowserVersion(browserVersion);
     * setOperatingSystem(operatingSystem);
     * setRunLocation(runLocation);
     * setEnvironment(environment);
     * setPageURL("http://orasi.github.io/Chameleon/sites/unitTests/orasi/core/angular/angularPage.html");
     * testStart("TestFrame");
     * }
     *
     * @FindByNG(ngRepeater = "x in names | orderBy:'country'")
     * public Label name2;
     *
     * @AfterTest(groups = { "regression", "utils", "dev" })
     * public void close(ITestContext testResults) {
     * endTest("TestFrame", testResults);
     * }
     */

    @Test
    public void findAndSwitchToFrameFromOutsideFrame() {
        System.out.println(DateTimeConversion.convert("10/11/2017", "MM/dd/yyyy", "MMM dd, yyyy"));
    }
}