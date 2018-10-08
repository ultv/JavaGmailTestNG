package com.gmail.ulsdet.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import utils.WaitAssistant;
import java.util.List;

public class LoginPage extends WaitAssistant {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(className = "whsOnd")
    private WebElement loginField;

    @FindBy(id = "profileIdentifier")
    private WebElement profileLogo;

    @FindBy(className = "whsOnd")
    private WebElement passField;

    @FindBy(className = "gb_bf")
    private WebElement searchField;

    @FindBy(id = "headingText")
    private WebElement welcomeText;

    @FindBy(id = "profileIdentifier")
    private WebElement profileText;

    @FindBy(id = "reload-button")
    private WebElement reloadButton;

    @FindBy(className = "dE00ab")
    private WebElement errorLoginPassMessage;

    private By profileLogoBy = By.id("profileIdentifier");

    private By welcomeTextBy = By.id("headingText");

    public String openPage(String url)
    {
        driver.get(url);

        while(waitReturnException(driver, welcomeTextBy, 2)) {

            reloadButton.click();
        }

        return driver.getTitle();
    }

    public void inputLogin(String login)
    {
        loginField.sendKeys(login);
        waitReturnException(driver, welcomeTextBy, 5);
        loginField.sendKeys(Keys.ENTER);
    }

    public void inputPass(String pass)
    {
        waitShowElement(driver, profileLogoBy, 15);
        passField.sendKeys(pass);
        waitReturnException(driver, profileLogoBy, 5);
        passField.sendKeys(Keys.ENTER);
    }

    public boolean itsVissibleElement(By element)
    {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        }
        catch (NoSuchElementException e) {
            return false;
        }

        return true;
    }

    public boolean isVissibleProfileIdentifier() {

        List<WebElement> elements = driver.findElements(profileLogoBy);

        if (elements.size() > 0)
            return true;
        else return false;
    }
}
