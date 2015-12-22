package com.orasi.core.interfaces;

import com.orasi.core.interfaces.impl.ListboxImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.utils.TestEnvironment;

public class TestListbox extends TestEnvironment{
    WebDriver driver = null;
//    private String multiSelectXpath = "//*[@id='page']/div[2]/div/select";
//    private String listboxXpath = "//*[@id='para1']/select";
    @BeforeTest(groups ={"regression", "interfaces", "listbox", "dev"})
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
	setPageURL("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/core/interfaces/listbox.html");
	testStart("TestListbox");
    }
    
    @AfterTest(groups ={"regression", "interfaces", "listbox", "dev"})
    public void close(ITestContext testResults){
	endTest("TestAlert", testResults);
    }

	@Test(groups ={"regression", "interfaces", "listbox"})
	public void constructorWithElement(){
		Assert.assertNotNull((new ListboxImpl(getDriver().findWebElement((By.id("singleSelect"))))));
	}

	@Test(groups ={"regression", "interfaces", "listbox"})
	public void constructorWithElementAndDriver(){
		Assert.assertNotNull((new ListboxImpl(getDriver().findWebElement((By.id("singleSelect"))), getDriver())));
	}
    @Test(groups ={"regression", "interfaces", "listbox"})
    public void isMultiple(){
	Listbox listbox= getDriver().findListbox(By.id("multiSelect"));
	Assert.assertTrue(listbox.isMultiple());
    }
    
    @Test(groups ={"regression", "interfaces", "listbox"})
    public void select(){
	Listbox listbox= getDriver().findListbox(By.id("singleSelect"));
	listbox.select("Sports");
	Assert.assertTrue(listbox.getFirstSelectedOption().getText().equals("Sports"));
    }

	@Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="select")
	public void selectNoText(){
		Listbox listbox= getDriver().findListbox(By.id("singleSelect"));
		listbox.select("");
		Assert.assertTrue(listbox.getAttribute("value").equals("Sports"));
	}

	@Test(groups ={"regression", "interfaces", "textbox"}, dependsOnMethods="select")
	public void selectNegative(){
		Listbox listbox= getDriver().findListbox(By.id("singleSelect"));
		boolean valid = false;
		try{
			listbox.select("text");
		}catch (RuntimeException rte){
			valid = true;
		}
		Assert.assertTrue(valid);
	}

    @Test(groups ={"regression", "interfaces", "listbox"})
    public void selectValue(){
        Listbox listbox= getDriver().findListbox(By.id("singleSelect"));
        listbox.selectValue("Sports");
        Assert.assertTrue(listbox.getFirstSelectedOption().getText().equals("Sports"));
    }

    @Test(groups ={"regression", "interfaces", "listbox"}, dependsOnMethods="select")
    public void selectValueNoText(){
        Listbox listbox= getDriver().findListbox(By.id("singleSelect"));
        listbox.selectValue("");
        Assert.assertTrue(listbox.getAttribute("value").equals("Sports"));
    }

    @Test(groups ={"regression", "interfaces", "listbox"}, dependsOnMethods="select")
    public void selectValueNegative(){
        Listbox listbox= getDriver().findListbox(By.id("singleSelect"));
        boolean valid = false;
        try{
            listbox.selectValue("text");
        }catch (RuntimeException rte){
            valid = true;
        }
        Assert.assertTrue(valid);
    }

    @Test(groups ={"regression", "interfaces", "listbox"}, dependsOnMethods="select")
    public void getAllOptions(){
        Listbox listbox= getDriver().findListbox(By.id("singleSelect"));
        Assert.assertTrue(listbox.getOptions().get(0).getText().equals("Other"));
    }
    @Test(groups ={"regression", "interfaces", "listbox"}, dependsOnMethods="select")
    public void getAllSelectedOptions(){
	Listbox listbox= getDriver().findListbox(By.id("singleSelect"));
	Assert.assertTrue(listbox.getAllSelectedOptions().get(0).getText().equals("Sports"));
    }
    
    @Test(groups ={"regression", "interfaces", "listbox"}, dependsOnMethods="getAllSelectedOptions")
    public void isSelected(){
	Listbox listbox= getDriver().findListbox(By.id("singleSelect"));
	Assert.assertTrue(listbox.isSelected("Sports"));
    }
    
    @Test(groups ={"regression", "interfaces", "listbox"}, dependsOnMethods="isSelected")
    public void deselectByVisibleText(){
	Listbox listbox= getDriver().findListbox(By.id("multiSelect"));
	listbox.select("Baseball");
	Assert.assertTrue(listbox.isSelected("Baseball"));
	listbox.deselectByVisibleText("Baseball");
	Assert.assertFalse(listbox.isSelected("Baseball"));
    }
    
    @Test(groups ={"regression", "interfaces", "listbox"}, dependsOnMethods="deselectByVisibleText")
    public void deselectAll(){
	Listbox listbox= getDriver().findListbox(By.id("multiSelect"));
	listbox.select("Basketball");
	listbox.select("Soccer");
	listbox.deselectAll();
	Assert.assertNull(listbox.getFirstSelectedOption());
    }
    
}
