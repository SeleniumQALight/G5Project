package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement = new HeaderElement(webDriver);
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;
    @FindBy(xpath = ".//div[contains(text(),'Invalid username / pasword')]")
    private WebElement message;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


//    public boolean isButtonSignOutDisplayed() {
//        try {
//            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
//            return buttonSignOut.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public boolean isButtonSignOutDisplayed() {
        isElementDisplayed(buttonSignOut);
        return true;
    }

//    public boolean isAMessageInvalidUsernamePassword() {
//        try {
//            WebElement message = webDriver.findElement(By.xpath(".//div[contains(text(),'Invalid username / pasword')]"));
//            return message.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public boolean isAMessageInvalidUsernamePassword(){
        isElementDisplayed(message);
        return true;
    }

    /**
     * Перевірка, чи завантажилась HomePage
     *
     * @return
     */
    public HomePage checkIsRedirectToHomePage() {
        //TODO checkURL
        Assert.assertTrue("HomePage doesn't loaded", isButtonSignOutDisplayed());
        return this;
    }

    /**
     * Метод, який відкриває HomePage і перевіряє, що саме вона відкрилась
     *
     * @return
     */

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.LoginWithValidCred();
        checkIsRedirectToHomePage();
        return this;
    }

    /**
     * віддіає private HeaderElement
     * @return
     */
    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}


