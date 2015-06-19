package com.orasi.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.Button;
import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.impl.ButtonImpl;
import com.orasi.core.interfaces.impl.ElementImpl;
import com.orasi.utils.TestEnvironment;

public class TestElement extends TestEnvironment{
        
    @BeforeTest(groups ={"regression", "interfaces", "element", "dev"})
    @Parameters({ "runLocation", "browserUnderTest", "browserVersion",
	    "operatingSystem", "environment" })
    public void setup(@Optional String runLocation, String browserUnderTest,
	    String browserVersion, String operatingSystem, String environment) {
	setApplicationUnderTest("Test Site");
	setBrowserUnderTest(browserUnderTest);
	setBrowserVersion(browserVersion);
	setOperatingSystem(operatingSystem);
	setRunLocation(runLocation);
	setTestEnvironment(environment);
	setPageURL("http://www.cs.tut.fi/~jkorpela/www/testel.html");
	testStart("TestElement");
    }
    
    @AfterTest(groups ={"regression", "interfaces", "element", "dev"})
    public void close(){
	endTest("TestElement");
    }

    @Test(groups ={"regression", "interfaces", "element"}, dependsOnMethods="getAttribute")
    public void clear(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	element.sendKeys("testing");
	element.clear();
	Assert.assertTrue(element.getAttribute("value").equals(""));
    }
    
    @Test(groups ={"regression", "interfaces", "element"}, dependsOnMethods="clear") 
    public void click(){
	Element element = new ElementImpl(getDriver().findElement(By.name("reset")));
	element.click();
	Assert.assertTrue(new ElementImpl(getDriver().findElement(By.id("f1"))).getAttribute("value").equals("Default text."));
    }
        
    @Test(groups ={"regression", "interfaces", "element"})
    public void elementWired(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertTrue(element.elementWired());
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getAttribute(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertTrue(element.getAttribute("type").equals("text"));
    }
  
    @Test(groups ={"regression", "interfaces", "element"})
    public void getCoordinates(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertTrue(element.getCoordinates().onPage().getX() > 0);
	Assert.assertTrue(element.getCoordinates().onPage().getY() > 0);
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getCssValue(){
	Element element= new ElementImpl(getDriver().findElement(By.name("reset")));
	if(getBrowserUnderTest().equalsIgnoreCase("html")) Assert.assertTrue(element.getCssValue("width").equals("auto"));  
	else Assert.assertTrue(element.getCssValue("font-family").equalsIgnoreCase("MS Shell Dlg")); 
    }
     
    @Test(groups ={"regression", "interfaces", "element"})
    public void getElementIdentifier(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertTrue(element.getElementIdentifier().equals("f1"));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getElementLocator(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertNotNull(element.getElementLocator());
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getElementLocatorInfo(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertNotNull(element.getElementLocatorInfo());
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getLocation(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertTrue(element.getLocation().getX() > 0);
	Assert.assertTrue(element.getLocation().getY() > 0);
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getSize(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertTrue(element.getSize().getHeight() > 0);
	Assert.assertTrue(element.getSize().getWidth() > 0);
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getTagName(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertTrue(element.getTagName().equals("input"));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getText(){
	Element element= new ElementImpl(getDriver().findElement(By.xpath("//a[@href='../index.html']")));
	Assert.assertTrue(element.getText().equals("main page"));
    }    
    
    @Test(groups ={"regression", "interfaces", "element"}, dependsOnMethods="getCssValue")
    public void highlight(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f2")));
	element.highlight(getDriver());
	Assert.assertTrue(element.getCssValue("border").contains("red"));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void isDisplayed(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertTrue(element.isDisplayed());
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void isEnabled(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertTrue(element.isEnabled());
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void isSelected(){
	Element element= new ElementImpl(getDriver().findElement(By.xpath("//*[@id='f10']/option[2]")));
	Assert.assertTrue(element.isSelected());
    }
    
    @Test(groups ={"regression",  "element"})
    public void isSelectedNegative(){
	Element element= new ElementImpl(getDriver().findElement(By.xpath("//*[@id='f10']/option[3]")));
	Assert.assertFalse(element.isSelected());
    }    
    
    @Test(groups ={"regression", "interfaces", "element"}, dependsOnMethods="click") 
    public void jsClick(){
	//getDriver().findElement(By.id("f1")).sendKeys("blah");
	Element element= new ElementImpl(getDriver().findElement(By.name("reset")));
	element.jsClick(getDriver());
	Assert.assertTrue(getDriver().findElement(By.id("f1")).getAttribute("value").equals("Default text."));
    }

    @Test(groups ={"regression", "interfaces", "element"}, dependsOnMethods="click")
    public void sendKeys(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	element.sendKeys("testing");
	Assert.assertTrue(element.getAttribute("value").equals("Default text.testing"));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void syncPresentBasic(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertTrue(element.syncPresent(getDriver()));
    }
    
    @Test(groups ={"regression",  "element"})
    public void syncPresentNegative(){
	boolean pass = false;
	try{
	    Element element= new ElementImpl(getDriver().findElement(By.id("NoElement")));
	    element.syncPresent(getDriver());
	} catch (RuntimeException rte){
	  pass = true;
	}
	
	Assert.assertTrue(pass);
    }
    
    @Test(groups ={"regression", "element"})
    public void syncPresentAdvanced(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertTrue(element.syncPresent(getDriver(), 5, false));
    }

    @Test(groups ={"regression", "interfaces", "element"} , dependsOnMethods={"jsClick","syncPresentAdvanced", "syncPresentNegative","syncPresentBasic","syncHiddenBasic","syncHiddenBasicNegative","syncHiddenAdvanced"})
    public void syncDisabledBasic(){
	
	getDriver().get("http://www.javascriptkit.com/javatutors/deform3.shtml");
	Element element= new ElementImpl(getDriver().findElement(By.name("B1")));
	Assert.assertTrue(element.syncDisabled(getDriver()));
    }
    
    @Test(groups ={"regression", "element"}, dependsOnMethods={"syncDisabledBasic"})
    public void syncDisabledBasicNegative(){
	boolean pass = false;
	try{
	    Element element= new ElementImpl(getDriver().findElement(By.name("T1")));
	    element.syncDisabled(getDriver());
	} catch (RuntimeException rte){
	  pass = true;
	}
	
	Assert.assertTrue(pass);
    }
    
    @Test(groups ={"regression", "element"}, dependsOnMethods={"syncDisabledBasic"})
    public void syncDisabledAdvanced(){
	Element element= new ElementImpl(getDriver().findElement(By.name("B1")));
	Assert.assertTrue(element.syncDisabled(getDriver(), 5, false));
    }
    
    @Test(groups ={"regression", "element"}, dependsOnMethods={"syncDisabledBasic"})
    public void syncDisabledAdvancedNegative(){
	Element element= new ElementImpl(getDriver().findElement(By.name("T1")));
	Assert.assertFalse(element.syncDisabled(getDriver(),1, false));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void syncHiddenBasic(){
	Element element= new ElementImpl(getDriver().findElement(By.name("hidden field")));
	Assert.assertTrue(element.syncHidden(getDriver()));
    }
    
    @Test(groups ={"regression", "element"})
    public void syncHiddenBasicNegative(){
	boolean pass = false;
	try{
	    Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	    element.syncHidden(getDriver());
	} catch (RuntimeException rte){
	  pass = true;
	}
	
	Assert.assertTrue(pass);
    }
    
    @Test(groups ={"regression", "element"})
    public void syncHiddenAdvanced(){
	Element element= new ElementImpl(getDriver().findElement(By.name("hidden field")));
	Assert.assertTrue(element.syncHidden(getDriver(), 5, false));
    }

    @Test(groups ={"regression", "element"})
    public void syncHiddenAdvancedNegative(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertFalse(element.syncHidden(getDriver(),1, false));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void syncVisibleBasic(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertTrue(element.syncVisible(getDriver()));
    }
    
    @Test(groups ={"regression", "element"})
    public void syncVisibleBasicNegative(){	
	boolean pass = false;
	try{
	    Element element= new ElementImpl(getDriver().findElement(By.name("hidden field")));
	    element.syncVisible(getDriver());
	} catch (RuntimeException rte){
	  pass = true;
	}
	
	Assert.assertTrue(pass);
    }
    
    @Test(groups ={"regression", "element"})
    public void syncVisibleAdvanced(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertTrue(element.syncVisible(getDriver(), 5, false));
    }
    
    @Test(groups ={"regression", "element"})
    public void syncVisibleAdvancedNegative(){
	Element element= new ElementImpl(getDriver().findElement(By.name("hidden field")));
	Assert.assertFalse(element.syncVisible(getDriver(),1, false));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void syncEnabledBasic(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertTrue(element.syncEnabled(getDriver()));
    }
    
    @Test(groups ={"regression", "element"}, dependsOnMethods={"syncDisabledAdvancedNegative"})
    public void syncEnabledBasicNegative(){
	boolean pass = false;
	try{
	    Element element= new ElementImpl(getDriver().findElement(By.name("B1")));
	    element.syncEnabled(getDriver());
	} catch (RuntimeException rte){
	  pass = true;
	}
	
	Assert.assertTrue(pass);
    }
    
    @Test(groups ={"regression", "element"})
    public void syncEnabledAdvanced(){
	Element element= new ElementImpl(getDriver().findElement(By.id("f1")));
	Assert.assertTrue(element.syncEnabled(getDriver(), 5, false));
    }
    
    @Test(groups ={"regression", "element"}, dependsOnMethods={"syncDisabledAdvancedNegative"})
    public void syncEnabledAdvancedNegative(){
	Element element = new ElementImpl(getDriver().findElement(By.name("B1")));
	Assert.assertFalse(element.syncEnabled(getDriver(),1, false));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void syncTextInElementBasic(){
	Element element= new ElementImpl(getDriver().findElement(By.xpath("//a[@href='../index.html']")));
	Assert.assertTrue(element.syncTextInElement(getDriver(), "main page"));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void syncTextInElementBasicNegative(){
	boolean pass = false;
	try{
	    Element element= new ElementImpl(getDriver().findElement(By.xpath("//a[@href='../index.html']")));
	    element.syncTextInElement(getDriver(), "Loading");
	} catch (RuntimeException rte){
	  pass = true;
	}
	
	Assert.assertTrue(pass);
    }
    
    @Test(groups ={"regression", "element"})
    public void syncTextInElementAdvanced(){
	Element element= new ElementImpl(getDriver().findElement(By.xpath("//a[@href='../index.html']")));
	Assert.assertTrue(element.syncTextInElement(getDriver(), "main page", 5, false));
    }
    
    @Test(groups ={"regression", "element"})
    public void syncTextInElementAdvancedNegative(){
	Element element= new ElementImpl(getDriver().findElement(By.xpath("//a[@href='../index.html']")));
	Assert.assertFalse(element.syncTextInElement(getDriver(), "negative", 2, false));
    } 
}
