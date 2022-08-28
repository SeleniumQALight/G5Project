package pages;

import libs.TestData;
import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage { // Alt+Insert↓ - create constructor
    // Описані елементи, які ми робимо через анотацію @FindBy та ініціалізуємо через PageFactory.initElements який ми винесли в цей батьківський клас
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordHeader;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    } //constructor

    // Дії над ціми (@FindBy) елементами

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
//        try {
////            WebElement webElement = webDriver.findElement(By.xpath(".//input[@name='username' and " +
////                    "@placeholder='Username']"));
//            inputUserNameHeader.clear();
//            inputUserNameHeader.sendKeys(userName);
//            logger.info(userName + " was entered into input");
//
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputUserNameHeader, userName); // ↑ замінили на виклик метода
    }


    public void enterPasswordIntoInputPassword(String password) {
//        try {
////            WebElement webElement = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
//            inputPasswordHeader.clear();
//            inputPasswordHeader.sendKeys(password);
//            logger.info(password + " was entered into input");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputPasswordHeader, password);
    }

    public void clickOnButtonLogIn() {
//        try {
////            webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
//            buttonSignIn.click();
//            logger.info("Button Sign In was clicked");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSignIn);
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();
        return new HomePage(webDriver);
    }

//    private void printErrorAndStopTest(Exception e) {
//        logger.error("Can not work with element " + e);
//        Assert.fail("Can not work with element " + e);
//    }
}
