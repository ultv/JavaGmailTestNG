package com.gmail.ulsdet.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import com.gmail.ulsdet.pages.LoginPage;
import utils.ConfigProperties;

public class LoginPageTest {

    public static WebDriver driver;
    public static LoginPage loginPage;

    //@Parameters("browser")
    @BeforeTest
    protected WebDriver getDriver() //String browser) {
    {
      //  if(browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromeDriverPath"));
            driver = new ChromeDriver();
       // }
       // else if (browser.equals("firefox")){
       //     System.setProperty("webdriver.gecko.driver", ConfigProperties.getTestProperty("firefoxDriverPath"));
       //     driver = new FirefoxDriver();
       // }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        return driver;
    }


    @Test
    public void loginTest() {

        loginPage = new LoginPage(driver);
        loginPage.openPage(ConfigProperties.getTestProperty("baseUrl"));
        loginPage.inputLogin(ConfigProperties.getTestProperty("login"));
        loginPage.inputPass(ConfigProperties.getTestProperty("pass"));
        boolean actual = loginPage.itsVissibleElement(loginPage.getSearchFieldBy());
        Assert.assertTrue(actual);
    }


   // @AfterClass
   // public static void tearDown() {

        //driver.quit();
   // }
}