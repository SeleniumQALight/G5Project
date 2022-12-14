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

public class LoginPage extends ParentPage { // Alt+Insert↓ - create constructor
    // Описані елементи, які ми робимо через анотацію @FindBy та ініціалізуємо через PageFactory.initElements який ми винесли в цей батьківський клас:
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordHeader;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement alertInvalidUsernamePassword;
    @FindBy(id = "username-register")
    private WebElement inputUsernameRegister;
    @FindBy(id = "email-register")
    private WebElement inputEmailRegister;
    @FindBy(id = "password-register")
    private WebElement inputPasswordRegister;
    @FindBy(xpath = ".//div[contains(@class, 'alert')]")
    private List<WebElement> visibleAlert;
    @FindBy(id = "username-register")
    private WebElement inputLoginRegistration;
    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;
    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    private String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;
    @FindBy(xpath = ".//*[contains(@class,'alert alert-danger text-center')]")
    private WebElement alertInCenter;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    } //constructor

    @Override
    String getRelativeUrl() {
        return "/";
    }

    // Дії над цими (@FindBy) елементами:

    /**
     * Метод, який буде відкривати LoginPage
     */
    @Step
    public LoginPage openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened");
            logger.info(baseUrl);
        } catch (Exception e) {
            logger.error("Can not work with site"); // повідомлення в наш лог
            Assert.fail("Can not work with site"); // якщо тест дійшов до цього повідомлення то маркає червоним і виходить з цього блоку
        }
        return this;
    }

    @Step
    public LoginPage enterUserNameIntoLoginInput(String userName) {
        enterTextIntoElement(inputUserNameHeader, userName); // ↑ замінили на виклик метода
        return this;
    }

    @Step
    public LoginPage enterPasswordIntoPasswordInput(String password) {
        enterTextIntoElement(inputPasswordHeader, password);
        return this;
    }

    @Step
    public LoginPage clickOnButtonLogIn() {
        clickOnElement(buttonSignIn);
        return this;
    }

    @Step
    public boolean isAlertDisplayed() {
        return isElementDisplayed(alertInvalidUsernamePassword);
    }

    @Step
    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonSignIn);
    }

    @Step
    public HomePage loginWithValidCred() {
        openLoginPage();
        loginWithValidCredWithOutOpenPage();
        return new HomePage(webDriver);
    }

    @Step
    public void enterUsernameIntoUsernameRegisterInput(String userName) {
        enterTextIntoElement(inputUsernameRegister, userName);
    }

    @Step
    public void enterEmailIntoEmailRegisterInput(String email) {
        enterTextIntoElement(inputEmailRegister, email);
    }

    @Step
    public void enterPasswordIntoPasswordRegisterInput(String password) {
        enterTextIntoElement(inputPasswordRegister, password);
    }

    @Step
    public void checkValidationAlertNumber() {
//        Util.waitABit(3);
        webDriverWaitLow.withMessage("Alerts are not shown")
                .until(ExpectedConditions
                        .numberOfElementsToBe(By.xpath(listOfErrorsLocator), 3));
        Assert.assertEquals("Incorrect number of alerts in Register form", 3, visibleAlert.size());
        logger.info("Number of alerts in Register form: " + visibleAlert.size());
    }

    @Step
    public void checkValidationAlertText() {
//        Util.waitABit(3);
        webDriverWaitLow.until(ExpectedConditions.visibilityOfAllElements(visibleAlert));
        Assert.assertEquals("Username must be at least 3 characters.", visibleAlert.get(0).getText());
        logger.info("Visible alert in UsernameRegister: " + visibleAlert.get(0).getText());
        Assert.assertEquals("You must provide a valid email address.", visibleAlert.get(1).getText());
        logger.info("Visible alert in EmailRegister: " + visibleAlert.get(1).getText());
        Assert.assertEquals("Password must be at least 12 characters.", visibleAlert.get(2).getText());
        logger.info("Visible alert in PasswordRegister: " + visibleAlert.get(2).getText());
    }

    @Step
    public LoginPage enterUsernameIntoRegistrationForm(String userName) {
        enterTextIntoElement(inputLoginRegistration, userName);
        return this;
    }

    @Step
    public LoginPage enterEmailIntoRegistrationForm(String email) {
        enterTextIntoElement(inputEmailRegistration, email);
        return this;
    }

    @Step
    public LoginPage enterPasswordIntoRegistrationForm(String password) {
        enterTextIntoElement(inputPasswordRegistration, password);
        return this;
    }

    @Step
    public LoginPage checkErrorsMessagesOnRegistrationForm(String expectedErrors) {
        // test;test1 → array[0] = test , array[1] = test1
        String[] expectedErrorsArray = expectedErrors.split(";");// пройдеться і розіб'є на частини у масив
        webDriverWaitLow
                .withMessage("Number of messages should be " + expectedErrorsArray.length)
                .until(ExpectedConditions.numberOfElementsToBe
                        (By.xpath(listOfErrorsLocator), expectedErrorsArray.length));
        Util.waitABit(1);
        Assert.assertEquals(expectedErrorsArray.length, listOfErrors.size());
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();
        return this;
    }

    @Step
    public HomePage loginWithValidCredWithOutOpenPage() {
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoPasswordInput(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage checkInvalidLoginMessage() {
        Assert.assertTrue("Button 'Sign In' and alert massage are not visible", isButtonSignInDisplayed() & isAlertDisplayed());
        return this;
    }

    public LoginPage checkAlertMessageText(String message) {
        Assert.assertEquals("Message in Alert ", message, alertInCenter.getText());
        return this;
    }
}
