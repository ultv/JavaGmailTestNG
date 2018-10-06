package com.gmail.ulsdet.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import com.gmail.ulsdet.pages.LoginPage;

public class LoginPageTest {

    public static WebDriver driver;
    public static LoginPage loginPage;

    @Parameters("browser")
    @BeforeTest
    protected WebDriver getDriver(String browser) {

        if(browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\CODE\\JAVA\\SeleniumDrivers\\chromedriver\\v.2.42\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if (browser.equals("firefox")){
            System.setProperty("webdriver.gecko.driver", "C:\\CODE\\JAVA\\SeleniumDrivers\\geckodriver\\v0.16.0\\geckodriver.exe");
            //System.setProperty("webdriver.gecko.driver", "C:\\CODE\\JAVA\\SeleniumDrivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        return driver;
    }


    @Test
    public void loginTest() {

        loginPage = new LoginPage(driver);
        loginPage.openPage("https://gmail.com");
        loginPage.inputLogin("ulsdet");
        loginPage.inputPass("ljrth%8102");
        boolean actual = loginPage.itsVissibleElement(loginPage.getSearchFieldBy());
        Assert.assertTrue(actual);
    }


   // @AfterClass
   // public static void tearDown() {

        //driver.quit();
   // }
}