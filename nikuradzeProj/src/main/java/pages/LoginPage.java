package pages;

import io.qameta.allure.Step;
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
    @FindBy(xpath = ".//*[contains(@class, 'liveValidateMessage--visible')]")
    private List<WebElement> validationMessage;

    private String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public LoginPage openLoginPage(){
        try{
            webDriver.get(baseUrl);
            logger.info("Login page was opened");
            logger.info(baseUrl);
        }catch (Exception e){
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
        return this;
    }
    @Step
    public LoginPage enterUserNameIntoLoginInput(String userName){
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
        return this;
    }
    @Step
    public LoginPage enterUserNameIntoLoginInputWithButtons(String userName){
        enterTextIntoActiveElement(userName);
        return this;
    }
    @Step
    public LoginPage enterPasswordIntoInputPassword(String password){
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
        return this;
    }
    @Step
    public LoginPage enterPasswordIntoInputPasswordWithButtons(String password){
        enterTextIntoActiveElement(password);
        return this;
    }
    @Step
    public LoginPage clickOnButtonLogIn(){
    /*    try{
        //    webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
            buttonSignIn.click();
            logger.info("Button Sign In was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }*/
        clickOnElement(buttonSignIn);
        return this;
    }

/*    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }*/
    @Step
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
    @Step
    public boolean isAlertInvalidLoginDisplayed(){
        return isElementDisplayed(alertInvalidLogin);
    }
    @Step
    public HomePage loginWithValidCred() {
        openLoginPage();
        loginWithValidCredWithoutOpenPage();
        return new HomePage(webDriver);
    }
    @Step
    public LoginPage enterUserNameIntoRegistrationInput(String userName) {
        enterTextIntoElement(inputUserNameSignUp, userName);
        return this;
    }
    @Step
    public LoginPage enterUserNameIntoRegistrationInputWithButtons(String userName) {
        enterTextIntoActiveElement(userName);
        return this;
    }

    public LoginPage enterEmailIntoRegistrationInput(String email) {
        enterTextIntoElement(inputEmailSignUp, email);
        return this;
    }
    public LoginPage enterEmailIntoRegistrationInputWithButtons(String email) {
        enterTextIntoActiveElement(email);
        return this;
    }

    public LoginPage enterPasswordIntoRegistrationInput(String password) {
        enterTextIntoElement(inputPasswordSignUp, password);
        return this;
    }
    public LoginPage enterPasswordIntoRegistrationInputWithButtons(String password) {
        enterTextIntoActiveElement(password);
        return this;
    }
    public LoginPage logOutInBothTabs(){
        isElementDisplayed(buttonSignIn);
        ArrayList<String> tabs = new ArrayList<> (webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(0));
        webDriver.navigate().refresh();
        isElementDisplayed(buttonSignIn);
        return this;
    }
    public void checkValidationMessagesNumber(int numberOfAlerts){
        webDriverWaitLow.until(ExpectedConditions.numberOfElementsToBe(By.xpath(".//*[contains(@class, 'liveValidateMessage--visible')]"), numberOfAlerts));
        Assert.assertEquals("Incorrect number of alerts are displayed", numberOfAlerts, validationMessage.size());
        logger.info("Number of error messages on registration form is " + validationMessage.size());
    }

    public void checkValidationMessageText() {
        webDriverWaitLow.until(ExpectedConditions.numberOfElementsToBe(By.xpath(".//*[contains(@class, 'liveValidateMessage--visible')]"), 3));
        Assert.assertEquals("Username must be at least 3 characters.", validationMessage.get(0).getText());
        logger.info("Valid username error message: " + validationMessage.get(0).getText());
        Assert.assertEquals("You must provide a valid email address.", validationMessage.get(1).getText());
        logger.info("Valid email error message: " + validationMessage.get(1).getText());
        Assert.assertEquals("Password must be at least 12 characters.", validationMessage.get(2).getText());
        logger.info("Valid password error message: " + validationMessage.get(2).getText());
    }

    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWaitLow.withMessage("Number of messages should be " + expectedErrorsArray.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsLocator), expectedErrorsArray.length));
        Util.waitABit(1);
        Assert.assertEquals(expectedErrorsArray.length, listOfErrors.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();
        return this;
    }
    public LoginPage checkInvalidLogin() {
        Assert.assertTrue("Button Sign In and alert message are not visible", isButtonSignInDisplayed() & isAlertInvalidLoginDisplayed());
        return this;
    }

    public HomePage loginWithValidCredWithoutOpenPage() {
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();
        return new HomePage(webDriver);
    }
}
