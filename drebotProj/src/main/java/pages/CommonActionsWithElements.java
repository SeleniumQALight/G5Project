package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
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
            String name = webElement.getAccessibleName();
            webElement.click();
            logger.info("'" + name + "' was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

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

    protected boolean isElementContainText(WebElement webElement, String text) {
        try {
            String getText = webElement.getText();
            String message;
            Boolean state;

            if (getText.equals(text)) {
                message = "'" + getText + "' is equaled input '" + text + "'";
                state = true;
            } else {
                message = "'" + getText + "' in element is NOT equaled input '" + text + "'";
                state = false;
            }
            logger.info(message);
            return state;

        } catch (Exception e) {
            logger.info("Elements aren't equaled");
            return false;
        }
    }

    //text po text
    protected void selectTextInDropDown(WebElement webElement, String text) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("'" + text + "' was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //text po value
    protected void selectValueInDropDown(WebElement webElement, String value) {
        try {
            Select select = new Select(webElement);
            select.selectByValue(value);
            logger.info("'" + value + "' was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectTextInDropDownByUI(WebElement dropDown, String text) {
        try {
            dropDown.click();
            WebElement webElement = dropDown.findElement(By.xpath(".//*[text()='" + text + "']"));
            String nameElement = webElement.getAccessibleName();
            webElement.click();
            logger.info("'" + nameElement + "' Element was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can't work with element " + e);
        Assert.fail("Can't work with element " + e);
    }

}
