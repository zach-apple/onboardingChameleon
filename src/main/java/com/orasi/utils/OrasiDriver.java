package com.orasi.utils;

import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.javascript.background.DefaultJavaScriptExecutor;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.orasi.core.by.angular.ByNG;
import com.orasi.core.by.angular.ByNG.ByNGButton;
import com.orasi.core.by.angular.ByNG.ByNGController;
import com.orasi.core.by.angular.ByNG.ByNGModel;
import com.orasi.core.by.angular.ByNG.ByNGRepeater;
import com.orasi.core.by.angular.ByNG.ByNGShow;
import com.orasi.core.by.angular.internal.ByAngular;
import com.orasi.core.interfaces.Button;
import com.orasi.core.interfaces.Checkbox;
import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.Label;
import com.orasi.core.interfaces.Link;
import com.orasi.core.interfaces.Listbox;
import com.orasi.core.interfaces.RadioGroup;
import com.orasi.core.interfaces.Textbox;
import com.orasi.core.interfaces.Webtable;
import com.orasi.core.interfaces.impl.ButtonImpl;
import com.orasi.core.interfaces.impl.CheckboxImpl;
import com.orasi.core.interfaces.impl.ElementImpl;
import com.orasi.core.interfaces.impl.LabelImpl;
import com.orasi.core.interfaces.impl.LinkImpl;
import com.orasi.core.interfaces.impl.ListboxImpl;
import com.orasi.core.interfaces.impl.RadioGroupImpl;
import com.orasi.core.interfaces.impl.TextboxImpl;
import com.orasi.core.interfaces.impl.WebtableImpl;

public class OrasiDriver implements WebDriver, JavaScriptExecutor, TakesScreenshot {
	/*
	 * Define fields to be used by an OrasiDriver
	 */
	private static final long serialVersionUID = -657563735440878909L;
	private WebDriver driver;
	private int currentPageTimeout = Constants.PAGE_TIMEOUT;
	private int currentElementTimeout = Constants.ELEMENT_TIMEOUT;
	private int currentScriptTimeout = Constants.DEFAULT_GLOBAL_DRIVER_TIMEOUT;

	/**
	 * Constructor for OrasiDriver
	 * Example usage: OrasiDriver oDriver = new OrasiDriver(caps);
	 * @param caps - Selenium desired capabilities, used to configure the OrasiDriver
	 */
	public OrasiDriver(DesiredCapabilities caps) {
		setDriverWithCapabilties(caps);
	}
	/**
	 * Constructor for OrasiDriver, specifically used to generate a remote WebDriver
	 * Example usage: OrasiDriver oDriver = new OrasiDriver(caps, url);
	 * @param caps - Selenium desired capabilities, used to configure the OrasiDriver
	 * @param url - 
	 */
	public OrasiDriver(DesiredCapabilities caps, URL url) {
		driver = new RemoteWebDriver(url, caps);
	}
	/**
	 * Method to return the current OrasiDriver
	 * Example usage: getDriver().getDriver();
	 * @return - current OrasiDriver
	 */
	public WebDriver getDriver() {
		return driver;
	}
	/**
	 * Method to navigate to a user-defined URL
	 * Example usage: getDriver().get(url);
	 * @param url - URL to which to navigate
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#get-java.lang.String-
	 */
	@Override
	public void get(String url) {
		driver.get(url);

	}
	/**
	 * Method to return the current URL
	 * Example usage: getDriver().getCurrentUrl();
	 * @return - current URL
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#getCurrentUrl--
	 */
	@Override
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	/**
	 * Method to return the title of the current page
	 * Example usage: getDriver().getTitle();
	 * @return - title of the current page
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#getTitle--
	 */
	@Override
	public String getTitle() {
		return driver.getTitle();
	}
	/**
	 * Method to set the script timeout
	 * @param timeout - timeout with which to set the script timeout
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.Timeouts.html#setScriptTimeout-long-java.util.concurrent.TimeUnit-
	 */
	public void setScriptTimeout(int timeout) {
		setScriptTimeout(timeout, TimeUnit.SECONDS);
	}
	/**
	 * Method to set the script timeout
	 * @param timeout - timeout with which to set the script timeout
	 * @param timeUnit -Java TimeUnit, used to determine the unit of time to be associated with the timeout
	 * @see ://docs.oracle.com/javase/8/docs/api/java/util/concurrent/TimeUnit.html
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.Timeouts.html#setScriptTimeout-long-java.util.concurrent.TimeUnit-
	 */
	public void setScriptTimeout(int timeout, TimeUnit timeUnit) {
		this.currentScriptTimeout = timeout;
		driver.manage().timeouts().setScriptTimeout(timeout, timeUnit);
	}
	/**
	 * Method to return the script timeout
	 * @return - script timeout
	 */
	public int getScriptTimeout() {
		return currentScriptTimeout;
	}
	/**
	 * Method to set the page timeout
	 * @param timeout - timeout with which to set the page timeout
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.Timeouts.html#pageLoadTimeout-long-java.util.concurrent.TimeUnit-
	 */
	public void setPageTimeout(int timeout) {
		setPageTimeout(timeout, TimeUnit.SECONDS);
	}
	/**
	 * Method to set the page timeout
	 * @param timeout - timeout with which to set the page timeout
	 * @param timeUnit -Java TimeUnit, used to determine the unit of time to be associated with the timeout
	 * @see ://docs.oracle.com/javase/8/docs/api/java/util/concurrent/TimeUnit.html
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.Timeouts.html#pageLoadTimeout-long-java.util.concurrent.TimeUnit-
	 */
	public void setPageTimeout(int timeout, TimeUnit timeUnit) {
		if (driver instanceof SafariDriver || driver.toString().contains("safari")) {
			System.out.println("SafariDriver does not support pageLoadTimeout");
		} else {
			this.currentPageTimeout = timeout;
			driver.manage().timeouts().pageLoadTimeout(timeout, timeUnit);
		}
	}
	/**
	 * Method to return the page timeout
	 * @return - page timeout
	 */
	public int getPageTimeout() {
		return currentPageTimeout;
	}
	/**
	 * Method to set element timeout
	 * @param timeout - timeout with which to set the element timeout
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.Timeouts.html#implicitlyWait-long-java.util.concurrent.TimeUnit-
	 */
	public void setElementTimeout(int timeout) {
		setElementTimeout(timeout, TimeUnit.SECONDS);
	}
	/**
	 * Method to set the element timeout
	 * @param timeout - timeout with which to set the element timeout
	 * @param timeUnit
	 * @see ://docs.oracle.com/javase/8/docs/api/java/util/concurrent/TimeUnit.html
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.Timeouts.html#implicitlyWait-long-java.util.concurrent.TimeUnit-
	 */
	public void setElementTimeout(int timeout, TimeUnit timeUnit) {
		this.currentElementTimeout = timeout;
		driver.manage().timeouts().implicitlyWait(timeout, timeUnit);
	}
	/**
	 * Method to return the element timeout
	 * @return - element timeout
	 */
	public int getElementTimeout() {
		return currentElementTimeout;
	}

	/*
	 * public List<Element> findElements(By by) { List<WebElement> webElements =
	 * driver.findElements(by); List test = webElements; List<Element> elements=
	 * (List<Element>)test; return elements; }
	 */
	/**
	 * Method to find all WebElements for a given page, using a Selenium <b><i>By</i></b> locator
	 * @param by - Selenium <b><i>By</i></b> locator with which to locate WebElements
	 * @return - List of WebElements, if any, found by using the Selenium <b><i>By</i></b> locator
	 * @see ://docs.oracle.com/javase/8/docs/api/java/util/List.html
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/By.html
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElements-org.openqa.selenium.By-
	 */
	@Override
	public List<WebElement> findElements(By by) {
		try {
			return findWebElements(by);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Element with context " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find all WebElements for a given page, using a Selenium <b><i>By</i></b> locator
	 * @param by - Selenium <b><i>By</i></b> locator with which to locate WebElements
	 * @return - List of WebElements, if any, found by using the Selenium <b><i>By</i></b> locator
	 * @see ://docs.oracle.com/javase/8/docs/api/java/util/List.html
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/By.html
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElements-org.openqa.selenium.By-
	 */
	public List<WebElement> findWebElements(By by) {
		try {
			return driver.findElements(by);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such WebElement with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single Element for a given page, using a Selenium <b><i>By</i></b> locator
	 * @param by - Selenium <b><i>By</i></b> locator with which to locate the Element
	 * @return Element, if any, found by using the Selenium <b><i>By</i></b> locator 
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/By.html
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/ElementImpl.java
	 */
	@Override
	public Element findElement(By by) {
		try {
			return new ElementImpl(driver.findElement(by), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Element with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single Element for a given page, using an Orasi <b><i>ByNG</i></b> locator
	 * @param by - Orasi <b><i>ByNG</i></b> locator with which to locate the Element
	 * @return Element, if any, found by using the Orasi <b><i>ByNG</i></b> locator
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/ElementImpl.java
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/by/angular/ByNG.java
	 */
	public Element findElement(ByNG by) {
		try {
			return new ElementImpl(driver.findElement(getByNGType(by)), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Element with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single Textbox for a given page, using a Selenium <b><i>By</i></b> locator
	 * @param by - Selenium <b><i>By</i></b> locator with which to locate the Textbox
	 * @return Textbox, if any, found by using the Selenium <b><i>By</i></b> locator
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/By.html
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/TextboxImpl.java
	 */
	public Textbox findTextbox(By by) {
		try {
			return new TextboxImpl(driver.findElement(by), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Textbox with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single Textbox for a given page, using an Orasi <b><i>ByNG</i></b> locator
	 * @param by - Orasi <b><i>ByNG</i></b> locator with which to locate the Textbox
	 * @return Textbox, if any, found by using the Orasi <b><i>ByNG</i></b> locator
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/TextboxImpl.java
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/by/angular/ByNG.java
	 */
	public Textbox findTextbox(ByNG by) {
		try {
			return new TextboxImpl(driver.findElement(getByNGType(by)), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Textbox with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single Button for a given page, using a Selenium <b><i>By</i></b> locator
	 * @param by - Selenium <b><i>By</i></b> locator with which to locate the Button
	 * @return Button, if any, found by using the Selenium <b><i>By</i></b> locator
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/By.html
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/ButtonImpl.java
	 */
	public Button findButton(By by) {
		try {
			return new ButtonImpl(driver.findElement(by), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Button with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single Button for a given page, using an Orasi <b><i>ByNG</i></b> locator
	 * @param  by - Orasi <b><i>ByNG</i></b> locator with which to locate the Button
	 * @return Button, if any, found by using the Orasi <b><i>ByNG</i></b> locator
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/ButtonImpl.java
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/by/angular/ByNG.java
	 */
	public Button findButton(ByNG by) {
		try {
			return new ButtonImpl(driver.findElement(getByNGType(by)), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Button with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single Checkbox for a given page, using a Selenium <b><i>By</i></b> locator
	 * @param by - Selenium <b><i>By</i></b> locator with which to locate the Checkbox
	 * @return Checkbox, if any, found by using the Selenium <b><i>By</i></b> locator
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/By.html
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/CheckboxImpl.java
	 */
	public Checkbox findCheckbox(By by) {
		try {
			return new CheckboxImpl(driver.findElement(by), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Checkbox with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single Checkbox for a given page, using an Orasi <b><i>ByNG</i></b> locator
	 * @param  by - Orasi <b><i>ByNG</i></b> locator with which to locate the Checkbox
	 * @return Checkbox, if any, found by using the Orasi <b><i>ByNG</i></b> locator
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/CheckboxImpl.java
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/by/angular/ByNG.java
	 */
	public Checkbox findCheckbox(ByNG by) {
		try {
			return new CheckboxImpl(driver.findElement(getByNGType(by)), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Checkbox with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single Label for a given page, using a Selenium <b><i>By</i></b> locator
	 * @param by - Selenium <b><i>By</i></b> locator with which to locate the Checkbox
	 * @return Label, if any, found by using the Selenium <b><i>By</i></b> locator
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/By.html
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/LabelImpl.java
	 */
	public Label findLabel(By by) {
		try {
			return new LabelImpl(driver.findElement(by), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Label with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single Label for a given page, using an Orasi <b><i>ByNG</i></b> locator
	 * @param by - Orasi <b><i>ByNG</i></b> locator with which to locate the Label
	 * @return Label, if any, found by using the Orasi <b><i>ByNG</i></b> locator
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/LabelImpl.java
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/by/angular/ByNG.java
	 */
	public Label findLabel(ByNG by) {
		try {
			return new LabelImpl(driver.findElement(getByNGType(by)), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Label with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single Link for a given page, using a Selenium <b><i>By</i></b> locator
	 * @param by - Selenium <b><i>By</i></b> locator with which to locate the Link
	 * @return Link, if any, found by using the Selenium <b><i>By</i></b> locator
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/By.html
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/LinkImpl.java
	 */
	public Link findLink(By by) {
		try {
			return new LinkImpl(driver.findElement(by), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Link with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single Link for a given page, using an Orasi <b><i>ByNG</i></b> locator
	 * @param by - Orasi <b><i>ByNG</i></b> locator with which to locate the Link
	 * @return Link, if any, found by using the Orasi <b><i>ByNG</i></b> locator
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/LinkImpl.java
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/by/angular/ByNG.java
	 */
	public Link findLink(ByNG by) {
		try {
			return new LinkImpl(driver.findElement(getByNGType(by)), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Link with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single Listbox for a given page, using a Selenium <b><i>By</i></b> locator
	 * @param by - Selenium <b><i>By</i></b> locator with which to locate the Listbox
	 * @return Listbox, if any, found by using the Selenium <b><i>By</i></b> locator
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/By.html
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/ListboxImpl.java
	 */
	public Listbox findListbox(By by) {
		try {
			return new ListboxImpl(driver.findElement(by), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Listbox with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single Listbox for a given page, using an Orasi <b><i>ByNG</i></b> locator
	 * @param by - Orasi <b><i>ByNG</i></b> locator with which to locate the Listbox
	 * @return Listbox, if any, found by using the Orasi <b><i>ByNG</i></b> locator
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/ListboxImpl.java
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/by/angular/ByNG.java
	 */
	public Listbox findListbox(ByNG by) {
		try {
			return new ListboxImpl(driver.findElement(getByNGType(by)), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Listbox with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single RadioGroup for a given page, using a Selenium <b><i>By</i></b> locator
	 * @param by - Selenium <b><i>By</i></b> locator with which to locate the RadioGroup
	 * @return RadioGroup, if any, found by using the Selenium <b><i>By</i></b> locator
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/By.html
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/RadioGroupImpl.java
	 */
	public RadioGroup findRadioGroup(By by) {
		try {
			return new RadioGroupImpl(driver.findElement(by), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such RadioGroup with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single RadioGroup for a given page, using an Orasi <b><i>ByNG</i></b> locator
	 * @param by - Orasi <b><i>ByNG</i></b> locator with which to locate the RadioGroup
	 * @return RadioGroup, if any, found by using the Orasi <b><i>ByNG</i></b> locator
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/RadioGroupImpl.java
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/by/angular/ByNG.java
	 */
	public RadioGroup findRadioGroup(ByNG by) {
		try {
			return new RadioGroupImpl(driver.findElement(getByNGType(by)), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such RadioGroup with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single Webtable for a given page, using a Selenium <b><i>By</i></b> locator
	 * @param by - Selenium <b><i>By</i></b> locator with which to locate the Webtable
	 * @return Webtable, if any, found by using the Selenium <b><i>By</i></b> locator
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/By.html
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/WebtableImpl.java
	 */
	public Webtable findWebtable(By by) {
		try {
			return new WebtableImpl(driver.findElement(by), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Webtable with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single Webtable for a given page, using an Orasi <b><i>ByNG</i></b> locator
	 * @param by - Orasi <b><i>ByNG</i></b> locator with which to locate the Webtable
	 * @return Webtable, if any, found by using the Orasi <b><i>ByNG</i></b> locator
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/interfaces/impl/WebtableImpl.java
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/by/angular/ByNG.java
	 */
	public Webtable findWebtable(ByNG by) {
		try {
			return new WebtableImpl(driver.findElement(getByNGType(by)), this);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such Webtable with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single WebElement for a given page, using a Selenium <b><i>By</i></b> locator
	 * @param by - Selenium <b><i>By</i></b> locator with which to locate the WebElement
	 * @return WebElement, if any, found by using the Orasi <b><i>ByNG</i></b> locator
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By-
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/By.html
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html
	 */
	public WebElement findWebElement(By by) {
		try {
			return driver.findElement(by);
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such WebElement with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to find a single WebElement for a given page, using an Orasi <b><i>ByNG</i></b> locator
	 * @param  by - Orasi <b><i>ByNG</i></b> locator with which to locate the WebElement
	 * @return WebElement, if any, found by using the Orasi <b><i>ByNG</i></b> locator
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#findElement-org.openqa.selenium.By
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/by/angular/ByNG.java
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html
	 */
	public WebElement findWebElement(ByNG by) {
		try {
			return driver.findElement(getByNGType(by));
		} catch (NoSuchElementException nse) {
			TestReporter.logFailure("No such WebElement with context: " + by.toString());
			throw new NoSuchElementException(nse.getMessage());
		}
	}
	/**
	 * Method to return the page source of a given current page
	 * @return page source of the given current page
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#getPageSource--
	 */
	@Override
	public String getPageSource() {
		return driver.getPageSource();
	}
	/**
	 * Method to close the current window
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#close--
	 */
	@Override
	public void close() {
		driver.close();
	}
	/**
	 * Method to quit the driver, closing every associated window
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#quit--
	 */
	@Override
	public void quit() {
		driver.quit();
	}
	/**
	 * Method to return all window handles contained within a given current driver
	 * @return Set of string window handles
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#getWindowHandles--
	 */
	@Override
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}
	/**
	 * Method to return the current window handle for a given currnet driver
	 * @return Current window handle as a String
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#getWindowHandle--
	 */
	@Override
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}
	/**
	 * Method to switch to another frame or window
	 * @return TargetLocator that can be used to select a frame or window
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#switchTo--
	 */
	@Override
	public TargetLocator switchTo() {
		return driver.switchTo();
	}
	/**
	 * Method to navigate to a pre-defined URL
	 * @return Navigation object that allows the selection of what to do next
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#navigate--
	 */
	@Override
	public Navigation navigate() {
		return driver.navigate();
	}
	/**
	 * Method to facilitate the management of the driver (e.g. timeouts, cookies, etc)
	 * @return Options interface
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.html#manage--
	 */
	@Override
	public Options manage() {
		return driver.manage();
	}
	/**
	 * Method to clone this class
	 * @return Object clone of the current state of this class
	 * @throws CloneNotSupportedException
	 * @see ://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	/**
	 * Method to determine if an object is equal to an instance of this class
	 * @param obj - object with which to compare
	 * @return -boolean true if the two objects are equal, false otherwise
	 * @see ://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	/**
	 * Method to dispose of system resources
	 * @throws Throwable
	 * @see ://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
	/**
	 * Method to return the hascode for an instance of this class
	 * @return hashcode for an instance of this class as an integer
	 * @see ://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#hashCode--
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	/**
	 * Method to return a current instance of this class as a String
	 * @return - current instance of this class as a String
	 * @see ://docs.oracle.com/javase/8/docs/api/java/lang/String.html#toString--
	 */
	@Override
	public String toString() {
		return super.toString();
	}
	/**
	 * Method to execute a user-defined JavaScript
	 * @param script script to be executed
	 * @param parameters any parameters that may need to be referenced by the script
	 * @return Return value types vary based on the return type of the script
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/JavascriptExecutor.html
	 */
	public Object executeJavaScript(String script, Object... parameters) {
		return ((JavascriptExecutor) driver).executeScript(script, parameters);
	}
	/**
	 * Method to execute a user-defined JavaScript
	 * @param script script to be executed
	 * @param parameters any parameters that may need to be referenced by the script
	 * @return Return value types vary based on the return type of the script
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/JavascriptExecutor.html
	 */
	public Object executeAsyncJavaScript(String script, Object... parameters) {
		return ((JavascriptExecutor) driver).executeAsyncScript(script, parameters);
	}

	@Override
	public void run() {
		((DefaultJavaScriptExecutor) driver).run();
	}

	@Override
	public void addWindow(WebWindow newWindow) {
		((DefaultJavaScriptExecutor) driver).addWindow(newWindow);
	}

	@Override
	public void shutdown() {
		((DefaultJavaScriptExecutor) driver).shutdown();
	}

	@Override
	public int pumpEventLoop(long timeoutMillis) {
		return ((DefaultJavaScriptExecutor) driver).pumpEventLoop(timeoutMillis);
	}
	/**
	 * Method to return the RemoteWebDriver session ID
	 * @return RemotWebDriver session ID as a String
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/remote/RemoteWebDriver.html#getSessionId()
	 */
	public String getSessionId() {
		return ((RemoteWebDriver) driver).getSessionId().toString();
	}
	/**
	 * Method to set the capabilities for a driver, based on the browser type
	 * @param caps - Selenium DesiredCapabilities to be used to generate a WebDriver
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/firefox/FirefoxDriver.html
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/ie/InternetExplorerDriver.html
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/chrome/ChromeDriver.html
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/safari/SafariDriver.html
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/htmlunit/HtmlUnitDriver.html
	 * @see ://msdn.microsoft.com/en-us/library/mt188085(v=vs.85).aspx
	 */
	private void setDriverWithCapabilties(DesiredCapabilities caps) {
		switch (caps.getBrowserName().toLowerCase()) {
		case "firefox":
			driver = new FirefoxDriver(caps);
			break;
		case "internet explorer":
		case "ie":
			driver = new InternetExplorerDriver(caps);
			break;
		case "chrome":
			driver = new ChromeDriver(caps);
			break;

		case "safari":
			driver = new SafariDriver(caps);
			break;
		case "htmlunit":
		case "html":
			driver = new HtmlUnitDriver(true);
			break;

		case "edge":
		case "microsoftedge":
			driver = new EdgeDriver(caps);
			break;
		default:
			break;
		}
	}
	/**
	 * Method to return the Selenium DesiredCapabilities
	 * @return Selenium DesiredCapabilitie
	 */
	public Capabilities getDriverCapability() {
		return new Capabilities();
	}
	/**
	 * Method to take a screen shot
	 * @param target - image type to capture the screenshot
	 * @return Object which si stored information about the screenshot
	 * @see ://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/TakesScreenshot.html#getScreenshotAs-org.openqa.selenium.OutputType-
	 */
	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		return ((TakesScreenshot) driver).getScreenshotAs(target);
	}
	/**
	 * Subclass to assist with interacting with a RemoteWebDriver
	 * @author Justin Phlegar
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/remote/RemoteWebDriver.html#getCapabilities()
	 * @see ://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/Capabilities.html
	 */
	public class Capabilities {

		public String browserName() {
			if(driver instanceof HtmlUnitDriver) return ((HtmlUnitDriver) driver).getCapabilities().getBrowserName();
			return ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
		}

		public String browserVersion() {
			if(driver instanceof HtmlUnitDriver) return ((HtmlUnitDriver) driver).getCapabilities().getVersion();
			return ((RemoteWebDriver) driver).getCapabilities().getVersion();
		}

		public String platformOS() {
			if(driver instanceof HtmlUnitDriver) {
				return ((HtmlUnitDriver) driver).getCapabilities().getPlatform().name() + " "
						+ ((HtmlUnitDriver) driver).getCapabilities().getPlatform().getMajorVersion() + "."
						+ ((HtmlUnitDriver) driver).getCapabilities().getPlatform().getMinorVersion();
			}
			return ((RemoteWebDriver) driver).getCapabilities().getPlatform().name() + " "
					+ ((RemoteWebDriver) driver).getCapabilities().getPlatform().getMajorVersion() + "."
					+ ((RemoteWebDriver) driver).getCapabilities().getPlatform().getMinorVersion();
		}

	}
	/**
	 * Method to return an Orasi <b><i>ByAngular.BaseBy</i></b> locator for a given Orasi <b><i>ByNG</i></b> locator
	 * @param by - Orasi <b><i>ByNG</i></b> locator
	 * @return Orasi <b><i>ByAngular.BaseBy</i></b> locator
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/by/angular/ByNG.java
	 * @see ://github.com/Orasi/Selenium-Java-Core/blob/master/src/main/java/com/orasi/core/by/angular/internal/ByAngular.java
	 */
	@SuppressWarnings("static-access")
	private ByAngular.BaseBy getByNGType(ByNG by) {
		String text = by.toString().replace("By.buttonText:", "").trim();
		if (by instanceof ByNGButton)
			return new ByAngular(getDriver()).buttonText(text);
		if (by instanceof ByNGController)
			return new ByAngular(getDriver()).controller(text);
		if (by instanceof ByNGModel)
			return new ByAngular(getDriver()).model(text);
		if (by instanceof ByNGRepeater)
			return new ByAngular(getDriver()).repeater(text);
		if (by instanceof ByNGShow)
			return new ByAngular(getDriver()).show(text);
		return null;
	}
	/**
	 * Method that return the <b><i>Page</i></b> class
	 * @return <b><i>Page</i></b> class
	 */
	public Page page() {
		return new Page();
	}

	/**
	 * This class contains methods that hook into the PageLoaded class, allowing for compact usage with the driver
	 * Example usages provided with each method contained within.
	 * @author Waightstill W Avery
	 */
	public class Page {
		/*
		 * isDomInteractive
		 */
		/**
		 * Method that determines when/if the DOM is interactive
		 * Example usage: getDriver().page().isDomInteractive()
		 * @return - boolean true if interactive, false otherwise
		 */
		public boolean isDomInteractive() {
			return  PageLoaded.isDomInteractive(getOrasiDriver());
		}
		/**
		 * Method that determines when/if the DOM is interactive
		 * Example usage: getDriver().page().isDomComplete(oDriver);
		 * @param oDriver - current OrasiDriver
		 * @return - boolean true if interactive, false otherwise
		 */
		public boolean isDomInteractive(OrasiDriver oDriver) {
			return  PageLoaded.isDomInteractive(oDriver);
		}
		/**
		 * Method that determines when/if the DOM is interactive
		 * Example usage: getDriver().page().initializePage(oDriver, timeout);
		 * @param oDriver - current OrasiDriver
		 * @param timeout - user-defined timeout to allow the DOM to become interactive
		 * @return - boolean true if interactive, false otherwise
		 */
		public boolean isDomInteractive(OrasiDriver oDriver, int timeout) {
			return  PageLoaded.isDomInteractive(oDriver, timeout);
		}

		/*
		 * isAngularComplete
		 */
		/**
		 * Method that determine when/if an AngularJS page is complete
		 * Example usage: getDriver().page().isAngularComplete();
		 */
		public void isAngularComplete() {
			 PageLoaded.isAngularComplete(getOrasiDriver());
		}

		/*
		 * isDomComplete
		 */
		/**
		 * Method that determines when/if the DOM is complete
		 * Example usage: getDriver().page().isDomComplete();
		 * @return - boolean true if complete, false otherwise
		 */
		public boolean isDomComplete() {
			return PageLoaded.isDomComplete(getOrasiDriver());
		}
		/**
		 * Method that determines when/if the DOM is complete
		 * Example usage: getDriver().page().isDomComplete(oDriver);
		 * @param oDriver - current OrasiDriver
		 * @return - boolean true if complete, false otherwise
		 */
		public boolean isDomComplete(OrasiDriver oDriver) {
			return  PageLoaded.isDomComplete(getOrasiDriver());
		}
		/**
		 * Method that determines when/if the DOM is complete
		 * Example usage: getDriver().page().isDomComplete(oDriver, timeout);
		 * @param oDriver - current OrasiDriver
		 * @param timeout - user-defined timeout to allow the DOM to become interactive
		 * @return - boolean true if complete, false otherwise
		 * @return
		 */
		public boolean isDomComplete(OrasiDriver oDriver, int timeout) {
			return  PageLoaded.isDomComplete(getOrasiDriver(), timeout);
		}

		/*
		 * syncPresent
		 */
		/**
		 * Method that determines if an element is present in the DOM
		 * Example usage: getDriver().page().syncPresent(oDriver, element);
		 * @param oDriver - current OrasiDriver
		 * @param element - element for which to be searched
		 * @return - boolean true if present, false otherwise
		 */
		public boolean syncPresent(OrasiDriver oDriver, Element element) {
			return PageLoaded.syncPresent(oDriver, element);
		}
		/**
		 * Method that determines if an element is present in the DOM
		 * Example usage: getDriver().page().syncPresent(oDriver, timeout, element);
		 * @param oDriver - current OrasiDriver
		 * @param timeout - amount of time to wait for the element to be present
		 * @param element - element for which to be searched
		 * @return - boolean true if present, false otherwise
		 */
		public boolean syncPresent(OrasiDriver oDriver, int timeout, Element element) {
			return PageLoaded.syncPresent(oDriver, timeout, element);
		}
		/**
		 * Method that determines if an element is present in the DOM
		 * Example usage: getDriver().page().syncPresent(oDriver, timeout, returnError, element);
		 * @param oDriver - current OrasiDriver
		 * @param timeout - amount of time to wait for the element to be present
		 * @param returnError - boolean true if and error is to be thrown if the element is not present, false otherwise
		 * @param element - element for which to be searched
		 * @return - boolean true if present, false otherwise
		 */
		public boolean syncPresent(OrasiDriver oDriver, int timeout, boolean returnError, Element element) {
			return PageLoaded.syncPresent(oDriver, timeout, returnError, element);
		}

		/*
		 * syncVisible
		 */
		/**
		 * Method that determines if an element is visible on the screen
		 * Example usage: getDriver().page().syncVisible(oDriver, element);
		 * @param oDriver - current OrasiDriver
		 * @param element - element for which to be searched
		 * @return - boolean true if visible, false otherwise
		 */
		public boolean syncVisible(OrasiDriver oDriver, Element element) {
			return PageLoaded.syncVisible(oDriver, element);
		}
		/**
		 * Method that determines if an element is visible on the screen
		 * Example usage: getDriver().page().syncVisible(oDriver, timeout, element);
		 * @param oDriver - current OrasiDriver
		 * @param timeout - amount of time to wait for the element to be visible on the screen
		 * @param element - element for which to be searched
		 * @return - boolean true if visible, false otherwise
		 */
		public boolean syncVisible(OrasiDriver oDriver, int timeout, Element element) {
			return PageLoaded.syncVisible(oDriver, timeout, true, element);
		}
		/**
		 * Method that determines if an element is visible on the screen
		 * Example usage: getDriver().page().syncVisible(oDriver, timeout, returnError, element);
		 * @param oDriver - current OrasiDriver
		 * @param timeout - amount of time to wait for the element to be visible on the screen
		 * @param returnError - boolean true if and error is to be thrown if the element is not visible, false otherwise
		 * @param element - element for which to be searched
		 * @return - boolean true if visible, false otherwise
		 */
		public boolean syncVisible(OrasiDriver oDriver, int timeout, boolean returnError, Element element) {
			return PageLoaded.syncVisible(oDriver, timeout, returnError, element);
		}

		/*
		 * syncHidden
		 */
		/**
		 * Method that determines if an element is hidden on the screen
		 * Example usage: getDriver().page().syncHidden(oDriver, element);
		 * @param oDriver - current OrasiDriver
		 * @param element - element for which to be searched
		 * @return - boolean true if hidden, false otherwise
		 */
		public boolean syncHidden(OrasiDriver oDriver, Element element) {
			return PageLoaded.syncHidden(oDriver, oDriver.getElementTimeout(), element);
		}
		/**
		 * Method that determines if an element is hidden on the screen
		 * Example usage: getDriver().page().syncHidden(oDriver, timeout, element);
		 * @param oDriver - current OrasiDriver
		 * @param timeout - amount of time to wait for the element to be hidden on the screen
		 * @param element - element for which to be searched
		 * @return - boolean true if hidden, false otherwise
		 */
		public boolean syncHidden(OrasiDriver oDriver, int timeout, Element element) {
			return PageLoaded.syncHidden(oDriver, timeout, true, element);
		}
		/**
		 * Method that determines if an element is hidden on the screen
		 * Example usage: getDriver().page().syncHidden(oDriver, timeout, returnError, element);
		 * @param oDriver - current OrasiDriver
		 * @param timeout - amount of time to wait for the element to be hidden on the screen
		 * @param returnError - boolean true if and error is to be thrown if the element is not hidden, false otherwise
		 * @param element - element for which to be searched
		 * @return - boolean true if hidden, false otherwise
		 */
		public boolean syncHidden(OrasiDriver oDriver, int timeout, boolean returnError, Element element) {
			return PageLoaded.syncHidden(oDriver, timeout, returnError, element);
		}

		/*
		 * syncEnabled
		 */
		/**
		 * Method that determines if an element is clickable
		 * Example usage: getDriver().page().syncEnabled(oDriver, element);
		 * @param oDriver - current OrasiDriver
		 * @param element - element for which to be searched
		 * @return - boolean true if clickable, false otherwise
		 */
		public boolean syncEnabled(OrasiDriver oDriver, Element element) {
			return PageLoaded.syncEnabled(oDriver, oDriver.getElementTimeout(), element);
		}
		/**
		 * Method that determines if an element is clickable
		 * Example usage: getDriver().page().syncEnabled(oDriver, timeout, element);
		 * @param oDriver - current OrasiDriver
		 * @param timeout - amount of time to wait for the element to be clickable
		 * @param element - element for which to be searched
		 * @return - boolean true if clickable, false otherwise
		 */
		public boolean syncEnabled(OrasiDriver oDriver, int timeout, Element element) {
			return PageLoaded.syncEnabled(oDriver, timeout, true, element);
		}
		/**
		 * Method that determines if an element is clickable
		 * Example usage: getDriver().page().syncEnabled(oDriver, timeout, returnError, element);
		 * @param oDriver - current OrasiDriver
		 * @param timeout - amount of time to wait for the element to be not clickable
		 * @param returnError - boolean true if and error is to be thrown if the element is not clickable, false otherwise
		 * @param element - element for which to be searched
		 * @return - boolean true if clickable, false otherwise
		 */
		public boolean syncEnabled(OrasiDriver oDriver, int timeout, boolean returnError, Element element) {
			return syncEnabled(oDriver, timeout, returnError, element);
		}

		/*
		 * syncDisabled
		 */
		/**
		 * Method that determines if an element is not clickable
		 * Example usage: getDriver().page().syncDisabled(oDriver, element);
		 * @param oDriver - current OrasiDriver
		 * @param element - element for which to be searched
		 * @return - boolean true if not clickable, false otherwise
		 */
		public boolean syncDisabled(OrasiDriver oDriver, Element element) {
			return PageLoaded.syncDisabled(oDriver, oDriver.getElementTimeout(), element);
		}
		/**
		 * Method that determines if an element is not clickable
		 * Example usage: getDriver().page().syncDisabled(oDriver, timeout, element);
		 * @param oDriver - current OrasiDriver
		 * @param timeout - amount of time to wait for the element to be not clickable
		 * @param element - element for which to be searched
		 * @return - boolean true if not clickable, false otherwise
		 */
		public boolean syncDisabled(OrasiDriver oDriver, int timeout, Element element) {		
			return PageLoaded.syncDisabled(oDriver, timeout, true, element);
		}
		/**
		 * Method that determines if an element is not clickable
		 * Example usage: getDriver().page().syncDisabled(oDriver, timeout, returnError, element);
		 * @param oDriver - current OrasiDriver
		 * @param timeout - amount of time to wait for the element to be not clickable
		 * @param returnError - boolean true if and error is to be thrown if the element is clickable, false otherwise
		 * @param element - element for which to be searched
		 * @return - boolean true if not clickable, false otherwise
		 */
		public boolean syncDisabled(OrasiDriver oDriver, int timeout, boolean returnError, Element element) {
			return PageLoaded.syncDisabled(oDriver, timeout, returnError, element);
		}

		/*
		 * syncTextInElement
		 */
		/**
		 * Method that determines if an element contains user-defined text
		 * Example usage: getDriver().page().syncTextInElement(oDriver, text, element);
		 * @param oDriver - current OrasiDriver
		 * @param text - text for which to search
		 * @param element - element for which to be searched
		 * @return - boolean true if the element contains the text, false otherwise
		 */
		public boolean syncTextInElement(OrasiDriver oDriver, String text, Element element) {
			return PageLoaded.syncTextInElement(oDriver, text, oDriver.getElementTimeout(), element);
		}
		/**
		 * Method that determines if an element contains user-defined text
		 * Example usage: getDriver().page().syncTextInElement(oDriver, text, timeout, element);
		 * @param oDriver - current OrasiDriver
		 * @param text - text for which to search
		 * @param timeout - amount of time to wait for the element to contain the text
		 * @param element - element for which to be searched
		 * @return - boolean true if the element contains the text, false otherwise
		 */
		public boolean syncTextInElement(OrasiDriver oDriver, String text, int timeout, Element element) {
			return PageLoaded.syncTextInElement(oDriver, text, timeout, true, element);
		}
		/**
		 * Method that determines if an element contains user-defined text
		 * Example usage: getDriver().page().syncTextInElement(oDriver, text, timeout, returnError, element);
		 * @param oDriver - current OrasiDriver
		 * @param text - text for which to search
		 * @param timeout - amount of time to wait for the element to contain the text
		 * @param returnError - boolean true if and error is to be thrown if the element contains the text, false otherwise
		 * @param element - element for which to be searched
		 * @return - boolean true if the element contains the text, false otherwise
		 */
		public boolean syncTextInElement(OrasiDriver oDriver, String text, int timeout, boolean returnError,
				Element element) {
			return PageLoaded.syncTextInElement(oDriver, text, timeout, returnError, element);
		}

		/*
		 * initializePage
		 */
		/**
		 * Method that (re)initializes all of the elements for a given page
		 * class. This is useful to stave off the Selenium StaleElementException
		 * Example usage: getDriver().page().initializePage(clazz);
		 * @param clazz - page class for which to (re)initialize all elements
		 */
		public void initializePage(Class<?> clazz) {
			PageLoaded.initializePage(clazz);
		}
		
		/**
		 * Method that (re)initializes all of the elements for a given page
		 * class. This is useful to stave off the Selenium StaleElementException
		 * Example usage: getDriver().page().initializePage(clazz, oDriver);
		 * @param clazz - page class for which to (re)initialize all elements
		 * @param oDriver - current OrasiDriver
		 */
		public void initializePage(Class<?> clazz, OrasiDriver oDriver) {
			PageLoaded.initializePage(clazz, oDriver);
		}
	}

	/**
	 * Method to return this OrasiDriver class
	 * @return - current OrasiDriver
	 */
	private OrasiDriver getOrasiDriver() {
		return this;
	}
}
