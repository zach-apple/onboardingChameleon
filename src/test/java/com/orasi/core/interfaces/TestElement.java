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

import com.orasi.utils.TestEnvironment;

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

	@Test(groups = { "regression", "interfaces", "element" })
	public void clear() {
		Element element = getDriver().findElement(By.id("text1"));
		element.clear();
		Assert.assertTrue(element.getAttribute("value").equals(""));
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "clear")
	public void click() {

		Element element = getDriver().findElement(By.id("buttonForText1"));
		element.click();
		Assert.assertTrue(getDriver().findElement(By.id("text1")).getAttribute("value").equals("Clicked button"));
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "click")
	public void elementWired() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.elementWired());
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getAttribute() {
		Element element = getDriver().findElement(By.xpath("//input[@value='female']"));
		Assert.assertTrue(element.getAttribute("type").equals("radio"));
	}

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

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getCssValue() {
		Element element = getDriver().findElement(By.id("buttonForText1"));
		if (getBrowserUnderTest().equalsIgnoreCase("html"))
			Assert.assertTrue(element.getCssValue("width").equals("auto"));
		else
			Assert.assertTrue(!element.getCssValue("font-family").isEmpty());
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getElementIdentifier() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.getElementIdentifier().equals("text1"));
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getElementLocator() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertNotNull(element.getElementLocator());
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getElementLocatorInfo() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertNotNull(element.getElementLocatorInfo());
	}

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

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getSize() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.getSize().getHeight() > 0);
		Assert.assertTrue(element.getSize().getWidth() > 0);
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getTagName() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.getTagName().equals("input"));
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void getText() {
		Element element = getDriver().findElement(By.id("pageheader"));
		Assert.assertTrue(element.getText().equals("Element test page"));
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "getAttribute")
	public void highlight() {
		Element element = getDriver().findElement(By.id("buttonForText1"));
		element.highlight();
		Assert.assertTrue(element.getAttribute("style").toLowerCase().contains("red"));
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void isDisplayed() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.isDisplayed());
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void isEnabled() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.isEnabled());
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void isSelected() {
		Element element = getDriver().findElement(By.xpath("//*[@id='radiogroup']/input[1]"));
		Assert.assertTrue(element.isSelected());
	}

	@Test(groups = { "regression", "element" })
	public void isSelectedNegative() {
		Element element = getDriver().findElement(By.xpath("//*[@id='radiogroup']/input[2]"));
		Assert.assertFalse(element.isSelected());
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "click")
	public void jsClick() {
		getDriver().findElement(By.id("text1")).sendKeys("blah");
		Element element = getDriver().findElement(By.id("buttonForText1"));
		element.jsClick();
		Assert.assertTrue(getDriver().findElement(By.id("text1")).getAttribute("value").equals("Clicked button"));
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "jsClick")
	public void sendKeys() {
		Element element = getDriver().findElement(By.id("text1"));
		element.sendKeys("testing");
		Assert.assertTrue(element.getAttribute("value").equals("Clicked buttontesting"));
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void syncPresentBasic() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.syncPresent());
	}

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

	@Test(groups = { "regression", "element" }, dependsOnMethods = "elementWired")
	public void syncPresentAdvanced() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.syncPresent(5, false));
	}

	@Test(groups = { "regression", "interfaces", "element" })
	public void syncDisabledBasic() {
		Element element = getDriver().findElement(By.id("disable"));
		Assert.assertTrue(element.syncDisabled());
	}

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

	@Test(groups = { "regression", "element" }, dependsOnMethods = { "syncDisabledBasic" })
	public void syncDisabledAdvanced() {
		Element element = getDriver().findElement(By.id("disable"));
		Assert.assertTrue(element.syncDisabled(5, false));
	}

	@Test(groups = { "regression", "element" }, dependsOnMethods = { "syncDisabledBasic" })
	public void syncDisabledAdvancedNegative() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertFalse(element.syncDisabled(1, false));
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void syncHiddenBasic() {
		Element element = getDriver().findElement(By.id("hidden"));
		Assert.assertTrue(element.syncHidden());
	}

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

	@Test(groups = { "regression", "element" }, dependsOnMethods = "elementWired")
	public void syncHiddenAdvanced() {
		Element element = getDriver().findElement(By.id("hidden"));
		Assert.assertTrue(element.syncHidden(5, false));
	}

	@Test(groups = { "regression", "element" }, dependsOnMethods = "elementWired")
	public void syncHiddenAdvancedNegative() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertFalse(element.syncHidden(1, false));
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void syncVisibleBasic() {
		Textbox element = getDriver().findTextbox(By.id("text1"));
		Assert.assertTrue(element.syncVisible());
	}

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

	@Test(groups = { "regression", "element" }, dependsOnMethods = "elementWired")
	public void syncVisibleAdvanced() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.syncVisible(5, false));
	}

	@Test(groups = { "regression", "element" }, dependsOnMethods = "elementWired")
	public void syncVisibleAdvancedNegative() {
		Element element = getDriver().findElement(By.id("hidden"));
		Assert.assertFalse(element.syncVisible(1, false));
	}

	@Test(groups = { "regression", "interfaces", "element" }, dependsOnMethods = "elementWired")
	public void syncEnabledBasic() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.syncEnabled());
	}

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

	@Test(groups = { "regression", "element" }, dependsOnMethods = "elementWired")
	public void syncEnabledAdvanced() {
		Element element = getDriver().findElement(By.id("text1"));
		Assert.assertTrue(element.syncEnabled(5, false));
	}

	@Test(groups = { "regression", "element" })
	public void syncEnabledAdvancedNegative() {
		Element element = getDriver().findElement(By.id("disable"));
		Assert.assertFalse(element.syncEnabled(1, false));
	}

	@Test(groups = { "regression", "interfaces", "element" })
	public void syncTextInElementBasic() {
		Element element = getDriver().findElement(By.id("pageheader"));
		Assert.assertTrue(element.syncTextInElement("Element test page"));
	}

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

	@Test(groups = { "regression", "element" })
	public void syncTextInElementAdvanced() {
		Element element = getDriver().findElement(By.id("pageheader"));
		Assert.assertTrue(element.syncTextInElement("Element test page", 5, false));
	}

	@Test(groups = { "regression", "element" })
	public void syncTextInElementAdvancedNegative() {
		Element element = getDriver().findElement(By.id("pageheader"));
		Assert.assertFalse(element.syncTextInElement("negative", 2, false));
	}
}
