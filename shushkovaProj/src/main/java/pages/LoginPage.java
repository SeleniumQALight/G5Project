package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserNameHeader;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputUserPasswordHeader;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement buttonSingIn;

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

    public void enterUserNameIntoLoginInput(String userName) {

        enterTextIntoElement(inputUserNameHeader,userName);
    }

    public void enterPasswordIntoLoginInput(String password) {

        enterTextIntoElement(inputUserPasswordHeader,password);
    }

    public void clickOnButtonSignIn() {
        try {
            buttonSingIn.click();
            logger.info("Button sign in was clicked");

        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
    public boolean isMessageInvalidCredsDisplayed(){
        try {
            WebElement messageInvalidCreds = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center' and contains(text(),'Invalid username / pasword')]"));
            return messageInvalidCreds.isDisplayed();

        }catch (Exception e){
            return true;
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    public HomePage loginWithValidCred() {
        openLoginPage();
        enterUserNameIntoLoginInput(TestData.VALID_LOGIN);
        enterPasswordIntoLoginInput(TestData.VALID_PASSWORD);

        return new HomePage(webDriver);
    }
}



