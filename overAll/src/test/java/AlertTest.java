import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;

import baseTest.BaseTest;
import libs.Util;

public class AlertTest extends BaseTest {
    Logger logger = Logger.getLogger(getClass());
    @Test
    public void alertTest(){
        webDriver.get("https://garne.com.ua/list/woman/clothes/sub=3");
        logger.info("site was opened");

        webDriver.findElement(By.xpath(".//a[@class='product__image'][1]")).click();
        logger.info("first item was clicked");

        webDriver.findElement(By.xpath(".//*[@data-product-size='s']")).click();
        logger.info("size was clicked");

        webDriver.findElement(By.xpath(".//*[@id='buttonAddToCart']")).click();
        logger.info("add to cart was clicked");

        webDriver.findElement(By.xpath(".//*[@id='cart']")).click();
        logger.info("cart was clicked");

        webDriver.findElement(By.xpath(".//*[@data-cart-action='item-remove']")).click();
        logger.info("delete was clicked");


        webDriver.switchTo().alert().accept();
        logger.info("alert was accepted");


        Util.waitABit(3);
    }
}
