package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
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
            String name = webElement.getAccessibleName();
            webElement.click();
            logger.info("'" + name + "' was clicked");
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

}
