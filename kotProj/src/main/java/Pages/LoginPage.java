package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
@FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
private WebElement inputUserNameHeader;

@FindBy(xpath=".//input[@placeholder='Password']")
private WebElement inputUserPasswordHeader;

@FindBy(xpath = ".//button[text()='Sign In']")
private WebElement buttonSignIn;
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
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

//    private void printErrorAndStopTest(Exception e) {
//        logger.info("Can not work with element " + e);
//        Assert.fail("Can not work with element " + e);
//    }
}
