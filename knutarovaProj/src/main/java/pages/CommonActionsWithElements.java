package pages;

import libs.ConfigProperties;
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

    protected void clickOnElement(String xpathLocator){
        try {
            WebElement element = webDriver.findElement((By.xpath(xpathLocator)));
            clickOnElement(element);
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
     private String getElementName (WebElement webElement){
        try{
            return webElement.getAccessibleName();
        }catch (Exception e){
            return "";
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
            if (text.equalsIgnoreCase("check")) {
                if (webElement.isSelected()) {
                    logger.info("webElement was clicked");
                } else {
                    clickOnElement(webElement);
                    logger.info("webElement wasn't clicked");
                }
            } else if (text.equalsIgnoreCase("uncheck")) {
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

    public void printErrorAndStopTest(Exception e) {
        logger.error("Can't work with element " + e);
        Assert.fail("Can't work with element " + e);
    }

    // методи для прикладу

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
        ((JavascriptExecutor)webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }

//********************
//    метод moveToElement (аналог скрола)

//    WebElement element = driver.findElement(By.id("my-id"));
//    Actions actions = new Actions(driver);
//actions.moveToElement(element);
//actions.perform();

//*********************

//    метод скрола з використанням javaScript

//    JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("javascript:window.scrollBy(250,350)");

//*********************
//    Емуляція натискання PageDown

//WebElement.sendKeys(Keys.DOWN);

//*********************
//    скрол до елемента з javaScript

//            webElement = driver.findElement(By.xpath("bla-bla-bla"));
//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);

}
