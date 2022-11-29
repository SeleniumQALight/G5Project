package pages;

import org.openqa.selenium.WebDriver;

abstract class ParentPagePB extends CommonActionsWithElements{
    protected String baseUrlUIPB;

    public ParentPagePB(WebDriver webDriver) {
        super(webDriver);
        baseUrlUIPB = configProperties.baseUrlUIPrivatBank();

    }
}


