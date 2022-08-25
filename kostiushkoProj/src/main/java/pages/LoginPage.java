package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends  ParentPage {
    @FindBy (xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;

    @FindBy (xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordNameHeader;

    @FindBy (xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

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
//    private void prinErrorAndStopTest(Exception e) {
//        logger.error("Can not work with element " + e);
//        Assert.fail("Can not work with element " + e);
//    }
}
