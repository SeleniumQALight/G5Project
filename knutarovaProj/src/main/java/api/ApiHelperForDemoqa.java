package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class ApiHelperForDemoqa {
    public static final String USERNAME = "irynaknutarova";
    private final String PASSWORD = "12345qwertyY!";
    private String TOKEN;
    private String USERID;
    Logger logger = Logger.getLogger(getClass());
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public UserDTODemoqa loginByUser() {
        return loginByUser(USERNAME, PASSWORD);
    }

    public UserDTODemoqa loginByUser(String username, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", username);
        requestParams.put("password", password);

        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                    .when()
                        .post(EndPointsForDemoqa.LOGIN)
                    .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody();

        return responseBody.as(UserDTODemoqa.class);
    }

    public void deleteAllBooksTillPresent() {
        deleteAllBooksTillPresent(TOKEN, USERID);
    }

    private void deleteAllBooksTillPresent(String token, String UserId) {
        token = loginByUser().getToken();
        deleteAllBooksByUserId(token, loginByUser().getUserId());
        logger.info(String.format("Books by username " + loginByUser().getUsername() + " was deleted"));
    }

    private void deleteAllBooksByUserId(String token, String userId) {
        String response = given()
                .spec(requestSpecification)
                .auth().oauth2(token)
            .when()
                .delete(EndPointsForDemoqa.DELETE_BOOKS, userId)
           .then()
                .statusCode(204)
                .log().all()
                .extract().response().getBody().asString();
    }

    public BookForBooksDTODemoqa getAllBooksInStore() {
        BookForBooksDTODemoqa responseBody = given()
                .spec(requestSpecification)
            .when()
                .get(EndPointsForDemoqa.ALL_BOOKS_TO_STORE)
            .then()
                .statusCode(200)
                .log().all()
                .extract()
            .response().as(BookForBooksDTODemoqa.class);
        return responseBody;
    }

    public BooksAddedDTOByDemoqa checkCountOfBooksByUser(String userId, String token) {
        return given()
                .spec(requestSpecification)
                .auth().oauth2(token)
            .when()
                .get(EndPointsForDemoqa.BOOKS_BY_USER, userId)
            .then()
                .statusCode(200)
                .log().all()
                .extract()
            .response().as(BooksAddedDTOByDemoqa.class);

    }

}
