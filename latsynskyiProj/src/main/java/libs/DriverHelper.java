package libs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;

public class DriverHelper {
    private static WebDriver webDriver ;

    public void createWebDriver(){
        initDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        webDriver.manage().window().maximize();
    }

    public static WebDriver getWebDriver(){
        return webDriver;
    }

    public void closeWebDriver() {
        webDriver.quit();
    }

    private WebDriver initDriver(){
        String browser = System.getProperty("browser");
        if ((browser==null) || "chrome".equalsIgnoreCase(browser)){
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        }else if ("firefox".equalsIgnoreCase(browser)){
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        }else if ("ie".equalsIgnoreCase(browser)) {
            //WebDriverManager.iedriver().setup();
            // in most cases 32bit version is needed
            WebDriverManager.iedriver().arch32().setup();
            webDriver =  new InternetExplorerDriver();
        }

        return webDriver;
    }
}
