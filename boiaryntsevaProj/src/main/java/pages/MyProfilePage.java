package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import io.qameta.allure.Step;

public class MyProfilePage extends ParentPage {
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

    @Step
    public MyProfilePage checkIsRedirectToMyProfilePage() {
        waitChatToBeHidden();
        checkUrlWithPatterns();
        Assert.assertTrue("My profile page is not there", isElementDisplayed(avatar));
        return this;
    }

    @Step
    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postList = getPostsWithTitle(title);
        Assert.assertEquals("Number of posts with title " + title, 1, postList.size());
        return this;
    }

    @Step
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

    @Step
    private MyProfilePage checkIsSuccessDeletedPostMessagePresent() {
        Assert.assertTrue("Element is not displayed",
                isElementDisplayed(successDeletePostMessage));
        return this;
    }

    @Step
    private List<WebElement> getPostsWithTitle(String title) {
        return webDriver.findElements(
                By.xpath(String.format(postTitleLocator, title)));
    }

    @Step
    public PostPage clickOnPostWithTitle(String title){
        if (getPostsWithTitle(title).size() > 1 || getPostsWithTitle(title).size() == 0 ){
            logger.info("There is no post with provided title");
        } else {
            webDriver.findElement(By.xpath(String.format(postTitleLocator, title))).click();
        }
        return new PostPage(webDriver);
    }
}
