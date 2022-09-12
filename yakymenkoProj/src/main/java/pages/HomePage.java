package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage { // Alt + Insert → create constructor
    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Перевірка чи дійсно завантажилась HomePage
     *
     * @return
     */
    public HomePage checkIsRedirectToHomePage() {
        //TODO checkURL
        Assert.assertTrue("HomePage is not loaded", getHeaderElement().isButtonSignOutDisplayed());
        return this;
    }

    /**
     * Метод який відкриває Home Page і перевіряє що саме вона відкрилась
     *
     * @return
     */
    public HomePage openHomePage() {
        //Login Page
        if (!getHeaderElement().isButtonSignOutDisplayed()) { // робимо перевірку що якщо ми не залогінені - потрібно залогінитись, інакше - ...(дописати)
            LoginPage loginPage = new LoginPage(webDriver);// створюємо об'єкт Логін пейджі
            loginPage.loginWithValidCred(); //alt+enter → LoginPage
        }
        //checkHomePage
        checkIsRedirectToHomePage();
        return this;
    }


    /**
     * Віддає приватний елемент Header
     *
     * @return
     */
    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}