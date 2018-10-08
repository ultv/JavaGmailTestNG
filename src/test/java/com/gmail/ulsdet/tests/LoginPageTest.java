package com.gmail.ulsdet.tests;

import com.gmail.ulsdet.pages.MailPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import com.gmail.ulsdet.pages.LoginPage;
import utils.ConfigProperties;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class LoginPageTest {

    public static WebDriver driver;
    public static LoginPage loginPage;
    public static MailPage mailPage;
    URL url = null;

    @Parameters("browser")
    @BeforeTest(description = "Configure something before test")
    protected WebDriver getDriver(String browser) {

        DesiredCapabilities capabilities = null;

        if(browser.equals("chrome")) {
            //System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromeDriverPath"));
            //driver = new ChromeDriver();
            capabilities =  DesiredCapabilities.chrome();
       }
        else if (browser.equals("firefox")){
            //System.setProperty("webdriver.gecko.driver", ConfigProperties.getTestProperty("firefoxDriverPath"));
            //driver = new FirefoxDriver();
            capabilities =  DesiredCapabilities.firefox();
        }


        //DesiredCapabilities capabilities =  DesiredCapabilities.chrome();

        try{
            url = new java.net.URL("http://localhost:5555/wd/hub");
        }
        catch( Exception ex) {ex.printStackTrace();}

        driver = new RemoteWebDriver(url, capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        return driver;
    }

    @Test(description = "Open home page")
    public void test_001() {

        loginPage = new LoginPage(driver);
        String expected = "Gmail";
        String actual = loginPage.openPage(ConfigProperties.getTestProperty("baseUrl"));
        Assert.assertEquals(actual, expected);
    }


    @Test(description = "Enter login")
    public void test_002() {

        loginPage.inputLogin(ConfigProperties.getTestProperty("login"));

        boolean actual = loginPage.isVissibleProfileIdentifier();
        Assert.assertTrue(actual);
    }

    @Test(description = "Enter pass")
    public void test_003() {

        loginPage.inputPass(ConfigProperties.getTestProperty("pass"));
        mailPage = new MailPage(driver);
        boolean actual = mailPage.isVissibleWriteButton();
        Assert.assertTrue(actual);
    }

    @Test(description = "Search mail")
    public void test_004() {

        boolean actual = mailPage.Search("in:inbox Александр Седов");
        Assert.assertTrue(actual);
    }

    @Test(description = "Send mail")
    public void test_005() {

        mailPage.resultCount();
        boolean actual = mailPage.writeMessage("Тестовое задание. Седов", "Количество присланных писем = ");
        Assert.assertTrue(actual);
    }

    @AfterTest
    public static void tearDown() {

        mailPage.logOut();
        driver.quit();
    }
}