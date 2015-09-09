package com.orasi.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertHandler {
	
	private static void alertHandler(WebDriver driver){
		try {
			TestReporter.log("Closing alert popup with text [ <i>" + driver.switchTo().alert().getText() +" </i> ]<br />");
			driver.switchTo().alert().accept();
            	        
            	    } catch (Exception e) {
            	        //exception handling
            	    }
	}
	
	public static boolean isAlertPresent(WebDriver driver, int timeout){
            try{
            	WebDriverWait wait = new WebDriverWait(driver, timeout);
    	        wait.until(ExpectedConditions.alertIsPresent());
    	        driver.switchTo().alert();
    	        return true;
            }
            catch(Exception e){
                return false;
            }
        }
	
	public static void handleAllAlerts(WebDriver driver, int timeout){
            while (isAlertPresent(driver, timeout)) alertHandler(driver);
            driver.switchTo().defaultContent();
	}
	
	public static void handleAlert(WebDriver driver, int timeout){
            if(isAlertPresent(driver, timeout)) alertHandler(driver);
            driver.switchTo().defaultContent();
	}
}
