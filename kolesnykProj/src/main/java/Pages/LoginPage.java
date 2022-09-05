package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    private LoginPage fillInUserNameRegister(String text){
        enterTextIntoElement(inputUserNameRegister, text);
        return this;
    }

    private LoginPage fillInUserEmailRegister(String email){
        enterTextIntoElement(inputUserEmailRegister, email);
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


    private LoginPage fillInUserPasswordRegister(String password){
        enterTextIntoElement(inputUserPasswordRegister, password);
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

//    private void printErrorAndStopTest(Exception e) {
//        log.error("Cannot work with element " + e);
//        Assert.fail("Cannot work with element " + e);
//    }
}
