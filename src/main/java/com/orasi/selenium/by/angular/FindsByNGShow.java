package com.orasi.selenium.by.angular;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.orasi.Beta;

@Beta
public interface FindsByNGShow {
    WebElement findElementByNGShow(String using);

    List<WebElement> findElementsByNGShow(String using);
}
