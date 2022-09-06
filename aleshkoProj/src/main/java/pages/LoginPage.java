package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputUsernameHeader;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordHeader;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//div[text()='Invalid username / pasword']")
    private WebElement alertInvalidLoginOrPassword;
    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputRegisterUsername;
    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputRegisterEmail;
    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputRegisterPassword;
    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSignUp;
    @FindBy(xpath = ".//input[@id='username-register']//..//div")
    private WebElement alertUsernameSignUp;
    @FindBy(xpath = ".//input[@id='email-register']//..//div")
    private WebElement alertEmailSignUp;
    @FindBy(xpath = ".//input[@id='password-register']//..//div")
    private WebElement alertPasswordSignUp;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        openPage("https://qa-complex-app-for-testing.herokuapp.com/");
        return this;
    }

    public LoginPage enterUsernameIntoLoginInput(String userName) {
        enterTextIntoElement(inputUsernameHeader, userName);
        return this;
    }

    public LoginPage enterPasswordIntoPasswordInput(String password) {
        enterTextIntoElement(inputPasswordHeader, password);
        return this;
    }

    public HomePage clickOnSignInButton() {
        clickOnElement(buttonSignIn);
        return new HomePage(webDriver);
    }

    public LoginPage enterUsernameIntoRegistrationInput(String userName) {
        enterTextIntoElement(inputRegisterUsername, userName);
        return this;
    }

    public LoginPage enterEmailIntoRegistrationInput(String email) {
        enterTextIntoElement(inputRegisterEmail, email);
        return this;
    }

    public LoginPage enterPasswordIntoRegistrationInput(String password) {
        enterTextIntoElement(inputRegisterPassword, password);
        return this;
    }

    public HomePage clickOnSignUpButton() {
        clickOnElement(buttonSignUp);
        return new HomePage(webDriver);
    }

    public LoginPage checkInlavidLoginAction() {
        Assert.assertTrue("Alert about wrong username/password is not visible", isElementDisplayed(alertInvalidLoginOrPassword));
        Assert.assertTrue("Button ''Sign In' is not visible", isElementDisplayed(buttonSignIn));
        return this;
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        enterUsernameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoPasswordInput(TestData.VALID_PASSWORD);
        clickOnSignInButton();
        return new HomePage(webDriver);
    }

    public LoginPage checkAlertAboutUsernameOnSignUpForm(String text) {
        webDriverWait10.until(ExpectedConditions.visibilityOf(alertUsernameSignUp));
        Assert.assertTrue("Alert is not displayed", isElementDisplayed(alertUsernameSignUp));
        Assert.assertEquals("Alert contains another text than requested", text, alertUsernameSignUp.getText());
        return this;
    }

    public LoginPage checkAlertAboutEmailOnSignUpForm(String text) {
        webDriverWait10.until(ExpectedConditions.visibilityOf(alertEmailSignUp));
        Assert.assertTrue("Alert is not displayed", isElementDisplayed(alertEmailSignUp));
        Assert.assertEquals("Alert contains another text than requested", text, alertEmailSignUp.getText());
        return this;
    }

    public LoginPage checkAlertAboutPasswordOnSignUpForm(String text) {
        webDriverWait10.until(ExpectedConditions.visibilityOf(alertPasswordSignUp));
        Assert.assertTrue("Alert is not displayed", isElementDisplayed(alertPasswordSignUp));
        Assert.assertEquals("Alert contains another text than requested", text, alertPasswordSignUp.getText());
        return this;
    }
}
