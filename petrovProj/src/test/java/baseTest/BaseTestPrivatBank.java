package baseTest;

import org.junit.Before;
import pages.PrivatBankHomePage;


public class BaseTestPrivatBank extends BaseTestComplexApp {

    protected PrivatBankHomePage privatBankMainPage;


    @Before
    public void setUpPrivatBank() {
        privatBankMainPage = new PrivatBankHomePage(webDriver);

    }

}
