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
    String getRelativeURL() {
        return "/";
    }

    public boolean isButtonSignOutDisplayed() {
        return headerElement.isButtonSignOutDisplayed();
    }

    /**
     * чи завантажилась HomePage
     *
     * @return
     */
    public HomePage checkIsRedirectToHomePage() {
        //TODO checkURL
        Assert.assertTrue("HomePage does not loaded", isButtonSignOutDisplayed());
        return this; //return HomePage
    }


    /**
     * метод який відкриває Home Page
     * і перевіряє що саме вона відкрилась
     *
     * @return
     */
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!headerElement.isButtonSignOutDisplayed()) {
            loginPage.loginWithValidCredWithoutOpenPage();
        }
        checkIsRedirectToHomePage();
        return this;
    }

    /**
     * віддає приватний елемент Header
     *
     * @return
     */
    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
