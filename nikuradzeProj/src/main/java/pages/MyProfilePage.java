package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage{
    // \" - екранування подвiйноi кавички в String
    @FindBy(xpath = ".//img[@class=\"avatar-small\"]")
    private WebElement avatar;

    private String postTitleLocator = ".//*[text()='%s']";
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletedPostMessage;

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
        Assert.assertTrue("My profile page is not loaded", isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postsList = getPostsListWithTitle(title);
        Assert.assertEquals("Number of posts with title " + title, 1, postsList.size());
        return this;
    }

    public MyProfilePage deletePostsWithTitleTillPresent(String title) {
        List<WebElement> listPost = getPostsListWithTitle(title);
        int counter = 0; //shchob ne zacyclylos'
        while (!listPost.isEmpty() && counter<100){
            clickOnElement(String.format(postTitleLocator, title)); // znayde pershyi post z takim title i zupinetsya
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                 .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletePostMessagePresent();
            logger.info("Post was deleted with title " + title);
            listPost = getPostsListWithTitle(title);
            counter++; // counter = counter + 1
        }
        logger.info("All posts were deleted with title " + title);
        return this;
    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("successDeletedPostMessage Element is not displayed", isElementDisplayed(successDeletedPostMessage));
        return this;
    }

    private List<WebElement> getPostsListWithTitle(String title){
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, title))); //znayde vsi posty
    }
}
