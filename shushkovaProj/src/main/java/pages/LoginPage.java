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
import pages.elements.HeaderElement;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    private HeaderElement headerElement= new HeaderElement(webDriver);


    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputUserPasswordHeader;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSingIn;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSingOut;

    @FindBy(id="username-register")
    private WebElement inputLoginRegistration;

    @FindBy(id="email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id="password-register")
    private WebElement inputPasswordRegistration;

    private String listOfErrorsLocator =".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath=".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }
    @Step
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
    @Step
    public LoginPage enterUserNameIntoLoginInput(String userName) {

        enterTextIntoElement(inputUserNameHeader,userName);
        return new LoginPage(webDriver);
    }
    @Step
    public LoginPage enterPasswordIntoLoginInput(String password) {

        enterTextIntoElement(inputUserPasswordHeader,password);
        return new LoginPage(webDriver);
    }
    @Step
    public void clickOnButtonSignIn() {
       clickOnElement(buttonSingIn);
    }

    public boolean isMessageInvalidCredsDisplayed(){
        try {
            WebElement messageInvalidCreds = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center' and contains(text(),'Invalid username / pasword')]"));
            return messageInvalidCreds.isDisplayed();

        }catch (Exception e){
            return true;
        }
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
    @Step
    public LoginPage enterUsernameIntoRegistrationForm(String userName) {
        enterTextIntoElement(inputLoginRegistration,userName);
        return this;
    }
    @Step
    public LoginPage enterEmailIntoRegistrationForm(String email) {
        enterTextIntoElement(inputEmailRegistration,email);
        return this;
    }
    @Step
    public LoginPage enterPasswordIntoRegistrationForm(String password) {
        enterTextIntoElement(inputPasswordRegistration,password);
        return this;
    }
    @Step
    public LoginPage checkErrorsMessage(String expectedErrors) {
        String[] expectedErrorsArray=expectedErrors.split(";");
        webDriverWaitLow
                .withMessage("Number of Messages")
                .until(ExpectedConditions.numberOfElementsToBe(
                        By.xpath(listOfErrorsLocator),expectedErrorsArray.length));
        Util.waitABit(1);
        Assert.assertEquals(expectedErrorsArray.length,listOfErrors.size());
        ArrayList<String> actualTextFromErrors=new ArrayList<>();
        for (WebElement element: listOfErrors)
             {
                 actualTextFromErrors.add(element.getText());
        }
        /** ???????????????? ?????????????????? ???????? ???? ???? ??????????????**/
        SoftAssertions softAssertions=new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);

        }
        /**???????? ???????? ???? ????????????????, ?????????? ???????????????? ???????? ?? ???????????????? ???????? ????????????????**/
        softAssertions.assertAll();

        return this;
    }

    @Step
    public HomePage loginWithValidCredWithoutOpenPage() {
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoLoginInput(TestData.VALID_PASSWORD);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
    public boolean isButtonSingInDisplayed(){
        return isElementDisplayed(buttonSingIn);
    }
}



