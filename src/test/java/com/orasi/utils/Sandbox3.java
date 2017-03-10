package com.orasi.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orasi.utils.dataHelpers.personFactory.Address;
import com.orasi.utils.dataHelpers.personFactory.Email;
import com.orasi.utils.dataHelpers.personFactory.Party;
import com.orasi.utils.dataHelpers.personFactory.Person;
import com.orasi.utils.dataHelpers.personFactory.Phone;

/**
 * Created by Adam on 12/22/2015.
 */
@Preamble(author = "Script.dev@automation.com", 
		  date = "06-Feb-17", 
		  summary = "This test will use a new account, modify Email Address, and validate modification was successful", 
		  precondition = "Requires a new account to use",
		  steps= {"1. Login to new account",
				  "2. Validate successful log in",
				  "3. Load Account Profile data",
				  "4. Validate Profile data",
				  "5. Modify Profile Email Address",
				  "6. Validate modifications"},
		  reviewers= {"Automation.Lead@test.com", 
				      "Requires Automation Lead 2", 
      				  "Requires QALead"})
public class Sandbox3 {

	public static void main(String[] args) {
		OrasiDriver driver = null;
		
		//Waiting for popup to go away and element on next page to appear
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExtendedExpectedConditions.elementToBeHidden(driver.findElement(By.id("popup"))));
		wait.until(ExtendedExpectedConditions.elementToBeVisible(driver.findElement(By.id("success-message"))));
		
		//Open a new window and swap to it. Name of the window is PaymentUI version x.x.x where x.x.x changes every update.
		driver.findButton(By.id("new-window")).click();
		wait.until(ExtendedExpectedConditions.findWindowContainsTitleAndSwitchToIt("PaymentUI version"));
		
		//Wait until element color attribute changes to red (which happens after 5 seconds
		wait.until(ExtendedExpectedConditions.textToMatchInElementAttribute(driver.findButton(By.id("stop")), "color", "red"));
	}
}