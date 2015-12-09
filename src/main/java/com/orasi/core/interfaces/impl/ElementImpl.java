package com.orasi.core.interfaces.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitWebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;

import com.orasi.core.Beta;
import com.orasi.core.interfaces.Element;
import com.orasi.utils.OrasiDriver;
import com.orasi.utils.TestReporter;

/**
 * An implementation of the Element interface. Delegates its work to an
 * underlying WebElement instance for custom functionality.
 */
public class ElementImpl implements Element {

	protected WebElement element;
	protected OrasiDriver driver;

	public ElementImpl(final WebElement element) {
		this.element = element;
	}

	public ElementImpl(final WebElement element, final OrasiDriver driver) {
		this.element = element;
		this.driver = driver;
	}

	/**
	 * @see org.openqa.selenium.WebElement#click()
	 */
	public void click() {
		try {
			getWrappedElement().click();
		} catch (RuntimeException rte) {
			TestReporter.interfaceLog(
					"Clicked [ <font size = 2 color=\"red\"><b>@FindBy: " + getElementLocatorInfo() + " </font></b>]");
		}
		TestReporter.interfaceLog("Clicked [ <b>@FindBy: " + getElementLocatorInfo() + " </b>]");
	}

	public void jsClick() {
		getWrappedDriver().executeJavaScript("arguments[0].scrollIntoView(true);arguments[0].click();", element);
		TestReporter.interfaceLog("Clicked [ <b>@FindBy: " + getElementLocatorInfo() + " </b>]");
	}

	@Override
	public void focus() {
		new Actions(getWrappedDriver()).moveToElement(element).click().perform();
	}

	@Override
	public void focusClick() {
		new Actions(getWrappedDriver()).moveToElement(element).click().perform();
		TestReporter.interfaceLog("Focus Clicked [ <b>@FindBy: " + getElementLocatorInfo() + " </b>]");
	}

	/**
	 * @see org.openqa.selenium.WebElement#getLocation()
	 */
	@Override
	public Point getLocation() {
		return element.getLocation();
	}

	/**
	 * @see org.openqa.selenium.WebElement#submit()
	 */
	@Override
	public void submit() {
		element.submit();
	}

	/**
	 * @see org.openqa.selenium.WebElement#getAttribute()
	 */
	@Override
	public String getAttribute(String name) {
		return element.getAttribute(name);
	}

	/**
	 * @see org.openqa.selenium.WebElement#getCssValue()
	 */
	@Override
	public String getCssValue(String propertyName) {
		return element.getCssValue(propertyName);
	}

	/**
	 * @see org.openqa.selenium.WebElement#getSize()
	 */
	@Override
	public Dimension getSize() {
		try {
			return element.getSize();
		} catch (WebDriverException wde) {
			if (wde.getMessage().toLowerCase().contains("not implemented")) {
				TestReporter.logFailure("getSize has not been implemented by EdgeDriver");
			}
		}
		return null;
	}

	/**
	 * @see org.openqa.selenium.WebElement#findElements()
	 */
	@Override
	public List<WebElement> findElements(By by) {
		return element.findElements(by);
	}

	/**
	 * @see org.openqa.selenium.WebElement#getText()
	 */
	@Override
	public String getText() {
		return element.getText();
	}

	/**
	 * @see org.openqa.selenium.WebElement#getTagName()
	 */
	@Override
	public String getTagName() {
		return element.getTagName();
	}

	/**
	 * @see org.openqa.selenium.WebElement#findElement()
	 */
	@Override
	public WebElement findElement(By by) {
		return element.findElement(by);
	}

	/**
	 * @see org.openqa.selenium.WebElement#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return element.isEnabled();
	}

	/**
	 * @see org.openqa.selenium.WebElement#isDisplayed()
	 */
	@Override
	public boolean isDisplayed() {
		return element.isDisplayed();
	}

	/**
	 * @see org.openqa.selenium.WebElement.isSelected()
	 */
	@Override
	public boolean isSelected() {
		try {
			return element.isSelected();
		} catch (WebDriverException wde) {
			if (wde.getMessage().toLowerCase().contains("not implemented")) {
				TestReporter.logFailure(" isSelected has not been implemented by EdgeDriver");
			}
		}
		return false;

	}

	/**
	 * @see org.openqa.selenium.WebElement#clear()
	 */
	@Override
	public void clear() {
		getWrappedElement().clear();
		TestReporter.interfaceLog(" Clear text from Element [ <b>@FindBy: " + getElementLocatorInfo() + " </b> ]");
	}

	/**
	 * @see org.openqa.selenium.WebElement#sendKeys()
	 */
	@Override
	public void sendKeys(CharSequence... keysToSend) {
		if (keysToSend.toString() != "") {
			getWrappedElement().sendKeys(keysToSend);
			TestReporter.interfaceLog(" Send Keys [ <b>" + keysToSend[0].toString() + "</b> ] to Textbox [ <b>@FindBy: "
					+ getElementLocatorInfo() + " </b> ]");
		}
	}

	/**
	 * @see org.openqa.selenium.WebElement#getWrappedElement()
	 */
	@Override
	public WebElement getWrappedElement() {
		return element;
	}

	@Override
	public OrasiDriver getWrappedDriver() {
		if (driver == null) {
			Field privateStringField = null;
			try {
				privateStringField = element.getClass().getDeclaredField("driver");
				privateStringField.setAccessible(true);
				return (OrasiDriver) privateStringField.get(element);
			} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}
		}
		return driver;
	}

	/**
	 * @see org.openqa.selenium.internal.Locatable#getCoordinates();
	 */
	@Override
	public Coordinates getCoordinates() {
		return ((Locatable) element).getCoordinates();
	}

	@Override
	public boolean elementWired() {
		return (element != null);
	}

	/**
	 * Get the By Locator object used to create this element
	 * 
	 * @author Justin
	 * @return {@link By} Return the By object to reuse
	 */
	@Override
	public By getElementLocator() {
		By by = null;
		String locator = "";
		try {
			locator = getElementLocatorAsString();
			switch (locator) {
			case "className":
				by = new ByClassName(getElementIdentifier());
				break;
			case "cssSelector":
				by = By.cssSelector(getElementIdentifier());
				break;
			case "id":
				by = By.id(getElementIdentifier());
				break;
			case "linkText":
				by = By.linkText(getElementIdentifier());
				break;
			case "name":
				by = By.name(getElementIdentifier());
				break;
			case "tagName":
				by = By.tagName(getElementIdentifier());
				break;
			case "xpath":
				by = By.xpath(getElementIdentifier());
				break;
			}
			return by;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getElementIdentifier() {
		String locator = "";
		int startPosition = 0;
		int endPosition = 0;
		if (element instanceof HtmlUnitWebElement) {
			startPosition = element.toString().indexOf("=\"") + 2;
			endPosition = element.toString().indexOf("\"", element.toString().indexOf("=\"") + 3);
			if (startPosition == -1 | endPosition == -1)
				locator = element.toString();
			else
				locator = element.toString().substring(startPosition, endPosition);
		} else if (element instanceof ElementImpl) {
			Field elementField = null;
			try {
				elementField = element.getClass().getDeclaredField("element");
				elementField.setAccessible(true);

				startPosition = elementField.get(element).toString().lastIndexOf(": ") + 2;
				locator = elementField.get(element).toString().substring(startPosition,
						elementField.get(element).toString().lastIndexOf("]"));
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		} else {
			startPosition = element.toString().lastIndexOf(": ") + 2;
			locator = element.toString().substring(startPosition, element.toString().lastIndexOf("]"));
		}
		return locator.trim();
	}

	/**
	 * Get the By Locator object used to create this element
	 * 
	 * @author Justin
	 * @return {@link By} Return the By object to reuse
	 */
	private String getElementLocatorAsString() {
		int startPosition = 0;
		String locator = "";

		if (element instanceof HtmlUnitWebElement) {
			startPosition = element.toString().indexOf(" ");
			if (startPosition == -1)
				locator = element.toString();
			else
				locator = element.toString().substring(startPosition, element.toString().indexOf("="));
		} else if (element instanceof ElementImpl) {
			Field elementField = null;
			try {
				elementField = element.getClass().getDeclaredField("element");
				elementField.setAccessible(true);

				startPosition = elementField.get(element).toString().lastIndexOf("->") + 3;
				locator = elementField.get(element).toString().substring(startPosition,
						elementField.get(element).toString().lastIndexOf(":"));
			} catch (IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}

		} else {

			// if (element instanceof HtmlUnitWebElement)
			startPosition = element.toString().lastIndexOf("->") + 3;
			locator = element.toString().substring(startPosition, element.toString().lastIndexOf(":"));
		}
		locator = locator.trim();
		return locator;

	}

	@Override
	public String getElementLocatorInfo() {
		return getElementLocatorAsString() + " = " + getElementIdentifier();
	}

	@Override
	public void highlight() {

		driver.executeJavaScript("arguments[0].style.border='3px solid red'", this);
	}

	@Override
	public void scrollIntoView() {

		driver.executeJavaScript("arguments[0].scrollIntoView(true);", element);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList getAllAttributes() {

		return (ArrayList) driver.executeJavaScript(
				"var s = []; var attrs = arguments[0].attributes; for (var l = 0; l < attrs.length; ++l) { var a = attrs[l]; s.push(a.name + ':' + a.value); } ; return s;",
				getWrappedElement());
	}

	@Beta
	@Override
	public <X> X getScreenshotAs(OutputType<X> target) {
		// TODO Auto-generated method stub
		return ((TakesScreenshot) driver.getDriver()).getScreenshotAs(target);
	}

	/*
	 * @Override public <X> X getScreenshotAs(OutputType<X> target) throws
	 * WebDriverException { getScreenshotAs(target); // String base64 = //
	 * execute(DriverCommand.SCREENSHOT).getValue().toString(); // ... and
	 * convert it.
	 * 
	 * System.out.println("getScreenShotAs is unimplemented"); return null; //
	 * target.convertFromBase64Png(base64); }
	 */
}