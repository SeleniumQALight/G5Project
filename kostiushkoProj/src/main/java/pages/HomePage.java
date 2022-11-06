package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

import java.sql.SQLException;

public class HomePage extends ParentPage{
    @FindBy (xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSingOut;

    @FindBy (xpath = ".//img [@alt='My profile']")
    private WebElement userAvatar;



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
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        //login Page
        if(!headerElement.isElementDisplayed(buttonSingOut)){
        loginPage.loginWithWalidCredWithoutOpenPage();}
        return this;
    }

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelateUrl() {
        return "/";
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

    public void isUserAvatarDisplayed(){
        Assert.assertTrue("User Avatar not displayed", isElementDisplayed(userAvatar));
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


    public HomePage openHomePageWithDataFromDB() throws SQLException, ClassNotFoundException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(!headerElement.isElementDisplayed(buttonSingOut)){
            loginPage.loginWithWalidCreduseBD();}
    return this;
    }
}
