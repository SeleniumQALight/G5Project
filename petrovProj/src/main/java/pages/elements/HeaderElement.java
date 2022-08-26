package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);

    }

    /**
     * клин на кнопку створення поста
     * и повертаемо пейдж створення пейджа
     * @return
     */
    public CreatePostPage clickOnButtonCreatePost(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}