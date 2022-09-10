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
            logger.info("Login page opened");
        } catch (Exception e) {
            logger.info("Can not work with site");
            Assert.fail("Can not work with site");
        }
    }

    public void enterUserNameIntoLoginInput(String userName) {
        try {
            WebElement webElement = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
            webElement.clear();
            webElement.sendKeys(userName);
            logger.info(userName + " entered in input");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void enterPasswordIntoLoginInput(String password) {
        try {
            WebElement webElement = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
            webElement.clear();
            webElement.sendKeys(password);
            logger.info(password + " entered in input");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void clickOnButtonLogin() {
        try {
            webDriver.findElement(By.xpath(".//button[text() ='Sign In']")).click();

        } catch (Exception e) {
            logger.info("Button Sigh in clicked");
        }
    }

    public void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

}
