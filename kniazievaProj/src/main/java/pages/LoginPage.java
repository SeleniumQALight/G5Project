package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder = 'Username']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = ".//input[@name='password' and @placeholder = 'Password']")
    private  WebElement inputPasswordHeader;

    @FindBy(xpath = ".//button[text() = 'Sign In' ]")
    private WebElement buttonSignIn;

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
        try{
            WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[text() = 'Sign In' ]"));
            return buttonSignIn.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public boolean isLoginOrPassInvalid(){
        try{
            WebElement inscriptionInvalidLogin = webDriver.findElement(By.xpath(".//div[text() = 'Invalid username / pasword']"));
            return inscriptionInvalidLogin.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();
        return new HomePage(webDriver);

    }
}
