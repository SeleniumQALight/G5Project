package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CommonActionsWithElements { // загальні дії з елементами які ми будемо робити на всіх сторінках
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass()); // ↓Alt+Insert з нового рядка

    // Конструктор працює тільки при створенні об'єкту
    public CommonActionsWithElements(WebDriver webDriver) { // якщо метод public - ми до нього будемо звертатися з тесту
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // ініціалізує елементи які наслідуються з цього класу
    }

    /**
     * Описуємо дію вводу тексту в елемент
     */
    protected void enterTextIntoElement(WebElement webElement, String text) { // метод protected - до нього можна буде достукатись тільки в пейджах
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted into '" + webElement.getAccessibleName() + "'");
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
            String name = webElement.getAccessibleName(); //спочатку беремо в нього ім'я
            webElement.click(); // клікаємо по елементу
            logger.info("'" + name + "' was clicked");// потім пишемо логгер
        } catch (Exception e) {
            printErrorAndStopTest(e);
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
                message = "Element is displayed";
            } else {
                message = "Element is Not displayed";
            }
            logger.info(message);
            return state;
        } catch (Exception e) {
            logger.info("Element is Not displayed");
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

    protected void selectTextInDropDownUI(WebElement dropDown, WebElement textInSelect) {
        try {
            clickOnElement(dropDown);
            clickOnElement(textInSelect);
            logger.info("'" + textInSelect + "' was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }
}
