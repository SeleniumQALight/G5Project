package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PrivatBankMainPage extends ParentPagePrivatBank{
    private String CurrencyLocator = ".//div[@data-cource_type='posts_course']//tbody//tr[1]/td[1]";


    public PrivatBankMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PrivatBankMainPage openPrivateBankPage() {
            try {
                webDriver.get(basePrivatBankUrl);
                logger.info("PrivatBank page was opened");
                logger.info(basePrivatBankUrl);
            } catch (Exception e) {
                assertFailedLogger("Can not work with site" + e);
            }
        return new PrivatBankMainPage(webDriver);
    }


    private void assertFailedLogger(String textForPrint){
        logger.error(textForPrint);
        Assert.fail(textForPrint);
    }

    public void saveCurrencyExchangeRates() {
        for (int i = 1; i <webDriver.findElements(By.xpath(".//div[@data-cource_type='posts_course']//tbody//tr")).size()+1; i++) {
           // for (int j = 1; j <webDriver.findElements(By.xpath(".//div[@data-cource_type='posts_course']//tbody//tr["+i+"]/td")).size()+1; j++) {
              //  WebElement element = webDriver.findElement(By.xpath(".//div[@data-cource_type='posts_course']//tbody//tr["+i+"]/td["+j+"]"));
                WebElement element = webDriver.findElement(By.xpath(".//div[@data-cource_type='posts_course']//tbody//tr["+i+"]"));
                String getData = element.getText();
                System.out.println(getData);
         //   }
        }

    }
}
