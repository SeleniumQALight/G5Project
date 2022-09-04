package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputUsernameHeader;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordHeader;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//div[text()='Invalid username / pasword']")
    private WebElement alertInvalidLoginOrPassword;

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
}
