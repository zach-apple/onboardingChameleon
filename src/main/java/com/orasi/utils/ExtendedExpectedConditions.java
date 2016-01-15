package com.orasi.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ExtendedExpectedConditions {
    /**
     * Grants access to WindowExistsWithTitle ExpectedCondition
     * @param title - Title of the Window to find
     * @return WindowExistsWithTitle - Expected Condition to find window with title
     */
    public static WindowExistsWithTitleAndSwitchToIt windowExistsWithTitleAndSwitchToIt(String title){
	return new WindowExistsWithTitleAndSwitchToIt(title);
    }
    
    /**
     * Loop through all Windows to see if the queried title is the title of a window
     * @author justin.phlegar@orasi.com
     *
     */
   static class WindowExistsWithTitleAndSwitchToIt implements ExpectedCondition<Boolean> {
       private final String title;
       
       public WindowExistsWithTitleAndSwitchToIt(final String title){
	   this.title = title;
       }
	    
       @Override
	public Boolean apply(final WebDriver driver) {
	   for(String handle : driver.getWindowHandles()){
	       driver.switchTo().window(handle);
	       if(driver.getTitle().equals(title)) return true;
	   }
	   return false;
	}
	    
   }
}
