package baseTest; // Батько для всіх тестів

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;


public class BaseTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();// перед кожним тестом перевіряємо чи встановлений екзешнік
        webDriver = new ChromeDriver(); // створюємо новий браузер
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("Browser was opened");
        loginPage = new LoginPage(webDriver); // Місце де ми створюємо кожну пейджу
        homePage = new HomePage(webDriver); // Місце де ми створюємо кожну пейджу
    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");

    }
}
