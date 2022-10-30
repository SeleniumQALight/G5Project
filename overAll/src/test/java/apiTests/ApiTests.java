package apiTests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiTests {
    String user_name = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser() {

        PostDTO[] responseBody = given()
                .contentType(ContentType.JSON)
//                .queryParam("exchange")
                .filter(new AllureRestAssured())
                .log().all()
                .when()
                .get(EndPoints.POST_BY_USER, user_name)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PostDTO[].class);

        logger.info("Number of posts = " + responseBody.length);
        logger.info("Post title post1 = " + responseBody[0].getTitle());
        logger.info("User name post1 = " + responseBody[0].getAuthor().getUsername());

        for (int i = 0; i < responseBody.length; i++) {
            Assert.assertEquals("Username is not matched ", user_name, responseBody[i].getAuthor().getUsername());
        }

        PostDTO[] expectedResult = {
//               new PostDTO("test","test body", "All Users", "no", new AuthorDTO("autoapi"), false) ,
//               new PostDTO("test2","test body2","All Users","no", new AuthorDTO("autoapi"), false)
                PostDTO.builder().title("test1").body("test body").select1("All Users").uniquePost("no")
                        .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                        .build(),
                PostDTO.builder().title("test2").body("test body2").select1("All Users").uniquePost("no")
                        .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                        .build()
        };

        Assert.assertEquals("Number of posts ", expectedResult.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedResult.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResult[i], "id", "createdDate", "author");
            softAssertions.assertThat(responseBody[i].getAuthor())
                    .isEqualToIgnoringGivenFields(expectedResult[i].getAuthor(), "avatar");
        }

        softAssertions.assertAll();


//https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11

    }

    @Test
    public void getAllPostsByUserNegative() {
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

        Assert.assertEquals("Message in response ", "\"Sorry, invalid user requested.undefined\"", actualResponse);
        Assert.assertEquals("Message in response ", "Sorry, invalid user requested.undefined",
                actualResponse.replace("\"", ""));

    }

    @Test
    public void getAllPostsByUserPath() {
        Response actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPoints.POST_BY_USER, user_name)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();

        List<String> actualTitleList = actualResponse.jsonPath().getList("title", String.class);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < actualTitleList.size(); i++) {
            softAssertions.assertThat(actualTitleList.get(i)).as("Item number " + i).contains("test");
        }


        List<Map> actualAuthorList = actualResponse.jsonPath().getList("author", Map.class);
        for (int i = 0; i < actualAuthorList.size(); i++) {
            softAssertions.assertThat(actualAuthorList.get(i).get("username"))
                    .as("Item number " + i).isEqualTo(user_name);
        }

        softAssertions.assertAll();

    }

    @Test
    public void getAllPostsByUserSchema() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.POST_BY_USER, user_name)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"))
        ;
    }


    @Test
    public void test() {
        given()
                .baseUri("http://qa-complex-app-for-testing.herokuapp.com").log().all()
                .when().get("/api/postsByAuthor/{0}");
    }


}
