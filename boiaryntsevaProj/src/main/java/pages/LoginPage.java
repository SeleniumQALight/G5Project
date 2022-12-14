package pages;

import io.qameta.allure.Step;
import libs.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//div//span[2]")
    private WebElement textLoggedInUserName;

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSingIn;

    @FindBy(xpath = ".//div[contains(text(), 'Invalid')]")
    private WebElement invalidLoginMessage;

    @FindBy(id = "username-register")
    private WebElement inputUserNameRegister;

    @FindBy(xpath = ".//*[@id='email-register']")
    private WebElement inputEmailRegister;

    @FindBy(xpath = ".//*[@id='password-register']")
    private WebElement inputPasswordRegister;

    @FindBy(xpath = ".//div//button[@type='submit']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[@class='form-group'][3]//div")
    private WebElement validationErrorMessagePassword;

    @FindBys(@FindBy(xpath = "//div[contains (@class, 'visible')]"))
    private List<WebElement> validationErrorMessage;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;

    private String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = ".//*[contains(@class,'alert alert-danger text-center')]")
    private WebElement alertInCenter;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public String getTextLoggedInUserNameAsString() {
        return textLoggedInUserName.getText();
    }

    @Step
    public LoginPage openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login Page is opened");
            logger.info(baseUrl);
        } catch (Exception e) {
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
        return this;
    }
    public LoginPage isRegisterPasswordActive() {
        Util.waitABit(1);
        isElementActive(inputPasswordRegister);
        String borderColor = inputPasswordRegister.getCssValue("border-color");
        if (borderColor.contains("128, 189, 255")){
            logger.info("The element is highlighted");
        }
        return this;
    }

    public LoginPage isPasswordActive() {
        isElementActive(inputPassword);
        return this;
    }

    public LoginPage isSignUpButtonActive() {
        isElementActive(buttonSignUp);
        return this;
    }

    @Step
    public LoginPage enterUserNameIntoLoginInput(String username) {
//        try {
////            WebElement webElement = webDriver.
////                    findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
//            inputUserNameHeader.clear();
//            inputUserNameHeader.sendKeys(username);
//            logger.info(username + "was entered into input");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputUserNameHeader, username);
        return this;
    }

    @Step
    public LoginPage enterUserNameIntoRegistrationInput(String username) {
        enterTextIntoElement(inputUserNameRegister, username);
        return this;
    }

    @Step
    public LoginPage enterPasswordIntoPasswordInput(String password) {
//        try {
////            WebElement webElement = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
//            inputPassword.clear();
//            inputPassword.sendKeys(password);
//            logger.info("Password was entered");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputPassword, password);
        return this;
    }

    @Step
    public HomePage clickOnButtonLogin() {
//        try {
////            webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
//            buttonSingIn.click();
//            logger.info("Sign in button was clicked");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSingIn);
        return new HomePage(webDriver);
    }

    //    private void printErrorAndStopTest(Exception e) {
//        logger.error("Can not work with element " + e);
//        Assert.fail("Can not work with element " + e);
//    }
    @Step
    public boolean isInvalidLoginMsgVisible() {
//        try {
//            WebElement invalidLoginMessage = webDriver.findElement(By.xpath(".//div[contains(text(), 'Invalid')]"));
//            return invalidLoginMessage.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
        return isElementDisplayed(invalidLoginMessage);
    }
    @Step
    public HomePage loginWithValidCred() {
        openLoginPage();
        loginWithValidCredWithoutOpenPage();
        return new HomePage(webDriver);
    }
    @Step
    public LoginPage loginWithInvalidCred(String name, String pass, String email) {
        openLoginPage();
        enterUserNameIntoRegistrationInput(name);
        enterEmailIntoRegistrationInput(email);
        enterUserPasswordIntoRegistrationInput(pass);
        // clickOnElement(buttonSignUp);
        return this;
    }
    @Step
    public LoginPage enterEmailIntoRegistrationInput(String email) {
        enterTextIntoElement(inputEmailRegister, email);
        return this;
    }
    @Step
    public LoginPage enterUserPasswordIntoRegistrationInput(String password) {
        enterTextIntoElement(inputPasswordRegister, password);
        return this;
    }
    @Step
    public LoginPage validateErrorMessagesCountOnLoginPage(int countOfErrorMessages) {
        webDriverWaitLow.until(ExpectedConditions.numberOfElementsToBe
                (By.xpath(".//div[contains (@class, 'visible')]"), countOfErrorMessages));
        Assert.assertEquals("Not all error messages are displayed", countOfErrorMessages, validationErrorMessage.size());
        logger.info("Error messages count on sign up form is " + validationErrorMessage.size());
        return this;
    }

    @Step
    public void validateErrorMessagesTextOnSignUp() {
        webDriverWaitLow.until(ExpectedConditions.visibilityOf(validationErrorMessagePassword));
        Assert.assertEquals("Username must be at least 3 characters."
                , validationErrorMessage.get(0).getText());
        logger.info("Text for username error message is validated: "
                + validationErrorMessage.get(0).getText());
        Assert.assertEquals("You must provide a valid email address."
                , validationErrorMessage.get(1).getText());
        logger.info("Text for email error message is validated: "
                + validationErrorMessage.get(1).getText());
        Assert.assertEquals("Password must be at least 12 characters."
                , validationErrorMessage.get(2).getText());
        logger.info("Text for password error message is validated: "
                + validationErrorMessage.get(2).getText());


    }
    @Step
    public LoginPage checkErrorMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWaitLow
                .withMessage("Number of messages should be " + expectedErrorsArray.length)
                .until(
                        ExpectedConditions.numberOfElementsToBe(
                                By.xpath(listOfErrorsLocator
                                ), expectedErrorsArray.length));
        Util.waitABit(1);
        Assert.assertEquals(expectedErrorsArray.length, listOfErrors.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : listOfErrors) {
            actualTextFromErrors.add(element.getText());
        } // po vsih weblementah 'element' zi spisky list of errors

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();
        return this;
    }
    @Step
    public HomePage loginWithValidCredWithoutOpenPage() {
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoPasswordInput(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public LoginPage checkAlertMessageText(String message) {
        Assert.assertEquals("Message in Allert", message, alertInCenter.getText());
        return this;
    }
}
