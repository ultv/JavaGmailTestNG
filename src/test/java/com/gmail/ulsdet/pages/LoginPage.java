package com.gmail.ulsdet.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;


public class LoginPage {

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

    public void openPage(String url)
    {
        driver.get(url);
    }

    public void inputLogin(String login)
    {
        loginField.sendKeys(login);
        loginField.sendKeys(Keys.ENTER);
    }

    public void inputPass(String pass)
    {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("profileIdentifier")));

        passField.sendKeys(pass);
        passField.sendKeys(Keys.ENTER);
    }

    public boolean itsVissibleElement(By element)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        }
        catch (NoSuchElementException e)
        {
            return false;
        }

        return true;
    }

    public By getSearchFieldBy()
    {
        return By.className("gb_bf");
    }
}
