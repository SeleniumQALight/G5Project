package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(tagName = "select")
    private WebElement dropDownRole;
    @FindBy(id = "post-body")
    private WebElement postBody;
    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement buttonSaveNewPost;




    @FindBy (xpath = ".//* [@name='uniquePost']")
    private WebElement checkBoxOnCreatePostPage;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelateUrl() {
        return "/create-post";
    }

    public CreatePostPage chekIsRedirectToCreatePostPage() {
        checkUrl();
        Assert.assertTrue("Page CreatePost is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String title) {
        enterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage selectTextInDropDownRole(String textForSelect) {
        this.selectTextInDropDown(dropDownRole, textForSelect);
        return this;
    }

    public CreatePostPage selectValueInDropDownRole(String valueForSelect) {
        selectValueInDropDownRole(dropDownRole, valueForSelect);

        return this;
    }

    public CreatePostPage selectTextInDropDownByUI(String valueForSelect) {
        selectTextInDropDownByUI(dropDownRole, valueForSelect);
        return this;
    }

    public CreatePostPage enterTextInTextAreaBodyContent(String text) {
        enterTextIntoElement(postBody, text);
        return this;
    }

    /**
     * clicking on the post button.
     *
     * @return
     */
    public PostPage clickSaveNewPostButton() {
        clicOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage checkCheckBoxAndClick(String check) {
    String lowerCheck = check.toLowerCase();
        Assert.assertTrue("Invalid checkbox state", lowerCheck.equals("check") || lowerCheck.equals("uncheck"));
        if (lowerCheck.equals("check")) {
            if (!checkBoxOnCreatePostPage.isSelected()) {
                checkBoxOnCreatePostPage.click();
                logger.info("Checkbox was click");
            } else logger.info("The checkbox was not clicked");
            return this;
        } else if (lowerCheck.equals("uncheck")) {
            if (checkBoxOnCreatePostPage.isSelected()) {
                checkBoxOnCreatePostPage.click();
                logger.info("Checkbox was click");
            } else logger.info("The checkbox was not clicked");
            return this;
        }return this;
    }

    public CreatePostPage checkIsRedirectToOldPostPage() {
        checkUrlwithPattern();
        Assert.assertTrue("Page CreatePost is not loaded", isElementDisplayed(inputTitle));
        return this;
    }


}


