package baseTest;

import Pages.CommonActionsWithElements;
import Pages.HomePage;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BaseTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;


    @Before
    public void setUp() {
        logger.info("------" + testName.getMethodName() + " was started -------");
        webDriver = initDriver();
//        WebDriverManager.chromedriver().setup();
//        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(CommonActionsWithElements.configProperties.TIME_FOR_DEFAULT_WAIT()));
        logger.info("Browser is opened");
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
    }

    //    @After
//    public void tearDown() {
//        webDriver.quit();
//        logger.info("Browser is closed");
//        logger.info("----- " + testName.getMethodName() + "  test was ended ------");
//    }
    @Rule
    public TestName testName = new TestName(); //current test name
    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            screenshot();
        }
        @Attachment(value = "Page screenshot", type = "image/png")
        public byte[] saveScreenshot(byte[] screenShot) {
            return screenShot;
        }
        public void screenshot() {
            if (webDriver == null) {
                logger.info("Driver for screenshot not found");
                return;
            }
            saveScreenshot(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES));
        }
        @Override
        protected void finished(Description description) {
            logger.info(String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
            try {
                webDriver.quit();
                logger.info("Browser was closed");
            } catch (Exception e) {
                logger.error(e);
            }
        }
    };

    private WebDriver initDriver() {
        String browser = System.getProperty("browser");
        if ((browser == null) || "chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        }



      return webDriver;
    }

}
//        else if ("ie".equalsIgnoreCase(browser)) {
//            //WebDriverManager.iedriver().setup();
//            // in most cases 32bit version is needed
//            WebDriverManager.iedriver().arch32().setup();
//            return new InternetExplorerDriver();
//        }

