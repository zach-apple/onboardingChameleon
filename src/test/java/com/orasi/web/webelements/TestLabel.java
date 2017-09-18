package com.orasi.web.webelements;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.orasi.web.WebBaseTest;
import com.orasi.web.webelements.Label;
import com.orasi.web.webelements.impl.LabelImpl;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

public class TestLabel extends WebBaseTest {

    @BeforeTest(groups = { "regression", "interfaces", "label", "dev" })
    public void setup() {
        setApplicationUnderTest("Test Site");
        setPageURL("http://orasi.github.io/Chameleon/sites/unitTests/orasi/core/interfaces/label.html");
        testStart("TestLabel");
    }

    @AfterTest(groups = { "regression", "interfaces", "label", "dev" })
    public void close(ITestContext testResults) {
        endTest("TestAlert", testResults);
    }

    @Features("Element Interfaces")
    @Stories("Label")
    @Title("constructor")
    @Test(groups = { "regression", "interfaces", "label" })
    public void constructorWithElement() {
        Assert.assertNotNull((new LabelImpl(getDriver(), (By.xpath("//*[@id='radioForm']/label[1]")))));
    }

    @Features("Element Interfaces")
    @Stories("Label")
    @Title("getFor")
    @Test(groups = { "regression", "interfaces", "label" })
    public void getFor() {
        Label label = getDriver().findLabel(By.xpath("//*[@id='radioForm']/label[1]"));
        Assert.assertTrue(label.getFor().equals("genderm"));
    }

}
