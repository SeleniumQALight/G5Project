package Pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CommonActionWithElements {

    protected WebDriver driver;
    Logger log = Logger.getLogger(getClass());

    public CommonActionWithElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void enterTextIntoElement(WebElement element,String text){
        try {
            element.clear();
            element.sendKeys(text);
            log.info("'" + text + "' was entered into '" + element.getAccessibleName() + "'");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement element){
        try {
            String name = element.getAccessibleName();
            element.click();
            log.info("'" + name + "' was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement element){
        try {
            boolean state = element.isDisplayed();
            String message ;
            if (state) message = "Element is displayed";
            else message = "Element is not displayed";
            log.info(message);
            return state;
        }catch (Exception e){
            log.info("Element is not displayed");
            return false;
        }
    }

    protected void selectTextInDropDown(WebElement dropdown, String text){
        try {
            Select select = new Select(dropdown);
            select.selectByVisibleText(text);
            log.info("'" + text + "' was selected in Dropdown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropdown, String value){
        try {
            Select select = new Select(dropdown);
            select.selectByValue(value);
            log.info("'" + value + "' was selected in Dropdown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }



    private void printErrorAndStopTest(Exception e) {
        log.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }



}
