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

public class CommonActionWithElement {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10,webDriverWait15;
    Logger logger = Logger.getLogger(getClass());


    public CommonActionWithElement(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver,Duration.ofSeconds(15));
    }
    protected void enterTextIntoElement (WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("' " + text + " '" + " was inputted into " + webElement.getAccessibleName() + "'");
        }catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
    protected void clickOnElement (WebElement webElement) {
        try {
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = webElement.getAccessibleName();
             webElement.click();
             logger.info("' " + name + "' was clicked" );

        }catch (Exception e) {
            printErrorAndStopTest(e);

        }
    }


    public void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    /**
     * Метод поверне true якщо элемент показаный
     * або поверне false якщо элемент не показананый або його взагалі немае.
     * @param webElement
     * @return
     */
    public boolean isElementDisplayed (WebElement webElement) {
        try {
              boolean state = webElement.isDisplayed();
              String message;
              if (state) {
                  message = "Element is displayed";
              } else  {
                  message = "Element is not displayed";
              }
              logger.info(message);
            return  state;
        }catch (Exception e) {
            logger.info("Element is not displayed");
            return false;

        }

    }

    /**
     * Выбираем значение в DropDown по видимому тексту
     * @param DropDown
     * @param text
     */
    protected void selectTextDropDown (WebElement DropDown, String text) {
        try {
              Select select = new Select(DropDown);
              select.selectByVisibleText(text);
              logger.info("'" + text + "was selected in Drop Down");
        }catch (Exception e) {
                printErrorAndStopTest(e);
        }

    }
    /**
     * Выбираем значение в DropDown по Value
     * @param DropDown
     * @param value - value
     */
    protected void selectValueDropDown (WebElement DropDown, String value) {
        try {
            Select select = new Select(DropDown);
            select.selectByValue(value);
            logger.info("'" + value + "was selected in Drop Down");
        }catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }
    protected void selectDropDownElementUi (WebElement dropDown, WebElement optionElementUi ) {
                  try {
                      clickOnElement(dropDown);
                      clickOnElement(optionElementUi);
                  }catch (Exception e) {
                      printErrorAndStopTest(e);
                  }
    }
}
