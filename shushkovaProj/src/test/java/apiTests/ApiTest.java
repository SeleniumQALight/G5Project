package apiTests;

import api.AuthorDTO;
import api.Endpoints;
import api.PostDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTest {

    String userName ="autoapi";
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
                 //           new PostDto("test","test body","All Users", "no",new AuthorDTO("autoapi"),false),
                 //           new PostDto("test2","test body2","All Users", "no",new AuthorDTO("autoapi"),false)

                      PostDto.builder().title("test").body("test body").select1("All Users").uniquePost("no")
                              .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                              .build(),
                      PostDto.builder().title("test2").body("test body2").select1("All Users").uniquePost("no")
                              .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                              .build()

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
    @Test
    public void getAllPostByUserNegative(){
        String actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(Endpoints.POST_BY_USER,"not valid user")
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();
        Assert.assertEquals("Message in response ","\"Sorry, invalid user requested.undefined\"", actualResponse);
        Assert.assertEquals("Message in response ","Sorry, invalid user requested.undefined", actualResponse.replace("\"",""));
    }
    @Test
    public void getAllPostsByUserPath(){
        Response actualResponse=
        given().contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(Endpoints.POST_BY_USER,userName)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
        List<String> actualTitleList=actualResponse.jsonPath().getList("title",String.class);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < actualTitleList.size(); i++) {
            softAssertions.assertThat(actualTitleList.get(i)).as("Item number"+i).contains("test");
        }
        List<Map> actualAuthorList=actualResponse.jsonPath().getList("author",Map.class);
        for (int i = 0; i < actualAuthorList.size(); i++) {
            softAssertions.assertThat(actualAuthorList.get(i).get("username"))
                    .as("Item number"+i).isEqualTo(userName);
            
        }
        softAssertions.assertAll();
    }
    @Test
    public void getAllPostsByUserShema(){
        given().contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(Endpoints.POST_BY_USER,userName)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"));

    }

}
