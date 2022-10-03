package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage { // Alt + Insert → create constructor
    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
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

    @Step
    /**
     * Метод який відкриває Home Page і перевіряє що саме вона відкрилась
     *
     * @return
     */
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);// створюємо об'єкт Логін пейджі
        loginPage.openLoginPage();
        //Login Page
        if (!headerElement.isButtonSignOutDisplayed()) { // робимо перевірку що якщо ми не залогінені - потрібно залогінитись, інакше - ...(дописати)
            loginPage.loginWithValidCredWithOutOpenPage(); //alt+enter → LoginPage
        }
        //checkHomePage
        checkIsRedirectToHomePage();
        return this;
    }

    @Step
    /**
     * Віддає приватний елемент Header
     *
     * @return
     */
    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}