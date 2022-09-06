package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    private final HeaderElement headerElement = new HeaderElement(webDriver);

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;
    @FindBy(xpath = ".//h2")
    private WebElement titleTextField;
    @FindBy(xpath = ".//div[@class='body-content'][2]")
    private WebElement bodyTextField;
    @FindBy(xpath = ".//*[@data-original-title='Edit']")
    private WebElement buttonEditPost;
    @FindBy(xpath = ".//*[@data-original-title='Delete']")
    private WebElement buttonDeletePost;
    @FindBy(xpath = ".//p[contains(text(),'Is this post unique?')]")
    private WebElement textPostIsUnique;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostPage checkAlertAboutNewPostCreation(String text) {
        Assert.assertEquals("Text in Alert", text, alertSuccess.getText());
        return this;
    }

    public PostPage checkPostContentAfterNewPostCreation(String titleText, String bodyText, String postIsUnique) {
        Assert.assertTrue("Post's title contains another text than entered while creation", isElementContainsText(titleTextField, titleText));
        Assert.assertTrue("Post's body contains another text than entered while creation", isElementContainsText(bodyTextField, bodyText));
        Assert.assertTrue("Post's unique condition has not expected condition", isPostUniqueCondition(postIsUnique));
        return this;
    }

    private boolean isPostUniqueCondition(String postIsUnique) {
        if (postIsUnique.equalsIgnoreCase("yes") || postIsUnique.equalsIgnoreCase("no")) {
            if (postIsUnique.equalsIgnoreCase("yes") && textPostIsUnique.getText().equals("Is this post unique? : yes")) {
                return true;
            } else if (postIsUnique.equalsIgnoreCase("no") && textPostIsUnique.getText().equals("Is this post unique? : no")) {
                return true;
            } else {
                return false;
            }
        } else {
            Assert.fail("Inputted state for unique condition is not valid");
            return false;
        }
    }

    public PostPage checkRedirectToPostPage() {
        //TODO check URL
        waitChatToBeHide();
        Assert.assertTrue("Post page is not loaded", isElementDisplayed(buttonEditPost));
        return this;
    }

    public MyProfilePage clickOnDeletePostButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }
}
