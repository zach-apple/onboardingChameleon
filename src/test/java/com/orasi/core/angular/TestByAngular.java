package com.orasi.core.angular;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.orasi.web.WebBaseTest;
import com.orasi.web.by.angular.ByNG;
import com.orasi.web.by.angular.WaitForAngularRequestsToFinish;
import com.orasi.web.by.angular.internal.ByAngular;
import com.orasi.web.by.angular.internal.ByAngularButtonText;
import com.orasi.web.by.angular.internal.ByAngularController;
import com.orasi.web.by.angular.internal.ByAngularModel;
import com.orasi.web.by.angular.internal.ByAngularRepeater;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

public class TestByAngular extends WebBaseTest {
    @BeforeTest(groups = { "regression", "interfaces", "ByAngularModel", "dev" })
    public void setup() {
        if (getBrowserUnderTest().toLowerCase().equals("html") || getBrowserUnderTest().isEmpty()) {
            throw new SkipException("Test not valid for HTMLUnitDriver");
        }

        setPageURL("http://cafetownsend-angular-rails.herokuapp.com/login");
        testStart("TestByAngularModel");
    }

    @AfterTest(groups = { "regression", "interfaces", "ByAngularModel", "dev" })
    public void close(ITestContext testResults) {
        endTest("TestAlert", testResults);
    }

    @Features("Element Interfaces")
    @Stories("Angular")
    @Title("constructor")
    @Test(groups = { "regression", "interfaces", "ByAngularModel" })
    public void byAngular() {
        ByAngular angular = new ByAngular(getDriver());
        Assert.assertNotNull(angular);
    }

    @Features("Element Interfaces")
    @Stories("Angular")
    @Title("Model")
    @Test(groups = { "regression", "interfaces", "ByAngularModel" })
    public void byAngularModel() {
        ByAngularModel model = new ByAngularModel((JavascriptExecutor) getDriver().getWebDriver(), "user.name");
        Assert.assertNotNull(model);
    }

    @Features("Element Interfaces")
    @Stories("Angular")
    @Title("Model")
    @Test(groups = { "regression", "interfaces", "ByAngularModel" })
    public void byAngularButtonText() {
        ByAngularButtonText buttonText = new ByAngularButtonText((JavascriptExecutor) getDriver().getWebDriver(), "Login");
        Assert.assertNotNull(buttonText);
        getDriver().findTextbox(ByNG.model("user.name")).set("Luke");
        getDriver().findTextbox(ByNG.model("user.password")).set("Skywalker");
        getDriver().findTextbox(ByNG.buttonText("Login")).click();

    }

    @Features("Element Interfaces")
    @Stories("Angular")
    @Title("waitForAgnularRequestsToFinish")
    @Test(groups = { "regression", "interfaces", "WaitForAngularRequestsToFinish" }, dependsOnMethods = "byAngularButtonText")
    public void waitForAngularRequestsToFinish() {
        WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish(getDriver());
    }

    @Features("Element Interfaces")
    @Stories("Angular")
    @Title("controller")
    @Test(groups = { "regression", "interfaces", "byAngularController" }, dependsOnMethods = "byAngularButtonText")
    public void byAngularController() {
        ByAngularController controller = new ByAngularController((JavascriptExecutor) getDriver().getWebDriver(), "HeaderController");
        Assert.assertNotNull(controller);
    }

    @Features("Element Interfaces")
    @Stories("Angular")
    @Title("repeater")
    @Test(groups = { "regression", "interfaces", "byAngularController" }, dependsOnMethods = "byAngularButtonText")
    public void byAngularRepeater() {
        ByAngularRepeater repeat = new ByAngularRepeater((JavascriptExecutor) getDriver().getWebDriver(), "employee in employees");
        Assert.assertNotNull(repeat);
    }

}
