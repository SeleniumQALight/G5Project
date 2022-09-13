package Pages;

import Pages.Elements.HeaderElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage {
    private HeaderElement headerElement = new HeaderElement(webDriver);
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;
    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = ".//p[contains(text(),'Is this post unique?')]")
    private WebElement postIsUnique;


    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectedToPostPage() {
        //TODO check URL
        Assert.assertTrue("Post Page is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in Alert", text, alertSuccess.getText());
        return this;
    }

      public boolean isPostUniqueOrNot(String postUnique) {
        if (postUnique.equalsIgnoreCase("yes") || postUnique.equalsIgnoreCase("no")) {
            if (postUnique.equalsIgnoreCase("yes") && postIsUnique.getText().equals("Is this post unique? : yes")) {
                return true;
            } else if (postUnique.equalsIgnoreCase("no") && postIsUnique.getText().equals("Is this post unique? : no")) {
                return true;
            } else {
                return false;
            }
        } else {
            Assert.fail("");
            return false;
        }
    }
    public PostPage checkPostIsUnique(String postUnique) {
        Assert.assertTrue("", isPostUniqueOrNot(postUnique));
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }
}
