package suite;

import apiTests.ApiTests;
import loginTest.LoginTest;
import loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTests.CreatePostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreatePostTest.class,
        ApiTests.class
})
public class SmokeSuite {
}
