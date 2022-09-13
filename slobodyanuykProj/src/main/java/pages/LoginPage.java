package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputUserNameHeader;
    @FindBy(xpath = ".//input[@placeholder='Password']" )
    private  WebElement inputPasswordHeader;
    @FindBy(xpath =".//button[text() ='Sign In']")
    private WebElement buttonSignIn;

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
//        try {
//            WebElement webElement = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
//            inputUserNameHeader.clear();
//            inputUserNameHeader.sendKeys(userName);
//            logger.info(userName + " entered in input");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputUserNameHeader,userName);
    }

    public void enterPasswordIntoLoginInput(String password) {
        enterTextIntoElement(inputPasswordHeader,password);
    }

    public void clickOnButtonLogin() {
//        try {
//            webDriver.findElement(By.xpath(".//button[text() ='Sign In']")).click();
//             buttonSignIn.click();
//
//        } catch (Exception e) {
//            logger.info("Button Sigh in clicked");
//        }
        clickOnElement(buttonSignIn);
    }



    public boolean messageInvalidUserPassword() {
        try {
            WebElement webElement =
                    webDriver.findElement(By.xpath(".//div[text() = 'Invalid username / pasword']"));
            return webElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
