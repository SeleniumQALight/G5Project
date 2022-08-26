package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage { // Alt + Insert
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

    public boolean isAlertDisplayed() {
        try {
            WebElement alertInvalidUsernamePassword = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']"));
            return alertInvalidUsernamePassword.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Перевірка чи завантажилась HomePage
     * @return
     */
    public HomePage checkIsRedirectToHomePage(){
        //TODO checkURL
        Assert.assertTrue("HomePage does not loaded",isButtonSignOutDisplayed());
        return this;
    }

    /**
     * Метод який відкриває Home Page і перевіряє що саме вона відкривлась
     * @return
     */
    public HomePage openHomePage() {
        //Login Page
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginWithValidCred(); //alt+enter → LoginPage
        //checkHomePage
        checkIsRedirectToHomePage();
        return this;
    }

//    public void clickOnCreatePostButton() { // видаляємо так як він перенесений
//    }

    /**
     * Віддає приватний елемент Header
     * @return
     */
    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
