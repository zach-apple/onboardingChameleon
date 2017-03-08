package com.orasi.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.Textbox;
import com.orasi.core.interfaces.impl.internal.ElementFactory;


public class Sandbox2 {
    private WebDriver driver;
    private String url = "https://the-internet.herokuapp.com";
    private WebDriverWait wait;
    @BeforeTest
    public void setup() {
	System.setProperty("webdriver.chrome.driver", "C:/Users/Justin/git/Orasi-Selenium-Java-Core/src/main/resources/drivers/ChromeDriver.exe");
	driver = new ChromeDriver();
	driver.get(url);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	wait = new WebDriverWait(driver,10);
    }
    
   
    
    @AfterTest
    public void clean(){
	driver.close();
    }
}
