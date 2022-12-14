package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage{

    @FindBy(xpath = ".//img[@class=\"avatar-small\"]")
    private WebElement avatarIcon;

    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    WebElement successDeletedPostMessage;

    private String postTitleLocator = ".//*[text()='%s']";

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
        Assert.assertTrue( "MyProfile Page is not loaded", isElementDisplayed(avatarIcon));
        return this;
    }


    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postsList = getPostsListWithTitle(title);
        Assert.assertEquals("Number of posts with the title " + title,1, postsList.size());
    return this;
    }

    public MyProfilePage deletePostWithTitleTillPresent(String title) {
            List<WebElement> listPost = getPostsListWithTitle(title);
            int counter = 0;
            while (!listPost.isEmpty() && counter<100){
                clickOnElement(String.format(postTitleLocator, title));
                new PostPage(webDriver)
                        .checkIsRedirectToPostPage()
                        .clickOnDeleteButton()
                        .checkIsRedirectToMyProfilePage()
                        .checkIsSuccessDeletePostMessagePresent();
                logger.info("Post was deleted with title: " + title);
                listPost = getPostsListWithTitle(title);
                counter++;
            }
            logger.info("All posts were deleted with title: " + title);
        return this;
    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("successDeletedPostMessage Element is not displayed", isElementDisplayed(successDeletedPostMessage));
        return this;
    }

    private List<WebElement> getPostsListWithTitle (String title){
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));
    }


    public PostPage clickOnThePost(String title) {
        clickOnElement(String.format(postTitleLocator, title));
        return new PostPage(webDriver);
    }

    public MyProfilePage checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts ", expectedNumberOfPosts, postsList.size());
        return this;
    }
}
