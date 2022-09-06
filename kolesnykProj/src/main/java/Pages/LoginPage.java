package Pages;

import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static libs.TestData.*;


public class LoginPage extends ParentPage {

    @FindBy(xpath = "//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = "//input[@name='password' and @placeholder='Password']")
    private WebElement inputPasswordHeader;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSingIn;

    @FindBy(xpath = "//input[@id='username-register']")
    private WebElement inputUserNameRegister;

    @FindBy(id = "email-register")
    private WebElement inputUserEmailRegister;

    @FindBy(id = "password-register")
    private WebElement inputUserPasswordRegister;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonSubmit;

    private String listOfErrorsLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage openLoginPage() {
        try {
            driver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            log.info("Login page is opened");
            return this;
        } catch (Exception e) {
            log.error("Warning : Login page is not opened");
            Assert.fail("Login page is not opened");
            return this;
        }
    }

    public void enterUserNameIntoLoginInput(String username) {
        enterTextIntoElement(inputUserNameHeader, username);
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextIntoElement(inputPasswordHeader, password);
    }

    public void clickOnLoginButton() {
        clickOnElement(buttonSingIn);
    }

    public boolean isButtonSignInDisplayed(){
        return isElementDisplayed(buttonSingIn);
    }

    public HomePage loginWithValidCredentials() {
        openLoginPage();
        enterUserNameIntoLoginInput(VALID_LOGIN);
        enterPasswordIntoInputPassword(VALID_PASSWORD);
        clickOnLoginButton();
        return new HomePage(driver);
    }

    public LoginPage registerUserWithInvalidCredential(String username, String email, String password){
        openLoginPage()
                .fillInUserNameRegister(username)
                .fillInUserEmailRegister(email)
                .fillInUserPasswordRegister(password)
                .clickOnButtonSubmit();
        return this;
    }

    public LoginPage fillInUserNameRegister(String text){
        enterTextIntoElement(inputUserNameRegister, text);
        return this;
    }

    public LoginPage fillInUserEmailRegister(String email){
        enterTextIntoElement(inputUserEmailRegister, email);
        return this;
    }

    public LoginPage fillInUserPasswordRegister(String password){
        enterTextIntoElement(inputUserPasswordRegister, password);
        return this;
    }

    public LoginPage checkAmountOfAlertDuringRegistration(){
        try {
            if (findAlerts().size() == 3){
                log.info("All three Alerts are displayed");
            }
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
        return this;
    }


    public LoginPage clickOnButtonSubmit(){
        clickOnElement(buttonSubmit);
        return this;
    }

    public LoginPage checkAlertsText(String alertText){
        try {
            findElementByText(alertText);
            log.info("Alert with text : '"+ alertText + "' found");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
        return this;
    }

    public LoginPage checkErrorMessages(String expectedError) {
        String[] expectedErrorsArray = expectedError.split(";");
        webDriverWait10
                .withMessage("Number of messages should be " + expectedErrorsArray.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsLocator), expectedErrorsArray.length));
        Util.waitABit(1);
        Assert.assertEquals(expectedErrorsArray.length, listOfErrors.size());

        return this;
    }


//    private void printErrorAndStopTest(Exception e) {
//        log.error("Cannot work with element " + e);
//        Assert.fail("Cannot work with element " + e);
//    }
}
