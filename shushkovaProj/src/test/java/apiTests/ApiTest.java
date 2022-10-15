package apiTests;

import api.AuthorDTO;
import api.Endpoints;
import api.PostDto;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ApiTest {

    String userName="autoapi";
    Logger logger= Logger.getLogger(getClass());
    @Test
    public void getAllPostsByUser(){
              PostDto[] responseBody= given()
                          .log().all()
                             .contentType(ContentType.JSON)
                                .when()
                                    .log().all()
                                       .get(Endpoints.POST_BY_USER,userName)
                                         .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response().as(PostDto[].class);
              logger.info("Number of posts = "+responseBody.length);
              logger.info("First post title = "+responseBody[0].getTitle());
              logger.info("User name = "+responseBody[0].getAuthor().getUsername());
              for (int i = 0; i < responseBody.length; i++) {
              Assert.assertEquals("Username is not matched ",userName,responseBody[i].getAuthor().getUsername());
        }
              PostDto[] expectedResult ={
                            new PostDto("test","test body","All Users", "no",new AuthorDTO("autoapi"),false),
                            new PostDto("test2","test body2","All Users", "no",new AuthorDTO("autoapi"),false)
                                        };
              Assert.assertEquals("Number of posts",expectedResult.length,responseBody.length);
              SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedResult.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResult[i],"id","createdDate","author");
            softAssertions.assertThat(responseBody[i].getAuthor())
                    .isEqualToIgnoringGivenFields(expectedResult[i].getAuthor(), "avatar");


        }



              softAssertions.assertAll();

    }
}
