package com.orasi.utils;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowHandler {

	/**
	 * Use FluentWait to halt the script until the window with the desired text in the title is active
	 * to return true. If the window is not found in the default timeout, return false. 
	 * @version Created 01/15/2016
	 * @author 	Justin Phlegar
	 * @param 	driver, title
	 * @return 	true/false
	 */
	public static boolean waitUntilWindowExistsWithTitle(WebDriver driver, String windowNameOrHandle){
		int timeout = Constants.PAGE_TIMEOUT;
		
		//Adding for backwards compatibility for WebDriver
		if(driver instanceof OrasiDriver){
			timeout = ((OrasiDriver)driver).getPageTimeout();
			driver = ((OrasiDriver)driver).getWebDriver();
		}
		
	    try{
	    	WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExtendedExpectedConditions.findWindowWithTitleAndSwitchToIt(windowNameOrHandle));
	    }catch(TimeoutException e){
	    	return false;
	    }
	    return true;
	}

	/**
	 * Use FluentWait to halt the script until the window with the desired text is contained in the title and 
	 * active to return true. If the window is not found in the default timeout, return false. 
	 * @version Created 01/15/2016
	 * @author 	Justin Phlegar
	 * @param 	driver, title
	 * @return 	true/false
	 */
	public static boolean waitUntilWindowExistsTitleContains(WebDriver driver, String windowNameOrHandle){
		int timeout = Constants.PAGE_TIMEOUT;
		
		//Adding for backwards compatibility for WebDriver
		if(driver instanceof OrasiDriver){
			timeout = ((OrasiDriver)driver).getPageTimeout();
			driver = ((OrasiDriver)driver).getWebDriver();
		}
		
	    try{
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExtendedExpectedConditions.findWindowContainsTitleAndSwitchToIt(windowNameOrHandle));
	    }catch(TimeoutException e){
	    	return false;
	    }
	    return true;
	}

	/**
	 * Use FluentWait to halt the script until the window with the desired regex pattern is matched in the 
	 * title and the window is active to return true. If the window is not found in the default timeout, return false. 
	 * @version Created 01/15/2016
	 * @author 	Justin Phlegar
	 * @param 	driver, title
	 * @return 	true/false
	 */
	public static boolean waitUntilWindowExistsTitleMatches(WebDriver driver, String regex){
		int timeout = Constants.PAGE_TIMEOUT;
		
		//Adding for backwards compatibility for WebDriver
		if(driver instanceof OrasiDriver){
			timeout = ((OrasiDriver)driver).getPageTimeout();
			driver = ((OrasiDriver)driver).getWebDriver();
		}
		
	    try{
	    	WebDriverWait wait = new WebDriverWait(driver, timeout);
	    	wait.until(ExtendedExpectedConditions.findWindowMatchesTitleAndSwitchToIt(regex));
	    }catch( TimeoutException e){
	    	return false;
	    }
	    return true;
	}


	/**
	 * Use FluentWait to halt the script until the specified number of windows are found
	 * @version Created 01/15/2016
	 * @author 	Justin Phlegar
	 * @param 	driver, expectedNumberOfWindows
	 * @return 	true/false
	 */
	public static boolean waitUntilNumberOfWindowsAre(WebDriver driver, int expectedNumberOfWindows){
		int timeout = Constants.PAGE_TIMEOUT;
		
		//Adding for backwards compatibility for WebDriver
		if(driver instanceof OrasiDriver){
			timeout = ((OrasiDriver)driver).getPageTimeout();
			driver = ((OrasiDriver)driver).getWebDriver();
		}
		
		try{
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
		}catch(TimeoutException e){
			return false;
		}
		return true;
	}
}
