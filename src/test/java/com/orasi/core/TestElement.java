package com.orasi.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

import com.orasi.core.interfaces.Element;
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
	setPageURL("file:" +getClass().getClassLoader().getResource("sites/htmlTest/testApp.html").getPath());
	testStart("TestElement");
    }
    
    @AfterTest(groups ={"regression", "interfaces", "element", "dev"})
    public void close(){
	endTest("TestElement");
    }

    @Test(groups ={"regression", "interfaces", "element"}, dependsOnMethods="getAttribute")
    public void clear(){
	Element element= new ElementImpl(getDriver().findElement(By.id("FirstName")));
	element.sendKeys("testing");
	element.clear();
	Assert.assertTrue(element.getAttribute("value").equals(""));
    }
    
    @Test(groups ={"regression", "interfaces", "element"}, dependsOnMethods="getText") 
    public void click(){
	Element element = new ElementImpl(getDriver().findElement(By.id("Load")));
	element.click();
	Assert.assertTrue(new ElementImpl(getDriver().findElement(By.id("count"))).getText().equals("VIP count: 6"));
	new ElementImpl(getDriver().findElement(By.id("Clear"))).click();
	Assert.assertTrue(new ElementImpl(getDriver().findElement(By.id("count"))).getText().equals("VIP count: 0"));
    }
        
    @Test(groups ={"regression", "interfaces", "element"})
    public void elementWired(){
	Element element= new ElementImpl(getDriver().findElement(By.id("FirstName")));
	Assert.assertTrue(element.elementWired());
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getAttribute(){
	Element element= new ElementImpl(getDriver().findElement(By.id("FirstName")));
	Assert.assertTrue(element.getAttribute("type").equals("text"));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getCoordinates(){
	Element element= new ElementImpl(getDriver().findElement(By.id("FirstName")));
	Assert.assertTrue(element.getCoordinates().onPage().getX() > 0);
	Assert.assertTrue(element.getCoordinates().onPage().getY() > 0);
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getCssValue(){
	Element element= new ElementImpl(getDriver().findElement(By.id("header-top")));
	Assert.assertTrue(element.getCssValue("height").equals("65px"));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getElementIdentifier(){
	Element element= new ElementImpl(getDriver().findElement(By.id("FirstName")));
	Assert.assertTrue(element.getElementIdentifier().equals("FirstName"));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getElementLocator(){
	Element element= new ElementImpl(getDriver().findElement(By.id("FirstName")));
	Assert.assertNotNull(element.getElementLocator());
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getElementLocatorInfo(){
	Element element= new ElementImpl(getDriver().findElement(By.id("FirstName")));
	Assert.assertNotNull(element.getElementLocatorInfo());
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getLocation(){
	Element element= new ElementImpl(getDriver().findElement(By.id("FirstName")));
	Assert.assertTrue(element.getLocation().getX() > 0);
	Assert.assertTrue(element.getLocation().getY() > 0);
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getSize(){
	Element element= new ElementImpl(getDriver().findElement(By.id("FirstName")));
	Assert.assertTrue(element.getSize().getHeight() > 0);
	Assert.assertTrue(element.getSize().getWidth() > 0);
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getTagName(){
	Element element= new ElementImpl(getDriver().findElement(By.id("FirstName")));
	Assert.assertTrue(element.getTagName().equals("input"));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void getText(){
	Element element= new ElementImpl(getDriver().findElement(By.id("count")));
	Assert.assertTrue(element.getText().equals("VIP count: 0"));
    }    
    
    @Test(groups ={"regression", "interfaces", "element"}, dependsOnMethods="getCssValue")
    public void highlight(){
	Element element= new ElementImpl(getDriver().findElement(By.id("LastName")));
	element.highlight(getDriver());
	Assert.assertTrue(element.getCssValue("border").contains("red"));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void isDisplayed(){
	Element element= new ElementImpl(getDriver().findElement(By.id("FirstName")));
	Assert.assertTrue(element.isDisplayed());
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void isEnabled(){
	Element element= new ElementImpl(getDriver().findElement(By.id("FirstName")));
	Assert.assertTrue(element.isEnabled());
    }
    
    @Test(groups ={"regression",  "element"})
    public void isEnabledNegative(){
	Element element= new ElementImpl(getDriver().findElement(By.id("Disabled")));
	Assert.assertFalse(element.isEnabled());
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void isSelected(){
	Element element= new ElementImpl(getDriver().findElement(By.xpath("//*[@id='Gender'][@value='Female']")));
	Assert.assertTrue(element.isSelected());
    }
    
    @Test(groups ={"regression",  "element"})
    public void isSelectedNegative(){
	Element element= new ElementImpl(getDriver().findElement(By.xpath("//*[@id='Gender'][@value='Male']")));
	Assert.assertFalse(element.isSelected());
    }    
    
    @Test(groups ={"regression", "interfaces", "element"}, dependsOnMethods="click") 
    public void jsClick(){
	Element element = new ElementImpl(getDriver().findElement(By.id("Load")));
	element.jsClick(getDriver());
	Assert.assertTrue(new ElementImpl(getDriver().findElement(By.id("count"))).getText().equals("VIP count: 6"));
	new ElementImpl(getDriver().findElement(By.id("Clear"))).jsClick(getDriver());
	Assert.assertTrue(new ElementImpl(getDriver().findElement(By.id("count"))).getText().equals("VIP count: 0"));
    }

    @Test(groups ={"regression", "interfaces", "element"})
    public void sendKeys(){
	Element element= new ElementImpl(getDriver().findElement(By.id("LastName")));
	element.sendKeys("testing");
	Assert.assertTrue(element.getAttribute("value").equals("testing"));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void syncPresentBasic(){
	Element element= new ElementImpl(getDriver().findElement(By.id("Disabled")));
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
	Element element= new ElementImpl(getDriver().findElement(By.id("Disabled")));
	Assert.assertTrue(element.syncPresent(getDriver(), 5, false));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void syncDisabledBasic(){
	Element element= new ElementImpl(getDriver().findElement(By.id("Disabled")));
	Assert.assertTrue(element.syncDisabled(getDriver()));
    }
    
    @Test(groups ={"regression", "element"})
    public void syncDisabledBasicNegative(){
	boolean pass = false;
	try{
	    Element element= new ElementImpl(getDriver().findElement(By.id("Load")));
	    element.syncDisabled(getDriver());
	} catch (RuntimeException rte){
	  pass = true;
	}
	
	Assert.assertTrue(pass);
    }
    
    @Test(groups ={"regression", "element"})
    public void syncDisabledAdvanced(){
	Element element= new ElementImpl(getDriver().findElement(By.id("Disabled")));
	Assert.assertTrue(element.syncDisabled(getDriver(), 5, false));
    }
    
    @Test(groups ={"regression", "element"})
    public void syncDisabledAdvancedNegative(){
	Element element= new ElementImpl(getDriver().findElement(By.id("Load")));
	Assert.assertFalse(element.syncDisabled(getDriver(),1, false));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void syncHiddenBasic(){
	Element element= new ElementImpl(getDriver().findElement(By.id("Hidden")));
	Assert.assertTrue(element.syncHidden(getDriver()));
    }
    
    @Test(groups ={"regression", "element"})
    public void syncHiddenBasicNegative(){
	boolean pass = false;
	try{
	    Element element= new ElementImpl(getDriver().findElement(By.id("Load")));
	    element.syncHidden(getDriver());
	} catch (RuntimeException rte){
	  pass = true;
	}
	
	Assert.assertTrue(pass);
    }
    
    @Test(groups ={"regression", "element"})
    public void syncHiddenAdvanced(){
	Element element= new ElementImpl(getDriver().findElement(By.id("Hidden")));
	Assert.assertTrue(element.syncHidden(getDriver(), 5, false));
    }

    @Test(groups ={"regression", "element"})
    public void syncHiddenAdvancedNegative(){
	Element element= new ElementImpl(getDriver().findElement(By.id("Load")));
	Assert.assertFalse(element.syncHidden(getDriver(),1, false));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void syncVisibleBasic(){
	Element element= new ElementImpl(getDriver().findElement(By.id("FirstName")));
	Assert.assertTrue(element.syncVisible(getDriver()));
    }
    
    @Test(groups ={"regression", "element"})
    public void syncVisibleBasicNegative(){	
	boolean pass = false;
	try{
	    Element element= new ElementImpl(getDriver().findElement(By.id("Hidden")));
	    element.syncVisible(getDriver());
	} catch (RuntimeException rte){
	  pass = true;
	}
	
	Assert.assertTrue(pass);
    }
    
    @Test(groups ={"regression", "element"})
    public void syncVisibleAdvanced(){
	Element element= new ElementImpl(getDriver().findElement(By.id("FirstName")));
	Assert.assertTrue(element.syncVisible(getDriver(), 5, false));
    }
    
    @Test(groups ={"regression", "element"})
    public void syncVisibleAdvancedNegative(){
	Element element= new ElementImpl(getDriver().findElement(By.id("Hidden")));
	Assert.assertFalse(element.syncVisible(getDriver(),1, false));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void syncEnabledBasic(){
	Element element= new ElementImpl(getDriver().findElement(By.id("FirstName")));
	Assert.assertTrue(element.syncEnabled(getDriver()));
    }
    
    @Test(groups ={"regression", "element"})
    public void syncEnabledBasicNegative(){
	boolean pass = false;
	try{
	    Element element= new ElementImpl(getDriver().findElement(By.id("Disabled")));
	    element.syncEnabled(getDriver());
	} catch (RuntimeException rte){
	  pass = true;
	}
	
	Assert.assertTrue(pass);
    }
    
    @Test(groups ={"regression", "element"})
    public void syncEnabledAdvanced(){
	Element element= new ElementImpl(getDriver().findElement(By.id("FirstName")));
	Assert.assertTrue(element.syncEnabled(getDriver(), 5, false));
    }
    
    @Test(groups ={"regression", "element"})
    public void syncEnabledAdvancedNegative(){
	Element element = new ElementImpl(getDriver().findElement(By.id("Disabled")));
	Assert.assertFalse(element.syncEnabled(getDriver(),1, false));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void syncTextInElementBasic(){
	Element element= new ElementImpl(getDriver().findElement(By.id("Load")));
	Assert.assertTrue(element.syncTextInElement(getDriver(), "Load"));
    }
    
    @Test(groups ={"regression", "interfaces", "element"})
    public void syncTextInElementBasicNegative(){
	boolean pass = false;
	try{
	    Element element= new ElementImpl(getDriver().findElement(By.id("Disabled")));
	    element.syncTextInElement(getDriver(), "Loading");
	} catch (RuntimeException rte){
	  pass = true;
	}
	
	Assert.assertTrue(pass);
    }
    
    @Test(groups ={"regression", "element"})
    public void syncTextInElementAdvanced(){
	Element element= new ElementImpl(getDriver().findElement(By.id("Load")));
	Assert.assertTrue(element.syncTextInElement(getDriver(), "Load", 5, false));
    }
    
    @Test(groups ={"regression", "element"})
    public void syncTextInElementAdvancedNegative(){
	Element element= new ElementImpl(getDriver().findElement(By.id("Load")));
	Assert.assertFalse(element.syncTextInElement(getDriver(), "Loading", 2, false));
    } 
}
