package com.gmail.ulsdet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitAssistant;
import java.util.List;

public class MailPage extends WaitAssistant {

    public MailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    private int countMail;

    @FindBy(className = "gb_bf")
    private WebElement searchField;

    @FindBy(className = "av")
    private WebElement resultSearch;

    @FindBy(css = ".T-I-KE")
    private WebElement writeButton;

    @FindBy(name = "to")
    private WebElement toField;

    @FindBy(name = "subjectbox")
    private WebElement subjectField;

    @FindBy(className = "Am")
    private WebElement messageField;

    @FindBy(className = "ams")
    private WebElement replyButton;

    @FindBy(className = "oL")
    private WebElement mailToText;

    @FindBy(className = "og")
    private WebElement delBytton;

    @FindBy(className = "gb_9a")
    private WebElement accountButton;

    private By exitLink = By.linkText("Выйти");

    private By okMessage = By.className("bAq");

    private By writeButtonBy = By.cssSelector(".T-I-KE");

    private By nonSortedTextBy = By.className("aKz");

    private By resultSearchBy = By.className("av");

    private By mailToTextBy = By.className("oL");

    private By toFieldBy = By.name("to");

    public boolean isVissibleWriteButton() {

        return !waitReturnException(driver, writeButtonBy, 15);
    }

    public boolean Search(String text) {

        searchField.clear();
        searchField.sendKeys(text + Keys.ENTER);

        return waitHideElement(driver, nonSortedTextBy, 15);
    }

    public int resultCount() {
        List<WebElement> elements = driver.findElements(resultSearchBy);
        countMail = elements.size();

        if (countMail > 0)
            elements.get(0).click();

        return countMail;
    }

    public boolean writeMessage(String subject, String message) {

        String mailTo = GetMailTo();
        delBytton.click();

        writeButton.click();
        WebElement sendTo = waitShowElement(driver, toFieldBy, 15);

        sendTo.sendKeys(mailTo);
        subjectField.sendKeys(subject);
        messageField.sendKeys(message + countMail);
        messageField.sendKeys(Keys.CONTROL, Keys.ENTER);

        if (!waitReturnException(driver, okMessage, 10))
        {
            return true;
        }

        return false;
    }

    public String GetMailTo() {
        replyButton.click();

        return waitShowElement(driver, mailToTextBy, 15).getText();
    }

    public void logOut() {

        accountButton.click();
        waitShowElement(driver, exitLink, 5).click();

    }


}
