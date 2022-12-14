package pages;

import io.qameta.allure.Step;
import libs.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @class='form-control form-control-sm input-dark']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = ".//input[@name='password' and @class='form-control form-control-sm input-dark']")
    private WebElement inputPasswordHeader;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement messageInvalidUserPassword;

    @FindBy(id = "username-register")
    private WebElement inputUserNameRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")//
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = ".//button[@class='py-3 mt-4 btn btn-lg btn-success btn-block']")
    private WebElement buttonSignUpForOurApp;

    private String alertTextLocator = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and contains(text(),'%s')]";
    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' ]")
    private List<WebElement> listOfError;

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
        } catch (Exception ex) {
            logger.error("Can't work with site");
            Assert.fail("Can't work with site");
        }
        return this;
    }

    public LoginPage enterUserNameIntoLoginInput(String userName) {
//        try {
//            //WebElement webElement = webDriver.findElement(By.xpath(".//input[@name='username' and @class='form-control form-control-sm input-dark']"));
//            inputUserNameHeader.clear();
//            inputUserNameHeader.sendKeys(userName);
//            logger.info((userName + " was entered into input"));
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputUserNameHeader, userName);
        return this;
    }

    public LoginPage enterPasswordIntoPasswordInput(String password) {
//        try {
////          WebElement webElement = webDriver.findElement(By.xpath(".//input[@name='password' and @class='form-control form-control-sm input-dark']"));
//            inputPasswordHeader.clear();
//            inputPasswordHeader.sendKeys(password);
//            logger.info(password + " was entered into input");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputPasswordHeader, password);
        return this;
    }

    public HomePage clickOnButtonLogIn() {
//        try {
//            //webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']")).click();
//            buttonSignIn.click();
//            logger.info("Button Sign in was clicked");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSignIn);
        return new HomePage(webDriver);
    }

    public LoginPage enterLoginIntoLoginInputUsingKeyTab(String login) {

        moveToElement(inputUserNameHeader);
        if (isElementIsActive(inputUserNameHeader)) {
            usersSendTextByActionTime(1, login);
        } else {
            logger.info("login '" + login + "' can't be inputted");
        }

        return this;
    }

    public LoginPage enterPasswordIntoPasswordInputUsingKeyTab(String password) {

        if (isElementIsActive(inputPasswordHeader)) {
            usersSendTextByActionTime(1, password);
        } else {
            logger.info("password '" + password + "' can't be inputted");
        }
        return this;
    }

    public HomePage clickOnButtonLogInUsingKey() {
        if (isElementIsActive(buttonSignIn)) {
            usersPressesKeyEnterTime(1);
        } else {
            logger.info("can't press ENTER");
        }
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage enterUserNameIntoRegistrationUsingKey(String userName) {

        moveToElement(inputUserNameRegistration);
        if (isElementIsActive(inputUserNameRegistration)) {
            usersSendTextByActionTime(1, userName);
        } else {
            logger.info("userName '" + userName + "' can't be inputted");
        }
        return this;
    }

    @Step
    public LoginPage enterEmailIntoRegistrationUsingKey(String email) {

        if (isElementIsActive(inputEmailRegistration)) {
            usersSendTextByActionTime(1, email);
        } else {
            logger.info("email '" + email + "' can't be inputted");
        }

        return this;
    }

    @Step
    public LoginPage enterPasswordIntoRegistrationUsingKey(String password) {

        if (isElementIsActive(inputPasswordRegistration)) {
            usersSendTextByActionTime(1, password);
        } else {
            logger.info("password '" + password + "' can't be inputted");
        }
        return this;
    }


    public boolean isMessageInvalidUserPassword() {
//        try {
//            WebElement messageInvalidUserPassword = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']"));
//            return messageInvalidUserPassword.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
        return isElementDisplayed(messageInvalidUserPassword);
    }

    public HomePage loginWithValidCredential() {
        openLoginPage();
        loginWithValidCredentialWithOutOpenPage();

        return new HomePage(webDriver);
    }


    public HomePage loginWithValidCredentialWithOutOpenPage() {
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoPasswordInput(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();

        return new HomePage(webDriver);
    }

    public LoginPage checkIsRedirectToLoginPage() {
        checkUrl();
        Assert.assertTrue("LoginPage doesn't loaded", isElementDisplayed(buttonSignIn));
        return this;
    }

    @Step
    public LoginPage enterUserNameIntoRegistration(String userName) {
        enterTextIntoElement(inputUserNameRegistration, userName);
        return this;
    }

    @Step
    public LoginPage enterEmailIntoRegistration(String email) {
        enterTextIntoElement(inputEmailRegistration, email);
        return this;
    }

    @Step
    public LoginPage enterPasswordIntoRegistration(String password) {
        enterTextIntoElement(inputPasswordRegistration, password);
        return this;
    }

    @Step
    public void checkAlertText(String[] text) {
        for (int i = 0; i < text.length; i++) {

            if (listOfError.isEmpty()) {
                logger.info("expected alert '" + text[i] + "' is not displayed");
                Assert.fail("can't find element '" + text[i] + "' ");
            }
            Assert.assertTrue("element '" + text[i] + "' is not displayed", isElementDisplayed(listOfError.get(0)));
            logger.info("alert message '" + text[i] + "' is displayed");

        }
    }

    @Step
    public void checkCountAlertMessage(String[] text) {
        webDriverWaitLow.until(ExpectedConditions.numberOfElementsToBe(By.xpath(String.format(alertTextLocator, "")), text.length));

        Assert.assertEquals("'" + text.length + "' alert are not displayed", text.length, listOfError.size());
        logger.info("'" + listOfError.size() + "' alert are displayed");
    }

    @Step
    public LoginPage checkErrorMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWaitLow.withMessage("Number of messages ").until(ExpectedConditions.numberOfElementsToBe(By.xpath(String.format(alertTextLocator, "")), expectedErrorsArray.length));

        Util.waitABit(1);
        Assert.assertEquals(expectedErrorsArray.length, listOfError.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : listOfError) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();

        return this;
    }


    public LoginPage checkAlertMessagesText(String message) {
        Assert.assertEquals("Message in alert", message, alertInCenter.getText());
        return this;
    }
}
