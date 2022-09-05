package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordHeader;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//div[text()='Invalid username / pasword']")
    private WebElement alertInvalidLogin;
    @FindBy(id = "username-register")
    private WebElement inputUserNameSignUp;
    @FindBy(id = "email-register")
    private WebElement inputEmailSignUp;
    @FindBy(id = "password-register")
    private WebElement inputPasswordSignUp;
    @FindBy(xpath = ".//*[contains(@class, 'visible')]")
    private List<WebElement> validationMessage;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try{
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
    }

    public void enterUserNameIntoLoginInput(String userName){
/*        try{
        //    WebElement webElement = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        //    webElement.clear();
        //    webElement.sendKeys(userName)
            inputUserNameHeader.clear();
            inputUserNameHeader.sendKeys(userName);
            logger.info(userName + " was entered into input");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }*/
        enterTextIntoElement(inputUserNameHeader, userName);
    }

    public void enterPasswordIntoInputPassword(String password){
       /* try{
        //    WebElement webElement = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        //    webElement.clear();
        //    webElement.sendKeys(password);
            inputPasswordHeader.clear();
            inputPasswordHeader.sendKeys(password);
            logger.info(password + " was entered into input");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }*/
        enterTextIntoElement(inputPasswordHeader, password);
    }

    public void clickOnButtonLogIn(){
    /*    try{
        //    webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
            buttonSignIn.click();
            logger.info("Button Sign In was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }*/
        clickOnElement(buttonSignIn);
    }

/*    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }*/

    public boolean isButtonSignInDisplayed(){
  /*      try{
            WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
            WebElement alert = webDriver.findElement(By.xpath(".//div[text()='Invalid username / pasword']"));
            return buttonSignIn.isDisplayed() & alert.isDisplayed();
        }catch (Exception e){
            return false;
        }*/
        return isElementDisplayed(buttonSignIn);
    }
    public boolean isAlertInvalidLoginDisplayed(){
        return isElementDisplayed(alertInvalidLogin);
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();
        return new HomePage(webDriver);
    }

    public void enterUserNameIntoRegistrationInput(String userName) {
        enterTextIntoElement(inputUserNameSignUp, userName);
    }

    public void enterEmailIntoRegistrationInput(String email) {
        enterTextIntoElement(inputEmailSignUp, email);
    }

    public void enterPasswordIntoRegistrationInput(String password) {
        enterTextIntoElement(inputPasswordSignUp, password);
    }
    public void checkValidationMessagesNumber(){
        //Util.waitABit(3);
        webDriverWait10.until(ExpectedConditions.visibilityOfAllElements(validationMessage.get(0), validationMessage.get(1), validationMessage.get(2)));
        Assert.assertEquals("Incorrect number of alerts are displayed", 3, validationMessage.size());
        logger.info("Number of error messages on registration form is " + validationMessage.size());
    }

    public void checkValidationMessageText() {
        //Util.waitABit(3);
        webDriverWait10.until(ExpectedConditions.visibilityOfAllElements(validationMessage.get(0), validationMessage.get(1), validationMessage.get(2)));
        Assert.assertEquals("Username must be at least 3 characters.", validationMessage.get(0).getText());
        logger.info("Valid username error message: " + validationMessage.get(0).getText());
        Assert.assertEquals("You must provide a valid email address.", validationMessage.get(1).getText());
        logger.info("Valid email error message: " + validationMessage.get(1).getText());
        Assert.assertEquals("Password must be at least 12 characters.", validationMessage.get(2).getText());
        logger.info("Valid password error message: " + validationMessage.get(2).getText());


    }
}
