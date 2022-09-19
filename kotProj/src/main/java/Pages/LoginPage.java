package Pages;


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
@FindBy(xpath=".//input[@placeholder='Password']")
private WebElement inputUserPasswordHeader;
@FindBy(xpath = ".//button[text()='Sign In']")
private WebElement buttonSignIn;


@FindBy(id = "username-register")
private WebElement inputUserNameReg;
@FindBy(id = "email-register")
private WebElement inputEmailReg;
@FindBy(id = "password-register")
private WebElement inputPasswordReg;

private String notValidCredentialsMessage = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']" ;

@FindBy(xpath = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']" )
private List<WebElement> listOfErrors;

public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelatedUrl() {
        return "/";
    }

//@FindBy(xpath = ".//button[@type='submit']")
//private WebElement buttonSignUp;


    public LoginPage openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened");
            logger.info(baseUrl);
        } catch (Exception e) {
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
        return this;
    }

    public void enterUsernameIntoLoginInput(String userName) {
//        try {
////            WebElement webElement = webDriver.
////                    findElement(By.xpath(".//input[@name='username' and " +
////                            "@placeholder='Username']"));
//            inputUserNameHeader.clear();
//            inputUserNameHeader.sendKeys(userName);
//            logger.info(userName + "was entered into input");
//
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputUserNameHeader, userName);
    }

    public void enterPasswordIntoPasswordInput(String password) {
        enterTextIntoElement(inputUserPasswordHeader, password);
    }

    public void clickOnButtonLogIn() {
        clickOnElement(buttonSignIn);
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
      loginWithValidCredWithoutOpeningPage();
      return new HomePage(webDriver);
    }


    public LoginPage enterUserNameIntoIntoRegInput(String userName){
        enterTextIntoElement(inputUserNameReg, userName);
        return this;
    }


    public LoginPage enterEmailIntoRegInput (String email){
        enterTextIntoElement(inputEmailReg, email);
        return this;
    }

    public LoginPage enterPasswordIntoRegInput (String password) {
        enterTextIntoElement (inputPasswordReg, password);
        return this;
    }

    public LoginPage checkNumberOfErrorMessages(int errorMessages){
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath
                        (".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
                , errorMessages));
        Assert.assertEquals("Incorrect number of alerts are displayed", errorMessages, listOfErrors.size());
        logger.info("Number of error messages on registration form is " + listOfErrors.size());
        return this;
    }


    public LoginPage checkTextInErrorMessages() {
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(".//*[contains(@class, 'liveValidateMessage--visible')]"),
                3));
        Assert.assertEquals("Username must be at least 3 characters.", listOfErrors.get(0).getText());
        logger.info("Username error message: " + listOfErrors.get(0).getText());
        Assert.assertEquals("You must provide a valid email address.", listOfErrors.get(1).getText());
        logger.info("Email error message: " + listOfErrors.get(1).getText());
        Assert.assertEquals("Password must be at least 12 characters.", listOfErrors.get(2).getText());
        logger.info("Password error message: " + listOfErrors.get(2).getText());
        return this;
    }

//    public HomePage clickOnSignUpButton() {
//        clickOnElement(buttonSignUp);
//        return new HomePage(webDriver);
//    }

    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";"); //порізали стрінгу з еррор меседжами сплітом на куски, які пішли в масив
        webDriverWaitLow
                .withMessage("Number of Error Messages should be "+ expectedErrorsArray.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(notValidCredentialsMessage), expectedErrorsArray.length));
        Util.waitABit(1);
        Assert.assertEquals(expectedErrorsArray.length,listOfErrors.size());
        ArrayList<String> actualTextFromErrors =new ArrayList<>();
        for (WebElement element:listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions=new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }


        softAssertions.assertAll();
        return this;
    }

//    private void printErrorAndStopTest(Exception e) {
//        logger.info("Can not work with element " + e);
//        Assert.fail("Can not work with element " + e);
//    }
}
