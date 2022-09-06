package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
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
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = webElement.getAccessibleName();
            webElement.click();
            logger.info("'" + name + "' was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    /**
     * метод поверне true якщо елемент показаний і false - якщо не показаний, або його взагалі немає
     *
     * @param webElement
     * @return
     */
    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            String message;
            if (state) {
                message = "Element is displayed";
            } else {
                message = "Element isn't displayed";
            }
            logger.info(message);
            return state;
        } catch (Exception e) {
            logger.info("Element isn't displayed");
            return false;
        }
    }

    /**
     * вибирає значення в dropDown по видимому тексту
     *
     * @param dropDown
     * @param text     - видимий текст
     */
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info("'" + text + "' was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    /**
     * вибирає значення в dropDown по value
     *
     * @param dropDown
     * @param value
     */
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info("'" + value + "' was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    /**
     * знаходить в dropDown елемент по тексту
     *
     * @param dropDownClosed
     * @param textLine
     */
    protected void selectTextInDropDownByUICase(WebElement dropDownClosed, WebElement textLine) {
        try {
            clickOnElement(dropDownClosed);
            clickOnElement(textLine);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void checkConditionInCheckBox(WebElement webElement, String text) {
        try {
            if (text.toUpperCase().equals("check")) {
                if (webElement.isSelected()) {
                    logger.info("webElement was clicked");
                } else {
                    clickOnElement(webElement);
                    logger.info("webElement wasn't clicked");
                }
            } else if (text.toUpperCase().equals("uncheck")) {
                if (webElement.isSelected()) {
                    clickOnElement(webElement);
                    logger.info("webElement was clicked");
                } else {
                    logger.info("webElement wasn't clicked");
                }
            } else {
                logger.error("It's unauthorized condition");
                Assert.fail("It's unauthorized condition");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can't work with element " + e);
        Assert.fail("Can't work with element " + e);
    }
}
