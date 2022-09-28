package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.Elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
        try {
            WebElement buttonSignOut =
                    webDriver.findElement(By.xpath(".//button[text() = 'Sign Out']"));

            return buttonSignOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Проверка, загущилась HomePage
     * @return
     */
      public HomePage checkIsRedirectToHomePage () {
          //TODO checkURL
          Assert.assertTrue("HomePage does not loaded", isButtonSignOutDisplayed());
          return this;
      }

    /**
     * Метод который открывает Home Page
     * и проверяет что именно она открылась.
     * @return
     */
    public HomePage openHomePage() {
        //loginPage;
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginWithValidCre();
        //checkHomePage
        checkIsRedirectToHomePage();

        return this;
    }

    /**
     * Отдает приватный элемент
     * @return
     */
    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
