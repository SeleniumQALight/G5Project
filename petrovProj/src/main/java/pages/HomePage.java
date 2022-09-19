package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSingOut;

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public boolean isButtonSignOutDisplayed(){
      return isElementWasDisplayed(buttonSingOut);
    }

    /**
     * Проверка, ганрузилась ли HomePage
     * @return
     */
    public HomePage checkIsRedirectToHomePage(){
        //TODO checkURL
        checkUrl();
        Assert.assertTrue("HomePage did not load", getHeaderElement().isButtonSignOutDisplayed());
        return this;
    }

    /**
     * метод открывает HomePage и проверяет что именно она открыта.
     * @return
     */
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(!headerElement.isButtonSignOutDisplayed()){
            loginPage.loginWithValidCredWithOutOpenPage();
        }
        checkIsRedirectToHomePage();
        return this;
    }

    /**
     * отдает приветные елемент хедера
     * @return
     */
    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
