package com.orasi.utils;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.Nullable;

import com.google.common.base.Function;

public class WindowHandler {

	public static void waitUntilWindowExistsWithTitle(OrasiDriver driver, String windowNameOrHandle){
	    try{
		WebDriverWait wait = new WebDriverWait(driver.getWebDriver(), driver.getPageTimeout());
		wait.until(ExtendedExpectedConditions.windowExistsWithTitleAndSwitchToIt(windowNameOrHandle));
	    }catch(NoSuchWindowException | NullPointerException e){}
	}

	/**
	 * @summary Swaps to a window with a specified title after waiting a specified number of
	 * 			seconds for the window to display.  Pass in a number of seconds (1,2 etc), and
	 * 			the title of the window you wish to switch to.  Do not need to pass in the whole title,
	 * 			can be a part of it such as "Lilo".  Can also pass in a null value ("") if the window
	 * 			does not have a title.  Returns a true if the window was found & switched to and false if not
	 * @version Created 10/29/2014
	 * @author 	Jessica Marshall
	 * @param 	driver, title, numOfWaitSeconds
	 * @throws 	NA
	 * @return 	true/false
	 */
	public static boolean swapToWindowWithTimeout(WebDriver driver, String title, int numOfWaitSeconds){
		int count = 0;
		//wait for the window count to be greater than 1
		while (driver.getWindowHandles().size()==1){
			Sleeper.sleep(1000);
			
			if (count > numOfWaitSeconds)
				return false;
			count++;
		}
		
		for (String winHandle : driver.getWindowHandles()) {
			
			driver.switchTo().window(winHandle);
			//code to handle if the title of the window you expect to switch to is null
			if (title.equals("")){
				if (driver.getTitle().toUpperCase().equals(""))
					return true;
			}
			//code to handle a non null tile of the winodw you wish to switch to	
			if (driver.getTitle().toUpperCase().contains(title.toUpperCase())){
				return true;
			}
		}
		
		return false;
	}
	
	static class WindowTitleIs implements ExpectedCondition<Boolean> {
	    private String title;
	    
	    public WindowTitleIs(String title){
		this.title = title;
	    }
	    
	    @Override
	    public Boolean apply(final WebDriver driver) {
		for(String handle : driver.getWindowHandles()){
		    driver.switchTo().window(handle);
		    if(driver.getTitle().contains(title)) return true;
		}
		return false;
	    }
	    
	}
}
