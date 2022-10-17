package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

import java.sql.SQLException;

public class HomePage extends ParentPage{
    private HeaderElement headerElement = new HeaderElement(webDriver);
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

/*    public boolean isButtonSignOutDisplayed(){
        try{
            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSignOut.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }*/

    /**
     * перевирка чи завантажилась HomePage
     * @return
     */
    public HomePage checkIsRedirectToHomePage(){
        checkUrl();
        Assert.assertTrue("HomePage is not loaded", getHeaderElement().isButtonSignOutDisplayed());
        return this;
    }

    /**
     * метод який видкривае HomePage и перевиряе що саме вона видкрилась
     * @return
     */
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!getHeaderElement().isButtonSignOutDisplayed()) {
            loginPage.loginWithValidCredWithoutOpenPage();
        }
        checkIsRedirectToHomePage();
        return this;
    }
    public HomePage openHomePageWithDataFromDB() throws SQLException, ClassNotFoundException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!getHeaderElement().isButtonSignOutDisplayed()) {
            loginPage.loginWithValidCredWithoutOpenPageWithDataFromDB();
        }
        checkIsRedirectToHomePage();
        return this;
    }
    public HomePage checkHomePageUserLoggedInNewTab(){
        userOpensNewTab();
        webDriver.get(baseUrl);
        Assert.assertTrue("Button Sign Out is not displayed", getHeaderElement().isButtonSignOutDisplayed());
        return this;
    }

    /**
     * виддае приватний елемент Header
     * @return
     */
    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
