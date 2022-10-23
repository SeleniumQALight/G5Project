package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BookApiHelper {

    public static final String USER_NAME = "testl321";
    private final String PASSWORD = "Test-!@#$%12345";

    Logger logger = Logger.getLogger(getClass());

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

        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                        .when()
                        .post(BookEndPoints.LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody();

        JSONObject usedIdAndToken = new JSONObject();
        usedIdAndToken.put("userId", responseBody.as(BookLoginDTO.class).getUserId());
        usedIdAndToken.put("token", responseBody.as(BookLoginDTO.class).getToken());

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

    public String getIsbnForFirstBook(){
        return getAllBooksInStore().getBooks()[0].getIsbn();
    }

    public BookListDTO getAllBooksInStore() {
        return given()
                .spec(requestSpecification)
        .when()
                .get(BookEndPoints.WORK_WITH_BOOKS)
        .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(BookListDTO.class);

    }

    public void addBookToUser(String token, String userId, String isbn){
        JSONObject bodyMainParams = new JSONObject();
        bodyMainParams.put("userId", userId);

        JSONObject bodyIsbnParams = new JSONObject();
        bodyIsbnParams.put("isbn", isbn);

        List <JSONObject> listOfIsbns = new ArrayList<>();
        listOfIsbns.add(bodyIsbnParams);
        bodyMainParams.put("collectionOfIsbns", listOfIsbns);

        given()
                .spec(requestSpecification)
                .auth()
                .oauth2(token)
                .body(bodyMainParams.toMap())
        .when()
                .post(BookEndPoints.WORK_WITH_BOOKS)
        .then()
                .statusCode(201)
                .log().all();

    }

}
