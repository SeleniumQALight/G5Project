package Pages;

import libs.DriverHelper;
import libs.Util;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class MainPagePrivatBank extends ParentPage{
    Logger logger = Logger.getLogger(getClass());

    DriverHelper driverHelper = new DriverHelper();
    private String currencyUiValue;

    public String getCurrencyUiValue() {
        return currencyUiValue;
    }

    public String getUsdUiValue() {
        return usdUiValue;
    }

    public String getEurUiValue() {
        return eurUiValue;
    }

    @Override
    protected void printErrorAndStopTest(Exception e) {
        super.printErrorAndStopTest(e);
    }

    public MainPagePrivatBank(WebDriver driver) {
        super(driver);
    }


    @Override
    String getRelativeUrl() {
        return "/";
    }

    public MainPagePrivatBank openMainPrivatBankPage(){
        driver.get("https://privatbank.ua/");
        return this;
    }


    private MainPagePrivatBank dropdownSelect(){
        Select currencyDropdown = new Select(driver.findElement(By.id("courses_type")));
        currencyDropdown.selectByVisibleText("НБУ");
        return this;
    }

    private MainPagePrivatBank getUICurrencyValue(String currency){
        currencyUiValue = driver.findElement(By.xpath(String.format("//td[contains(text(), '%s')]/following-sibling::td[@id='%s_buy']", currency,currency))).getText().trim();
        return this;
    }


    public MainPagePrivatBank getCurrencyValue(String currency){
        //dropdownSelect();
        getUICurrencyValue(currency);
        logger.info("USD value on UI : " + usdUiValue);
        logger.info("EUR value on UI : " + eurUiValue);
        return this;
    }





}
