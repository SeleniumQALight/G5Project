package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {
    @FindBy(xpath = ".//img[@class=\"avatar-small\"]")
    private WebElement avatar;

    private String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletedPostMessage;


    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }
    public MyProfilePage checkIsRedirectTpMyProfilePage(){
        Assert.assertTrue("my profile is not loaded",
                isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkPostWasCreated(String title) {
        List <WebElement> postList = webDriver.findElements(By.xpath(String.format(postTitleLocator,title)));
        Assert.assertEquals("Number of Posts  with title" + title,1, postList.size());

        return this;
    }

    public MyProfilePage deletePostsWithTitleTillPresent(String title) {
        List <WebElement> listPost = getPostsListWithTitle (title);
        int counter = 0;
        while (!listPost.isEmpty()){
            clickOnElement(webDriver.findElement (By.xpath(String.format(postTitleLocator,title))));
            new PostPage (webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsSuccessDeletedPostMessagePresent();
            logger.info("Post was deleted with title" + title);
            listPost = getPostsListWithTitle(title);
            counter++;  //counter = counter +1

        }
        logger.info("All posts were deleted with title" + title);

        return this;
    }

    private MyProfilePage checkIsSuccessDeletedPostMessagePresent(){
        Assert.assertTrue("element is not present", isElementDisplayed (successDeletedPostMessage));
        return this;
    }
    private List <WebElement> getPostsListWithTitle(String title) {
        return webDriver.findElements(
                By.xpath(String.format(postTitleLocator,title)));

    }
}
