package suits;

import loginTest.LoginTest;
import loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postsTests.CreatePostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
        CreatePostTest.class,
        LoginTestWithPageObject.class
})
public class SmokeSuite {
}
