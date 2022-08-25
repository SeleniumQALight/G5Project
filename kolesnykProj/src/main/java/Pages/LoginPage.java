package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    @FindBy(xpath = "//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = "//input[@name='password' and @placeholder='Password']")
    private WebElement inputPasswordHeader;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSingIn;


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
        enterTextIntoElement(inputUserNameHeader, username);
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextIntoElement(inputPasswordHeader, password);
    }

    public void clickOnLoginButton() {
        clickOnElement(buttonSingIn);
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

//    private void printErrorAndStopTest(Exception e) {
//        log.error("Cannot work with element " + e);
//        Assert.fail("Cannot work with element " + e);
//    }
}
