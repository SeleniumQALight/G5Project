package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordHeader;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[text()='Invalid username / pasword']")
    private WebElement messageErrorLoginOrPassword;


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

    public void enterPasswordIntoInputPassword(String password) {
       enterTextIntoElement(inputPasswordHeader, password);
    }

    public void clickOnButtonLogin() {
        clickOnElement(buttonSignIn);
    }

    public boolean isMessageErrorLoginOrPassword(){
       return isElementWasDisplayed(messageErrorLoginOrPassword);

    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();

        return new HomePage(webDriver);
    }
}