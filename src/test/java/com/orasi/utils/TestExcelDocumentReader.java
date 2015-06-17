package com.orasi.utils;

import org.junit.Assert;
import org.testng.annotations.Test;

public class TestExcelDocumentReader {
    @Test(groups="regression")
    public void readDataFilePathConstructorAllRows(){
	Object[][] data = null;
	String filepath = getClass().getResource("/excelsheets/TestData.xlsx").getPath();
	data = new ExcelDocumentReader(filepath).readData("Data");
	
	Assert.assertNotNull(data);
	Assert.assertTrue(data.length == 3);
	Assert.assertTrue(data[0].length == 5);
	Assert.assertTrue(data[0][0].toString().equals("DataSet1"));
	
    }
    
    @Test(groups="regression")
    public void readDataFilePathConstructorSingleRow(){
	Object[][] data = null;
	String filepath = getClass().getResource("/excelsheets/TestData.xlsx").getPath();
	data = new ExcelDocumentReader(filepath).readData("Data", 1);
	
	Assert.assertNotNull(data);
	Assert.assertTrue(data.length == 1);
	Assert.assertTrue(data[0].length == 5);
	Assert.assertTrue(data[0][0].toString().equals("DataSet1"));
	
    }
    
    @Test(groups="regression")
    public void readDataDefaultConstructorAllRows(){
	Object[][] data = null;
	String filepath = getClass().getResource("/excelsheets/TestData.xlsx").getPath();
	data = new ExcelDocumentReader().readData(filepath, "Data");
	
	Assert.assertNotNull(data);
	Assert.assertTrue(data.length == 3);
	Assert.assertTrue(data[0].length == 5);
	Assert.assertTrue(data[0][0].toString().equals("DataSet1"));
	
    }
    @Test(groups="regression")
    public void readDataDefaultConstructorSingleRow(){
	Object[][] data = null;
	String filepath = getClass().getResource("/excelsheets/TestData.xlsx").getPath();
	data = new ExcelDocumentReader().readData(filepath, "Data", 1);
	
	Assert.assertNotNull(data);
	Assert.assertTrue(data.length == 1);
	Assert.assertTrue(data[0].length == 5);
	Assert.assertTrue(data[0][0].toString().equals("DataSet1"));
	
    }
}
