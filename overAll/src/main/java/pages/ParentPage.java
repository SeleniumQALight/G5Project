package pages;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

abstract class ParentPage extends CommonActionsWithElements{
    protected String baseUrl;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        baseUrl = "https://[env]-complex-app-for-testing.herokuapp.com"
                .replace("[env]", System.getProperty("env", "qa"));

    }

    abstract String getRelativeUrl();

    protected void checkUrl(){
        Assert.assertEquals("Invalid page "
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());
    }

    protected void checkUrlWithPattern(){
        logger.debug(webDriver.getCurrentUrl());
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , containsString(baseUrl + getRelativeUrl())  );
    }

    protected void waitChatToBeHide(){
        webDriverWait10.withMessage("Chat is not closed")
                .until(ExpectedConditions
                .invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }

}
