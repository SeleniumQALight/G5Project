package apiTests;

import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    String userName = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser(){


        PostDTO [] responseBody =
        given()
                    .contentType(ContentType.JSON)
                .log().all()
                .when()
                    .get(EndPoints.POST_BY_USER,userName)
                .then()
                    .statusCode(200)
                .log().all()
                .extract().as(PostDTO[].class);
                ;
        logger.info("Number of posts = " + responseBody.length);
        logger.info("Title of the first post is "+ responseBody[0].getTitle());//тайтл першого поста, нуль то перший пост
        logger.info("UsernAame is " + responseBody[0].getAuthor().getUsername());

        for (int i = 0; i < responseBody.length; i++) {
            Assert.assertEquals("Username is not matched", userName,responseBody[i].getAuthor().getUsername());
        }

    PostDTO [] expectedResult = {
            new PostDTO("test","test body","All Users","no",new AuthorDTO("autoapi"),false ),
            new PostDTO( "test2", "test body2", "All Users","no", new AuthorDTO("autoapi"), false)
    };

Assert.assertEquals("Number of posts does not equal", expectedResult.length, responseBody.length);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedResult.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResult[i],"id","createdDate","author");
            softAssertions.assertThat(responseBody[i].getAuthor())
                    .isEqualToIgnoringGivenFields(expectedResult[i].getAuthor(), "avatar");
        }

        softAssertions.assertAll();//завжди зелений буде без цього

    }
}
