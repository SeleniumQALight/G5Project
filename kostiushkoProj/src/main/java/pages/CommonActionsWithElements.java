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
import java.util.List;

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
            logger.info("'" + text + "'was input into " + getElementName(webElement) + "'");
        } catch (Exception e) {
            prinErrorAndStopTest(e);
        }
    }

    protected void clicOnElement(WebElement webElement) {
        try {
            webDriverWaitHight.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = getElementName(webElement);
            webElement.click();
            logger.info("'" + name + " ' was clicked");
        } catch (Exception e) {
            prinErrorAndStopTest(e);
        }

    }
    protected void clicOnElement(String xpathLocator) {
        try {
           WebElement element = webDriver.findElement(By.xpath(xpathLocator));
           clicOnElement(element);
        } catch (Exception e) {
            prinErrorAndStopTest(e);
        }

    }


    /**
     * Метод вернет тру если елемент есть
     * или фолс если елемента нету
     *
     * @param webElement
     * @return
     */
    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            String message;
            if (state = true) {
                message = "Element is displayed";
            } else {
                message = "Element is not displayed";
            }
            logger.info(message);
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
        }
        return false;
    }


    /**
     * Вибираем значение в Дробдауни по видимому тексту
     *
     * @param dropDown
     * @param text
     */
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info("'" + text + " ' was selected in DropDown");

        } catch (Exception e) {
            prinErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDownRole(WebElement dropDown, String Value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(Value);
            logger.info("'" + Value + " ' was selected in DropDown");

        } catch (Exception e) {
            prinErrorAndStopTest(e);
        }
    }

    protected void selectTextInDropDownByUI(WebElement dropDown, String text) {
        try {
            dropDown.click();
            webDriver.findElement(By.xpath(".//*[contains(text(),'" + text + "')]")).click();
            logger.info("WebElement was selected in DropDown");

        } catch (Exception e) {
            prinErrorAndStopTest(e);
        }
    }



    public List<WebElement> createListWithElements (String xPath, int numberOfElements){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(xPath), numberOfElements));
        List<WebElement> list = webDriver.findElements(By.xpath(xPath));
        return list;
    }
    public int countingTheNumberOfElements (String xPath){
        List<WebElement> listMessage = webDriver.findElements(By.xpath(xPath));
        int number = listMessage.size();
        return number;
    }

    private void prinErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
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
        ((JavascriptExecutor)webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }

//    метод moveToElement (аналог скрола )

//    WebElement element = driver.findElement(By.id("my-id"));
//    Actions actions = new Actions(driver);
//actions.moveToElement(element);
//actions.perform();
//
//—————————-
////    метод скрола з використанням javaScript
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


    private String getElementName (WebElement webElement){
        try {return webElement.getAccessibleName();

        }catch (Exception e){
            return "";
        }
    }

}