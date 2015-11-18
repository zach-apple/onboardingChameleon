package com.orasi.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.hamcrest.core.IsNull;
import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.configuration.BrowserName;
import com.orasi.core.interfaces.Button;
import com.orasi.core.interfaces.Checkbox;
import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.Label;
import com.orasi.core.interfaces.Link;
import com.orasi.core.interfaces.Listbox;
import com.orasi.core.interfaces.RadioGroup;
import com.orasi.core.interfaces.Textbox;
import com.orasi.core.interfaces.Webtable;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.saucerest.SauceREST;

public class TestOrasiDriver{
    protected ResourceBundle appURLRepository = ResourceBundle
	    .getBundle(Constants.ENVIRONMENT_URL_PATH);
    protected SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(
	    Base64Coder.decodeString(appURLRepository
		    .getString("SAUCELABS_USERNAME")),
	    Base64Coder.decodeString(appURLRepository
		    .getString("SAUCELABS_KEY")));
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
	    this.browserUnderTest = System.getProperty("jenkinsBrowser").trim();
	} else{
	    this.browserUnderTest = browserUnderTest;
	}
	
	if (browserVersion.equalsIgnoreCase("jenkinsParameter")) {
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
	caps = new DesiredCapabilities();
	caps.setCapability("ignoreZoomSetting", true);
	caps.setCapability(CapabilityType.BROWSER_NAME,this.browserUnderTest);
	caps.setCapability(CapabilityType.VERSION,browserVersion);
	caps.setCapability(CapabilityType.PLATFORM,operatingSystem);
	caps.setCapability("enablePersistentHover", false);
	caps.setCapability("name", "TestOrasiDriver");
	if(runLocation.toLowerCase().equals("local")){
	    driver = new OrasiDriver(caps);		    
	}else{
	    TestEnvironment te = new TestEnvironment("",this.browserUnderTest,browserVersion, operatingSystem,runLocation,environment);
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
    private void endSauceTest(int result)  {
  	Map<String, Object> updates = new HashMap<String, Object>();
  	updates.put("name", "TestOrasiDriver");
  	
  	
  	if (result == ITestResult.FAILURE) {
  		updates.put("passed", false);
  	} else {
  		updates.put("passed", true);
  	}

  	SauceREST client = new SauceREST(authentication.getUsername() ,authentication.getAccessKey() );
  	client.updateJobInfo(driver.getSessionId().toString(), updates);		
  	
  }
    @AfterTest(groups ={"regression", "utils", "orasidriver"})
    public void close(ITestContext testResults){
	if(runLocation.equalsIgnoreCase("sauce")){
 	    if(testResults.getFailedTests().size() == 0) {
 		endSauceTest(ITestResult.SUCCESS);
 	    }else{
 		endSauceTest(ITestResult.FAILURE);
 	    }
 	}
	driver.quit();
    }

    @Test(groups={"regression", "utils", "orasidriver"})
    public void getPageTimeout(){
	Assert.assertTrue( driver.getPageTimeout() == Constants.PAGE_TIMEOUT);
    }
    
    @Test(groups={"regression", "utils", "orasidriver"}, dependsOnMethods="getPageTimeout")
    public void setPageTimeout(){
	System.out.println(browserUnderTest);
	if(this.browserUnderTest.toLowerCase().contains("safari") || driver.toString().contains("safari") ) throw new SkipException("Test not valid for SafariDriver");
	driver.setPageTimeout(15);
	Assert.assertTrue( driver.getPageTimeout() == 15);
    }
    
    @Test(groups={"regression", "utils", "orasidriver"})
    public void getElementTimeout(){
	Assert.assertTrue( driver.getElementTimeout() == Constants.ELEMENT_TIMEOUT);
    }
    
    @Test(groups={"regression", "utils", "orasidriver"}, dependsOnMethods="getElementTimeout")
    public void setElementTimeout(){
	driver.setElementTimeout(15);
	Assert.assertTrue( driver.getElementTimeout() == 15);
    }
    
    @Test(groups={"regression", "utils", "orasidriver"})
    public void getScriptTimeout(){
	Assert.assertTrue( driver.getScriptTimeout() == Constants.DEFAULT_GLOBAL_DRIVER_TIMEOUT);
    }
    
    @Test(groups={"regression", "utils", "orasidriver"}, dependsOnMethods="getScriptTimeout")
    public void setScriptTimeout(){
	driver.setScriptTimeout(15);
	Assert.assertTrue( driver.getScriptTimeout() == 15);
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
	if(browserUnderTest.toLowerCase().equals("html") || browserUnderTest.isEmpty() ) throw new SkipException("Test not valid for HTMLUnitDriver");
	driver.get("https://builtwith.angularjs.org/");
	driver.executeAsyncJavaScript("var callback = arguments[arguments.length - 1];angular.element(document.body).injector().get('$browser').notifyWhenNoOutstandingRequests(callback);");
    }
    
}
