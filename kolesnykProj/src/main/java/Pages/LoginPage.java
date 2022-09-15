package Pages;

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

import static libs.TestData.*;


public class LoginPage extends ParentPage {

    @FindBy(xpath = "//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = "//input[@name='password' and @placeholder='Password']")
    private WebElement inputPasswordHeader;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSingIn;

    @FindBy(xpath = "//input[@id='username-register']")
    private WebElement inputUserNameRegister;

    @FindBy(id = "email-register")
    private WebElement inputUserEmailRegister;

    @FindBy(id = "password-register")
    private WebElement inputUserPasswordRegister;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonSubmit;


    private String listOfErrorsLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public LoginPage openLoginPage() {
        try {
            driver.get(baseUrl);
            log.info("Login page is opened");
            return this;
        } catch (Exception e) {
            log.error("Warning : Login page is not opened");
            Assert.fail("Login page is not opened");
            return this;
        }
    }

    public LoginPage enterUserNameIntoLoginInput(String username) {
        enterTextIntoElement(inputUserNameHeader, username);
        return this;
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextIntoElement(inputPasswordHeader, password);
    }

    public void clickOnLoginButton() {
        clickOnElement(buttonSingIn);
    }

    public boolean isButtonSignInDisplayed(){
        return isElementDisplayed(buttonSingIn);
    }

    public HomePage loginWithValidCredentials() {
        openLoginPage();
        loginWithValidCredentialsWithoutOpenPage();
        return new HomePage(driver);
    }

    public LoginPage registerNewUserWithCredential(String username, String email, String password){
        openLoginPage()
                .fillInUserNameRegister(username)
                .fillInUserEmailRegister(email)
                .fillInUserPasswordRegister(password);

        return this;
    }

    public LoginPage fillInUserNameRegister(String text){
        enterTextIntoElement(inputUserNameRegister, text);
        return this;
    }

    public LoginPage fillInUserEmailRegister(String email){
        enterTextIntoElement(inputUserEmailRegister, email);
        return this;
    }

    public LoginPage fillInUserPasswordRegister(String password){
        enterTextIntoElement(inputUserPasswordRegister, password);
        return this;
    }

    public LoginPage checkAmountOfAlertDuringRegistration(){
        try {
            webDriverWait10.until(ExpectedConditions
                    .numberOfElementsToBe(By.xpath("//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"),3));
            Assert.assertEquals(3, findAlerts().size());
            log.info("All three Alerts are displayed");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
        return this;
    }


    public LoginPage clickOnButtonSubmit(){
        clickOnElement(buttonSubmit);
        return this;
    }

    public LoginPage checkAlertsText(String alertText){
        try {
            WebElement element = findElementByText(alertText);
            Assert.assertEquals(alertText, element.getText());
            log.info("Alert with text : '"+ alertText + "' found");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
        return this;
    }

    public LoginPage checkErrorMessages(String expectedError) {
        String[] expectedErrorsArray = expectedError.split(";");
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

    protected List<WebElement> findAlerts(){
        List<WebElement> listOfAlert = driver.findElements(By.xpath("//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"));
        return listOfAlert;
    }

    public HomePage loginWithValidCredentialsWithoutOpenPage() {
        enterUserNameIntoLoginInput(VALID_LOGIN);
        enterPasswordIntoInputPassword(VALID_PASSWORD);
        clickOnLoginButton();
        return new HomePage(driver);
    }


//    private void printErrorAndStopTest(Exception e) {
//        log.error("Cannot work with element " + e);
//        Assert.fail("Cannot work with element " + e);
//    }

    public LoginPage fillInPasswordField(String text){
        usersPressesKeyTabTime(1);
        inputPasswordHeader.sendKeys(text);
        return this;
    }

    public void pressEnterToSubmit(){
        usersPressesKeyEnterTime(1);
    }
}
