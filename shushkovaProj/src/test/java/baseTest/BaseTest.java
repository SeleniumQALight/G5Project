package baseTest;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pages.HomePage;
import pages.LoginPage;

public class BaseTest {
    WebDriver webDriver;
    Logger  logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;

    @Before
    public void setUp(){
        logger.info("------"+testName.getMethodName()+" was started-------");
        initDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("Browser was opened");
        loginPage = new LoginPage(webDriver);
        homePage= new HomePage(webDriver);
    }
    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");
        logger.info("------"+testName.getMethodName()+" was ended-------");
    }
    @Rule
    public TestName testName=new TestName();

    private WebDriver initDriver(){
        String browser=System.getProperty("browser");
        if((browser==null)||"chrome".equalsIgnoreCase(browser)){
            WebDriverManager.chromedriver().setup();
            webDriver=new ChromeDriver();
        }else if("firefox".equalsIgnoreCase(browser)){
            WebDriverManager.firefoxdriver().setup();
            webDriver=new FirefoxDriver();
        }else if ("ie".equalsIgnoreCase(browser)) {
            //WebDriverManager.iedriver().setup();
            // in most cases 32bit version is needed
            WebDriverManager.iedriver().arch32().setup();
            return new InternetExplorerDriver();
        }
        return webDriver;

    }

}
