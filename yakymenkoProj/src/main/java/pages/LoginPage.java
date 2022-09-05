package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    } //constructor

    // Дії над цими (@FindBy) елементами:

    /**
     * Метод, який буде відкривати LoginPage
     */
    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not work with site"); // повідомлення в наш лог
            Assert.fail("Can not work with site"); // якщо тест дійшов до цього повідомлення то маркає червоним і виходить з цього блоку
        }
    }

    public void enterUserNameIntoLoginInput(String userName) {
        enterTextIntoElement(inputUserNameHeader, userName); // ↑ замінили на виклик метода
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextIntoElement(inputPasswordHeader, password);
    }

    public void clickOnButtonLogIn() {
        clickOnElement(buttonSignIn);
    }

    public boolean isAlertDisplayed() {
        return isElementDisplayed(alertInvalidUsernamePassword);
    }

    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonSignIn);
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);// звертаємось до TestData і кажемо - дістань VALID_LOGIN
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();
        return new HomePage(webDriver);
    }

    public void enterUsernameIntoUsernameRegisterInput(String userName) {
        enterTextIntoElement(inputUsernameRegister, userName);
    }

    public void enterEmailIntoEmailRegisterInput(String email) {
        enterTextIntoElement(inputEmailRegister, email);
    }

    public void enterPasswordIntoPasswordRegisterInput(String password) {
        enterTextIntoElement(inputPasswordRegister, password);
    }

    public void checkValidationAlertNumber() {
//        Util.waitABit(3);
        webDriverWait10.withMessage("Alerts are not shown")
                .until(ExpectedConditions
                        .visibilityOfAllElements(visibleAlert));
        Assert.assertEquals("Incorrect number of alerts in Register form", 3, visibleAlert.size());
        logger.info("Number of alerts in Register form: " + visibleAlert.size());
    }

    public void checkValidationAlertText() {
//        Util.waitABit(3);
        webDriverWait10.until(ExpectedConditions.visibilityOfAllElements(visibleAlert));
        Assert.assertEquals("Username must be at least 3 characters.", visibleAlert.get(0).getText());
        logger.info("Visible alert in UsernameRegister: " + visibleAlert.get(0).getText());
        Assert.assertEquals("You must provide a valid email address.", visibleAlert.get(1).getText());
        logger.info("Visible alert in EmailRegister: " + visibleAlert.get(1).getText());
        Assert.assertEquals("Password must be at least 12 characters.", visibleAlert.get(2).getText());
        logger.info("Visible alert in PasswordRegister: " + visibleAlert.get(2).getText());
    }
}
