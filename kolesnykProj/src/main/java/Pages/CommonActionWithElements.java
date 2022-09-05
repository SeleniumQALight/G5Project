package Pages;

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

import static org.junit.Assert.fail;

public class CommonActionWithElements {

    protected WebDriver driver;
    Logger log = Logger.getLogger(getClass());

    protected WebDriverWait webDriverWait10, webDriverWait15;
    public CommonActionWithElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        webDriverWait10 = new WebDriverWait(driver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(driver, Duration.ofSeconds(15));
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
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(element));
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

    protected void selectDropDownByText(WebElement element, String text){
        clickOnElement(element);
        findElementByText(text).click();
    }

    protected WebElement findElementByText(String text) {
        WebElement element = driver.findElement(By.xpath("//*[text()='"+text+"']"));
        return element;
    }


    protected void printErrorAndStopTest(Exception e) {
        log.error("Cannot work with element " + e);
        fail("Cannot work with element " + e);
    }


    protected List<WebElement> findAlerts(){
        List<WebElement> listOfAlert = driver.findElements(By.xpath("//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"));
        return listOfAlert;
    }

    protected void actionWithCheckBox(WebElement element, String action){
        try {
            if(!element.isSelected()){
                if(action.equals("Check")){
                    clickOnElement(element);
                }
            }else{
                if (action.equals("Uncheck")){
                    clickOnElement(element);
                }
            }
            if (!action.equals("Check") && !action.equals("Uncheck")){
                log.error("Cannot work with element : checkbox");
                fail("Cannot work with element : checkbox");
            }
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }



}
