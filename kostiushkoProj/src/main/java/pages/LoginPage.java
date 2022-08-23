package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends  ParentPage {

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
        try {
            WebElement webElement = webDriver.findElement(By.xpath(".//input[@name='username' and " +
                    "@placeholder='Username']"));
            webElement.clear();
            webElement.sendKeys(userName);
            logger.info("User name was entered into input");
        } catch (Exception e){
            prinErrorAndStopTest(e);
        }

    }

    public void enterPasswordIntoInputPassword (String password){
        try {
            WebElement webElement = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
            webElement.clear();
            webElement.sendKeys(password);
            logger.info(password +" wos entered into input");
        }catch (Exception e){
            prinErrorAndStopTest(e);
        }
    }

    public void clickOnButtonLogIn(){
        try {webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
            logger.info("button Sin In was clicked");

        } catch (Exception e){
            prinErrorAndStopTest(e);
        }
    }
    private void prinErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }
}
