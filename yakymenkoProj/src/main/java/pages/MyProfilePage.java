package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {
    // екранування подвійної кавички в String
    @FindBy(xpath = ".//img[@class=\"avatar-small\"]")
    private WebElement avatar;

    private String postTitleLocator = ".//*[text()='%s']"; // параметризований локатор
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        waitChatToBeHide();
        checkUrlWithPattern();
        Assert.assertTrue("My profile page is not loaded", isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postList = getPostListWithTitle(title);
        Assert.assertEquals("Number of posts with title " + title, 1, postList.size());
        return this;
    }

    public MyProfilePage deletePostsWithTitleTillPresents(String title) {
        List<WebElement> listPost = getPostListWithTitle(title);
        // якщо б ми пройшли якусь кількість ітерацій і зациклились то пройди 100 разів
        int counter = 0;
        while (!listPost.isEmpty() && counter < 100) { // виконуй поки наш listPost не пустий
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, title))));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletedPostMessagePresent();
            logger.info("Post was deleted with title " + title);
            listPost = getPostListWithTitle(title);
            counter++; // counter = counter + 1 // вийди якщо більше 100
        }
        logger.info("All posts were deleted with title " + title);
        return this;
    }

    private MyProfilePage checkIsSuccessDeletedPostMessagePresent() {
        Assert.assertTrue("successDeletePostMessage Element is not displayed", isElementDisplayed(successDeletePostMessage));
        return this;
    }

    private List<WebElement> getPostListWithTitle(String title) {
        return webDriver.findElements(
                By.xpath(String.format(postTitleLocator, title)));
    }
}
