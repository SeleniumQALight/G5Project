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

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage(){
        waitChatToBeHide();
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
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator,title))));
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
}
