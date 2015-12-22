package com.orasi.core.interfaces;

import com.orasi.core.interfaces.impl.TextboxImpl;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.utils.TestEnvironment;

public class TestTextbox extends TestEnvironment{
    
    @BeforeTest(groups ={"regression", "interfaces", "textbox", "dev"})
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
        setPageURL("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/core/interfaces/textbox.html");
        testStart("TestTextbox");
    }
    
    @AfterTest(groups ={"regression", "interfaces", "textbox", "dev"})
    public void close(ITestContext testResults){
	endTest("TestAlert", testResults);
    }

	@Test(groups ={"regression", "interfaces", "textbox"})
	public void constructorWithElement(){
		Assert.assertNotNull((new TextboxImpl(getDriver().findWebElement((By.id("text1"))))));
	}

	@Test(groups ={"regression", "interfaces", "textbox"})
	public void constructorWithElementAndDriver(){
		Assert.assertNotNull((new TextboxImpl(getDriver().findWebElement((By.id("text1"))), getDriver())));
	}

	@Test(groups ={"regression", "interfaces", "textbox"})
    public void getText(){
        Textbox textbox= getDriver().findTextbox(By.id("text1"));
	    Assert.assertTrue(textbox.getText().equals("Testing methods"));
    }
    
    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="setNoText")
    public void set(){
        Textbox textbox= getDriver().findTextbox(By.id("text1"));
        textbox.set("set");
        Assert.assertTrue(textbox.getAttribute("value").equals("set"));
    }

    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="getText")
    public void setNoText(){
        Textbox textbox= getDriver().findTextbox(By.id("text1"));
        textbox.set("");
        Assert.assertTrue(textbox.getAttribute("value").equals("Testing methods"));
    }

    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="set")
    public void setNegative(){
        Textbox textbox= getDriver().findTextbox(By.name("lname"));
        boolean valid = false;
        try{
            textbox.set("text");
        }catch (RuntimeException rte){
            valid = true;
        }
        Assert.assertTrue(valid);
    }
    
    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="set")
    public void scrollAndSet(){
    	Textbox textbox= getDriver().findTextbox(By.id("text1"));
    	textbox.scrollAndSet( "setScrollIntoView");
    	Assert.assertTrue(textbox.getAttribute("value").equals("setScrollIntoView"));
    }


    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="scrollAndSet")
    public void scrollAndSetNoText(){
        Textbox textbox= getDriver().findTextbox(By.id("text1"));
        textbox.scrollAndSet( "");
        Assert.assertTrue(textbox.getAttribute("value").equals("setScrollIntoView"));
    }


    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="scrollAndSet")
    public void scrollAndSetNegative(){
        Textbox textbox= getDriver().findTextbox(By.name("lname"));
        boolean valid = false;
        try{
            textbox.scrollAndSet("text");
        }catch (RuntimeException rte){
            valid = true;
        }
        Assert.assertTrue(valid);
    }
    
    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="scrollAndSetNoText")
    public void clear(){
        Textbox textbox= getDriver().findTextbox(By.id("text1"));
        textbox.clear();
        Assert.assertTrue(textbox.getAttribute("value").equals(""));
    }

    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="clear")
    public void clearNegative(){
        Textbox textbox= getDriver().findTextbox(By.name("lname"));
        boolean valid = false;
        try{
            textbox.clear();
        }catch (RuntimeException rte){
            valid = true;
        }
        Assert.assertTrue(valid);
    }

    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="clear")
    public void safeSet(){
        Textbox textbox= getDriver().findTextbox(By.id("text1"));
        textbox.safeSet("safeSet");
        Assert.assertTrue(textbox.getAttribute("value").contains("safeSet"));
    }

    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="safeSet")
    public void safeSetNoText(){
        Textbox textbox= getDriver().findTextbox(By.id("text1"));
        textbox.safeSet("");
        Assert.assertTrue(textbox.getAttribute("value").contains("safeSet"));
    }

    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="safeSet")
    public void safeSetNegative(){
        Textbox textbox= getDriver().findTextbox(By.name("lname"));
        boolean valid = false;
        try{
            textbox.safeSet("text");
        }catch (RuntimeException rte){
            valid = true;
        }
        Assert.assertTrue(valid);
    }

    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="safeSet")
    public void setSecure(){
        Textbox textbox= getDriver().findTextbox(By.id("text1"));
        textbox.setSecure("c2V0U2VjdXJl");
        Assert.assertTrue(textbox.getAttribute("value").contains("safeSetsetSecure"));
        textbox.clear();
    }

    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="setSecure")
    public void setSecureNoText(){
        Textbox textbox= getDriver().findTextbox(By.id("text1"));
        textbox.setSecure("");
        Assert.assertTrue(textbox.getAttribute("value").contains(""));
        textbox.clear();
    }

    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="setSecureNoText")
    public void setSecureNegative(){
        Textbox textbox= getDriver().findTextbox(By.name("lname"));
        boolean valid = false;
        try{
            textbox.setSecure("text");
        }catch (RuntimeException rte){
            valid = true;
        }
        Assert.assertTrue(valid);
    }

    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="setSecure")
    public void safeSetSecure(){
        Textbox textbox= getDriver().findTextbox(By.id("text1"));
        textbox.safeSetSecure("c2V0U2VjdXJl");
        Assert.assertTrue(textbox.getAttribute("value").contains("setSecure"));
        textbox.clear();
    }

    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="safeSetSecure")
    public void safeSetSecureNoText(){
        Textbox textbox= getDriver().findTextbox(By.id("text1"));
        textbox.safeSetSecure("");
        Assert.assertTrue(textbox.getAttribute("value").contains(""));
        textbox.clear();
    }

    @Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="safeSetSecure")
    public void safeSetSecureNegative(){
        Textbox textbox= getDriver().findTextbox(By.name("lname"));
        boolean valid = false;
        try{
            textbox.safeSetSecure("text");
        }catch (RuntimeException rte){
            valid = true;
        }
        Assert.assertTrue(valid);
    }
}
