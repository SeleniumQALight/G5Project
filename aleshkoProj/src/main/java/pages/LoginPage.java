package pages;

import io.qameta.allure.Step;
import libs.DB_Util_seleniumUsers;
import libs.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputUsernameHeader;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordHeader;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//*[contains(@class,'alert alert-danger text-center')]")
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

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;

    private String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    @Step
    public LoginPage openLoginPage() {
        openPage(baseUrl);
        return this;
    }

    @Step
    public LoginPage enterUsernameIntoLoginInput(String userName) {
        enterTextIntoElement(inputUsernameHeader, userName);
        return this;
    }

    @Step
    public LoginPage enterPasswordIntoPasswordInput(String password) {
        enterTextIntoElement(inputPasswordHeader, password);
        return this;
    }

    @Step
    public HomePage clickOnSignInButton() {
        clickOnElement(buttonSignIn);
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage enterUsernameIntoRegistrationInput(String userName) {
        enterTextIntoElement(inputRegisterUsername, userName);
        return this;
    }

    @Step
    public LoginPage enterEmailIntoRegistrationInput(String email) {
        enterTextIntoElement(inputRegisterEmail, email);
        return this;
    }

    @Step
    public LoginPage enterPasswordIntoRegistrationInput(String password) {
        enterTextIntoElement(inputRegisterPassword, password);
        return this;
    }

    @Step
    public HomePage clickOnSignUpButton() {
        clickOnElement(buttonSignUp);
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage checkInlavidLoginAction() {
        Assert.assertTrue("Alert about wrong username/password is not visible", isElementDisplayed(alertInvalidLoginOrPassword));
        Assert.assertTrue("Button ''Sign In' is not visible", isElementDisplayed(buttonSignIn));
        return this;
    }

    @Step
    public HomePage loginWithValidCred() {
        openLoginPage();
        loginWithValidCredWithoutOpenPage();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage checkAlertAboutUsernameOnSignUpForm(String text) {
        webDriverWaitLow.until(ExpectedConditions.visibilityOf(alertUsernameSignUp));
        Assert.assertTrue("Alert is not displayed", isElementDisplayed(alertUsernameSignUp));
        Assert.assertEquals("Alert contains another text than requested", text, alertUsernameSignUp.getText());
        return this;
    }

    @Step
    public LoginPage checkAlertAboutEmailOnSignUpForm(String text) {
        webDriverWaitLow.until(ExpectedConditions.visibilityOf(alertEmailSignUp));
        Assert.assertTrue("Alert is not displayed", isElementDisplayed(alertEmailSignUp));
        Assert.assertEquals("Alert contains another text than requested", text, alertEmailSignUp.getText());
        return this;
    }

    @Step
    public LoginPage checkAlertAboutPasswordOnSignUpForm(String text) {
        webDriverWaitLow.until(ExpectedConditions.visibilityOf(alertPasswordSignUp));
        Assert.assertTrue("Alert is not displayed", isElementDisplayed(alertPasswordSignUp));
        Assert.assertEquals("Alert contains another text than requested", text, alertPasswordSignUp.getText());
        return this;
    }

    @Step
    public LoginPage checkErrorsMessagesOnRegistrationForm(String expectedErrors) {
        String[] expectedErrorArray = expectedErrors.split(";");
        webDriverWaitLow
                .withMessage("Number of messages should be " + expectedErrorArray.length)
                .until(ExpectedConditions.numberOfElementsToBe
                        (By.xpath(listOfErrorsLocator), expectedErrorArray.length));
        Util.waitABit(1);
        Assert.assertEquals(expectedErrorArray.length, listOfErrors.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorArray.length; i++) {
            softAssertions.assertThat(expectedErrorArray[i]).isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();
        return this;
    }

    @Step
    public HomePage loginWithValidCredWithoutOpenPage() {
        enterUsernameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoPasswordInput(TestData.VALID_PASSWORD);
        clickOnSignInButton();
        return new HomePage(webDriver);
    }

    @Step
    public HomePage loginWithDataFromDB() throws SQLException, ClassNotFoundException {
        DB_Util_seleniumUsers db_util_seleniumUsers = new DB_Util_seleniumUsers();
        enterUsernameIntoLoginInput("newqaauto");
        enterPasswordIntoPasswordInput(db_util_seleniumUsers.getPassForLogin("newqaauto"));
        clickOnSignInButton();
        return new HomePage(webDriver);
    }

    public LoginPage checkAlertMessageText(String message) {
        Assert.assertTrue("Alert does not displayed", alertInvalidLoginOrPassword.isDisplayed());
        Assert.assertEquals("Alert has different text", message, alertInvalidLoginOrPassword.getText());
        return this;
    }
}
