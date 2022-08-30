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

    public boolean isButtonSignOutDisplayed(){
      return isElementWasDisplayed(buttonSingOut);
    }

    /**
     * Проверка, ганрузилась ли HomePage
     * @return
     */
    public HomePage checkIsRedirectToHomePage(){
        //TODO checkURL
        Assert.assertTrue("HomePage did not load",isButtonSignOutDisplayed());
        return this;
    }

    /**
     * метод открывает HomePage и проверяет что именно она открыта.
     * @return
     */
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginWithValidCred();
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
