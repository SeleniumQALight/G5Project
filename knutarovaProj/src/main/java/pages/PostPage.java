package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

import java.util.List;

public class PostPage extends ParentPage{
    private HeaderElement headerElement = new HeaderElement(webDriver);
    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;
    @FindBy(xpath = ".//p[text()='Is this post unique? : yes']")
    private WebElement messageThisPostUniqueYes;
    private String postTitle2Locator = ".//*[text()='%s']";

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public void setHeaderElement(HeaderElement headerElement) {
        this.headerElement = headerElement;
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        Assert.assertTrue("Post page is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in Alert",text,alertSuccess.getText());
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public PostPage checkIsThisPostUniqueYes() {
        Assert.assertTrue(isElementDisplayed(messageThisPostUniqueYes));
        return this;
    }

    public EditPostPage clickOnButtonEdit() {
            clickOnElement(buttonEdit);
            return new EditPostPage(webDriver);
    }

    public MyProfilePage checkTitleWasEdited(String title) {
        List<WebElement> postsList = getPostsListsWithTitle2(title);
        Assert.assertEquals("Number of posts with title" + title,1,postsList.size());
        return new MyProfilePage(webDriver);
    }

    private List<WebElement> getPostsListsWithTitle2(String title){
        return webDriver.findElements(By.xpath(String.format(postTitle2Locator,title)));
    }
}
