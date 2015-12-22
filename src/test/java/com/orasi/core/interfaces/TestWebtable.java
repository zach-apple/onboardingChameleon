package com.orasi.core.interfaces;

import com.orasi.core.interfaces.impl.WebtableImpl;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
    public void close(ITestContext testResults){
	endTest("TestAlert", testResults);
    }

    @Test(groups ={"regression", "interfaces", "webtable"})
    public void constructorWithElement(){
        Assert.assertNotNull((new WebtableImpl(getDriver().findWebElement((By.xpath(xpath))))));
    }

    @Test(groups ={"regression", "interfaces", "webtable"})
    public void constructorWithElementAndDriver(){
        Assert.assertNotNull((new WebtableImpl(getDriver().findWebElement((By.xpath(xpath))), getDriver())));
    }

    @Test(groups ={"regression", "interfaces", "webtable"})
    public void clickCell(){
    	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
    	webtable.clickCell( 1, 1);
    	//Assert.assertTrue(getDriver().findElement(By.id("LastName")).getAttribute("value").equals("clicked"));
      } 
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getCell(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getCell( 2, 2).getText().equals("Office Supplies"));
	
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getCellData(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getCellData( 2, 2).equals("Office Supplies"));
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getColumnCount(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getColumnCount( 1) == 3);
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getColumnWithCellText(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getColumnWithCellText( "Supplies and Expenses") == 1);
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getColumnWithCellTextUsingRow(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getColumnWithCellText( "$xx,xxx", 7) == 3);
    }

    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getColumnWithCellTextNothingFound(){
        Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
        Assert.assertTrue(webtable.getColumnWithCellText("Blah") == 0);
    }

    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getRowCount(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getRowCount() == 7);
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getRowWithCellText(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getRowWithCellText("Mailing") == 5);
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getRowWithCellTextSpecifiedColumn(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getRowWithCellText( "13.", 1) == 5);
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getRowWithCellTextSpecifiedRowAndColumn(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getRowWithCellText( "14.", 1, 2) == 6);
    }
    
    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getRowWithCellTextNotExact(){
	Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
	Assert.assertTrue(webtable.getRowWithCellText( "duplICatING", 2, 2, false) == 4);
    }

    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getRowWithCellTextNothingFound(){
        Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
        Assert.assertTrue(webtable.getRowWithCellText( "NOTHING!", 2, 2, true) == 0);
    }

    @Test(groups ={"regression", "interfaces", "webtable"})
    public void getRowWithCellTextNoColumn(){
        Webtable webtable= getDriver().findWebtable(By.xpath(xpath));
        Assert.assertTrue(webtable.getRowWithCellText( "Supplies and Expenses", -1, 2, false) == 7);
    }
}
