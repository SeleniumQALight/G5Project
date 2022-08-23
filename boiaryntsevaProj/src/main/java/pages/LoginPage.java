package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage {

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login Page is opened");
        } catch (Exception e) {
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
    }

    public void enterUserNameIntoLoginInput(String username) {
        try {
            WebElement webElement = webDriver.
                    findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
            webElement.clear();
            webElement.sendKeys(username);
            logger.info(username + "was entered into input");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void enterPasswordIntoPasswordInput(String password) {
        try {
            WebElement webElement = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
            webElement.clear();
            webElement.sendKeys(password);
            logger.info("Password was entered");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void clickOnButtonLogin() {
        try {
            webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
            logger.info("Sign in button was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    public boolean isInvalidLoginMsgVisible(){
        try{
            WebElement invalidLoginMessage = webDriver.findElement(By.xpath(".//div[contains(text(), 'Invalid')]"));
            return invalidLoginMessage.isDisplayed();
        } catch (Exception e){
            return false;
        }
    }
}
