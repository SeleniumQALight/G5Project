package pages.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionWithElement;
import pages.CreatePostPage;

public class HeaderElement extends CommonActionWithElement {
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }
    /**
     * клик на кнопку "Create Post"
     * и возвращаем пейжу Создания поста
     */
    public CreatePostPage clickOnButtonCreatePost () {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
