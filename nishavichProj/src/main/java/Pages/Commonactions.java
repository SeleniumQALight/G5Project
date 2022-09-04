package Pages;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Commonactions {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public Commonactions(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 =  new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }


    public void enterTextIntoElement (WebElement webElement, String text ) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("" + text + " 'was input  into'" + webElement. getAccessibleName() + " ''");

        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement (WebElement webElement) {
        try {
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = webElement.getAccessibleName();
            webElement.click();
            logger.info("'" + name + "' was clicked");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    protected boolean  isElementDisplayed (WebElement webElement){
        try{
            boolean state = webElement.isDisplayed();
            String message;
            if (state){
                message = "element is displayed";}
            else {
                message = "element is not displayed";
            }
            logger.info(message);
            return state;

            } catch (Exception e){
            logger.info("element is not displayed");
            return false;
        }
    }

    /** вибираємо значення у дропдауні по видимому тексту*/
    protected void selectTextInDropDown(WebElement dropDown , String text){
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(" ' " + text + " was selected in dropdown");

        } catch (Exception e){
            printErrorAndStopTest(e);
        }

    }
    protected void selectValueInDropDown(WebElement dropDown , String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(" ' " + value + " was selected in dropdown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
    private void printErrorAndStopTest (Exception e) {
        logger. error ("can not work with element " + e);
        Assert.fail ("can not work with elemnt");
    }
}
