package com.orasi.web.by.angular;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface FindsByNGRepeat {
    WebElement findElementByNGRepeat(String using);

    List<WebElement> findElementsByNGRepeat(String using);
}
