package pages;

import io.qameta.allure.Step;
import libs.ConfigProperties;
import libs.Util;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWaitLow, webDriverWaitHight;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWaitLow = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWaitHight = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_HIGHT()));
    }

    protected void enterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted into '" + getElementName(webElement) + "'");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWaitHight.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = getElementName(webElement);
            webElement.click();
            logger.info("'" + name + "' was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(String xpathLocator) {
        try {
            WebElement element = webDriver.findElement(By.xpath((xpathLocator)));
            clickOnElement(element);
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
            String nameElement = getElementName(webElement);
            webElement.click();
            logger.info("'" + nameElement + "' Element was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean isCheckCheckBox(WebElement checkBox) {

        try {
            boolean state = checkBox.isSelected();
            String message;
            if (state) {
                message = "Initial state of checkbox: element is check";
            } else {
                message = "Initial state of checkbox: element isn't check";
            }
            logger.info(message);
            return state;
        } catch (Exception e) {
            logger.info("Initial state of checkbox: element isn't check: " + e);
            return false;
        }
    }

    public void usersPressesKeyEnterTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.ENTER).build().perform();
            logger.info("'ENTER' was pressed ");
        }
    }

    @Step
    public void usersPressesKeyTabTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
            logger.info("'TAB' was pressed ");
        }
    }

    protected void usersSendTextByActionTime(int numberOfTimes, String text) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(text).build().perform();
            logger.info("value '" + text + "' was send");
        }
    }

    public void userOpensNewTab() {
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
        logger.info("open and switch new window");
    }

    protected void moveToElement(WebElement webElement) {
        Actions actions = new Actions(webDriver);
        webDriverWaitLow.until(ExpectedConditions.elementToBeClickable(webElement));
        try {
            actions.moveToElement(webElement).click().build().perform();
        } catch (Exception e) {
            logger.info("Move to element '" + webElement.getAccessibleName() + "' wasn't perform");
        }

        //actions.moveToElement(webElement).click().build().perform();
        //logger.info("Move to element '" + webElement.getAccessibleName() + "'");
    }

    protected boolean isElementIsActive(WebElement webElement) {
        if (webElement.equals(webDriver.switchTo().activeElement())) {
            logger.info("element '" + webElement.getAccessibleName() + "' is focused");
            return true;
        }

        logger.info("element '" + webElement.getAccessibleName() + "' wasn't focus");
        return false;
    }

//    ?????????? moveToElement (???????????? ???????????? )
//
//    WebElement element = driver.findElement(By.id("my-id"));
//    Actions actions = new Actions(driver);
//    actions.moveToElement(element);
//    actions.perform();
//
//???????????????????????????-
//    ?????????? ???????????? ?? ?????????????????????????? javaScript
//
//    JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("javascript:window.scrollBy(250,350)");
//
//???????????????????????????-
//    ???????????????? ???????????????????? PageDown
//
//WebElement.sendKeys(Keys.DOWN);
//
//???????????????????????????-
//    ?????????? ???? ???????????????? ?? javaScript
//
//            webElement = driver.findElement(By.xpath("bla-bla-bla"));
//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can't work with element " + e);
        Assert.fail("Can't work with element " + e);
    }

}
