package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.containsString;

abstract public class ParentPage extends CommonActionsWithElements {
    protected String baseUrl;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        baseUrl = configProperties.base_url()
                //"https://[env]-complex-app-for-testing.herokuapp.com"
                .replace("[env]", System.getProperty("env", "qa"));

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

    protected void checkUrlByRegEx() {
        //method check url using regular expression
        Pattern pattern = Pattern.compile(baseUrl + getRelativeUrl());
        Matcher matcher = pattern.matcher(webDriver.getCurrentUrl());

        Assert.assertTrue("Invalid page", matcher.find());
        //but i don't know how to use assertThat in this case...it's very sad, because i spent a lot of time and it didn't work
        //if you can, give me a small clue to resolve this
        //Assert.assertThat("Invalid page",webDriver.getCurrentUrl(), containsString(matcher.group()));

    }

    protected void waitChatToBeHide() {
        webDriverWaitLow.withMessage("chat is not closed").until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }
}
