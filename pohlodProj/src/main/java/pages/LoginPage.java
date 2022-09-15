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

    @FindBy(id="username-register")
    private WebElement inputUserNameRegistration;

    @FindBy(id="email-register")
    private WebElement inputUserEmailRegistration;

    @FindBy(id="password-register")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = ".//button[@type=\"submit\"]")
    private WebElement getButtonSignUp;

    @FindBy(xpath = ".//div[@class=\"alert alert-danger small liveValidateMessage liveValidateMessage--visible\"]")
    private List<WebElement> listOfErrors;

    private String validationErrorLocators  = ".//div[@class=\"alert alert-danger small liveValidateMessage liveValidateMessage--visible\"]";

    @FindBy (xpath = ".//div[@class=\"alert alert-danger small liveValidateMessage liveValidateMessage--visible\"]")
    private WebElement error;

    @FindBy (xpath = ".//*[text()=\"Username must be at least 3 characters.\"]")
    private WebElement errorUserName;

    @FindBy (xpath = ".//*[text()=\"You must provide a valid email address.\"]")
    private WebElement errorEmail;

    @FindBy (xpath = ".//*[text()=\"Password must be at least 12 characters.\"]")
    private WebElement errorPassword;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public LoginPage openLoginPage(){
        try{
            webDriver.get(baseUrl);
            logger.info("Login page was opened");
            logger.info(baseUrl);
        } catch (Exception e){
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
        return this;
    }

    public void enterUserNameIntoLoginInput(String userName){
        /*try{
            WebElement webElement = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']")); // deleted
            inputUserNameHeader.clear();
            inputUserNameHeader.sendKeys(userName);
            logger.info(userName + " was entered into input");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }*/
        enterTextIntoElement(inputUserNameHeader, userName);
    }

    public void enterPasswordIntoInputPassword(String password){
        enterTextIntoElement(inputPasswordHeader, password);
    }

    public void clickOnButtonLogIn(){
        /*try{
            webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click(); // deleted
            buttonSignIn.click();
            logger.info("Button SignIn was clicked");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }*/
        clickOnElement(buttonSignIn);
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        loginWithValidCredWithoutOpenPage();
        return new HomePage(webDriver);
    }

    public LoginPage enterUserNameIntoTheRegisterForm (String username){
        enterTextIntoElement(inputUserNameRegistration, username);
        return this;
    }

    public LoginPage enterEmailIntoTheRegisterForm (String email){
        enterTextIntoElement(inputUserEmailRegistration, email);
        return this;
    }

    public LoginPage enterPasswordIntoTheRegisterForm (String password){
        enterTextIntoElement(inputPasswordRegistration, password);
        return this;
    }

    public LoginPage clickOnTheSignUpButton (){
        clickOnElement(getButtonSignUp);
        return this;
    }





    public LoginPage checkNumberOfValidationErrorMessages(){
        List <WebElement> errorsList = webDriver.findElements(By.xpath(String.format(validationErrorLocators)));
        Assert.assertEquals("Number of validation errors: ",3, errorsList.size());
        logger.info(errorsList.size() + " validation errors found");
        return this;
    }

    private List<String> listOfErrors(){
        List<String> errorList = new ArrayList<String>();
        errorList.add(errorUserName.getText());
        errorList.add(errorEmail.getText());
        errorList.add(errorPassword.getText());
        return errorList;
    }
    public LoginPage checkTextOnError(String text) {
        if (listOfErrors().contains(text)){
        logger.info("Error is displayed: " + text);}
        else {
            logger.info("Error is NOT displayed " + text);
            Assert.fail("Error is not displayed");
        }
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";");
        webDriverWaitLow
                .withMessage("Number of errors should be: " + expectedErrorsArray.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(validationErrorLocators), expectedErrorsArray.length));
        Util.waitABit(1);
        Assert.assertEquals("Incorrect number of errors", expectedErrorsArray.length, listOfErrors.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listOfErrors){
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();
        return this;
    }


    public HomePage loginWithValidCredWithoutOpenPage() {
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();
    return new HomePage(webDriver);
    }
}
