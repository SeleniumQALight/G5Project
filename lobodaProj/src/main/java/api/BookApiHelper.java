package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;


import static io.restassured.RestAssured.given;

public class BookApiHelper {

    public static final String USER_NAME = "testl321";
    private final String PASSWORD = "Test-!@#$%12345";

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    /**
     * Gettoken for default user
     * @return
     */
    public JSONObject getUserIdAndToken(){
        return getUserIdAndToken(USER_NAME, PASSWORD);
    }

    public JSONObject getUserIdAndToken (String user_name, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", user_name);
        requestParams.put("password", password);

        BookLoginDTO responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                        .when()
                        .post(BookEndPoints.LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(BookLoginDTO.class);

        JSONObject usedIdAndToken = new JSONObject();
        usedIdAndToken.put("userId", responseBody.getUserId());
        usedIdAndToken.put("token", responseBody.getToken());

        return usedIdAndToken;
    }

    public void deleteAllBooksByUserId(String token, String userId) {

        given()
                .spec(requestSpecification)
                .param("UserId", userId)
                .auth()
                .oauth2(token)
        .when()
                .delete(BookEndPoints.WORK_WITH_BOOKS)
        .then()
                .statusCode(204)
                .log().all();

    }

    public String getIsbnForFirstBookInStore(){
        return getAllBooksInStore().getBooks()[0].getIsbn();
    }

    public BookListInStoreDTO getAllBooksInStore() {
        return given()
                .spec(requestSpecification)
        .when()
                .get(BookEndPoints.WORK_WITH_BOOKS)
        .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(BookListInStoreDTO.class);

    }

    public BookListByUserDTO getAllBooksByUserId(String userId, String token) {
         return given()
                .spec(requestSpecification)
                .auth()
                .oauth2(token)
        .when()
                .get(BookEndPoints.WORK_WITH_USER,userId)
        .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(BookListByUserDTO.class);

    }

}
