package pages;

import org.openqa.selenium.WebDriver;


abstract class ParentPagePrivatBank extends CommonActionsWithElements{

        protected String basePrivatBankUrl;

    public ParentPagePrivatBank(WebDriver webDriver) {
        super(webDriver);
        basePrivatBankUrl = configProperties.base_url_privat();

    }

}