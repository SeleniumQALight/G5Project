package pages;



import io.qameta.allure.Step;
import libs.DB_Util_seleniumUsers;
import libs.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class LoginPage extends  ParentPage {

    @FindBy (xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;

    @FindBy (xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordNameHeader;

    @FindBy (xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy (xpath = ".//* [@class='alert alert-danger text-center']")
    private WebElement textNoLoggin;
    @FindBy (id = "username-register")
    private WebElement inputLoginRegistration;
    @FindBy (id = "email-register")
    private WebElement inputEmailRegistration;
    @FindBy (id = "password-register" )
    private WebElement inputPasswordRegistration;
    private  String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy (xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrors;
    @FindBy(xpath = ".//input [@id='username-register']")
    private WebElement inputRegistationUserName;
    @FindBy (xpath = ".//input [@id='email-register']")
    private WebElement inputRegistationEmail;
    @FindBy (xpath = ".//input [@id='password-register']")
    private WebElement inputRegistationPassword;

    private String alerValidateMessage = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy (xpath = ".//*[contains(@class,'alert alert-danger text-center')]")
    private WebElement alertInCenter;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelateUrl() {
        return "/";
    }

    public LoginPage openLoginPage(){
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened");
            logger.info(baseUrl);
        } catch (Exception e){
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
        return this;
    }

    public void enterUserNameIntoLogininInput (String userName){
//        try {
//            inputUserNameHeader.clear();
//            inputUserNameHeader.sendKeys(userName);
//            logger.info("User name was entered into input");
//        } catch (Exception e){
//            prinErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputUserNameHeader, userName);
    }

    public void enterPasswordIntoInputPassword (String password){
//        try {
//            inputPasswordNameHeader.clear();
//            inputPasswordNameHeader.sendKeys(password);
//            logger.info(password +" wos entered into input");
//        }catch (Exception e){
//            prinErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputPasswordNameHeader, password);
    }



    public void clickOnButtonLogIn(){
//        try {buttonSignIn.click();
//            logger.info("button Sin In was clicked");
//
//        } catch (Exception e){
//            prinErrorAndStopTest(e);
//        }
        clicOnElement(buttonSignIn);
    }

    public HomePage loginWithWalidCred() {
        openLoginPage();
        loginWithWalidCredWithoutOpenPage();
        return new HomePage(webDriver);
    }

    public void isTextNoLogginDisplayed(){
        //TODo check url
        Assert.assertTrue("Text 'No Loggin' not displayed", isElementDisplayed(textNoLoggin));
    }

    public LoginPage enterUserNameIntoRegistrationForm(String shortUserName) {
    enterTextIntoElement(inputLoginRegistration, shortUserName);
        return this;
    }

    public LoginPage enterEmailIntoRegistrationForm(String email) {
        enterTextIntoElement(inputEmailRegistration, email);
        return  this;
    }

    public LoginPage enterPasseordIntoRegistrationForm(String password) {
        enterTextIntoElement(inputPasswordRegistration, password);
        return this;
    }

    public LoginPage chekErrorsMessages(String expectedErrors) {
        // test;test1 -> array[0]=test, array[1] = test1
        String [] expectedErrorsArray = expectedErrors.split(";");
        webDriverWaitLow
                .withMessage("Number of message should be "+expectedErrorsArray.length)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsLocator), expectedErrorsArray.length));
        Util.waitABit(1);
        Assert.assertEquals(expectedErrorsArray.length, listOfErrors.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listOfErrors){
            actualTextFromErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length ; i++) {
   softAssertions.assertThat(expectedErrorsArray[i]).isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();
        return this;
    }

    public HomePage loginWithWalidCredWithoutOpenPage() {
        enterUserNameIntoLogininInput(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();
        return new HomePage (webDriver);
    }



    public void enterUserNameIntoRegistrationUserNameField (String userName){
        enterTextIntoElement(inputRegistationUserName, userName);
    }
    public void enterEmailIntoRegistrationEmailField(String email) {
        enterTextIntoElement(inputRegistationEmail, email);
    }

    public void enterPasswordIntoRegistrationPasswordField(String password) {
    enterTextIntoElement(inputRegistationPassword, password );
    }

    public void checkMessageEquals(String text, int numberOfElements) {
        boolean isMessageFound = false;
        List<WebElement> list = createListWithElements(alerValidateMessage, numberOfElements);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().equals(text)) {
               logger.info("Message '"+text+"' wos displayed");
                isMessageFound = true;
                break;
            }
        }
        Assert.assertTrue("Message not found", isMessageFound );
    }





public int countingTheNumberOfMessage (){
    int   numberElements =  countingTheNumberOfElements(alerValidateMessage);
return numberElements;
    }

    public HomePage loginWithWalidCreduseBD() throws ClassNotFoundException, SQLException {
            DB_Util_seleniumUsers db_util_seleniumUsers = new DB_Util_seleniumUsers();
            enterUserNameIntoLogininInput("newqaauto");
            enterPasswordIntoInputPassword(db_util_seleniumUsers.getPassword("newqaauto"));
            clickOnButtonLogIn();
            return new HomePage(webDriver);
        }

    public LoginPage checkMessageText(String message) {
        Assert.assertEquals("Message in Alert ", message, alertInCenter.getText() );
        return this;
    }
}
//    private void prinErrorAndStopTest(Exception e) {
//        logger.error("Can not work with element " + e);
//        Assert.fail("Can not work with element " + e);
//    }

