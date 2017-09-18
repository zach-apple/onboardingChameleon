package com.orasi.web.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.orasi.web.WebBaseTest;
import com.orasi.web.exceptions.OptionNotInListboxException;
import com.orasi.web.webelements.Listbox;
import com.orasi.web.webelements.impl.ListboxImpl;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

public class TestListbox extends WebBaseTest {
    WebDriver driver = null;

    @BeforeTest(alwaysRun = true)
    public void setup() {
        setApplicationUnderTest("Test Site");
        setPageURL("http://orasi.github.io/Chameleon/sites/unitTests/orasi/core/interfaces/listbox.html");
        testStart("TestListbox");
    }

    @AfterTest(alwaysRun = true)
    public void close(ITestContext testResults) {
        endTest("TestListbox", testResults);
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("constructor")
    @Test(groups = { "regression", "interfaces", "listbox" })
    public void constructorWithElement() {
        Assert.assertNotNull((new ListboxImpl(getDriver(), (By.id("singleSelect")))));
    }

    /*
     * @Features("Element Interfaces")
     *
     * @Stories("Listbox")
     *
     * @Title("constructorWithElementAndDriver")
     *
     * @Test(groups ={"regression", "interfaces", "listbox"})
     * public void constructorWithElementAndDriver(){
     * Assert.assertNotNull((new ListboxImpl(getDriver().findWebElement((By.id("singleSelect"))), getDriver())));
     * }
     */
    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("isMultiple")
    @Test(groups = { "regression", "interfaces", "listbox" })
    public void isMultiple() {
        Listbox listbox = getDriver().findListbox(By.id("multiSelect"));
        Assert.assertTrue(listbox.isMultiple());
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("select")
    @Test(groups = { "regression", "interfaces", "listbox", "mustard" })
    public void select() {
        Listbox listbox = getDriver().findListbox(By.id("singleSelect"));
        listbox.select("Sports");
        Assert.assertTrue(listbox.getFirstSelectedOption().getText().equals("Sports"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("selectNoText")
    @Test(groups = { "regression", "interfaces", "textbox" }, dependsOnMethods = "select")
    public void selectNoText() {
        if (getBrowserUnderTest().toLowerCase().equals("html") || getBrowserUnderTest().isEmpty()) {
            throw new SkipException("Test not valid for HTMLUnitDriver");
        }
        Listbox listbox = getDriver().findListbox(By.id("singleSelect"));
        listbox.select("");
        Assert.assertTrue(listbox.getAttribute("value").equals("Sports"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("selectNegative")
    @Test(groups = { "regression", "interfaces", "textbox" }, dependsOnMethods = "select", expectedExceptions = OptionNotInListboxException.class)
    public void selectNegative() {
        Listbox listbox = getDriver().findListbox(By.id("singleSelect"));
        listbox.select("text");
        Assert.assertTrue(false);
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("selectValue")
    @Test(groups = { "regression", "interfaces", "listbox" })
    public void selectValue() {
        Listbox listbox = getDriver().findListbox(By.id("singleSelect"));
        listbox.selectValue("Sports");
        Assert.assertTrue(listbox.getFirstSelectedOption().getText().equals("Sports"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("selectValueNoText")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "select")
    public void selectValueNoText() {
        if (getBrowserUnderTest().toLowerCase().equals("html") || getBrowserUnderTest().isEmpty()) {
            throw new SkipException("Test not valid for HTMLUnitDriver");
        }
        Listbox listbox = getDriver().findListbox(By.id("singleSelect"));
        listbox.selectValue("");
        Assert.assertTrue(listbox.getAttribute("value").equals("Sports"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("selectValueNegative")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "select", expectedExceptions = OptionNotInListboxException.class)
    public void selectValueNegative() {
        if (getBrowserUnderTest().toLowerCase().equals("html") || getBrowserUnderTest().isEmpty()) {
            throw new SkipException("Test not valid for HTMLUnitDriver");
        }
        Listbox listbox = getDriver().findListbox(By.id("singleSelect"));
        listbox.selectValue("text");
        Assert.assertTrue(false);
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("getAllOptions")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "select")
    public void getAllOptions() {
        Listbox listbox = getDriver().findListbox(By.id("singleSelect"));
        Assert.assertTrue(listbox.getOptions().get(0).getText().equals("Other"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("getAllOptionsSelected")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "select")
    public void getAllSelectedOptions() {
        Listbox listbox = getDriver().findListbox(By.id("singleSelect"));
        Assert.assertTrue(listbox.getAllSelectedOptions().get(0).getText().equals("Sports"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("isSelected")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "getAllSelectedOptions")
    public void isSelected() {
        Listbox listbox = getDriver().findListbox(By.id("singleSelect"));
        Assert.assertTrue(listbox.isSelected("Sports"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("deselectByVisibleText")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "isSelected")
    public void deselectByVisibleText() {
        Listbox listbox = getDriver().findListbox(By.id("multiSelect"));
        listbox.select("Baseball");
        Assert.assertTrue(listbox.isSelected("Baseball"));
        listbox.deselectByVisibleText("Baseball");
        Assert.assertFalse(listbox.isSelected("Baseball"));
    }

    @Features("Element Interfaces")
    @Stories("Listbox")
    @Title("deselectAll")
    @Test(groups = { "regression", "interfaces", "listbox" }, dependsOnMethods = "deselectByVisibleText")
    public void deselectAll() {
        Listbox listbox = getDriver().findListbox(By.id("multiSelect"));
        listbox.select("Basketball");
        listbox.select("Soccer");
        listbox.deselectAll();
        Assert.assertNull(listbox.getFirstSelectedOption());
    }

}
