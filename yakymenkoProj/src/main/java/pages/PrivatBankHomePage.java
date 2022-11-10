package pages;

import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrivatBankHomePage {
    private final String BASE_URL_PRIVAT_BANK = "https://privatbank.ua/";
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;

    public PrivatBankHomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void openHomePage() {
        try {
            webDriver.get(BASE_URL_PRIVAT_BANK);
            logger.info("Home page was opened");
            logger.info(BASE_URL_PRIVAT_BANK);
        } catch (Exception e) {
            logger.error("Can not work with site");
            Assert.fail("Can not work with site");
        }
    }

    public static Float roundingValue(Float value) {
        BigDecimal bigDecimal = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
        return Float.valueOf(String.valueOf(bigDecimal));
    }

    public void getExchangeRateByCurrency(String currency) {
        WebElement buyCourse = webDriver.findElement(By.id(String.format("%s_buy", currency)));
        WebElement saleCourse = webDriver.findElement(By.id(String.format("%s_sell", currency)));
        TestData.COURSE_BUY_UI = roundingValue(Float.valueOf(buyCourse.getText()));
        TestData.COURSE_SALE_UI = roundingValue(Float.valueOf(saleCourse.getText()));

        logger.info("Purchase course on UI is: " + TestData.COURSE_BUY_UI + " and sale course on UI is: " + TestData.COURSE_SALE_UI);
    }

    public void checkSameCourse() {
        Assert.assertEquals("Buy rate is ", TestData.COURSE_BUY_API, TestData.COURSE_BUY_UI);
        Assert.assertEquals("Sale rate is ", TestData.COURSE_SALE_API, TestData.COURSE_SALE_UI);
        logger.info("Purchase and sale exchange courses are the same on API and UI");
    }
}
