package apiTest;

import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class ApiTest {
    String userName = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser(){
        PostDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
                .log().all()
                .when()
                .get(EndPoints.POST_BY_USER, userName)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PostDTO[].class)
        ;
        logger.info("Number of posts = " + responseBody.length);
        logger.info("Post title post1 = " + responseBody[0].getTitle());
        logger.info("User name post1 = " + responseBody[0].getAuthor().getUsername());

        for (int i = 0; i < responseBody.length; i++) {
            Assert.assertEquals("Username is not matched ", userName, responseBody[i].getAuthor().getUsername());
        }

        PostDTO[] expectedResult = {
            /*new PostDTO("test", "test body", "All Users","no", new AuthorDTO("autoapi"),false),
                new PostDTO("test2", "test body2", "All Users","no", new AuthorDTO("autoapi"),false),*/
                PostDTO.builder().title("test").body("test body").select1("All Users").uniquePost("no")
                        .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                        .build(),
                PostDTO.builder().title("test2").body("test body2").select1("All Users").uniquePost("no")
                        .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                        .build(),
        };

        Assert.assertEquals("Number of posts ", expectedResult.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < responseBody.length; i++) {
            softAssertions.assertThat(responseBody[i]).isEqualToIgnoringGivenFields(expectedResult[i], "id", "createdDate", "author");
            softAssertions.assertThat(responseBody[i].getAuthor()).isEqualToIgnoringGivenFields(expectedResult[i].getAuthor(), "avatar");
        }

        softAssertions.assertAll();

    }


    @Test
    public void getAllPostsByUserNegative(){
        String actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                .when()
                        .get(EndPoints.POST_BY_USER, "notValidUser")
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();

        Assert.assertEquals("Message in response","\"Sorry, invalid user requested.undefined\"", actualResponse);
        Assert.assertEquals("Message in response","Sorry, invalid user requested.undefined", actualResponse.replace("\"",""));

    }

    @Test
    public void getAllPostsByUserPath(){
        Response actualResponse =
            given()
                    .contentType(ContentType.JSON)
                    .log().all()
            .when()
                    .get(EndPoints.POST_BY_USER, userName)
            .then()
                    .statusCode(200)
                    .log().all()
                    .extract().response();

        List<String> actualTitleList = actualResponse.jsonPath().getList("title", String.class);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < actualTitleList.size(); i++) {
            softAssertions.assertThat(actualTitleList.get(i)).as("Item number " +i).contains("test");

        }


        List<Map> actualAuthorList = actualResponse.jsonPath().getList("author", Map.class);
        for (int i = 0; i < actualAuthorList.size(); i++) {
            softAssertions.assertThat(actualAuthorList.get(i).get("username")).as("Item number " + i).isEqualTo(userName);
        }


        softAssertions.assertAll();

    }

    @Test
    public void getAllPostsByUserSchema() {
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPoints.POST_BY_USER, userName)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .assertThat().body(matchesJsonSchemaInClasspath("response.json"));

    }


}
