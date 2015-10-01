package com.orasi.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.hamcrest.core.IsNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.Button;
import com.orasi.core.interfaces.Checkbox;
import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.Label;
import com.orasi.core.interfaces.Link;
import com.orasi.core.interfaces.Listbox;
import com.orasi.core.interfaces.RadioGroup;
import com.orasi.core.interfaces.Textbox;
import com.orasi.core.interfaces.Webtable;

public class TestOrasiDriver{
    DesiredCapabilities caps = null;
    OrasiDriver driver = null;
    File file = null;	
    String runLocation = "";
    String browserUnderTest = "";
    String browserVersion = "";
    String operatingSystem = "";
    String environment = "";
    
    @BeforeTest(groups ={"regression", "utils", "orasidriver"})
    @Parameters({ "runLocation", "browserUnderTest", "browserVersion",
	    "operatingSystem", "environment" })
    public void setup(@Optional String runLocation, String browserUnderTest,
	    String browserVersion, String operatingSystem, String environment) {
	if (browserUnderTest.equalsIgnoreCase("jenkinsParameter")) {
	    browserUnderTest = System.getProperty("jenkinsBrowser").trim();
	} else{
	    this.browserUnderTest = browserUnderTest;
	}
	
	if (browserUnderTest.equalsIgnoreCase("jenkinsParameter")) {
	    browserVersion = System.getProperty("jenkinsBrowserVersion").trim();
	} else{
	    this.browserVersion = browserVersion;
	}
	
	if (operatingSystem.equalsIgnoreCase("jenkinsParameter")) {
	    operatingSystem = System.getProperty("jenkinsOperatingSystem").trim();
	} else{
	    this.operatingSystem = operatingSystem;
	}
	
	if (runLocation.equalsIgnoreCase("jenkinsParameter")) {
	    runLocation = System.getProperty("jenkinsRunLocation").trim();
	} else{
	    this.runLocation = runLocation;
	}    
	
	this.environment = environment;
	caps = new DesiredCapabilities(browserUnderTest, browserVersion, Platform.valueOf(operatingSystem.toUpperCase()));
	caps.setCapability("ignoreZoomSetting", true);
	caps.setCapability("enablePersistentHover", false);
	if(runLocation.toLowerCase().equals("local")){
	    driver = new OrasiDriver(caps);
	    
	}else{
	    TestEnvironment te = new TestEnvironment("",browserUnderTest,browserVersion, operatingSystem,runLocation,environment);
	    try {
		driver = new OrasiDriver(caps, new URL(te.getRemoteURL()));
	    } catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	driver.get("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/core/interfaces/testsite.html");
	
	//testStart("TestOrasiDriver");
    }
    
    @AfterTest(groups ={"regression", "utils", "orasidriver"})
    public void close(){
	driver.close();
    }

    @Test(groups={"regression", "utils", "orasidriver"})
    public void getDriver(){
	Assert.assertNotNull(driver.getDriver());
    }

    @Test(groups={"regression", "utils", "orasidriver"})
    public void executeJavaScript(){
	WebElement element = (WebElement) driver.executeJavaScript("return document.getElementById('FirstName')");
	Assert.assertNotNull(element);
    }
    
    @Test(groups={"regression", "utils", "orasidriver"}, dependsOnMethods="getDriver")
    public void findButton(){
	Button button = driver.findButton(By.id("Add"));
	Assert.assertNotNull(button);
    }

    @Test(groups={"regression", "utils", "orasidriver"}, dependsOnMethods="findButton")
    public void findElement(){
	Element element= driver.findElement(By.id("FirstName"));
	Assert.assertNotNull(element);
    }    

    @Test(groups={"regression", "utils", "orasidriver"}, dependsOnMethods="findElement")
    public void findListbox(){
	Listbox listbox= driver.findListbox(By.id("Category"));
	Assert.assertNotNull(listbox);
    }

    @Test(groups={"regression", "utils", "orasidriver"}, dependsOnMethods="findListbox")
    public void findTextbox(){
	Textbox textbox = driver.findTextbox(By.id("FirstName"));
	Assert.assertNotNull(textbox);
    }
    
    @Test(groups={"regression", "utils", "orasidriver"}, dependsOnMethods="findTextbox")
    public void findWebtable(){
	Webtable webtable = driver.findWebtable(By.id("VIPs"));
	Assert.assertNotNull(webtable);
    }

    @Test(groups={"regression", "utils", "orasidriver"}, dependsOnMethods="findWebtable")
    public void findRadioGroup(){
	driver.get("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/core/interfaces/radioGroup.html");
	RadioGroup radioGroup= driver.findRadioGroup(By.id("Content"));
	Assert.assertNotNull(radioGroup);
    }
    
    @Test(groups={"regression", "utils", "orasidriver"}, dependsOnMethods="findRadioGroup")
    public void findCheckbox(){
	driver.get("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/core/interfaces/checkbox.html");
	Checkbox checkbox= driver.findCheckbox(By.name("checkbox"));
	Assert.assertNotNull(checkbox);
    }

    @Test(groups={"regression", "utils", "orasidriver"}, dependsOnMethods="findCheckbox")
    public void findLabel(){
	driver.get("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/core/interfaces/label.html");
	Label label= driver.findLabel(By.xpath("//*[@id='radioForm']/label[1]"));
	Assert.assertNotNull(label);
    }
    

    @Test(groups={"regression", "utils", "orasidriver"}, dependsOnMethods="findLabel")
    public void findLink(){
	driver.get("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/core/interfaces/link.html");
	Link link= driver.findLink(By.xpath("//a[@href='testLinks.html']"));
	Assert.assertNotNull(link);
    }    

    @Test(groups={"regression", "utils", "orasidriver"}, dependsOnMethods="findLink")
    public void executeAsyncJavaScript(){
	if(browserUnderTest.toLowerCase().equals("html")) throw new SkipException("Test not valid for HTMLUnitDriver");
	driver.get("https://builtwith.angularjs.org/");
	driver.executeAsyncJavaScript("var callback = arguments[arguments.length - 1];angular.element(document.body).injector().get('$browser').notifyWhenNoOutstandingRequests(callback);");
    }
    
}
