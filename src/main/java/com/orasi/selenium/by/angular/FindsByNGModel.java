package com.orasi.selenium.by.angular;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface FindsByNGModel {
    WebElement findElementByNGModel(String using);

    List<WebElement> findElementsByNGModel(String using);
}
