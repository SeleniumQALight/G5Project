package pages;

import libs.Util;
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
import java.util.List;

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
            logger.info("'" + text + "'was input into " + webElement.getAccessibleName() + "'");
        } catch (Exception e) {
            prinErrorAndStopTest(e);
        }
    }

    protected void clicOnElement(WebElement webElement) {
        try {
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = webElement.getAccessibleName();
            webElement.click();
            logger.info("'" + name + " ' was clicked");
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

    public void checkAllVisibilityOf(WebElement webElement){
        WebDriverWait waitUntilVisibilityOf = new WebDriverWait(webDriver, Duration.ofSeconds(5));
       waitUntilVisibilityOf.until(ExpectedConditions.visibilityOfAllElements(webElement));
    }

    public String getTextFromElement (WebElement webElement){
        String text = webElement.getText();
        return text;
    }

    public List<WebElement> createListWithElements (String xPath){
        Util.waitABit(1);
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


}