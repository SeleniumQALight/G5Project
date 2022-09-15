package Pages;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {
    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;

    private String postTitleLocator = ".//*[text()='%s']";//зберегли стрінгою postTitleLocator параметризований локатор

    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;


    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelatedUrl() {
        return "/profile/";
    }

    //метод перевірки чи потрапили ми на потрібну сторінку через ассерт
    public MyProfilePage checkIsRedirectToMyProfilePage() {
        waitChatToBeHide();   //перевірка, що чат зник, а потім вже, що сторінка загрузилась
        checkUrlWithPattern();
        Assert.assertTrue("My Profile page is not loaded", isElementDisplayed(avatar));
        return this;
    }
    //шукаємо всі пости з заданим тайтлом, Elements (not Element) щоб не впав, якщо знайде декілька
    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postList = getPostWithTitle(title); //якщо 1 тест зелений, якщо більше то красний
        Assert.assertEquals("Number of posts with titles" + title,1,postList.size());
        return this;


    }

    public MyProfilePage deletePostsWithTitleTillPresent(String title) {
        List<WebElement> listPost = getPostWithTitle(title);
        int counter = 0;
        while (!listPost.isEmpty() && counter<100){
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, title))));
            new PostPage(webDriver)
                    .checkIsRedirectedToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletedPostMessagePresent();
            logger.info("Post was deleted with title " + title);
            listPost = getPostWithTitle(title);
            counter++; //counter = counter + 1
        }
        logger.info("All posts were deleted with title " + title);
        return this;
    }


    public MyProfilePage checkIsSuccessDeletedPostMessagePresent() {
        Assert.assertTrue("Message about post deletion is not displayed ", isElementDisplayed(successDeletePostMessage));
        return this;
    }

    private List<WebElement> getPostWithTitle(String title){
        return  webDriver.findElements
                (By.xpath(String.format(postTitleLocator, title))) ;

    }
}
