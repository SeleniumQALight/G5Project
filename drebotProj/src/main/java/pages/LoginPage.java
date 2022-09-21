package pages;

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

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = ".//button[@class='py-3 mt-4 btn btn-lg btn-success btn-block']")
    private WebElement buttonSignUpForOurApp;

    private String alertTextLocator = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and contains(text(),'%s')]";
    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' ]")
    private List<WebElement> listOfError;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

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

    public void clickOnButtonLogIn() {
//        try {
//            //webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']")).click();
//            buttonSignIn.click();
//            logger.info("Button Sign in was clicked");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSignIn);
    }

    public LoginPage enterLoginIntoLoginInputUsingKeyTab(String login) {

        moveToElement(inputUserNameHeader);
        usersSendTextByActionTime(1, login);
        return this;
    }

    public LoginPage enterPasswordIntoPasswordInputUsingKeyTab(String password) {

        usersSendTextByActionTime(1, password);
        return this;
    }

    public LoginPage enterUserNameIntoRegistrationUsingKey(String userName) {

        moveToElement(inputUserNameRegistration);
        usersSendTextByActionTime(1, userName);
        usersPressesKeyTabTime(1);

        return this;
    }

    public LoginPage enterEmailIntoRegistrationUsingKey(String email) {

        moveToElement(inputEmailRegistration);
        usersSendTextByActionTime(1, email);
        usersPressesKeyTabTime(1);

        return this;
    }

    public LoginPage enterPasswordIntoRegistrationUsingKey(String password) {

        moveToElement(inputPasswordRegistration);
        usersSendTextByActionTime(1, password);

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

    public LoginPage enterUserNameIntoRegistration(String userName) {
        enterTextIntoElement(inputUserNameRegistration, userName);
        return this;
    }

    public LoginPage enterEmailIntoRegistration(String email) {
        enterTextIntoElement(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterPasswordIntoRegistration(String password) {
        enterTextIntoElement(inputPasswordRegistration, password);
        return this;
    }

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

    public void checkCountAlertMessage(String[] text) {
        webDriverWaitLow.until(ExpectedConditions.numberOfElementsToBe(By.xpath(String.format(alertTextLocator, "")), text.length));

        Assert.assertEquals("'" + text.length + "' alert are not displayed", text.length, listOfError.size());
        logger.info("'" + listOfError.size() + "' alert are displayed");
    }

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


}
