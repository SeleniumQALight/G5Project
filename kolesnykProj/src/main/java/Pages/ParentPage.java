package Pages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.CoreMatchers.containsString;

abstract class ParentPage extends CommonActionWithElements{

    protected String baseUrl;

    public ParentPage(WebDriver driver) {
        super(driver);
        baseUrl = configProperties.base_url()
                .replace("[env]", System.getProperty("env", "qa"));
    }

    abstract String getRelativeUrl();
    protected void checkUrl(){
        Assert.assertEquals("Invalid page"
                ,baseUrl + getRelativeUrl()
                ,driver.getCurrentUrl() );
    }

    protected void checkUrlWithPattern(){
        log.debug(driver.getCurrentUrl());
        Assert.assertThat("Invalid page"
                ,driver.getCurrentUrl()
                , containsString(baseUrl + getRelativeUrl()));
    }
    protected void waitChatToBeHide(){
        webDriverWaitLow.withMessage("Chat is not closed")
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(By.xpath("//div[@id='chat-wrapper']")));
    }


}
