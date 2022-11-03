package apiTests;

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

public class ApiTests {
    String user_name = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser() {

        PostDTO[] responseBody = //респонс разбивается на массивы
                given()//подготовка
                        .contentType(ContentType.JSON)
                        .filter(new AllureRestAssured())//в allur-report добавляются степы
                        .log().all()//увитеть запрос

                        .when()//сам запрос
                        .get(EndPoints.POST_BY_USER, user_name)

                        .then()//проверка
                        .statusCode(200)
                        .log().all() //то что он получает,respons
                        .extract()//достань респонс
                        .response().as(PostDTO[].class)//возьми описание в этом классе и создай описание
                ;
        logger.info("Number of posts = " + responseBody.length);
        logger.info("post title post1 = " + responseBody[0].getTitle());
        logger.info("User Name = " + responseBody[0].getAuthor().getUsername());

        for (int i = 0; i < responseBody.length; i++) {
            Assert.assertEquals("User Name is not matched", user_name, responseBody[i].getAuthor().getUsername());
        }

        PostDTO[] expectedResult = {
//                new PostDTO("test", "test body", "All Users", "no", new AuthorDTO("autoapi"), false),
//                new PostDTO("test2", "test body2", "All Users", "no", new AuthorDTO("autoapi"), false)
                PostDTO.builder().title("test").body("test body").select1("All Users").uniquePost("no")
                        .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                        .build(),
                PostDTO.builder().title("test2").body("test body2").select1("All Users").uniquePost("no")
                        .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                        .build(),
        };

        Assert.assertEquals("Number of post ", expectedResult.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedResult.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResult[i], "id", "createdDate", "author");
            softAssertions.assertThat(responseBody[i].getAuthor())
                    .isEqualToIgnoringGivenFields(expectedResult[i].getAuthor(), "avatar");
        }

        softAssertions.assertAll();
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
        Assert.assertEquals("Message in response", "\"Sorry, invalid user requested.undefined\"", actualResponse);
        Assert.assertEquals("Message in response", "Sorry, invalid user requested.undefined", actualResponse.replace("\"", ""));
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
            softAssertions.assertThat(actualTitleList.get(i)).as("item number " + i).contains("test");
        }


        List<Map> actualAuthorList = actualResponse.jsonPath().getList("author", Map.class);
        for (int i = 0; i < actualAuthorList.size(); i++) {
            softAssertions.assertThat(actualAuthorList.get(i).get("username"))
                    .as("Item number " + i).isEqualTo(user_name);

        }

        softAssertions.assertAll();

    }

    @Test
    public void getAllPostByUserSchema() {
        given()
                .contentType(ContentType.JSON)
                .log().all()

                .when()
                .get(EndPoints.POST_BY_USER, user_name)

                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"));
    }
}
