package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ToolsQaApiHelper {
    public static final String USER_NAME = "boiaryntseva";
    private final String PASSWORD_BOOKS = "123Demo!";
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public HashMap<String, String> getTokenAndIdWhenOtherResponseFieldsArePresent() {
        return getTokenAndIdWhenOtherResponseFieldsArePresent(USER_NAME, PASSWORD_BOOKS);
    }

    public HashMap<String, String> getTokenAndIdWhenOtherResponseFieldsArePresent(String user_name, String password) {
        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("userName", user_name);
        requestParams.put("password", password);

        Response actualResponse = given()
                .spec(requestSpecification)
                .body(requestParams)
                .when()
                .post(ToolsQABookEndpoints.LOGIN)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();

        HashMap<String, String> tokenAndId = new HashMap<>();
        tokenAndId.put("token", actualResponse.jsonPath().get("token"));
        tokenAndId.put("userId", actualResponse.jsonPath().get("userId"));
        logger.info("Token and id were saved");
        return tokenAndId;
    }
//    public void deleteBooksByUserId(){
//        String userId = getTokenAndIdWhenOtherResponseFieldsArePresent().get("userId");
//        String token = getTokenAndIdWhenOtherResponseFieldsArePresent().get("token");
//
//        given()
//                .spec(requestSpecification)
//                .auth().oauth2(token)
//                .queryParams("UserId", userId)
//                .when()
//                .delete(ToolsQABookEndpoints.BOOKS_LIST)
//                .then()
//                .statusCode(204)
//                .log().all();
//    }

    public void deleteBooksByUserIdMapProvided(String token, String id) {

        given()
                .spec(requestSpecification)
                .auth().oauth2(token)
                .queryParams("UserId", id)
                .when()
                .delete(ToolsQABookEndpoints.BOOKS_LIST)
                .then()
                .statusCode(204)
                .log().all();
    }

    public ToolsQABookListDTO getAllAvailableBooks() {
        ToolsQABookListDTO listOfAvailableBooks = given()
                .spec(requestSpecification)
                .when()
                .get(ToolsQABookEndpoints.BOOKS_LIST)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(ToolsQABookListDTO.class);
        return listOfAvailableBooks;
    }

    public ToolsQaUserBooksDTO getUserBooks(String token, String userId) {
        ToolsQaUserBooksDTO listOfUserBooks = given()
                .spec(requestSpecification)
                .auth().oauth2(token)
                .when()
                .get(ToolsQABookEndpoints.USER_BOOKS, userId)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(ToolsQaUserBooksDTO.class);
        return listOfUserBooks;
    }


}
