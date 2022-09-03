package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSingIn;

    @FindBy(xpath = ".//div[contains(text(), 'Invalid')]")
    private WebElement invalidLoginMessage;

    @FindBy(xpath = ".//*[@id='username-register']")
    private WebElement inputUserNameRegister;

    @FindBy (xpath = ".//*[@id='email-register']")
    private WebElement inputEmailRegister;

    @FindBy (xpath = ".//*[@id='password-register']")
    private WebElement inputPasswordRegister;

    @FindBy (xpath = ".//div//button[@type='submit']")
    private WebElement buttonSignUp;

    @FindBys(@FindBy (xpath = "//div[contains (@class, 'alert')]"))
    private List <WebElement> validationErrorMessage;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login Page is opened");
        } catch (Exception e) {
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
    }

    public void enterUserNameIntoLoginInput(String username) {
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
    }

    public void enterUserNameIntoRegistrationInput(String username){
        enterTextIntoElement(inputUserNameRegister, username);
    }

    public void enterPasswordIntoPasswordInput(String password) {
//        try {
////            WebElement webElement = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
//            inputPassword.clear();
//            inputPassword.sendKeys(password);
//            logger.info("Password was entered");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputPassword, password);
    }

    public void clickOnButtonLogin() {
//        try {
////            webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
//            buttonSingIn.click();
//            logger.info("Sign in button was clicked");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSingIn);
    }

//    private void printErrorAndStopTest(Exception e) {
//        logger.error("Can not work with element " + e);
//        Assert.fail("Can not work with element " + e);
//    }

    public boolean isInvalidLoginMsgVisible() {
//        try {
//            WebElement invalidLoginMessage = webDriver.findElement(By.xpath(".//div[contains(text(), 'Invalid')]"));
//            return invalidLoginMessage.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
        return isElementDisplayed(invalidLoginMessage);
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoPasswordInput(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    public LoginPage loginWithInvalidCred(){
        openLoginPage();
        enterUserNameIntoRegistrationInput(TestData.INVALID_LOGIN);
        enterUserPasswordIntoRegistrationInput(TestData.INVALID_PASSWORD);
        enterEmailIntoRegistrationInput(TestData.INVALID_EMAIL);
        clickOnElement(buttonSignUp);
        return this;
    }

    private void enterEmailIntoRegistrationInput(String email) {
        enterTextIntoElement(inputEmailRegister, email);
    }

    private void enterUserPasswordIntoRegistrationInput(String password) {
        enterTextIntoElement(inputEmailRegister, password);
    }

    public LoginPage validateErrorMessagesCountOnLoginPage(){
        loginWithInvalidCred();
        Assert.assertEquals("Not all error messages are displayed", 3, validationErrorMessage.size());
        logger.info("Error messages count on sign up form is " + validationErrorMessage.size());
        return this;
    }


    public void validateErrorMessagesTextOnSignUp() {
        loginWithInvalidCred();
        Assert.assertEquals("Username must be at least 3 characters."
                ,validationErrorMessage.get(0).getText());
        logger.info("Text for username error message is validated: "
                +validationErrorMessage.get(0).getText());
        Assert.assertEquals("You must provide a valid email address."
                ,validationErrorMessage.get(1).getText());
        logger.info("Text for email error message is validated: "
                +validationErrorMessage.get(1).getText());
        Assert.assertEquals("Password must be at least 12 characters."
                ,validationErrorMessage.get(2).getText());
        logger.info("Text for password error message is validated: "
                +validationErrorMessage.get(2).getText());


    }
}
