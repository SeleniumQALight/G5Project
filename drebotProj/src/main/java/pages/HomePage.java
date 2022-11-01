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
        try {
            webDriver.get(currentUrl);
            logger.info("in new window load url '" + currentUrl + "'");
        } catch (Exception e) {
            logger.info("can't load url '" + currentUrl + "'");
            Assert.fail("Can't work with url");
        }
        return this;
    }

    public void checkLogOutInWindow() {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());

        webDriver.switchTo().window(tabs.get(0));
        logger.info("switch to first window");

        getHeaderElement().clickOnButtonSignOut().checkIsRedirectToLoginPage();

        webDriver.switchTo().window(tabs.get(1));

        //webDriver.navigate().refresh();
        ((JavascriptExecutor) webDriver).executeScript("history.go(0)");

        Assert.assertFalse("login page wasn't loaded", getHeaderElement().isButtonSignOutDisplayed());

    }

    public HomePage checkIsAvatarDisplayed() {
        Assert.assertTrue("Avatar is displayed", headerElement.isAvatarDisplayed());
        return this;
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
