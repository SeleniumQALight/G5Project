package Pages;

import Pages.Elements.HeaderElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelatedUrl() {
        return "/";
    }

    public boolean isButtonSignOutDisplayed() {
        try {
            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSignOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean doesAlertInvalidPasswordAppear(){
        try {
            WebElement alertInvalidPassword = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']"));
            return alertInvalidPassword.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    /** перевіряем чи ми потрапили на HomePage
     * (ассерт+ наявність кнопки SignOut)
     */

    public HomePage checkIsRedirectToHomePage (){
        //TODO checkURL
        Assert.assertTrue("HomePage is not loaded", isButtonSignOutDisplayed() );
        return this;
    }

    /**
     * метод, який вікриває HomePage і, що відкрилась саме вона
     * @return
     */
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();

        if (!headerElement.isButtonSignOutDisplayed()) {
            loginPage.loginWithValidCredWithoutOpeningPage();
        }
        checkIsRedirectToHomePage();
        return this;
    }



//генеруємо метод щоб брати прайвет HeaderElement через гет
    public HeaderElement getHeaderElement(){
        return headerElement;
    }
}
