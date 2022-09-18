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

@FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
private  WebElement alertInvalidUsernamePassword;

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
        } catch (Exception e) {
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
        return this;
    }

    public LoginPage enterUsernameIntoLoginInput(String userName) {
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
        return this;
    }

    public LoginPage enterPasswordIntoPasswordInput(String password) {
        enterTextIntoElement(inputUserPasswordHeader, password);
        return this;
    }

    public LoginPage clickOnButtonLogIn() {
        clickOnElement(buttonSignIn);
        return this;
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
      loginWithValidCredWithoutOpeningPage();
      return new HomePage(webDriver);
    }

    public LoginPage checkLoginIsInvalid() {
        Assert.assertTrue("Invalid username/password alert does not appear", isElementDisplayed(alertInvalidUsernamePassword));
        Assert.assertTrue("Sign In button is not displayed", isElementDisplayed(buttonSignIn));
        return this;
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


//    public HomePage clickOnSignUpButton() {
//        clickOnElement(buttonSignUp);
//        return new HomePage(webDriver);
//    }

    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(";"); //порізали стрінгу з еррор меседжами сплітом на куски, які пішли в масив
        webDriverWait10
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

    public HomePage loginWithValidCredWithoutOpeningPage() {
        enterUsernameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoPasswordInput(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();
        return new HomePage(webDriver);
    }



//    private void printErrorAndStopTest(Exception e) {
//        logger.info("Can not work with element " + e);
//        Assert.fail("Can not work with element " + e);
//    }
}
