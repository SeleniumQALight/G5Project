package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder = 'Username']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = ".//input[@name='password' and @placeholder = 'Password']")
    private  WebElement inputPasswordHeader;

    @FindBy(xpath = ".//button[text() = 'Sign In' ]")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[text() = 'Invalid username / pasword']")
    private WebElement invalidUsernameOrPassword;

    @FindBy(xpath = ".//*[@class = 'alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> alertMessage;

    @FindBy(xpath = ".//input[@id = 'username-register']")
    private WebElement inputUsernameRegistered;
    @FindBy(xpath = ".//input[@id = 'email-register']")
    private WebElement inputEmailRegistered;
    @FindBy(xpath = ".//input[@id = 'password-register']")
    private WebElement inputPasswordRegister;
    @FindBy(xpath = ".//button[@class = 'py-3 mt-4 btn btn-lg btn-success btn-block']")
    private WebElement signUpForOurApp;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
    }

    public void enterUserNameIntoLoginInput(String userName) {
        enterTextIntoElement(inputUserNameHeader, userName);
    }

    public  void  enterPasswordIntoInputPassword(String password){
        enterTextIntoElement(inputPasswordHeader, password);
    }

    public void clickOnButtonLogIn(){
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInDisplayed(){
        return isElementDisplayed(buttonSignIn);
    }

    public boolean isLoginOrPassInvalid(){
        return isElementDisplayed(invalidUsernameOrPassword);
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();
        return new HomePage(webDriver);

    }

    public void checkNumberOfMessageInRegistrationForm(int numberOfMessage){
        webDriverWait15.until(ExpectedConditions.numberOfElementsToBe(By.xpath(".//*[@class = 'alert alert-danger small liveValidateMessage liveValidateMessage--visible']"), numberOfMessage));
    }


    public void checkMessageDisplayedWithText(String text){
        try{
            WebElement tempElement = webDriver.findElement(By.xpath(".//*[text() = '"+text+"']"));
            Assert.assertTrue(isElementDisplayed(tempElement));

        }catch (Exception e){
            Assert.fail("Can not find element with text " + text);
        }

    }

    public void enterUsernameInRegistrationForm(String text){
        enterTextIntoElement(inputUsernameRegistered, text);
    }

    public void enterEmailInRegistrationForm(String text){
        enterTextIntoElement(inputEmailRegistered, text);
    }

    public void enterPasswordInRegistrationForm(String text){
        enterTextIntoElement(inputPasswordRegister, text);
    }

}
