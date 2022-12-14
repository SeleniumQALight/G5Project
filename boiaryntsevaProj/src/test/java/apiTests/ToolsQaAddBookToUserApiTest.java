package apiTests;

import api.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;

public class ToolsQaAddBookToUserApiTest {
    ToolsQaApiHelper toolsQaApiHelper = new ToolsQaApiHelper();

    HashMap<String, String> tokenAndId = toolsQaApiHelper.getTokenAndIdWhenOtherResponseFieldsArePresent();
    String token = tokenAndId.get("token");
    String userId = tokenAndId.get("userId");

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .setAuth(oauth2(token))
            .build();

    @Before
    public void deleteBooksByUserId() {
        toolsQaApiHelper.deleteBooksByUserIdMapProvided(token, userId);
    }

    @Test
    public void addBookToUserThroughApi() {

        String bookToBeAdded =
                toolsQaApiHelper.getAllAvailableBooks().getBooks().get(0).getIsbn();

        Map<String, Object> bookToBeAddedInRequestBody = new LinkedHashMap<>();
        bookToBeAddedInRequestBody.put("userId", userId);
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

        ToolsQaUserBooksDTO actualResult = toolsQaApiHelper.getUserBooks(token, userId);

        Assert.assertEquals(actualResult.getBooks().get(0).getIsbn(), bookToBeAdded);

    }
}


