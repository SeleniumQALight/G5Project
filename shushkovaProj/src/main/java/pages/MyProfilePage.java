package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage{
    /** экранування подвійних кавичок **/
    @FindBy(xpath=".//img[@class=\"avatar-small\"]")
    private WebElement avatar;

    private String postTitleLocator=".//*[text()='%s']";
    
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeleteMessage;

    public MyProfilePage(WebDriver webDriver){ super(webDriver);
    }
    /** на кожній сторінці перевіряємо,чи потрапили ми на потрібну пейджу**/
    public MyProfilePage checkIsRedirectToMyProfilePage(){
        waitChatToBeHide();
        Assert.assertTrue("My Profile Page is not loaded"
                ,isElementDisplayed(avatar));
        return this;
    }
   /**checkPostWasCreated перевіряє,скільки постів з заданим title**/
    public MyProfilePage checkPostWasCreated(String title) {
        List<WebElement> postList=getPostListWithTitle(title);
        /**Expected result-чекаємо 1 пост ;
         * Actual result - скільки фактично прийшло постів з Ліста**/
        Assert.assertEquals("Number of posts with title"+title,1,postList.size());
        return this;
    }

    public MyProfilePage deletePostWithTitleTillPresence(String title) {
        List<WebElement> listPost =getPostListWithTitle(title);
        int counter=0;
        while (listPost.size()>0&&counter<100) {
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator,title))));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .ckickOnDeteleButton()
                    .checkIsDeleteMessageIsPresent();
            logger.info("Post was deleted with title"+title);
            listPost=getPostListWithTitle(title);
            counter++;
        }
        logger.info("All posts were deleted with title"+title);

        return this;
    }

    private MyProfilePage checkIsDeleteMessageIsPresent() {
        Assert.assertTrue("Element is not present",isElementDisplayed(successDeleteMessage));
        return this;
    }

    /** метод,що повертає список постів**/
    private List<WebElement> getPostListWithTitle(String title){

        return webDriver.findElements(
                By.xpath(String.format(postTitleLocator,title)));

    }


    //  public MyProfilePage checkPostWasCreated(String title){
//        List<>
 //   }
}
