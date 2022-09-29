package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.CoreMatchers.containsString;

// У цьому класі наслідуються всі пейджі
abstract class ParentPage extends CommonActionsWithElements { // Alt+Enter → create constructor
    protected String baseUrl;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        baseUrl = configProperties.base_url()
                .replace("[env]", System.getProperty("env", "qa"));
    }

    abstract String getRelativeUrl();

    @Step
    protected void checkUrl() {
        Assert.assertEquals("Invalid page "
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());
    }

    @Step
    protected void checkUrlWithPattern() {
        logger.debug(webDriver.getCurrentUrl());
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , containsString(baseUrl + getRelativeUrl())); // Alt+Enter - import
    }

    @Step
    protected void waitChatToBeHide() {
        webDriverWaitLow.withMessage("Chat is not closed")
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }
}
