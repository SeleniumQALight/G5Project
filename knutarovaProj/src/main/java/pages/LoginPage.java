package pages;

import io.qameta.allure.Step;
import libs.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.checkerframework.checker.units.qual.C;
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
    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement errorHeaderMessage;
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;
    @FindBy(xpath = ".//*[contains(@class,'alert alert-danger text-center')]")
    private WebElement alertInCenter;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public LoginPage openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened");
            logger.info(baseUrl);
        } catch (Exception e) {
            logger.error("Can't work with site");
            Assert.fail("Can't work with site");
        }
        return this;
    }

    @Step
    public LoginPage enterUsernameIntoLoginInput(String userName) {
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
        enterTextIntoElement(inputUserNameHeader, userName);
        return this;
    }

    @Step
    public LoginPage enterPasswordIntoInputPassword(String password) {
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
        enterTextIntoElement(inputPasswordHeader, password);
        return this;
    }

    @Step
    public LoginPage clickOnButtonLogIn() {
        //       try{
        //           webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        //           buttonSignIn.click();
        //           logger.info("Button Sign In was clicked");
        //       }catch (Exception e){
        //           printErrorAndStopTest(e);
        //       }
        clickOnElement(buttonSignIn);
        return this;
    }

    @Step
    public HomePage loginWithValidCred() {
        openLoginPage();
        loginWithValidCredWithOutOpenPage();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage enterTextInInputUsername(String text) {
        enterTextIntoElement(inputUsername, text);
        return this;
    }

    @Step
    public LoginPage enterTextInInputEmail(String text) {
        enterTextIntoElement(inputEmail, text);
        return this;
    }

    @Step
    public LoginPage enterTextInInputPassword(String text) {
        enterTextIntoElement(inputPassword, text);
        return this;
    }

    @Step
    public LoginPage checkWeSeeValidationMessagesInRegistrationForm(int number) {
        List<WebElement> registrationValidationMessagesList = getListWithRegistrationValidationMessages();
        Assert.assertEquals("There are not enough validation messages", number,
                registrationValidationMessagesList.size());
        return this;
    }

    @Step
    public List<WebElement> getListWithRegistrationValidationMessages() {
        webDriverWaitLow.withMessage("Validation messages is not on display")
                .until(ExpectedConditions
                        .numberOfElementsToBe(By.xpath(validationMessagesLocator), 3));
        return webDriver.findElements(By.xpath(validationMessagesLocator));
    }

    @Step
    public LoginPage checkErrorMessageDisplayed(String text) {
        try {
            WebElement errorMessage = webDriver.findElement(By.xpath(String.format(errorMessageLocator, text)));
            Assert.assertTrue(isElementDisplayed(errorMessage));
        } catch (Exception e) {
            logger.error("Validation message " + text + " not found");
            Assert.fail("Validation message " + text + " not found");
        }
        return this;
    }

    @Step
    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWaitLow.withMessage("Number of messages should be " + expectedErrors.length())
                .until(ExpectedConditions.numberOfElementsToBe
                        (By.xpath(validationMessagesLocator), expectedErrorsArray.length));
        Util.waitABit(1);
        Assert.assertEquals(expectedErrorsArray.length, listOfErrors.size());
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();
        return this;
    }

    @Step
    public LoginPage checkErrorMessageForInvalidHeaderUsernamePassword() {
        Assert.assertTrue(isElementDisplayed(errorHeaderMessage));
        return this;
    }

    @Step
    public HomePage loginWithValidCredWithOutOpenPage() {
        enterUsernameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();
        return new HomePage(webDriver);
    }

    @Step
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    @Step
    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public LoginPage checkAlertMessageText(String message) {
        Assert.assertEquals("Message in Alert ", message, alertInCenter.getText());
        return this;
    }
}