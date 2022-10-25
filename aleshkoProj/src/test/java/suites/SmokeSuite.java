package suites;

import apiTests.ApiTests;
import loginTest.LoginTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postsTest.CreatePostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
        CreatePostTest.class,
        ApiTests.class
})
public class SmokeSuite {

}
