package pages;

import org.openqa.selenium.WebDriver;

// У цьому класі наслідуються всі пейджі
public class ParentPage extends CommonActionsWithElements { // Alt+Enter → create constructor
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
}
