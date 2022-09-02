package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends PostPage{
    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatarOnProfilePage;

    private String postTitleLocators = ".//*[text()='%s']";

    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;


    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage(){
        //TODO check url
        waitChatToBeHide();
        Assert.assertTrue(" MyProfilePage is not loaded", isElementDisplayed(avatarOnProfilePage));
        return  this;
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postList = getPostListWithTitle(title);
        Assert.assertEquals("Number of posts with title = "+title, 1, postList.size());
        return this;
    }


    public MyProfilePage deletePostWithTitleTillPresent(String title) {
        List<WebElement> listPost = getPostListWithTitle(title);
        int counter = 0;
        while (!listPost.isEmpty() && counter<100){
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocators, title))));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                  .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletePostMessagePresent();
            logger.info("Post was deleted with title " + title);
            listPost = getPostListWithTitle(title);
            counter++;
        }
        logger.info("all posts were deleted with title " + title);
        return this;
    }

    public MyProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("Element is not Displayed ", isElementDisplayed(successDeletePostMessage));
        return this;
    }

    private List<WebElement> getPostListWithTitle (String title){
        return webDriver.findElements(By.xpath(String.format(postTitleLocators, title)));
    }
}
