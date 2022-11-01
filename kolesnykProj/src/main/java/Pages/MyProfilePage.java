package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage{

    @FindBy(xpath = "//img[@class='avatar-small']")
    private WebElement avatarElement;

    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;

    private String postTitleLocator = ".//*[text()='%s']";
    @FindBy(xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postsList;

    public MyProfilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage(){
        waitChatToBeHide();
        checkUrlWithPattern();
        Assert.assertTrue("My Profile page is not opened", isElementDisplayed(avatarElement));
        return this;
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postList = getPostListWithTitle(title);
        Assert.assertEquals("Number of posts with title " + title, 1, postList.size());
        return this;
    }

    public MyProfilePage deletePostsWithTitleTillPresent(String title) {
        List<WebElement> listPost = getPostListWithTitle(title);
        int counter = 0;
        while (!listPost.isEmpty() && counter < 100){
            clickOnElement(String.format(postTitleLocator,title));
            new PostPage(driver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletedPostMessagePresent();
            log.info("Post was deleted with title : " + title);
            listPost = getPostListWithTitle(title);
            counter++;
        }
        log.info("All posts were deleted with title : " + title);
        return this;
    }

    public MyProfilePage checkIsSuccessDeletedPostMessagePresent() {
        Assert.assertTrue("'successDeletePostMessage' Element is not displayed", isElementDisplayed(successDeletePostMessage));
        return this;
    }

    private List<WebElement> getPostListWithTitle(String title){
        return driver.findElements(By.xpath(String.format(postTitleLocator,title)));
    }

    public PostPage clickOnPost(String title){
        clickOnElement(String.format(postTitleLocator,title));
        return new PostPage(driver);
    }

    public MyProfilePage checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of post is : ", expectedNumberOfPosts, postsList.size());
        return this;
    }
}
