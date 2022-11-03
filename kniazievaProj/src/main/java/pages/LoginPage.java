package pages;

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

    @FindBy(xpath = ".//input[@name='username' and @placeholder = 'Username']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = ".//input[@name='password' and @placeholder = 'Password']")
    private  WebElement inputPasswordHeader;

    @FindBy(xpath = ".//button[text() = 'Sign In' ]")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[text() = 'Invalid username / pasword']")
    private WebElement invalidUsernameOrPassword;

    private String listOfErrorsLocator = ".//*[@class = 'alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


    @FindBy(xpath = ".//input[@id = 'username-register']")
    private WebElement inputUsernameRegistered;
    @FindBy(xpath = ".//input[@id = 'email-register']")
    private WebElement inputEmailRegistered;
    @FindBy(xpath = ".//input[@id = 'password-register']")
    private WebElement inputPasswordRegister;
    @FindBy(xpath = ".//button[@class = 'py-3 mt-4 btn btn-lg btn-success btn-block']")
    private WebElement signUpForOurApp;
    @FindBy(xpath = ".//*[@class = 'alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;


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
        } catch (Exception e) {
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
        return this;
    }

    public LoginPage enterUserNameIntoLoginInput(String userName) {
        enterTextIntoElement(inputUserNameHeader, userName);
        return this;
    }

    public  LoginPage  enterPasswordIntoInputPassword(String password){
        enterTextIntoElement(inputPasswordHeader, password);
        return this;
    }

    public HomePage clickOnButtonLogIn(){
        clickOnElement(buttonSignIn);
        return new HomePage(webDriver);
    }

    public boolean isButtonSignInDisplayed(){
        return isElementDisplayed(buttonSignIn);
    }

    public boolean isLoginOrPassInvalid(){
        return isElementDisplayed(invalidUsernameOrPassword);
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        loginWithValidCredWithoutOpenPage();
        return new HomePage(webDriver);

    }

    public void checkNumberOfMessageInRegistrationForm(int numberOfMessage){
        webDriverWaitHigh.until(ExpectedConditions.numberOfElementsToBe(By.xpath(".//*[@class = 'alert alert-danger small liveValidateMessage liveValidateMessage--visible']"), numberOfMessage));
    }


    public void checkMessageDisplayedWithText(String text){
        try{
            WebElement tempElement = webDriver.findElement(By.xpath(".//*[text() = '"+text+"']"));
            Assert.assertTrue(isElementDisplayed(tempElement));

        }catch (Exception e){
            Assert.fail("Can not find element with text " + text);
        }

    }

    public LoginPage enterUsernameInRegistrationForm(String userName){
        enterTextIntoElement(inputUsernameRegistered, userName);
        return this;
    }

    public LoginPage enterEmailInRegistrationForm(String email){
        enterTextIntoElement(inputEmailRegistered, email);
        return this;
    }

    public LoginPage enterPasswordInRegistrationForm(String password){
        enterTextIntoElement(inputPasswordRegister, password);
        return this;
    }

    public LoginPage checkErrorMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWaitLow
                .withMessage("Number of messages should be " + expectedErrorsArray.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsLocator),expectedErrorsArray.length ));

        Util.waitABit(1);
        Assert.assertEquals(expectedErrorsArray.length, listOfErrors.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listOfErrors){
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();


        return this;
    }

    public HomePage loginWithValidCredWithoutOpenPage() {
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();
        return new HomePage(webDriver);
    }
}
