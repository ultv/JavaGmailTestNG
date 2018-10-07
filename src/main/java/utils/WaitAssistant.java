package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.NoSuchElementException;

public class WaitAssistant {

    public WebElement waitShowElement(WebDriver driver, By element, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public boolean waitReturnException(WebDriver driver, By element, int seconds) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            return false;
        } catch (NoSuchElementException e) {
            return true;
        } catch (TimeoutException ex) {
            return true;
        }
    }

    public boolean waitHideElement(WebDriver driver, By element, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }



}
