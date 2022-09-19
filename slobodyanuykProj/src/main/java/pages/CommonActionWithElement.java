package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonActionWithElement {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    public CommonActionWithElement(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    protected void enterTextIntoElement (WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("' " + text + " '" + " was inputted into " + webElement.getAccessibleName() + "'");
        }catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
    protected void clickOnElement (WebElement webElement) {
        try {
            String name = webElement.getAccessibleName();
             webElement.click();
             logger.info("' " + name + "' was clicked" );

        }catch (Exception e) {
            printErrorAndStopTest(e);

        }
    }
    public void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }
}
