package Pages;

import io.qameta.allure.Step;
import libs.Color;
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
    @FindBy(xpath = ".//*[contains(@class,'alert alert-danger text-center')]")
    private WebElement alertInCenter;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public LoginPage openLoginPage() {
        try {
            driver.get(baseUrl);
            log.info("Login page is opened");
            log.info(baseUrl);
            return this;
        } catch (Exception e) {
            log.error("Warning : Login page is not opened");
            Assert.fail("Login page is not opened");
            return this;
        }
    }

    @Step
    public LoginPage enterUserNameIntoLoginInput(String username) {
        enterTextIntoElement(inputUserNameHeader, username);
        return this;
    }

    @Step
    public void enterPasswordIntoInputPassword(String password) {
        enterTextIntoElement(inputPasswordHeader, password);
    }

    @Step
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

    public LoginPage registerNewUserViaTab(String name, String email, String password){
        openLoginPage()
                .fillInUserNameRegister(name);

        usersPressesKeyTabTime(1);
        driver.switchTo().activeElement().sendKeys(email);

        usersPressesKeyTabTime(1);
        driver.switchTo().activeElement().sendKeys(password);

        usersPressesKeyEnterTime(1);
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
            webDriverWaitLow.until(ExpectedConditions
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


    public LoginPage checkMessageAlert(String message){
        Assert.assertEquals("Message in Alert : ", message, alertInCenter.getText());
        return this;
    }

    public LoginPage checkErrorMessages(String expectedError) {
        String[] expectedErrorsArray = expectedError.split(";");
        webDriverWaitLow
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

//    public void checkPasswordHeaderActive(){
//        String attributeName = "placeholder";
//        String expectedAttributeValue = "Password";
////        String attributeName1 = "border-top-color";
////        String expectedAttributeValue2 = "rgb(128, 189, 255)";
//        WebElement element = driver.findElement(By.xpath("//input[@placeholder='Password']"));
//        Assert.assertTrue("Border-color is different",isElementActive(element,attributeName,expectedAttributeValue));
//    }

//    public void checkPasswordRegisterActive(){
//        String cssValue = "border-top-color";
//        String color = "rgb(128, 189, 255)";
//        Assert.assertTrue("",isElementActive(inputUserPasswordRegister,cssValue,color));
//    }

    public LoginPage checkPasswordRegisterActive(){
        String color = "rgb(206, 212, 218)";
        Assert.assertEquals( color, inputUserPasswordRegister.getCssValue("border-color"));
        return this;
    }

    public LoginPage checkPasswordFieldIsActive(){
        String attributeName = "border-top-color";
        Util.waitABit(1);
        Assert.assertEquals(Color.BLUE.toString(),getElementCssValue(inputPasswordHeader,"border-color"));
        return this;
    }
}
