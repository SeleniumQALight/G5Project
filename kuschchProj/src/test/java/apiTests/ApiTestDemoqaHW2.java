package apiTests;

import api.ApiHelperDemoqa;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class ApiTestDemoqaHW2 {
    Logger logger = Logger.getLogger(getClass());
    ApiHelperDemoqa apiHelperDemoqa = new ApiHelperDemoqa();

    @Before
    public void deleteAllBooks(){
        apiHelperDemoqa.login();
        apiHelperDemoqa.deleteUsersBooks();
        logger.info("login and deleted oll books in ysers");
    }

    @Test
    public void addBooksInProfile() {
        apiHelperDemoqa.addBooksInProfile();
        logger.info("book added in profile");
    }

}
