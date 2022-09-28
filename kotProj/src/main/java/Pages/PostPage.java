package Pages;

import Pages.Elements.HeaderElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage {
    private HeaderElement headerElement = new HeaderElement(webDriver);
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement alertSuccess;
    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = ".//p[contains(text(),'Is this post unique?')]")
    private WebElement postIsUnique;


    public PostPage(WebDriver webDriver) {super(webDriver);}

    @Override
    String getRelatedUrl() {return "/post/.*";}

    public PostPage checkIsRedirectedToPostPage() {
        checkUrlWithPattern();
        Assert.assertTrue("Post Page is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostPage checkTextInAlert(String text) {
        Assert.assertEquals("Text in Alert", text, alertSuccess.getText());
        return this;
    }

      public PostPage checkPostIsUnique() {
          if (postIsUnique.getText().contains("yes")){
              Assert.assertTrue("Post is not unique", postIsUnique.getText().contains("yes"));
              logger.info("Post is unique");
          } else {
              Assert.assertTrue("Post is unique", postIsUnique.getText().contains("no"));
              logger.info("Post is not unique");
          }
          return this;
      }


    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }


    public EditPostPage clickOnEditPostButton() {
        clickOnElement(buttonEdit);
        return new EditPostPage(webDriver);
    }
}

