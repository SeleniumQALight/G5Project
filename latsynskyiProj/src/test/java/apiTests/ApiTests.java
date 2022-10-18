package apiTests;

import static io.restassured.RestAssured.given;

import api.AuthorDTO;
import api.PostDTO;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import api.EndPoints;
import io.restassured.http.ContentType;

public class ApiTests {
    String user_name = "autoapi";
    Logger logger =  Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser(){
        PostDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                //.queryParam("exchange")
                .log().all()
                .when()
                .get(EndPoints.POST_BY_USER, user_name)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PostDTO[].class);
        ;
        logger.info("Number of posts = " + responseBody.length);
        logger.info("Post title post1 = " + responseBody[0].getTitle());
        logger.info("Usre mame post1 = " + responseBody[0].getAuthor().getUsername());

        for (int i = 0; i < responseBody.length ; i++) {
            Assert.assertEquals("Username is not matched " , user_name, responseBody[i].getAuthor().getUsername());
        }

        PostDTO[] expectedResult = {
                new PostDTO("test","test body","All Users","no", new AuthorDTO("autoapi"),false),
                new PostDTO("test2","test body2","All Users","no", new AuthorDTO("autoapi"),false)

        };
        Assert.assertEquals("Number of posts ",expectedResult.length,responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedResult.length ; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResult[i],"id","createdDate","author");
            softAssertions.assertThat(responseBody[i].getAuthor())
                    .isEqualToIgnoringGivenFields(expectedResult[i].getAuthor(),"avatar");
        }

        softAssertions.assertAll();














    }
}
