package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
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
import org.openqa.selenium.ie.InternetExplorerDriver;
import pages.CommonActionsWithElements;
import pages.PrivatBankMainPage;

import java.time.Duration;

public class BaseTestPrivatBank {

    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected PrivatBankMainPage privatBankMainPage;


    @Before
    public void setUp() {
        logger.info("--- "+testName.getMethodName()+" was started ---");
        webDriver = initDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(CommonActionsWithElements.configProperties.TIME_FOR_DEFAULT_WAIT()));
        logger.info("Browser was opened");
        privatBankMainPage = new PrivatBankMainPage(webDriver);

    }

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

    @Rule
    public TestName testName = new TestName();

    private WebDriver initDriver(){
        String browser = System.getProperty("browser");
        if(browser == null || "chrome".equalsIgnoreCase(browser)){
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
        }else if("firefox".equalsIgnoreCase(browser)){
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        }else if ("ie".equalsIgnoreCase(browser)) {
            //WebDriverManager.iedriver().setup();
            // in most cases 32bit version is needed
            WebDriverManager.iedriver().arch32().setup();
            return new InternetExplorerDriver();
        }

        return webDriver;
    }
}
