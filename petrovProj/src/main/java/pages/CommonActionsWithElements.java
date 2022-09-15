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
    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void enterTextIntoElement(WebElement webElement, String text){
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted into '" + webElement.getAccessibleName() + "'");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = webElement.getAccessibleName();
            webElement.click();
            logger.info("'" + name + "' was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(String xpathLocator){
        try {
            WebElement element = webDriver.findElement(By.xpath(xpathLocator));
            clickOnElement(element);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    public boolean isElementWasDisplayed(WebElement webElement){
        try{
           return webElement.isDisplayed();
        }catch (Exception e){
            return false;
        }

    }

    protected boolean isElementDisplayed(WebElement webElement){
        try {
            boolean state = webElement.isDisplayed();
            String message;
            if(state){
                message = "Element is displayed";
            }else {
                message = "Element is not displayed";
            }
            logger.info(message);
            return state;
        }catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }

    /**
     * выбираем значение в дропдауне по видимому тексту
     * @param dropDown
     * @param text
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
     * вибираемо значенно по value
     * @param dropDown
     * @param valueForSelect
     */
    protected void selectValueInDropDown(WebElement dropDown, String valueForSelect) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(valueForSelect);
            logger.info("'" + valueForSelect + "' was selected in DropDown");

        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }


    protected void selectTextInDropDownByUI(){
        try {
            clickOnElement(webDriver.findElement(By.xpath(".//option[text()='Загальнодоступне']")));
            clickOnElement(webDriver.findElement(By.xpath(".//option[text()='Групове повідомлення']")));

        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }


    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    public void selectedCheckBox(WebElement check, String checkCondition) {
        checkCondition = checkCondition.toLowerCase();
        if(checkCondition.equals("check")){
            if(!check.isSelected()){
                clickOnElement(check);
                logger.info("checkbox changed to check");
            }else {
                logger.info("checkbox already check");
            }
        }else if(checkCondition.equals("uncheck")){
            if(check.isSelected()){
                clickOnElement(check);
                logger.info("checkbox changed to uncheck");
            }else {
                logger.info("checkbox already uncheck");
            }
        }else {
            logger.error("Such a state cannot be established " + checkCondition);
            Assert.fail("Such a state cannot be established " + checkCondition);

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
        ((JavascriptExecutor)webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }

    /*
    скрол до елемента з javaScript
    webElement = driver.findElement(By.xpath("bla-bla-bla"));
((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);

*/
/*
    Емуляція натискання PageDown

WebElement.sendKeys(Keys.DOWN);
*/

    /*
    метод скрола з використанням javaScript

JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(250,350)");

     */

    /*
    метод moveToElement (аналог скрола )

WebElement element = driver.findElement(By.id("my-id"));
Actions actions = new Actions(driver);
actions.moveToElement(element);
actions.perform();
     */



}
