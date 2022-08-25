package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @class='form-control form-control-sm input-dark']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = ".//input[@name='password' and @class='form-control form-control-sm input-dark']")
    private WebElement inputPasswordHeader;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSignIn;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception ex) {
            logger.error("Can't work with site");
            Assert.fail("Can't work with site");
        }
    }

    public void enterUserNameIntoLoginInput(String userName) {
//        try {
//            //WebElement webElement = webDriver.findElement(By.xpath(".//input[@name='username' and @class='form-control form-control-sm input-dark']"));
//            inputUserNameHeader.clear();
//            inputUserNameHeader.sendKeys(userName);
//            logger.info((userName + " was entered into input"));
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputUserNameHeader, userName);
    }

    public void enterPasswordIntoPasswordInput(String password) {
//        try {
////          WebElement webElement = webDriver.findElement(By.xpath(".//input[@name='password' and @class='form-control form-control-sm input-dark']"));
//            inputPasswordHeader.clear();
//            inputPasswordHeader.sendKeys(password);
//            logger.info(password + " was entered into input");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputPasswordHeader, password);
    }

    public void clickOnButtonLogIn() {
//        try {
//            //webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']")).click();
//            buttonSignIn.click();
//            logger.info("Button Sign in was clicked");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonSignIn);
    }

//    private void printErrorAndStopTest(Exception e) {
//        logger.error("Can't work with element " + e);
//        Assert.fail("Can't work with element " + e);
//    }

    public boolean isMessageInvalidUserPassword() {
        try {
            WebElement messageInvalidUserPassword = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']"));
            return messageInvalidUserPassword.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
