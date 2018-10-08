package com.gmail.ulsdet.tests;

import com.gmail.ulsdet.pages.MailPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import com.gmail.ulsdet.pages.LoginPage;
import utils.ConfigProperties;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class LoginPageTest {

    public static WebDriver driver;
    public static LoginPage loginPage;
    public static MailPage mailPage;
    URL url = null;

    String login = ConfigProperties.getTestProperty("login");
    String pass = ConfigProperties.getTestProperty("pass");
    String baseUrl = ConfigProperties.getTestProperty("baseUrl");
    String search = ConfigProperties.getTestProperty("search");
    String subject = ConfigProperties.getTestProperty("subject");
    String message = ConfigProperties.getTestProperty("message");
    String node = ConfigProperties.getTestProperty("node");

    @Parameters("browser")
    @BeforeTest(description = "Configure something before test")
    protected WebDriver getDriver(String browser) {

        DesiredCapabilities capabilities = null;

        if(browser.equals("chrome")) {
            capabilities =  DesiredCapabilities.chrome();
        }
        else if (browser.equals("firefox")){
            capabilities =  DesiredCapabilities.firefox();
        }

        try {
            url = new java.net.URL(node);
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
        String actual = loginPage.openPage(baseUrl);
        Assert.assertEquals(actual, expected);
    }


    @Test(description = "Enter login")
    public void test_002() {

        loginPage.inputLogin(login);

        boolean actual = loginPage.isVissibleProfileIdentifier();
        Assert.assertTrue(actual);
    }

    @Test(description = "Enter pass")
    public void test_003() {

        loginPage.inputPass(pass);
        mailPage = new MailPage(driver);
        boolean actual = mailPage.isVissibleWriteButton();
        Assert.assertTrue(actual);
    }

    @Test(description = "Search mail")
    public void test_004() {

        boolean actual = mailPage.Search(search);
        Assert.assertTrue(actual);
    }

    @Test(description = "Send mail")
    public void test_005() {

        mailPage.resultCount();
        boolean actual = mailPage.writeMessage(subject, message);
        Assert.assertTrue(actual);
    }

    @AfterTest
    public static void tearDown() {

        mailPage.logOut();
        driver.quit();
    }
}