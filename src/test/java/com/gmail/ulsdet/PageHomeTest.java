package com.gmail.ulsdet;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.concurrent.TimeUnit;

public class PageHomeTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\CODE\\JAVA\\SeleniumDrivers\\chromedriver\\v.2.42\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://gmail.com");
    }
    @Test
    public void userLogin() {
        WebElement loginField = driver.findElement(By.className("whsOnd"));
        loginField.sendKeys("ulsdet");
        loginField.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("profileIdentifier")));

        loginField = driver.findElement(By.className("whsOnd"));
        loginField.sendKeys("ljrth%8102");
        loginField.sendKeys(Keys.ENTER);

        boolean expected = true;

        try
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("gb_bf")));
        }
        catch (NoSuchElementException e)
        {
            expected = false;
        }

        Assert.assertTrue(expected);
    }
    //@AfterClass
    //public static void tearDown() {
    //    WebElement menuUser = driver.findElement(By.cssSelector(".login-button__menu-icon"));
    //    menuUser.click();
    //    WebElement logoutButton = driver.findElement(By.id("login__logout"));
    //    logoutButton.click();
    //    driver.quit();
    //}
}