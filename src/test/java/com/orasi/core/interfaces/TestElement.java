package com.orasi.core.interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.utils.Sleeper;
import com.orasi.utils.TestEnvironment;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

public class TestElement extends TestEnvironment {

	@BeforeTest(groups = { "regression", "interfaces", "element", "dev" })
	@Parameters({ "runLocation", "browserUnderTest", "browserVersion", "operatingSystem", "environment" })
	public void setup(@Optional String runLocation, String browserUnderTest, String browserVersion,
			String operatingSystem, String environment) {
		setApplicationUnderTest("Test Site");
		setBrowserUnderTest(browserUnderTest);
		setBrowserVersion(browserVersion);
		setOperatingSystem(operatingSystem);
		setRunLocation(runLocation);
		setTestEnvironment(environment);
		setPageURL("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/core/interfaces/element.html");
		testStart("TestElement");
		getDriver().findWebElement(By.id("text1")).sendKeys("blah");
	}

	@AfterTest(groups = { "regression", "interfaces", "element", "dev" })
	public void close(ITestContext testResults) {
		endTest("TestAlert", testResults);
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("clear")
	@Test(groups = { "regression", "interfaces", "element" })
	public void clear() {
		Element element = getDriver().findElement(By.id("text1"));
		element.clear();
		Assert.assertTrue(element.getAttribute("value").equals(""));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("click")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "clear")
	public void click() {
		Element element = getDriver().findElement(By.id("buttonForText1"));
		if(getBrowserUnderTest().toLowerCase().contains("edge")) element.jsClick();
		else element.click();
		//temporary fix for edge click
		Assert.assertTrue(getDriver().findElement(By.id("text1")).getAttribute("value").equals("Clicked button"));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("elementWired")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "click")
	public void elementWired() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.elementWired());
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("getAttribute")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getAttribute() {
		Element element = getDriver().findElement(By.xpath("//input[@value='female']"));
		Assert.assertTrue(element.getAttribute("type").equals("radio"));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("getCoordinates")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getCoordinates() {
		Element element = getDriver().findElement(By.id("text1"));
		try {
			Assert.assertTrue(element.getCoordinates().onPage().getX() > 0);
			Assert.assertTrue(element.getCoordinates().onPage().getY() > 0);
		} catch (WebDriverException wde) {
			if (getBrowserUnderTest().toLowerCase().contains("edge"))
				throw new AssertionError("getCoordinates not supported by EdgeDriver");
		}
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("getCssValue")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getCssValue() {
		Element element = getDriver().findElement(By.id("buttonForText1"));
		if (getBrowserUnderTest().equalsIgnoreCase("html"))
			Assert.assertTrue(element.getCssValue("width").equals("auto"));
		else
			Assert.assertTrue(!element.getCssValue("font-family").isEmpty());
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("getElementIdentifier")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getElementIdentifier() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.getElementIdentifier().equals("text1"));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("getElementLocator")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getElementLocator() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertNotNull(element.getElementLocator());
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("getElementLocatorInfo")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getElementLocatorInfo() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertNotNull(element.getElementLocatorInfo());
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("getLocation")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getLocation() {
		Element element = getDriver().findElement(By.id("text1"));
		try {
			Assert.assertTrue(element.getLocation().getX() > 0);
			Assert.assertTrue(element.getLocation().getY() > 0);
		} catch (WebDriverException wde) {
			if (getBrowserUnderTest().toLowerCase().contains("edge"))
				throw new AssertionError("getLocation not supported by EdgeDriver");
		}
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("getSize")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getSize() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.getSize().getHeight() > 0);
		Assert.assertTrue(element.getSize().getWidth() > 0);
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("getTagName")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getTagName() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.getTagName().equals("input"));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("getText")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getText() {
		Element element = getDriver().findElement(By.id("pageheader"));
		Assert.assertTrue(element.getText().equals("Element test page"));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("highlight")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "getAttribute")
	public void highlight() {
		Element element = getDriver().findElement(By.id("buttonForText1"));
		element.highlight();
		Assert.assertTrue(element.getAttribute("style").toLowerCase().contains("red"));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("isDisplayed")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void isDisplayed() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.isDisplayed());
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("isEnabled")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void isEnabled() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.isEnabled());
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("isSelected")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void isSelected() {
		Element element = getDriver().findElement(By.xpath("//*[@id='radiogroup']/input[1]"));
		Assert.assertTrue(element.isSelected());
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("isSelectedNegative")
	@Test(groups = { "regression", "element" })
	public void isSelectedNegative() {
		Element element = getDriver().findElement(By.xpath("//*[@id='radiogroup']/input[2]"));
		Assert.assertFalse(element.isSelected());
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("jsClick")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "click")
	public void jsClick() {
		getDriver().findElement(By.id("text1")).sendKeys("blah");
		Element element = getDriver().findElement(By.id("buttonForText1"));
		element.jsClick();
		Assert.assertTrue(getDriver().findElement(By.id("text1")).getAttribute("value").equals("Clicked button"));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("sendKeys")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "jsClick")
	public void sendKeys() {
		Element element = getDriver().findElement(By.id("text1"));
		element.sendKeys("testing");
		Assert.assertTrue(element.getAttribute("value").equals("Clicked buttontesting"));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncPresentBasic")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void syncPresentBasic() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.syncPresent());
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncPresentNegative")
	@Test(groups = { "regression", "element" }, dependsOnMethods = "elementWired")
	public void syncPresentNegative() {
		boolean pass = false;
		try {
			Element element = getDriver().findElement(By.id("NoElement"));
			element.syncPresent();
		} catch (RuntimeException rte) {
			pass = true;
		}

		Assert.assertTrue(pass);
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncPresentAdvanced")
	@Test(groups = { "regression", "element" }, dependsOnMethods = "elementWired")
	public void syncPresentAdvanced() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.syncPresent(5, false));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncDisabledBasic")
	@Test(groups = { "regression", "interfaces", "element" })
	public void syncDisabledBasic() {
		Element element = getDriver().findElement(By.id("disable"));
		Assert.assertTrue(element.syncDisabled());
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncDisabledBasicNegative")
	@Test(groups = { "regression", "element" }, dependsOnMethods = { "syncDisabledBasic" })
	public void syncDisabledBasicNegative() {
		boolean pass = false;
		try {
			Element element = getDriver().findElement(By.id("text1"));
			element.syncDisabled();
		} catch (RuntimeException rte) {
			pass = true;
		}

		Assert.assertTrue(pass);
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncDisabledAdvanced")
	@Test(groups = { "regression", "element" }, dependsOnMethods = { "syncDisabledBasic" })
	public void syncDisabledAdvanced() {
		Element element = getDriver().findElement(By.id("disable"));
		Assert.assertTrue(element.syncDisabled(5, false));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncDisabledAdvancedNegative")
	@Test(groups = { "regression", "element" }, dependsOnMethods = { "syncDisabledBasic" })
	public void syncDisabledAdvancedNegative() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertFalse(element.syncDisabled(1, false));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncHiddenBasic")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void syncHiddenBasic() {
		Element element = getDriver().findElement(By.id("hidden"));
		Assert.assertTrue(element.syncHidden());
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncHiddenBasicNegative")
	@Test(groups = { "regression", "element" }, dependsOnMethods = "elementWired")
	public void syncHiddenBasicNegative() {
		boolean pass = false;
		try {
			Element element = getDriver().findElement(By.id("text1"));
			element.syncHidden();
		} catch (RuntimeException rte) {
			pass = true;
		}

		Assert.assertTrue(pass);
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncHiddenAdvanced")
	@Test(groups = { "regression", "element" }, dependsOnMethods = "elementWired")
	public void syncHiddenAdvanced() {
		Element element = getDriver().findElement(By.id("hidden"));
		Assert.assertTrue(element.syncHidden(5, false));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncHiddenAdvancedNegative")
	@Test(groups = { "regression", "element" }, dependsOnMethods = "elementWired")
	public void syncHiddenAdvancedNegative() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertFalse(element.syncHidden(1, false));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncVisibleBasic")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void syncVisibleBasic() {
		Textbox element = getDriver().findTextbox(By.id("text1"));
		Assert.assertTrue(element.syncVisible());
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncVisibleBasicNegative")
	@Test(groups = { "regression", "element" }, dependsOnMethods = "elementWired")
	public void syncVisibleBasicNegative() {
		boolean pass = false;
		try {
			Element element = getDriver().findElement(By.id("hidden"));
			element.syncVisible();
		} catch (RuntimeException rte) {
			pass = true;
		}

		Assert.assertTrue(pass);
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncVisibleAdvanced")
	@Test(groups = { "regression", "element" }, dependsOnMethods = "elementWired")
	public void syncVisibleAdvanced() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.syncVisible(5, false));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncVisibleAdvancedNegative")
	@Test(groups = { "regression", "element" }, dependsOnMethods = "elementWired")
	public void syncVisibleAdvancedNegative() {
		Element element = getDriver().findElement(By.id("hidden"));
		Assert.assertFalse(element.syncVisible(1, false));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncEnabledBasic")
	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void syncEnabledBasic() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.syncEnabled());
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncEnabledBasicNegative")
	@Test(groups = { "regression", "element" })
	public void syncEnabledBasicNegative() {
		boolean pass = false;
		try {
			Element element = getDriver().findElement(By.id("disable"));
			element.syncEnabled();
		} catch (RuntimeException rte) {
			pass = true;
		}

		Assert.assertTrue(pass);
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncEnabledAdvanced")
	@Test(groups = { "regression", "element" }, dependsOnMethods = "elementWired")
	public void syncEnabledAdvanced() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.syncEnabled(5, false));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncEnabledAdvancedNegative")
	@Test(groups = { "regression", "element" })
	public void syncEnabledAdvancedNegative() {
		Element element = getDriver().findElement(By.id("disable"));
		Assert.assertFalse(element.syncEnabled(1, false));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncTextInElementBasic")
	@Test(groups = { "regression", "interfaces", "element" })
	public void syncTextInElementBasic() {
		Element element = getDriver().findElement(By.id("pageheader"));
		Assert.assertTrue(element.syncTextInElement("Element test page"));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncTextInElementBasicNegative")
	@Test(groups = { "regression", "interfaces", "element" })
	public void syncTextInElementBasicNegative() {
		boolean pass = false;
		try {
			Element element = getDriver().findElement(By.id("pageheader"));
			element.syncTextInElement("Loading");
		} catch (RuntimeException rte) {
			pass = true;
		}

		Assert.assertTrue(pass);
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncTextInElementAdvanced")
	@Test(groups = { "regression", "element" })
	public void syncTextInElementAdvanced() {
		Element element = getDriver().findElement(By.id("pageheader"));
		Assert.assertTrue(element.syncTextInElement("Element test page", 5, false));
	}

	@Features("Element Interfaces")
	@Stories("Element")
	@Title("syncTextInElementAdvancedNegative")
	@Test(groups = { "regression", "element" })
	public void syncTextInElementAdvancedNegative() {
		Element element = getDriver().findElement(By.id("pageheader"));
		Assert.assertFalse(element.syncTextInElement("negative", 2, false));
	}
}
