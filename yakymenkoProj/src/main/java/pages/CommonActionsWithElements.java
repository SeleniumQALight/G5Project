package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
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

public class CommonActionsWithElements { // загальні дії з елементами які ми будемо робити на всіх сторінках
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass()); // ↓Alt+Insert з нового рядка
    protected WebDriverWait webDriverWaitLow, webDriverWaitHight;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);

    // Конструктор працює тільки при створенні об'єкту
    public CommonActionsWithElements(WebDriver webDriver) { // якщо метод public - ми до нього будемо звертатися з тесту
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // ініціалізує елементи які наслідуються з цього класу
        webDriverWaitLow = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWaitHight = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_HIGHT()));
    }

    /**
     * Описуємо дію вводу тексту в елемент
     */
    protected void enterTextIntoElement(WebElement webElement, String text) { // метод protected - до нього можна буде достукатись тільки в пейджах
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted into '" + getElementName(webElement) + "'");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    /**
     * Метод, який дозволяє клікати на елементи, виводити меседж і оброблювати ексепшен
     *
     * @param webElement
     */
    protected void clickOnElement(WebElement webElement) { // метод protected - до нього можна буде достукатись тільки в пейджах
        try {
            webDriverWaitHight.withMessage("Button is not clickable").until(ExpectedConditions.elementToBeClickable(webElement));// очікування
            String name = getElementName(webElement); //спочатку беремо в нього ім'я
            webElement.click(); // клікаємо по елементу
            logger.info("Element '" + name + "' was clicked");// потім пишемо логгер
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(String xpathLocator) {
        try {
            WebElement element = webDriver.findElement(By.xpath(xpathLocator));
            clickOnElement(element);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void setCheckboxWithStatus(WebElement checkBox, String state) {
        if (state.equalsIgnoreCase("check") || state.equalsIgnoreCase("uncheck")) {
            if (checkBox.isSelected() && state.equals("check")) {
                logger.info("CheckBox is already selected");
            } else if (!checkBox.isSelected() && state.equalsIgnoreCase("check")) {
                checkBox.click();
                logger.info("CheckBox is selected");
            } else if (checkBox.isSelected() && state.equalsIgnoreCase("uncheck")) {
                checkBox.click();
                logger.info("CheckBox is unselected");
            } else if (!checkBox.isSelected() && state.equalsIgnoreCase("uncheck")) {
                logger.info("CheckBox is not already selected");
            }
        } else {
            Assert.fail("Inputted state for checkBox is not valid");
        }
    }

    /**
     * Метод поверне true якщо елемент показаний
     * або поверне false якщо елемент не показаний або його взагалі немає
     *
     * @param webElement
     * @return
     */
    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            String message;
            if (state) {
                message = "Element '" + getElementName(webElement) + "' is displayed";
            } else {
                message = "Element '" + getElementName(webElement) + "' is Not displayed";
            }
            logger.info(message);
            return state;
        } catch (Exception e) {
            logger.info("Element is Not displayed");
            return false;
        }
    }

    protected boolean isElementContainsText(WebElement webElement, String text) {
        try {
            String webElementText = webElement.getText();
            return webElementText.equals(text);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    /**
     * Вибираємо значення в ДропДауні по видимому тексту
     *
     * @param dropDown
     * @param text     - видимий текст
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
     * Вибираємо значення в ДропДауні по value
     *
     * @param dropDown
     * @param value    - value
     */
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info("'" + value + "' was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //    protected void selectTextInDropDownUI(WebElement dropDown, WebElement textInSelect) {
//        try {
//            clickOnElement(dropDown);
//            clickOnElement(textInSelect);
//            logger.info("'" + textInSelect + "' was selected in DropDown");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
//    }
// ↓↑
    protected void selectTextInDropDownUI(WebElement dropDown, String text) {
        try {
            dropDown.click();
            webDriver.findElement(By.xpath(".//select//*[text()='" + text + "']")).click();
            logger.info("'" + text + "' was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }
}
