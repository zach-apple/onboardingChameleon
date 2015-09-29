package com.orasi.utils;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.javascript.background.DefaultJavaScriptExecutor;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptJobManager;
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

public class OrasiDriver implements WebDriver,   JavaScriptExecutor {
    /**
     * 
     */
    private static final long serialVersionUID = -657563735440878909L;
    private WebDriver driver;
    
    public OrasiDriver(DesiredCapabilities caps){
	setDriverWithCapabilties(caps);	
     }
    

    public OrasiDriver(DesiredCapabilities caps, URL url){
	driver = new RemoteWebDriver(url, caps);
     }


    public WebDriver getDriver(){
	return driver;
    }
    
    @Override
    public void get(String url) {
	driver.get(url);
	
    }

    @Override
    public String getCurrentUrl() {
	return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {	
	return driver.getTitle();
    }

   /* public List<Element> findElements(By by) {
	List<WebElement> webElements = driver.findElements(by);
	List test = webElements;
	List<Element> elements= (List<Element>)test;
	return elements;
    }*/

    @Override
    public List<WebElement> findElements(By by) {
	return findWebElements(by);
    }
    
    public List<WebElement> findWebElements(By by){
	return driver.findElements(by);
    }
    
    @Override
    public Element findElement(By by) {
	return new ElementImpl(driver.findElement(by));
    }

    public Textbox findTextbox(By by) {
	return new TextboxImpl(driver.findElement(by));
    }
    
    public Button findButton(By by) {
	return new ButtonImpl(driver.findElement(by));
    }
    
    public Checkbox findCheckbox(By by) {
	return new CheckboxImpl(driver.findElement(by));
    }
    
    public Label findLabel(By by) {
	return new LabelImpl(driver.findElement(by));
    }
    
    public Link findLink(By by) {
	return new LinkImpl(driver.findElement(by));
    }
    
    public Listbox findListbox(By by) {
	return new ListboxImpl(driver.findElement(by));
    }
    
    public RadioGroup findRadioGroup(By by) {
	return new RadioGroupImpl(driver.findElement(by));
    }
    
    public Webtable findWebtable(By by) {
	return new WebtableImpl(driver.findElement(by));
    }
    public WebElement findWebElement(By by) {
	return driver.findElement(by);
    }
    
    @Override
    public String getPageSource() {
	return driver.getPageSource();
    }

    @Override
    public void close() {
	driver.close();
    }

    @Override
    public void quit() {
	driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
	return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
	return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
	return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
	return driver.navigate();
    }

    @Override
    public Options manage() {
	return driver.manage();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
	return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
	return super.equals(obj);
    }

    @Override
    protected void finalize() throws Throwable {
	super.finalize();
    }

    @Override
    public int hashCode() {
	return super.hashCode();
    }

    @Override
    public String toString() {
	return super.toString();
    }
   
    
    public Object executeJavaScript(String script, Object... parameters) {
        return ((JavascriptExecutor) driver).executeScript(script, parameters);
    }
    
    public Object executeAsyncJavaScript(String script, Object... parameters) {
        return ((JavascriptExecutor) driver).executeAsyncScript(script, parameters);
    }

    
    public void run() {
	((DefaultJavaScriptExecutor) driver).run();
    }

    public void addWindow(WebWindow newWindow) {
	((DefaultJavaScriptExecutor) driver).addWindow(newWindow);
    }

    
    public void shutdown() {
	((DefaultJavaScriptExecutor) driver).shutdown();
    }

    
    public int pumpEventLoop(long timeoutMillis) {
	return ((DefaultJavaScriptExecutor) driver).pumpEventLoop(timeoutMillis);
    }

    private void setDriverWithCapabilties(DesiredCapabilities caps){
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

}
