package Pages;

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
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
    }

    public void enterUsernameIntoLoginInput(String userName) {
        try {
            WebElement webElement = webDriver.
                    findElement(By.xpath(".//input[@name='username' and " +
                            "@placeholder='Username']"));
            webElement.clear();
            webElement.sendKeys(userName);
            logger.info(userName + "was entered into input");

        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void enterPasswordIntoPasswordInput(String password) {
        try {
            WebElement webElement = webDriver.
                    findElement(By.xpath(".//input[@placeholder='Password']"));
            webElement.clear();
            webElement.sendKeys(password);
            logger.info(password + " was entered into input");

        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void clickOnButtonLogIn() {
        try {
            webDriver.
                    findElement(By.xpath(".//button[text()='Sign In']")).click();
            logger.info("Sign in button was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void alertInvalidPassword(){
        try{
            WebElement webElement = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']"));
            logger.info("Alert Invalid Password appears");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    private void printErrorAndStopTest(Exception e) {
        logger.info("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }
}
