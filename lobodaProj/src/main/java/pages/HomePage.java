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

    @Override
    String getRelativeUrl() {
        return "/";
    }

    /**
     * Перевірка чи завантажилась HomePage
     *
     * @return
     */
    public HomePage checkIsRedirectToHomePage() {
        checkUrl();
        Assert.assertTrue("HomePage does not loaded"
                , getHeaderElement().isButtonSighOutDisplayed());
        return this;
    }

    /**
     * Метод який відкриває Home Page
     * і перевіряє що саме вона відкрилась
     *
     * @return
     */
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!headerElement.isButtonSighOutDisplayed()) {
            loginPage.loginWithValidCredWithOutOpenPage();
        }
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

    public HomePage checkAvatarIsDisplayed() {
        Assert.assertTrue(headerElement.isAvatarDisplayed());
        return this;
    }
}
