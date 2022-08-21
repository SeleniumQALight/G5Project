package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openLoginPage() {
        try {
            driver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            log.info("Login page is opened");
        } catch (Exception e) {
            log.error("Warning : Login page is not opened");
            Assert.fail("Login page is not opened");
        }
    }

    public void enterUserNameIntoLoginInput(String username) {
        try {
            WebElement element = driver.findElement(By.xpath("//input[@name='username' and @placeholder='Username']"));
            element.clear();
            element.sendKeys(username);
            log.info(username + " was entered inti input");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void enterPasswordIntoInputPassword(String password) {
        try {
            WebElement element = driver.findElement(By.xpath("//input[@name='password' and @placeholder='Password']"));
            element.clear();
            element.sendKeys(password);
            log.info(password + " was entered into input Password");
        } catch (Exception e) {
            printErrorAndStopTest(e);

        }
    }

    public void clickOnLoginButton() {
        try {
            driver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm']")).click();
            log.info("Button 'Sing In' was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public boolean isButtonSignInDisplayed(){
        try {
            WebElement singInButton = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm']"));
            return singInButton.isDisplayed();
        }catch (Exception e){
            System.out.println("Button 'Sight In' is not found");
            return false;
        }
    }

    private void printErrorAndStopTest(Exception e) {
        log.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }
}
