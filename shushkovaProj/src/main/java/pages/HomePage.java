package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{
   private HeaderElement headerElement= new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    /** метод чи завантажилась HomePage
     * */
    public HomePage checkIsRedirectToHomePage(){
        Assert.assertTrue("Home page is not open",headerElement.isButtonSingOutDisplayed());
        return this;
    }
    /** метод що відкриває HomePage і перевіряє що саме вона відкрилась
     * */
    public HomePage openHomePage() {
        //LoginPage
        LoginPage loginPage=new LoginPage(webDriver);
        if(!getHeaderElement().isButtonSingOutDisplayed()){
            loginPage.loginWithValidCred();
        }
        checkIsRedirectToHomePage();
        //check Homepage
        return this;
    }
    /** віддвє приватний елемент Хедера*/
    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
