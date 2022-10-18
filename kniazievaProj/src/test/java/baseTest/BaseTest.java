package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.CommonActionsWithElements;
import pages.HomePage;
import pages.LoginPage;
import pages.MyProfilePage;

import java.time.Duration;

public class BaseTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected MyProfilePage myProfilePage;

    @Before
    public void setUp() {
        logger.info("----------------"+testName.getMethodName()+" was started ---------------");
        webDriver = initDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(CommonActionsWithElements.configProperties.TIME_FOR_DFFAULT_WAIT()));
        logger.info("Browser was opened");
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        myProfilePage = new MyProfilePage(webDriver);
    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");
        logger.info("----------------"+testName.getMethodName()+" was ended ---------------");
    }

    @Rule
    public TestName testName = new TestName();


    private WebDriver initDriver(){
        String browser = System.getProperty("browser");
        if((browser == null)||"chrome".equalsIgnoreCase(browser)){
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        }

        return webDriver;
    }

}
