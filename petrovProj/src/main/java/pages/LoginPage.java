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

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordHeader;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[text()='Invalid username / pasword']")
    private WebElement messageErrorLoginOrPassword;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUserNameRegisterForm;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputUserEmailRegistrationForm;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputUserPasswordRegistrationForm;

    private String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;


    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement authInValidError;

    @FindBy(xpath = ".//*[contains(@class,'alert alert-danger text-center')]")
    private WebElement alertInCenter;


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
            assertFailedLogger("Can not work with site" + e);
        }
        return this;
    }

    public LoginPage enterUserNameIntoLoginInput(String userName) {
       enterTextIntoElement(inputUserNameHeader, userName);
       return this;
    }

    public LoginPage enterPasswordIntoInputPassword(String password) {
       enterTextIntoElement(inputPasswordHeader, password);
       return this;
    }

    public HomePage clickOnButtonLogin() {
        clickOnElement(buttonSignIn);
        return new HomePage(webDriver);
    }

    public boolean isMessageErrorLoginOrPassword(){
       return isElementWasDisplayed(messageErrorLoginOrPassword);

    }

    public LoginPage enterUserNameIntoRegistrationForm(String userNameCreate){
        enterTextIntoElement(inputUserNameRegisterForm, userNameCreate);
        return this;
    }
    public LoginPage enterEmailIntoRegistrationForm(String userEmail) {
        enterTextIntoElement(inputUserEmailRegistrationForm, userEmail);
        return this;
    }
    public LoginPage enterPasswordIntoRegistrationForm(String userPassword){
        enterTextIntoElement(inputUserPasswordRegistrationForm, userPassword);
        return this;
    }



    public boolean isMessageErrorDisplayed(String messageText){
        return isElementDisplayed(webDriver.findElement(By.xpath(".//form[@action='/register']//div[text()='" + messageText + "']")));
    }


    public HomePage loginWithValidCred() {
        openLoginPage();
        loginWithValidCredWithOutOpenPage();
        return new HomePage(webDriver);
    }


    public HomePage loginWithValidCredWithOutOpenPage() {
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }


    public LoginPage checkErrorsMessage(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWaitLow
                .withMessage("Number of message should be "+ expectedErrors.length())
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



    public void checkAuthErrorsMessage(String expectedErrorText) {
        isElementDisplayed(authInValidError);
        Assert.assertEquals(expectedErrorText, authInValidError.getText());
    }


    private void assertFailedLogger(String textForPrint){
        logger.error(textForPrint);
        Assert.fail(textForPrint);
    }


    public LoginPage checkAlertMessageText(String message) {
        Assert.assertEquals("Message in alert ", message, alertInCenter.getText());
        return this;
    }
}