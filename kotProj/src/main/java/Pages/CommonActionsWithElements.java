package Pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;

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
            logger.info(" ' " + text + " ' was input into  '" + getElementName(webElement) + " ' ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = getElementName(webElement);
            webElement.click();
            logger.info("'" + name + "' was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

//    protected void clickOnElement (String xpathLocator)
//        try {
//            WebElement element = webDriver.findElement(by.xpath(xpathLocator));
//            clickOnElement(element);
//
//
//    }catch(Exeption e){
//
//    printErrorAndStopTest(e);
//}
//    }

    /**
     * Метод поверне тру - якщо елемент показаний
     * фолс якщо - якщо не показаний або взагалі немає
     */

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            String message;
            if (state) {
                message = "Element is displayed";
            } else {
                message = "Element is not displayed";
            }
            logger.info(message);
            return state;
        } catch (Exception e) {
            logger.info("Element is Not displayed");
            return false;
        }
    }

    /**
     * метод по роботі з дропдауном, вибираємо значення в дропдауні
     * по видимому тексту
     *
     * @param dropDown
     * @param text     - видимий текст
     */


    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info("'" + " ' was selected from dropdown list");

        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //вибір з дропдауну по велью
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info("'" + value + " ' was selected from dropdown list");

        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectTextInDropDownUI(WebElement dropdown, WebElement groupMessage) {
        try {
            dropdown.click();
            clickOnElement(groupMessage);
            logger.info("'" + groupMessage + "' was selected in dropdown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    public void usersPressesKeyEnterTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.ENTER).build().perform();
        }
    }

    public void usersPressesKeyTabTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
        }

    }

    public void userOpensNewTab() {
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }
//    метод moveToElement (аналог скрола )
//
//    WebElement element = driver.findElement(By.id("my-id"));
//    Actions actions = new Actions(driver);
//    actions.moveToElement(element);
//    actions.perform();
//
//    метод скрола з використанням javaScript
//
//    JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("javascript:window.scrollBy(250,350)");
//
//—————————-
//    Емуляція натискання PageDown
//
//WebElement.sendKeys(Keys.DOWN);
//
//—————————-
//    скрол до елемента з javaScript
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
        private void printErrorAndStopTest (Exception e){
            logger.info("Can not work with element " + e);
            Assert.fail("Can not work with element " + e);
        }
    }
