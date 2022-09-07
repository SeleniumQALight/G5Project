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

    @FindBy(id = "username-register")
    private WebElement inputLoginRegistration;
    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;
    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    private String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
        return this;
    }

    public void enterUserNameIntoLoginInput(String userName) {
//        try {
////            WebElement webElement = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
//            inputUserNameHeader.clear();
//            inputUserNameHeader.sendKeys(userName);
//            logger.info(userName + " was entered into input");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputUserNameHeader, userName);
    }

    public void enterPasswordIntoInputPassword(String password) {
//        try {
////            WebElement webElement = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
//            inputPasswordHeader.clear();
//            inputPasswordHeader.sendKeys(password);
//            logger.info(password + " was entered into input");
//
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

    public LoginPage enterUserNameIntoRegistrationForm(String userName) {
        enterTextIntoElement(inputLoginRegistration, userName);
        return this;
    }

    public LoginPage enterEmailIntoRegistrationForm(String email) {
        enterTextIntoElement(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterPasswordIntoRegistrationForm(String password) {
        enterTextIntoElement(inputPasswordRegistration, password);
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedErrors) {
        // test;test1 -> array[0] = test, array[1] = test1
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWait10
                .withMessage("Number of messages should be " + expectedErrorsArray.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsLocator), expectedErrorsArray.length));
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
//    private void printErrorAndStopTest(Exception e) {
//        logger.error("Can not work with element" + e);
//        Assert.fail("Can not work with element" + e);
//    }
}
