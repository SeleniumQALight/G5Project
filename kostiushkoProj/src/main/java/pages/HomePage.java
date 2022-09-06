package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{
    @FindBy (xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSingOut;


    private HeaderElement headerElement = new HeaderElement(webDriver);
    /**
     * Проверка на отображение того что открылась хоуйм пейдж
     * @return
     */
//    public HomePage chekIsRedirectToHomepage(){
//        Assert.assertTrue("HomePage does not loaded", isButtonSignOutDisplayed(buttonSingOut));
//        return this;
//    }

    /**
     * Метод який відкриває хоме пейдж і перевіряє що саме вона відкрилась
     * @return
     */
    public HomePage openHomePage(){
        //login Page
        if(!headerElement.isElementDisplayed(buttonSingOut)){
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginWithWalidCred();}
        return this;
    }

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }
//    public  boolean isButtonSignOutDisplayed() {
//        try { buttonSingOut.isDisplayed();
//             return buttonSingOut.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public void isButtonSignOutDisplayed(){
        //TODo check url
        Assert.assertTrue("Button sign out not displayed", isElementDisplayed(buttonSingOut));
    }

//    public boolean isTextNoLogginDisplayed() {
//        try {textNoLoggin.isDisplayed();
//            return textNoLoggin.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }

    /**
     * Отдает приватный елемент хедера
     * @return
     */
    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
