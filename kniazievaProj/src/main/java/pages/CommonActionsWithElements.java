package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {

    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait( webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void enterTextIntoElement(WebElement webElement, String text){
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "'was inputted into '" + webElement.getAccessibleName()+ "'");
        }catch(Exception e){
            printErrorAndStopTest(e);
        }

    }

    protected void clickOnElement(WebElement webElement){
        try{
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = webElement.getAccessibleName();
            webElement.click();
            logger.info("'"+ name +"' was clicked");
        }catch(Exception e){
            printErrorAndStopTest(e);
        }
    }

    /**
     * Метод поверне true якщо елемент показаний
     * або поверне false якщо елемент не показаний або його взагалі немає
     * @param webElement
     * @return
     */

    protected boolean isElementDisplayed(WebElement webElement){
        try{
            boolean state = webElement.isDisplayed();
            String message;
            if (state){
                message = "Element is displayed";
            } else{
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
     * Обирає значення в ДропДауні по видимому тексту
     * @param dropDown
     * @param text - видимий текст
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
     * Обирає значення в ДропДауні по value
     * @param dropDown
     * @param value - value
     */
    protected void selectValueInDropDown(WebElement dropDown, String value){
        try{
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info("'" + value + "' was selected in DropDown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectTextInDropDownUI(WebElement webElement, WebElement webElementText){
        try{
            webElement.click();
            webElementText.click();
            logger.info("Was selected value");
        } catch(Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected boolean isCheckCheckBox(WebElement webElement){
        if (webElement.isSelected()){
            return true;
        }
        else {
            return false;
        }
    }


    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);

    }
}
