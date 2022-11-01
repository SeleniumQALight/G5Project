package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {

    @FindBy(xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postList;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/.*";
    }

    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;

    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;


    private String postTitleLocator = ".//*[text()='%s']";

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        waitChatToBeHidden();
        checkUrlWithPatterns();
        Assert.assertTrue("My profile page is not there", isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postList = getPostsWithTitle(title);
        Assert.assertEquals("Number of posts with title " + title, 1, postList.size());
        return this;
    }

    public MyProfilePage deletePostWithTitleTillPresent(String title) {

        List<WebElement> listPost = getPostsWithTitle(title);
        int counter = 0;
        while (!listPost.isEmpty() && counter < 100) {
            clickOnElement(String.format(postTitleLocator, title));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsSuccessDeletedPostMessagePresent();
            logger.info("Post was deleted with title " + title);
            listPost = getPostsWithTitle(title);
            counter++;
        }
        logger.info("All posts were deleted with title" + title);
        return this;
    }

    private MyProfilePage checkIsSuccessDeletedPostMessagePresent() {
        Assert.assertTrue("Element is not displayed",
                isElementDisplayed(successDeletePostMessage));
        return this;
    }

    private List<WebElement> getPostsWithTitle(String title) {
        return webDriver.findElements(
                By.xpath(String.format(postTitleLocator, title)));
    }

    public PostPage clickOnPostWithTitle(String title) {
        if (getPostsWithTitle(title).size() > 1 || getPostsWithTitle(title).size() == 0) {
            logger.info("There is no post with provided title");
        } else {
            webDriver.findElement(By.xpath(String.format(postTitleLocator, title))).click();
        }
        return new PostPage(webDriver);
    }

    public MyProfilePage checkNumberOfPosts(int expectedNumOfPosts) {
        Assert.assertEquals("Number of posts ", expectedNumOfPosts, postList.size());
        return this;
    }
}
