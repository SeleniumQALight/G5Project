package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordHeader;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try{
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
    }

    public void enterUserNameIntoLoginInput(String userName){
/*        try{
        //    WebElement webElement = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        //    webElement.clear();
        //    webElement.sendKeys(userName)
            inputUserNameHeader.clear();
            inputUserNameHeader.sendKeys(userName);
            logger.info(userName + " was entered into input");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }*/
        enterTextIntoElement(inputUserNameHeader, userName);
    }

    public void enterPasswordIntoInputPassword(String password){
       /* try{
        //    WebElement webElement = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        //    webElement.clear();
        //    webElement.sendKeys(password);
            inputPasswordHeader.clear();
            inputPasswordHeader.sendKeys(password);
            logger.info(password + " was entered into input");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }*/
        enterTextIntoElement(inputPasswordHeader, password);
    }

    public void clickOnButtonLogIn(){
    /*    try{
        //    webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
            buttonSignIn.click();
            logger.info("Button Sign In was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }*/
        clickOnElement(buttonSignIn);
    }

/*    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }*/

    public boolean areButtonSignInAndAlertDisplayed(){
        try{
            WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
            WebElement alert = webDriver.findElement(By.xpath(".//div[text()='Invalid username / pasword']"));
            return buttonSignIn.isDisplayed() & alert.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogIn();
        return new HomePage(webDriver);
    }
}
