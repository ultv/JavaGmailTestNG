package com.gmail.ulsdet.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import com.gmail.ulsdet.pages.LoginPage;

public class LoginPageTest {

    public static WebDriver driver;
    public static LoginPage loginPage;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\CODE\\JAVA\\SeleniumDrivers\\chromedriver\\v.2.42\\chromedriver.exe");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://gmail.com");
    }

    @Test
    public void loginTest() {

        loginPage.inputLogin("ulsdet");
        loginPage.inputPass("ljrth%8102");
        boolean actual = loginPage.itsVissibleElement(loginPage.getSearchFieldBy());
        Assert.assertTrue(actual);
    }


    @AfterClass
    public static void tearDown() {

        //driver.quit();
    }
}