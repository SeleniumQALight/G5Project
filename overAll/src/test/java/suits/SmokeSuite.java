package suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


import loginTest.LoginTestWithPageObject;
import postsTests.CreatePostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreatePostTest.class
})
public class SmokeSuite {
}
