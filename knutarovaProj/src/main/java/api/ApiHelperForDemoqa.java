package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ApiHelperForDemoqa {
    public static final String USERNAME = "irynaknutarova";
    private final String PASSWORD = "12345qwertyY!";
    private String token;
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
        deleteAllBooksTillPresent(token, USERID);
    }

    public void deleteAllBooksTillPresent(String token, String UserId) {
        deleteAllBooksByUserId(token, UserId);
        logger.info(String.format("Books by username " + loginByUser().getUsername() + " was deleted"));
    }

    private void deleteAllBooksByUserId(String token, String userId) {
        String response = given()
                .spec(requestSpecification)
                .auth().oauth2(token)
                .queryParam("UserId", userId)
            .when()
                .delete(EndPointsForDemoqa.ALL_BOOKS_TO_STORE_AND_DELETE)
           .then()
                .statusCode(204)
                .log().all()
                .extract().response().getBody().asString();
    }

    public BookForBooksDTODemoqa getAllBooksInStore() {
        BookForBooksDTODemoqa responseBody = given()
                .spec(requestSpecification)
            .when()
                .get(EndPointsForDemoqa.ALL_BOOKS_TO_STORE_AND_DELETE)
            .then()
                .statusCode(200)
                .log().all()
                .extract()
            .response().as(BookForBooksDTODemoqa.class);
        return responseBody;
    }

    public BooksAddedDTOByDemoqa getNomenclatureOfBooksByUser(String userId, String token) {
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
