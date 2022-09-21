package baseTest;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;

public class BaseTest {

    WebDriver driver;
    Logger log = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected PostPage postPage;
    protected MyProfilePage myProfilePage;

    @Before
    public void setUp() {
        log.info("STARTED : "+ testName.getMethodName() + " ---->");
        initDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration
                .ofSeconds(CommonActionWithElements.configProperties.TIME_FOR_DFFAULT_WAIT()));
        log.info("Browser is opened");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        postPage = new PostPage(driver);
        myProfilePage = new MyProfilePage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
        log.info("Browser is closed");
        log.info("CLOSED : " + testName.getMethodName() + " ----<\n");

    }

    @Rule
    public TestName testName = new TestName();

    private WebDriver initDriver(){
        String browser = System.getProperty("browser");
        if ((browser == null) || "chrome".equalsIgnoreCase(browser)){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if ("firefox".equalsIgnoreCase(browser)){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if ("ie".equalsIgnoreCase(browser)) {
            //WebDriverManager.iedriver().setup();
            // in most cases 32bit version is needed
            WebDriverManager.iedriver().arch32().setup();
            return new InternetExplorerDriver();
        }
        return driver;
    }
}
