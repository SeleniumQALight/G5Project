package pages;

import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

import java.util.ArrayList;

public class HomePage extends ParentPage {
    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

//    public boolean isButtonSignOutDisplayed() {
//        try {
//            WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[@class='btn btn-sm btn-secondary']"));
//            return buttonSignOut.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public HomePage checkIsRedirectToHomePage() {
        checkUrl();
        Assert.assertTrue("HomePage doesn't loaded", headerElement.isButtonSignOutDisplayed());//,isButtonSignOutDisplayed());
        return this;
    }


    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        //wait
        if (!headerElement.isButtonSignOutDisplayed()) {
            loginPage.loginWithValidCredentialWithOutOpenPage();
        }
        checkIsRedirectToHomePage();
        return this;
    }

    public HomePage openTheSameNewWindow() {
        String currentUrl = webDriver.getCurrentUrl();
        userOpensNewTab();
        Util.waitABit(2);
        webDriver.get(currentUrl);
        Util.waitABit(3);
        return this;
    }

    public void checkLogOutInWindow() {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(0));
        logger.info("switch to first window");
        Util.waitABit(2);
        getHeaderElement().clickOnButtonSignOut().checkIsRedirectToLoginPage();
        Util.waitABit(2);
        webDriver.switchTo().window(tabs.get(1));
        Util.waitABit(2);
        ((JavascriptExecutor) webDriver).executeScript("history.go(0)");
        Util.waitABit(2);
        Assert.assertFalse("login page wasn't loaded", getHeaderElement().isButtonSignOutDisplayed());

    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
