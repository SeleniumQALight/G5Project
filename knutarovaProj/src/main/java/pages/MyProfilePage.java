package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage{
    // \" - екранування подвійних лапок в String
    @FindBy(xpath = ".//img[@class=\"avatar-small\"]")
    private WebElement avatar;
    private String postTitleLocator = ".//*[text()='%s']";
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;
    @FindBy(xpath = ".//button[@class='btn btn-sm btn-secondary']")
    private WebElement buttonSignOut;
    @FindBy(xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postsList;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage(){
        waitChatToBeHide();
        checkUrlWithPattern();
        Assert.assertTrue("My Profile page is not loaded",isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postsList = getPostsListsWithTitle(title);
        Assert.assertEquals("Number of posts with title" + title,1,postsList.size());
        return this;
    }

    public MyProfilePage deletePostsWithTitleTillPresent(String title) {
        List<WebElement> listPost = getPostsListsWithTitle(title);
        int counter = 0;
        while (!listPost.isEmpty() && counter<100){
            clickOnElement((String.format(postTitleLocator,title)));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsSuccessDeletedPostMessagePresent();
            logger.info("Post was deleted with title " + title);
            listPost = getPostsListsWithTitle(title);
            counter++;
        }
        logger.info("All posts were deleted with title " + title);
        return this;
    }

    private MyProfilePage checkIsSuccessDeletedPostMessagePresent() {
        Assert.assertTrue("successDeletePostMessage - element is not displayed",isElementDisplayed(successDeletePostMessage));
        return this;
    }

    private List<WebElement> getPostsListsWithTitle(String title){
        return webDriver.findElements(By.xpath(String.format(postTitleLocator,title)));
    }

    public PostPage clickOnPost(String text) {
            clickOnElement((String.format(postTitleLocator,text)));
            return new PostPage(webDriver);
    }

    public HomePage clickOnButtonSignOut() {
            clickOnElement(buttonSignOut);
            return new HomePage(webDriver);
    }

    public MyProfilePage checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts ", expectedNumberOfPosts, postsList.size());
        return this;
    }
}
