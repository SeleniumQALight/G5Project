package pages;


import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    @FindBy(xpath = ".//input [@id=\"username-register\"]")
    private WebElement inputRegistationUserName;
    @FindBy (xpath = ".//input [@id=\"email-register\"]")
    private WebElement inputRegistationEmail;
    @FindBy (xpath = ".//input [@id=\"password-register\"]")
    private WebElement inputRegistationPassword;

    private String alerValidateMessage = ".//div[@class=\"alert alert-danger small liveValidateMessage liveValidateMessage--visible\"]";


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e){
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
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
        enterUserNameIntoLogininInput(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();
        return new HomePage(webDriver);
    }

    public void isTextNoLogginDisplayed(){
        //TODo check url
        Assert.assertTrue("Text 'No Loggin' not displayed", isElementDisplayed(textNoLoggin));
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

    public void getMessageListWithXpath (String text) {
        List<WebElement> listMessage = webDriver.findElements(By.xpath(alerValidateMessage));
        for (int i = 0; i < listMessage.size(); i++) {
            if (listMessage.get(i).getText().equals(text)) {
               logger.info("Message '"+text+"' wos displayed");
                break;
            }
        }
    }
    public int countingTheNumberOfElements (){

        List<WebElement> listMessage = webDriver.findElements(By.xpath(alerValidateMessage));
        int number = listMessage.size();
        return number;
    }

    public void getTextFromAllertMessage (WebElement webElement){
        getTextFromElement(webElement);
    }


//    private void prinErrorAndStopTest(Exception e) {
//        logger.error("Can not work with element " + e);
//        Assert.fail("Can not work with element " + e);
//    }
}
