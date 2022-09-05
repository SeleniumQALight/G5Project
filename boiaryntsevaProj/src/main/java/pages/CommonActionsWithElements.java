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
    protected WebDriverWait webDriverWait10;
    protected WebDriverWait webDriverWait15;

    Logger logger = Logger.getLogger(getClass());

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
            logger.info("'" + text + "' was entered into" + webElement.getAccessibleName());
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = webElement.getAccessibleName();
            webElement.click();
            logger.info("" + name + " was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            String name = webElement.getAccessibleName();
            boolean state = webElement.isDisplayed();
            String message;
            if (state) {
                message = name + "Element is displayed";
            } else {
                message = name + "Element is not displayed";
            }
            logger.info(message);
            return state;

        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info("'" + value + "' was selected in Drop down ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropdown, WebElement textToSelect) {
        try {
            clickOnElement(dropdown);
            clickOnElement(textToSelect);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectCheckBoxValue(WebElement checkbox, String neededValue) {
        if ((!neededValue.equalsIgnoreCase("check")) || (!neededValue.equalsIgnoreCase("uncheck"))) {
            logger.info("Invalid input for checkbox");
        } else if (checkbox.isSelected() && neededValue.equalsIgnoreCase("check")) {
            logger.info("Check box is already selected");
        } else if (checkbox.isSelected() && neededValue.equalsIgnoreCase("uncheck")) {
            checkbox.click();
            logger.info("Checkbox is unselected");
        } else if ((!checkbox.isSelected()) && neededValue.equalsIgnoreCase("check")) {
            checkbox.click();
            logger.info("Checkbox is selected");
        } else if ((!checkbox.isSelected()) && neededValue.equalsIgnoreCase("uncheck")) {
            logger.info("Checkbox is already not selected");
        }


    }
}
