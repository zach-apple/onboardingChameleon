package com.orasi.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrameHandler {
    	@Deprecated
	public static void findAndSwitchToFrame(WebDriver driver, String frame){
		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver,TestEnvironment.getDefaultTestTimeout());
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));		
	}
	
	public static String getCurrentFrameName(WebDriver driver){
	    if(driver instanceof OrasiDriver){
		driver = ((OrasiDriver) driver).getDriver();
	    }
	    
	    String frameName =  ((JavascriptExecutor) driver).executeScript("return self.name").toString();
	    if (frameName.isEmpty()) frameName = null;
	    return frameName;
	}
	
	/*public static String getCurrentFrameName(OrasiDriver driver){
	    String frameName =  driver.executeJavaScript("return self.name").toString();
	    if (frameName.isEmpty()) frameName = null;
	    return frameName;
	}
	*/
	public static void moveToDefaultContext(WebDriver driver){
	    driver.switchTo().defaultContent();
	}
	
	public static void moveToSiblingFrame(WebDriver driver, String frameName){
	    driver.switchTo().parentFrame();
	    switchToFrame(driver, frameName);
	}
	
	public static void moveToSiblingFrame(WebDriver driver, By byFrameLocator){
	    driver.switchTo().parentFrame();
	    switchToFrame(driver, byFrameLocator);
	}
	
	public static void moveToChildFrame(WebDriver driver, String frameName){
	    switchToFrame(driver, frameName);
	}
	
	public static void moveToChildFrame(WebDriver driver, By byFrameLocator){
	    switchToFrame(driver, byFrameLocator);
	}
	
	private static void switchToFrame(WebDriver driver, String frameName){
	    WebDriverWait wait = new WebDriverWait(driver, TestEnvironment.getDefaultTestTimeout());
	    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));	
	}
	
	private static void switchToFrame(WebDriver driver, By byFrameLocator){
	    WebDriverWait wait = new WebDriverWait(driver, TestEnvironment.getDefaultTestTimeout());
	    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(byFrameLocator));	
	}
}
