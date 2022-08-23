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
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Cannot work with site");
            Assert.fail("Cannot work with site");
        }
    }

    public void enterUsernameIntoLoginInput(String userName) {
        try {
            WebElement webElement = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
            webElement.clear();
            webElement.sendKeys(userName);
            logger.info("\"" + userName + "\" was entered into the input");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void enterPasswordIntoPasswordInput(String password) {
        try {
            WebElement webElement = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
            webElement.clear();
            webElement.sendKeys(password);
            logger.info("\"" + password + "\" was entered into the input");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void clickOnSignInButton() {
        try {
            webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
            logger.info("Button \"Sign In\" was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public boolean isAlertDisplayed() {
        try {
            WebElement buttonSignIn = webDriver.findElement(By.xpath(".//div[text()='Invalid username / pasword']"));
            logger.info("Alert about wrong username/password is visible");
            return buttonSignIn.isDisplayed();
        } catch (Exception e) {
            logger.error("Alert about wrong username/password is not visible: " + e);
            return false;
        }
    }

    public boolean isButtonSignInDisplayed() {
        try {
            WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
            logger.info("Button \"Sign In\" is visible");
            return buttonSignIn.isDisplayed();
        } catch (Exception e) {
            logger.error("Button \"Sign In\" is not visible: " + e);
            return false;
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element: " + e);
        Assert.fail("Cannot work with element: " + e);
    }
}
