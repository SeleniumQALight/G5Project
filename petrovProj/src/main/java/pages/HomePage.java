package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{
    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed(){
        try{
            WebElement buttonSingOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSingOut.isDisplayed();
        }catch (Exception e){
            return false;
        }

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
