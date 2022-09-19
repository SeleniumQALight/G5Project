package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {
    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletedPostMessage;

    private String postTitleLocator = ".//*[text()='%s']";

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        waitChatToBeHide();
        checkUrlWithPattern();
        Assert.assertTrue("My profile page is not loaded", isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postsList = getPostsListWithTitle(title);
        Assert.assertEquals("Number of posts with title '" + title + "'", 1, postsList.size());
        return this;
    }

    public MyProfilePage deletePostWithTitleUntilPresent(String title) {
        List<WebElement> postsList = getPostsListWithTitle(title);
        int counter = 0;
        while (!postsList.isEmpty() && counter < 100) {
            clickOnElement(String.format(postTitleLocator, title));
            new PostPage(webDriver)
                    .checkRedirectToPostPage()
                    .clickOnDeletePostButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletedPostMessagePresent();
            logger.info("Post was deleted with title: " + title);
            postsList = getPostsListWithTitle(title);
            counter++;
        }
        logger.info("All post were deleted with title: " + title);
        return this;
    }

    private MyProfilePage checkIsSuccessDeletedPostMessagePresent() {
        Assert.assertTrue("Message is not displayed", isElementDisplayed(successDeletedPostMessage));
        return this;
    }

    private List<WebElement> getPostsListWithTitle(String title) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));
    }
}
