package com.orasi;

import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.utils.TestReporter;
import com.orasi.web.WebBaseTest;
import com.orasi.web.by.angular.ByNG;
import com.orasi.web.by.angular.FindByNG;
import com.orasi.web.webelements.Label;
import com.orasi.web.webelements.impl.internal.ElementFactory;

public class Sandbox extends WebBaseTest {
    @BeforeTest(groups = { "regression", "utils", "dev", "framehandler" })
    @Parameters({ "runLocation", "browserUnderTest", "browserVersion", "operatingSystem", "environment" })
    public void setup(@Optional String runLocation, String browserUnderTest, String browserVersion,
            String operatingSystem, String environment) {
        setApplicationUnderTest("Test Site");
        setBrowserUnderTest(browserUnderTest);
        setBrowserVersion(browserVersion);
        setOperatingSystem(operatingSystem);
        setRunLocation(runLocation);
        setEnvironment(environment);
        setPageURL("http://orasi.github.io/Chameleon/sites/unitTests/orasi/core/angular/angularPage.html");
        testStart("TestFrame");
    }

    @FindByNG(ngRepeater = "x in names | orderBy:'country'")
    public Label name2;

    @AfterTest(groups = { "regression", "utils", "dev" })
    public void close(ITestContext testResults) {
        endTest("TestFrame", testResults);
    }

    @Test
    public void findAndSwitchToFrameFromOutsideFrame() {
        TestReporter.setDebugLevel(3);
        Label name = getDriver().findLabel(ByNG.repeater("x in names | orderBy:'country'"));
        ElementFactory.initElements(getDriver(), this);
        name.highlight();

        name2.syncTextMatchesInElement("(.*Joe, Denmark.*)");
    }
}