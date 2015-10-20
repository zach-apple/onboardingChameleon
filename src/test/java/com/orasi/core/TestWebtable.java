package com.orasi.core;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.Webtable;
import com.orasi.core.interfaces.impl.WebtableImpl;
import com.orasi.utils.TestEnvironment;

public class TestWebtable extends TestEnvironment{

    private String xpath = "//div[6]/table";
    
    @BeforeTest(groups ={"regression", "interfaces", "webtable", "dev"})
    @Parameters({ "runLocation", "browserUnderTest", "browserVersion",
	    "operatingSystem", "environment" })
    public void setup(@Optional String runLocation, String browserUnderTest,
	    String browserVersion, String operatingSystem, String environment) {
	setApplicationUnderTest("Bluesource");
	setBrowserUnderTest(browserUnderTest);
	setBrowserVersion(browserVersion);
	setOperatingSystem(operatingSystem);
	setRunLocation(runLocation);
	setTestEnvironment(environment);
	setPageURL("http://www.iupui.edu/~webtrain/tutorials/tables.html");
	testStart("TestWebtable");
    }
    
    @AfterTest(groups ={"regression", "interfaces", "webtable", "dev"})
    public void close(){
	endTest("TestWebtable");
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void clickCell(){
    	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
    	webtable.clickCell(this, 1, 1);
    	//Assert.assertTrue(getDriver().findElement(By.id("LastName")).getAttribute("value").equals("clicked"));
      } 
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getCell(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getCell(this, 2, 2).getText().equals("Office Supplies"));
	
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getCellData(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getCellData(this, 2, 2).equals("Office Supplies"));
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getColumnCount(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getColumnCount(this, 1) == 3);
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getColumnWithCellText(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getColumnWithCellText(this, "Supplies and Expenses") == 1);
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getColumnWithCellTextUsingRow(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getColumnWithCellText(this, "$xx,xxx", 7) == 3);
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getRowCount(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getRowCount(this) == 7);
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getRowWithCellText(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getRowWithCellText(this, "Mailing") == 5);
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getRowWithCellTextSpecifedColumn(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getRowWithCellText(this, "13.", 1) == 5);
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getRowWithCellTextSpecifedRowAndColumn(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getRowWithCellText(this, "14.", 1, 2) == 6);
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getRowWithCellTextNotExact(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getRowWithCellText(this, "duplicating", 2, 2, false) == 4);
    }
    

}
