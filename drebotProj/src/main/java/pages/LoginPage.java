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

    @FindBy(xpath = ".//input[@name='username' and @class='form-control form-control-sm input-dark']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = ".//input[@name='password' and @class='form-control form-control-sm input-dark']")
    private WebElement inputPasswordHeader;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement messageInvalidUserPassword;

    @FindBy(id = "username-register")
    private WebElement inputUserNameRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = ".//button[@class='py-3 mt-4 btn btn-lg btn-success btn-block']")
    private WebElement buttonSignUpForOurApp;

    private String alertTextLocator = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";// and contains(text(),'%s')]";//".//div[text()='%s']";
    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' ]")
    private List<WebElement> listOfError;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception ex) {
            logger.error("Can't work with site");
            Assert.fail("Can't work with site");
        }
        return this;
    }

    public void enterUserNameIntoLoginInput(String userName) {
//        try {
//            //WebElement webElement = webDriver.findElement(By.xpath(".//input[@name='username' and @class='form-control form-control-sm input-dark']"));
//            inputUserNameHeader.clear();
//            inputUserNameHeader.sendKeys(userName);
//            logger.info((userName + " was entered into input"));
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputUserNameHeader, userName);
    }

    public void enterPasswordIntoPasswordInput(String password) {
//        try {
////          WebElement webElement = webDriver.findElement(By.xpath(".//input[@name='password' and @class='form-control form-control-sm input-dark']"));
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
//            //webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']")).click();
//            buttonSignIn.click();
//            logger.info("Button Sign in was clicked");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSignIn);
    }

    public boolean isMessageInvalidUserPassword() {
//        try {
//            WebElement messageInvalidUserPassword = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']"));
//            return messageInvalidUserPassword.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
        return isElementDisplayed(messageInvalidUserPassword);
    }

    public HomePage loginWithValidCredential() {
        openLoginPage();
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoPasswordInput(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();

        return new HomePage(webDriver);
    }

    public LoginPage enterUserNameIntoRegistration(String userName) {
        enterTextIntoElement(inputUserNameRegistration, userName);
        return this;
    }

    public LoginPage enterEmailIntoRegistration(String email) {
        enterTextIntoElement(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterPasswordIntoRegistration(String password) {
        enterTextIntoElement(inputPasswordRegistration, password);
        return this;
    }

    public void clickOnSignUpForOurApp() {
        waitChatToBeHide();
        clickOnElement(buttonSignUpForOurApp);
    }

    public void checkAlertText(String[] text) {
        for (int i = 0; i < text.length; i++) {
            List<WebElement> listAlarmMessage = getAlertsListInRegistration(text[i]);
            if (!listAlarmMessage.isEmpty()) {
                Assert.assertTrue("element '" + text[i] + "' is not displayed", isElementDisplayed(webDriver.findElement(By.xpath(String.format(alertTextLocator, text[i])))));
                logger.info("alert message '" + webDriver.findElement(By.xpath(String.format(alertTextLocator, text[i]))).getText() + "' is displayed");
            } else {
                logger.info("expected alert '" + text[i] + "' is not displayed");
                Assert.fail("can't find element '" + text[i] + "' ");
            }
        }
    }

    public void checkCountAlertMessage(String[] text) {
        List<WebElement> listAlarmMessage = webDriver.findElements(By.xpath(String.format(alertTextLocator, "")));
        Assert.assertEquals("'" + text.length + "' alert are not displayed", text.length, listAlarmMessage.size());
        logger.info("'" + listAlarmMessage.size() + "' alert are displayed");
    }

    private List<WebElement> getAlertsListInRegistration(String message) {
        return webDriver.findElements(By.xpath(String.format(alertTextLocator, message)));
    }

    public LoginPage checkErrorMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage("Number of messages ").until(ExpectedConditions.numberOfElementsToBe(By.xpath(alertTextLocator), expectedErrorsArray.length));

        Util.waitABit(1);
        Assert.assertEquals(expectedErrorsArray.length, listOfError.size());

        ArrayList<String> actualTextFromErrors=new ArrayList<>();
        for (WebElement element: listOfError) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions=new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();

        return this;
    }
}
