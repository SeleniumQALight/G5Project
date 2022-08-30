package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage{
    @FindBy(xpath = ".//div[text()='New post successfully created.']")
    private WebElement alertSuccess;
    @FindBy(xpath = ".//h2")
    private WebElement titleTextField;
    @FindBy(xpath = ".//div[@class='body-content'][2]")
    private WebElement bodyTextField;
    @FindBy(xpath = ".//h2//..//a")
    private WebElement buttonEditPost;
    @FindBy(xpath = ".//h2//..//form")
    private WebElement buttonDeletePost;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkRedirectToPostPageAfterNewPostCreation() {
        Assert.assertTrue("Alert about new post creating is not displayed", isElementDisplayed(alertSuccess));
        Assert.assertTrue("Edit post button is not displayed", isElementDisplayed(buttonEditPost));
        Assert.assertTrue("Delete post button is not displayed", isElementDisplayed(buttonDeletePost));
        return this;
    }

    public PostPage checkTitleAndBodyTextsAfterNewPostCreation(String titleText, String bodyText) {
        Assert.assertTrue("Post's title contains another text than entered while creation", isElementContainsText(titleTextField, titleText));
        Assert.assertTrue("Post's body contains another text than entered while creation", isElementContainsText(bodyTextField, bodyText));
        return this;
    }
}
