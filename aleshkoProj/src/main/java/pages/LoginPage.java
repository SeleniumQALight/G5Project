package pages;

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

    public void openLoginPage() {
        openPage("https://qa-complex-app-for-testing.herokuapp.com/");
    }

    public void enterUsernameIntoLoginInput(String userName) {
        enterTextIntoElement(inputUsernameHeader, userName);
    }

    public void enterPasswordIntoPasswordInput(String password) {
        enterTextIntoElement(inputPasswordHeader, password);
    }

    public void clickOnSignInButton() {
        clickOnElement(buttonSignIn);
    }

    public boolean isAlertDisplayed() {
        return isElementDisplayed(alertInvalidLoginOrPassword);
    }

    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonSignIn);
    }
}
