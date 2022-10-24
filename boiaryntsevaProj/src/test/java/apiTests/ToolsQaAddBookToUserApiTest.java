package apiTests;

import api.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;

public class ToolsQaAddBookToUserApiTest {
    ToolsQaApiHelper toolsQaApiHelper = new ToolsQaApiHelper();
    String token = toolsQaApiHelper.getTokenAndIdWhenOtherResponseFieldsArePresent().get("token");
    String userId = toolsQaApiHelper.getTokenAndIdWhenOtherResponseFieldsArePresent().get("userId");

    RequestSpecification requestSpecification =  new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .setAuth(oauth2(token))
            .build();

    @Before
    public void deleteBooksByUserId(){
        toolsQaApiHelper.deleteBooksByUserId();
    }

    @Test
    public void addBookToUserThroughApi (){
        ToolsQABookListDTO listOfBooksResponse= given()
                .spec(requestSpecification)
                .when()
                .get(ToolsQABookEndpoints.BOOKS_LIST)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(ToolsQABookListDTO.class);

                String bookToBeAdded = listOfBooksResponse.getBooks().get(0).getIsbn();

        Map<String, Object> bookToBeAddedInRequestBody = new LinkedHashMap<>();
        bookToBeAddedInRequestBody.put("userId",userId );
        bookToBeAddedInRequestBody.put("collectionOfIsbns", Arrays.asList(new LinkedHashMap<String, Object>() {
            {
                put("isbn", bookToBeAdded);
            }
        }));

                given()
                        .spec(requestSpecification)
                        .body(bookToBeAddedInRequestBody)
                        .when()
                        .post(ToolsQABookEndpoints.BOOKS_LIST)
                        .then()
                        .statusCode(201)
                        .log().all();

        ToolsQaUserBooksDTO actualResult = given()
                .spec(requestSpecification)
                .when()
                .get(ToolsQABookEndpoints.USER_BOOKS, userId)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(ToolsQaUserBooksDTO.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualResult.getBooks().get(0).getIsbn()).isEqualTo(bookToBeAdded);

        softAssertions.assertAll();
    }
}


