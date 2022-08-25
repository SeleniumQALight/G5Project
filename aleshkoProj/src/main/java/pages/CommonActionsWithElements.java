package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    protected void openPage(String URL) {
        try {
            webDriver.get(URL);
            logger.info("[" + URL + "] was opened");
        } catch (Exception e) {
            logger.error("Cannot work with site");
            Assert.fail("Cannot work with site");
        }
    }

    protected void enterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted into '" + webElement.getAccessibleName() + "'");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            String webElementName = webElement.getAccessibleName();
            webElement.click();
            logger.info("Element '" + webElementName + "' was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean isElementDisplayedStatus = webElement.isDisplayed();
            logger.info("Element '" + webElement.getAccessibleName() + "' is displayed");
            return isElementDisplayedStatus;
        } catch (Exception e) {
            logger.error("Element is not displayed: " + e);
            return false;
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element: " + e);
        Assert.fail("Cannot work with element: " + e);
    }
}
