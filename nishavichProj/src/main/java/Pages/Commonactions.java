package Pages;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class Commonactions {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    public Commonactions(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
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
            String name = webElement.getAccessibleName();
            webElement.click();
            logger.info("'" + name + "' was clicked");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    private void printErrorAndStopTest (Exception e) {
        logger. error ("can not work with element " + e);
        Assert.fail ("can not work with elemnt");
    }
}
