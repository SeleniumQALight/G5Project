package pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends ParentPage{
    // \" - екранування подвійної кавички в String
    @FindBy(xpath = ".//img[@class=\"avatar-small\"]")
    private WebElement avatar;

    private String postTitleLocator = ".//*[text()='%s']";

    @FindBy (xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletedPostMessage;
    @FindBy (xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postsList;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/.*";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage(){
        waitChatToBeHide();
        checkUrlWithPattern();
        Assert.assertTrue("My profile page is not loaded"
                , isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postsList = getPostsListWithTitle(title);
        Assert.assertEquals("Number of posts with title " + title , 1, postsList.size());
        return this;
    }

    public MyProfilePage deletePostsWithTitleTillPresent(String title) {
        List<WebElement> listPost = getPostsListWithTitle(title);
        int counter = 0;
        while (!listPost.isEmpty() && counter<100){
            clickOnElement(String.format(postTitleLocator, title));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                  .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletedPostMessagePresent();
            logger.info("Post was deleted with title " + title);
            listPost = getPostsListWithTitle(title);
            counter++; //counter = counter + 1
        }
        logger.info("All posts were deleted with title " + title);
        return this;
    }

    private MyProfilePage checkIsSuccessDeletedPostMessagePresent() {
        Assert.assertTrue("successDeletedPostMessage Element is not displayed"
                , isElementDisplayed(successDeletedPostMessage));
        return this;
    }

    private List<WebElement> getPostsListWithTitle(String title){
        return webDriver.findElements(
                By.xpath(String.format(postTitleLocator, title)));
    }

    public MyProfilePage checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts", expectedNumberOfPosts, postsList.size());
        return this;
    }
}
