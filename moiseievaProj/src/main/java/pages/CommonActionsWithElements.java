package pages;

import java.time.Duration;
import java.util.ArrayList;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import libs.ConfigProperties;

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

    protected void enterTextIntoElement(WebElement webElement, String text){
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted into '" + getElementName(webElement) + "'");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }


   protected void clickOnElement(WebElement webElement){
        try{
            webDriverWaitHight.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = getElementName(webElement);
            webElement.click();
            logger.info("'" + name + "' was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(String xpathLocator){
        try {
            WebElement element = webDriver.findElement(By.xpath(xpathLocator));
            clickOnElement(element);
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    /**
     * ?????????? ?????????????? true ???????? ?????????????? ??????????????????
     * ?????? ?????????????? false ???????? ?????????????? ???? ?????????????????? ?????? ???????? ?????????????? ??????????
     * @param webElement
     * @return
     */
    protected boolean isElementDisplayed(WebElement webElement){
        try{
            boolean state = webElement.isDisplayed();
            String message;
            if (state) {
                message = "Element is displayed";
            } else {
                message = "Element is Not displayed";
            }
            logger.info(message);
            return state;
        }catch (Exception e){
            logger.info("Element is Not displayed");
            return false;
        }
    }

    /**
     * ?????????????????? ?????????????? ?? ?????????????????? ???? ???????????????? ????????????
     * @param dropDown
     * @param text - ?????????????? ??????????
     */
    protected void selectTextInDropDown(WebElement dropDown, String text){
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info("'" + text + "' was selected in DropDown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    /**
     * ?????????????????? ?????????????? ?? ?????????????????? ???? value
     * @param dropDown
     * @param value - value
     */
    protected void selectValueInDropDown(WebElement dropDown, String value){
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info("'" + value + "' was selected in DropDown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void comperesTextFromElement(String text, WebElement element) {
        Assert.assertEquals("Message in Alert ", text, element.getText());
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
        ArrayList<String> tabs = new ArrayList<> (webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }
//
//    ?????????? moveToElement (???????????? ???????????? )
//
//    WebElement element = driver.findElement(By.id("my-id"));
//    Actions actions = new Actions(driver);
//actions.moveToElement(element);
//actions.perform();
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

    private String getElementName(WebElement webElement){
        try{
            return webElement.getAccessibleName();
        }catch (Exception e){
            return  "";
        }
    }


    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }



}
