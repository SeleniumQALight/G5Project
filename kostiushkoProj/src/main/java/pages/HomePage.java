package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{
    private HeaderElement headerElement = new HeaderElement(webDriver);
    /**
     * Проверка на отображение того что открылась хоуйм пейдж
     * @return
     */
    public HomePage chekIsRedirectToHomepage(){
        Assert.assertTrue("HomePage does not loaded", isButtonSignOutDisplayed());
        return this;
    }

    /**
     * Метод який відкриває хоме пейдж і перевіряє що саме вона відкрилась
     * @return
     */
    public HomePage openHomePage(){
        //login Page
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginWithWalidCred();
        //chekHomePage
        return this;
    }

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }
    public  boolean isButtonSignOutDisplayed() {
        try {
            WebElement buttonSingOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
            return buttonSingOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isTextNoLogginDisplayed() {
        try {
            WebElement textNoLoggin = webDriver.findElement(By.xpath(".//* [@class='alert alert-danger text-center']"));
            return textNoLoggin.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Отдает приватный елемент хедера
     * @return
     */
    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
