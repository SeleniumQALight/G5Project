package libs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static void waitABit(Integer second){
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method returned SystemDateAndTime In Format yyyy-MM-dd_HH-mm-ss
     * @return
     */
    public static String getDateAndTimeFormatted(){
        return getDateAndTime("yyyy-MM-dd_HH-mm-ss");
    }

    /**
     * Method returned SystemDateAndTime
     * @return
     */
    public static String getDateAndTime(String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }

    /*
    //Методи для натискання кнопок і відкритя нової вкладки і переключення в неї

    protected void usersPressesKeyEnterTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.ENTER).build().perform();
        }
    }

    protected void usersPressesKeyTabTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
        }
    }

    protected void userOpensNewTab() {
        ((JavascriptExecutor)webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }
    */

    /*
    //метод moveToElement (аналог скрола )
    WebElement element = driver.findElement(By.id("my-id"));
    Actions actions = new Actions(driver);
    actions.moveToElement(element);
    actions.perform();

    //метод скрола з використанням javaScript
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("javascript:window.scrollBy(250,350)");

    //Емуляція натискання PageDown
    WebElement.sendKeys(Keys.DOWN);

    //скрол до елемента з javaScript
    webElement = driver.findElement(By.xpath("bla-bla-bla"));
    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);
*/
}
