package apiTest;

import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    String user_name =  "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostByUser(){

        PostDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                //.queryParam("exchange")
                //.log().all()
                .when()
                .get(EndPoints.POST_BY_USER, user_name)
                .then()
                .statusCode(HttpStatus.SC_OK)
                //.log().all()
                .extract()
                .response().as(PostDTO[].class);

        logger.info("Number of posts = " + responseBody.length);
        logger.info("Post title post1 = " + responseBody[0].getTitle());
        logger.info("User name post1 = " + responseBody[0].getAuthor().getUsername());

        for(int i = 0; i < responseBody.length; i++){
            Assert.assertEquals("Username is not matched", user_name , responseBody[i].getAuthor().getUsername());
        }

        PostDTO[] expectedResult = {
                new PostDTO("test", "test body", "All Users", "no", new AuthorDTO("autoapi"), false),
                new PostDTO("test2", "test body2", "All Users", "no", new AuthorDTO("autoapi"), false)
        };

        Assert.assertEquals("Number of post is different :",expectedResult.length, responseBody.length);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedResult.length; i++){
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResult[i], "id", "createdDate", "author");

            softAssertions.assertThat(responseBody[i].getAuthor())
                    .isEqualToIgnoringGivenFields(expectedResult[i].getAuthor(), "avatar");
        }
        softAssertions.assertAll();
    }
}
