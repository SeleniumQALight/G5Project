package suits;

import loginTest.LoginTestWithInValidParams;
import loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTests.CreatePostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        LoginTestWithInValidParams.class,
        CreatePostTest.class
})
public class SmokeSuite {
}
