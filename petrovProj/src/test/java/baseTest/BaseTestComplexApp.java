package baseTest;

import org.junit.Before;
import pages.HomePage;
import pages.LoginPage;


public class BaseTestComplexApp extends BaseTest{

    protected LoginPage loginPage;
    protected HomePage homePage;


    @Before
    public void setUpComplexApp() {

        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);

    }
}
