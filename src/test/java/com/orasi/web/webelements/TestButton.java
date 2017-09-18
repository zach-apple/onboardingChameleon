package com.orasi.web.webelements;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.orasi.web.WebBaseTest;
import com.orasi.web.webelements.Button;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

public class TestButton extends WebBaseTest {
    @BeforeTest(groups = { "regression", "interfaces", "button", "dev" })

    public void setup() {
        setApplicationUnderTest("Test App");
        setPageURL("http://orasi.github.io/Chameleon/sites/unitTests/orasi/core/interfaces/button.html");
        testStart("TestButton");
    }

    @AfterTest(groups = { "regression", "interfaces", "button", "dev" })
    public void close(ITestContext testResults) {
        endTest("TestAlert", testResults);
    }

    @Features("Element Interfaces")
    @Stories("Button")
    @Title("click")
    @Test(groups = { "regression", "interfaces", "button" })
    public void click() {
        Button button = getDriver().findButton(By.id("click"));
        button.click();
        button.getWrappedDriver();
        Assert.assertTrue(getDriver().findElement(By.id("testClick")).getText().equals("Successful"));
    }

    @Features("Element Interfaces")
    @Stories("Button")
    @Title("jsClick")
    @Test(groups = { "regression", "interfaces", "button" }, dependsOnMethods = "click")
    public void jsClick() {
        Button button = getDriver().findButton(By.id("jsClick"));
        button.jsClick();
        Assert.assertTrue(getDriver().findButton(By.id("testJsClick")).getText().equals("Successful"));
    }
}
