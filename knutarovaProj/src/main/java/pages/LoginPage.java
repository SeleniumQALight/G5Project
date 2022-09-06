package pages;

import com.google.common.cache.AbstractCache;
import libs.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordHeader;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUsername;
    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmail;
    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPassword;

    private String validationMessagesLocator =
            ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    private String errorMessageLocator = ".//div[text()='%s']";
    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can't work with site");
            Assert.fail("Can't work with site");
        }return this;
    }

    public void enterUsernameIntoLoginInput(String userName) {
 //       try {
 //           WebElement webElement = webDriver.findElement(By.xpath(".//input[@name='username' and " +
 //                   "@placeholder='Username']"));
 //           webElement.clear();
 //           webElement.sendKeys(userName);
 //           inputUserNameHeader.clear();
 //           inputUserNameHeader.sendKeys(userName);
 //           logger.info(userName + " was entered into input");
 //       } catch (Exception e) {
 //           printErrorAndStopTest(e);
 //       }
        enterTextIntoElement(inputUserNameHeader,userName);
    }

    public void enterPasswordIntoInputPassword(String password){
 //       try{
 //           WebElement webElement = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
 //           webElement.clear();
 //           webElement.sendKeys(password);
 //           inputPasswordHeader.clear();
 //           inputPasswordHeader.sendKeys(password);
 //           logger.info(password + " was entered into input");
 //       }catch (Exception e){
 //           printErrorAndStopTest(e);
 //       }
        enterTextIntoElement(inputPasswordHeader,password);
    }

    public void clickOnButtonLogIn(){
 //       try{
 //           webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
 //           buttonSignIn.click();
 //           logger.info("Button Sign In was clicked");
 //       }catch (Exception e){
 //           printErrorAndStopTest(e);
 //       }
        clickOnElement(buttonSignIn);
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can't work with element " + e);
        Assert.fail("Can't work with element " + e);
    }

    public HomePage LoginWithValidCred() {
        openLoginPage();
        enterUsernameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();
        return new HomePage(webDriver);
    }

    public LoginPage enterTextInInputUsername(String text) {
        enterTextIntoElement(inputUsername, text);
        return this;
    }

    public LoginPage enterTextInInputEmail(String text) {
        enterTextIntoElement(inputEmail, text);
        return this;
    }

    public LoginPage enterTextInInputPassword(String text) {
        enterTextIntoElement(inputPassword, text);
        return this;
    }

    public LoginPage checkWeSeeValidationMessagesInRegistrationForm(int number) {
        List<WebElement> registrationValidationMessagesList = getListWithRegistrationValidationMessages();
        Assert.assertEquals("There are not enough validation messages", number,
                registrationValidationMessagesList.size());
        return this;
    }

    public List<WebElement> getListWithRegistrationValidationMessages() {
        webDriverWait10.withMessage("Validation messages is not on display")
                .until(ExpectedConditions
                        .numberOfElementsToBe(By.xpath(validationMessagesLocator),3));
        return webDriver.findElements(By.xpath(validationMessagesLocator));
    }

    public LoginPage checkErrorMessageDisplayed(String text){
        try {
            WebElement errorMessage = webDriver.findElement(By.xpath(String.format(errorMessageLocator, text)));
            Assert.assertTrue(isElementDisplayed(errorMessage));
        }catch (Exception e){
            logger.error("Validation message " + text + " not found");
            Assert.fail("Validation message " + text + " not found");
        }
        return this;
    }


    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage("Number of messages shoud be " + expectedErrors.length())
                .until(ExpectedConditions.numberOfElementsToBe
                        (By.xpath(validationMessagesLocator), expectedErrorsArray.length));
        Util.waitABit(1);
        Assert.assertEquals(expectedErrorsArray.length, listOfErrors.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();

        return this;
    }
}
