package Pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.Assert.fail;

public class CommonActionWithElements {

    protected WebDriver driver;
    Logger log = Logger.getLogger(getClass());

    protected WebDriverWait webDriverWaitLow, webDriverWaitHight;
    protected static boolean checkboxSelected;

    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);

    public CommonActionWithElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        webDriverWaitLow = new WebDriverWait(driver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWaitHight = new WebDriverWait(driver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_HIGHT()));
    }

    protected void enterTextIntoElement(WebElement element,String text){
        try {
            element.clear();
            element.sendKeys(text);
            log.info("'" + text + "' was entered into '" + getElementName(element) + "'");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement element){
        try {
            webDriverWaitHight.until(ExpectedConditions.elementToBeClickable(element));
            String name = getElementName(element);
            element.click();
            log.info("'" + name + "' was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(String xPathLocator){
        try {
            WebElement element = driver.findElement(By.xpath(xPathLocator));
            clickOnElement(element);
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




    protected void actionWithCheckBox(WebElement element, String action){
        try {
            if(!element.isSelected()){
                if(action.equalsIgnoreCase("Check")){
                    clickOnElement(element);
                    checkboxSelected = true;
                }
            }else{
                if (action.equals("Uncheck")){
                    clickOnElement(element);
                    checkboxSelected = false;
                }else {
                    log.info("Checkbox is already selected");
                }

            }
            if (!action.equalsIgnoreCase("Check") && !action.equalsIgnoreCase("Uncheck")){
                log.error("Cannot work with element : checkbox");
                fail("Cannot work with element : checkbox");
            }
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    public void usersPressesKeyEnterTime(int numberOfTimes) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.ENTER).build().perform();
        }
    }

    public void usersPressesKeyTabTime(int numberOfTimes) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
        }
    }

    public void userOpensNewTab() {
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    private String getElementName(WebElement webElement){
        try {
            return webElement.getAccessibleName();
        }catch (Exception e){
            return "";
        }

    }

}
