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

    private  String postTitleLocator = ".//*[text()='%s']";
    @FindBy (xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletedPostmessage;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelateUrl() {
        return "/profile/";
    }

    public MyProfilePage chekIsRedirectTomyProfilePage() {
        waitChatToBeHide();
        checkUrlwithPattern();
        Assert.assertTrue("My Profile Page is nt loaded", isElementDisplayed(avatar));
        return this;
    }


    public MyProfilePage chekPostWasCreated(String title) {
        List<WebElement> postsList = getPostsListsWithTitle(title);
        Assert.assertEquals("Number of post with title "+title,1,postsList.size());
        return this;
    }


    public MyProfilePage deletePostsWithTitleTillPresent(String title) {
        List<WebElement> listPost = getPostsListsWithTitle(title);
        int counter = 0;
        while (!listPost.isEmpty() && counter<100){
            clicOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, title))));
            new PostPage(webDriver).chekIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsSuccessDeletePostMessagePresent();
            logger.info("Post was deleted with title " + title);
            listPost = getPostsListsWithTitle(title);
            counter++;
        }
        logger.info("All posts were deleted with title "+ title);
        return this;
    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {
Assert.assertTrue("SuccessDeletePostMessage element is not displayed", isElementDisplayed(successDeletedPostmessage) );
        return this;
    }

    private List<WebElement> getPostsListsWithTitle (String title){
        return webDriver.findElements(By.xpath(String.format(postTitleLocator,title)));
    }

    public MyProfilePage findOldPostWithTitle(String title) {
       List<WebElement> post =  webDriver.findElements(By.xpath(".//*[contains(text(),'"+title+"')]"));
       logger.info("Found post by title");
       Assert.assertEquals("Wrong number of posts", 1, post.size());
        return this;
    }

    public PostPage clickOnButtonTitlePost(String title) {
        List<WebElement> post =  webDriver.findElements(By.xpath(".//*[contains(text(),'"+title+"')]"));
        clicOnElement(post.get(0));
        return new PostPage(webDriver);
    }
}
