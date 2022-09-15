package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10, webDriverWait15;
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
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

    protected void enterTextIntoElement(WebElement webElement, String textForInput) {
        try {
            webElement.clear();
            webElement.sendKeys(textForInput);
            logger.info("'" + textForInput + "' was inputted into '" + getElementName(webElement) + "'");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.withMessage("Button is not clickable").until(ExpectedConditions.elementToBeClickable(webElement));
            String webElementName = getElementName(webElement);
            webElement.click();
            logger.info("Element '" + webElementName + "' was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(String xpathLocator) {
        try {
            WebElement element = webDriver.findElement(By.xpath(xpathLocator));
            clickOnElement(element);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void setCheckboxWithStatus(WebElement checkBox, String state) {
        if (state.equalsIgnoreCase("check") || state.equalsIgnoreCase("uncheck")) {
            if (checkBox.isSelected() && state.equals("check")) {
                logger.info("Checkbox is already selected");
            } else if (!checkBox.isSelected() && state.equalsIgnoreCase("check")) {
                checkBox.click();
                logger.info("Checkbox is selected");
            } else if (checkBox.isSelected() && state.equalsIgnoreCase("uncheck")) {
                checkBox.click();
                logger.info("Checkbox is unselected");
            } else if (!checkBox.isSelected() && state.equalsIgnoreCase("uncheck")) {
                logger.info("Checkbox is not already selected");
            }
        } else {
            Assert.fail("Inputted state for checkbox is not valid");
        }
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean isElementDisplayedStatus = webElement.isDisplayed();
            if (isElementDisplayedStatus) {
                logger.info("Element '" + getElementName(webElement) + "' is displayed");
            } else {
                logger.info("Element is not displayed");
            }
            return isElementDisplayedStatus;
        } catch (Exception e) {
            logger.error("Element is not displayed: " + e);
            return false;
        }
    }

    protected boolean isElementContainsText(WebElement webElement, String text) {
        try {
            String webElementText = webElement.getText();
            return webElementText.equals(text);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    protected void selectTextInDropDown(WebElement dropdown, String text) {
        try {
            Select select = new Select(dropdown);
            select.selectByVisibleText(text);
            logger.info("'" + text + "' was selected in dropdown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropdown, String value) {
        try {
            Select select = new Select(dropdown);
            select.selectByValue(value);
            logger.info("'" + value + "' was selected in dropdown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectTextInDropDownLikeUI(WebElement dropdown, String text) {
        try {
            dropdown.click();
            WebElement webElement = webDriver.findElement(By.xpath(".//select//*[text()='" + text + "']"));
            webElement.click();
            logger.info("'" + text + "' was selected in dropdown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element: " + e);
        Assert.fail("Cannot work with element: " + e);
    }
}
