package Pages.elements;

import Pages.CommonActionWithElements;
import Pages.CreatePostPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderElements extends CommonActionWithElements {

    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    public HeaderElements(WebDriver driver) {
        super(driver);
    }

    public CreatePostPage clickOnButtonCreatePost(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(driver);
    }
}
