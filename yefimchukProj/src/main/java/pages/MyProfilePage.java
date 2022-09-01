package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {

    // \" - екранування подвійної кавички в String
    @FindBy(xpath = ".//img[@class=\"avatar-small\"]")
    private WebElement avatar;

    private String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletedPostMessage;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        waitChatToBeHide();
        Assert.assertTrue("My profile page is not loaded", isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postList = getPostListWithTitle(title);
        Assert.assertEquals("Number of posts with title " + title, 1, postList.size());
        return this; // return MyProfilePage
    }

    public MyProfilePage deletePostsWithTitleTillPresent(String title) {
        List<WebElement> listPost = getPostListWithTitle(title);
        int counter = 0;
        while (!listPost.isEmpty() && counter < 100) {
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, title))));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletedPostMessagePresent();
            logger.info("Post was deleted with title " + title);
            listPost = getPostListWithTitle(title);
            counter++; // counter = counter +1
        }
        logger.info("All posts were deleted with title" + title);
        return this;
    }

    private MyProfilePage checkIsSuccessDeletedPostMessagePresent() {
        Assert.assertTrue("successDeletedPostMessage Element is not displayed", isElementDisplayed(successDeletedPostMessage));
        return this;
    }

    private List<WebElement> getPostListWithTitle(String title) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, title)));
    }
}
