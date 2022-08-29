package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostInfoPage extends ParentPage{
    @FindBy(xpath = ".//div[text()='New post successfully created.']")
    private WebElement messageSuccessfullyCreated;
    @FindBy(xpath = ".//div[@class='d-flex justify-content-between']")
    private WebElement textTitle;

    public PostInfoPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostInfoPage checkIsRedirectToPostInfoPage() {
        Assert.assertTrue("Page InfoPost was NOT loaded",isElementDisplayed(messageSuccessfullyCreated));
        return this;
    }

    public PostInfoPage checkTitleInPostInfoPage(String title) {
        Assert.assertTrue("Page InfoPost was NOT loaded",isElementContainText(textTitle,title));

        return this;
    }
}
