package pages;

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
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void enterTextIntoElement(WebElement webElement, String text ){
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted into the '" + webElement.getAccessibleName() + "'");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement){
        try{
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = webElement.getAccessibleName();
            webElement.click();
            logger.info("'" + name + "' was clicked");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(String xpathLocator){
        try{
            WebElement element = webDriver.findElement(By.xpath(xpathLocator));
            clickOnElement(element);
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectTextInDropDown (WebElement dropDown, String option){
        try{
            Select  select = new Select(dropDown);
            select.selectByVisibleText(option);
            logger.info("'" + option + "' was selected in DropDown");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown (WebElement dropDown, String value){
        try{
            Select  select = new Select(dropDown);
            select.selectByValue(value);
            logger.info("'" + value + "' was selected in DropDown");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectFoundedTextInDropDown(WebElement webElement, String foundedText){
        try{
            webElement.click();
            webDriver.findElement(By.xpath(".//option[contains(text(),\"" + foundedText +"\")]")).click();
            logger.info("'" + foundedText + "' was selected in DropDown");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }


    protected boolean isElementDisplayed(WebElement webElement){
        try{
            boolean state = webElement.isDisplayed();
            String message;
            if (state) {
                message = "Element is displayed";
            } else {
                message = "Element is not displayed";
            }
            logger.info(message);
            return state;
        }catch(Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected void checkboxState(WebElement webElement, String state){
        if (state.equalsIgnoreCase("check")  && !webElement.isSelected()) {
            clickOnElement(webElement);
            logger.info("Checkbox is checked");
        } else if (state.equalsIgnoreCase("check")  && webElement.isSelected()) {
            logger.info("Checkbox is checked");
        }
        else if (state.equalsIgnoreCase("uncheck") && !webElement.isSelected()){
            logger.info("Checkbox is unchecked");
        } else if (state.equalsIgnoreCase("uncheck") && webElement.isSelected()){
            clickOnElement(webElement);
            logger.info("Checkbox is unchecked");
        }
        else {
            logger.info("Checkbox state is incorrect");
            Assert.fail("Checkbox state is incorrect");
        }
    }


    private void printErrorAndStopTest(Exception e) {
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
        ArrayList<String> tabs = new ArrayList<> (webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }

    /*
    метод moveToElement (аналог скрола )

WebElement element = driver.findElement(By.id("my-id"));
Actions actions = new Actions(driver);
actions.moveToElement(element);
actions.perform();

—————————-
метод скрола з використанням javaScript

JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(250,350)");

—————————-
Емуляція натискання PageDown

WebElement.sendKeys(Keys.DOWN);

—————————-
скрол до елемента з javaScript

webElement = driver.findElement(By.xpath("bla-bla-bla"));
((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);
     */

}
