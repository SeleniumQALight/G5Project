package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
        try {
            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSignOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAMessageInvalidUsernamePassword() {
        try {
            WebElement message = webDriver.findElement(By.xpath(".//div[contains(text(),'Invalid username / pasword')]"));
            return message.isDisplayed();
        } catch (Exception e) {
            return false;
        }
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


