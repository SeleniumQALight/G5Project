package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.CoreMatchers.containsString;

abstract public class ParentPage extends CommonActionsWithElements {
    protected String baseUrl;
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        baseUrl = configProperties.base_url()
                //"https://[env]-complex-app-for-testing.herokuapp.com"
                .replace("[env]",System.getProperty("env","qa"));

    }

    abstract String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("Invalid page "
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());
    }

    protected void checkUrlWithPattern() {
        logger.debug(webDriver.getCurrentUrl());//vcluchitsya kogda v log4j menyaem log4j.rootLogger=debug, stdout, R
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , containsString(baseUrl + getRelativeUrl()));
    }

    protected void waitChatToBeHide() {
        webDriverWaitLow.withMessage("chat is not closed").until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }
}
