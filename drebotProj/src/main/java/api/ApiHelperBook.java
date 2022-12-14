package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelperBook {

    public final String USER_NAME = "katedr";
    private final String PASSWORD = "123456Qwerty!";

    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public UserInfoDTO getUserInfoAfterLogin() {
        return getUserInfoAfterLogin(USER_NAME, PASSWORD);
    }

    public UserInfoDTO getUserInfoAfterLogin(String userName, String password) {

        JSONObject bodyParam = new JSONObject();
        bodyParam.put("userName", userName);
        bodyParam.put("password", password);

        UserInfoDTO responseBody =
                given()
                        .spec(requestSpecification)
                        .body(bodyParam.toMap())

                        .when()
                        .post(EndPointsBook.LOGIN)

                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().as(UserInfoDTO.class);

        Assert.assertEquals("User name ", userName, responseBody.getUserName());
        Assert.assertEquals("Password ", password, responseBody.getPassword());

        return responseBody;
    }

//    public void deleteAllBooksByUser() {
//        deleteAllBooksByUser(USER_NAME, PASSWORD);
//    }

    public void deleteAllBooksByUser(String userId, String token) {

        given()
                .spec(requestSpecification)
                .queryParam("UserId", userId)
                .auth().oauth2(token)

                .when()
                .delete(EndPointsBook.LIST_OF_ALL_BOOKS)

                .then()
                .statusCode(204)
                .log().all();

        logger.info("\nall books was deleted \n");

        ProfileDTO profileDTO = getProfileInfo(userId, token);

        Assert.assertEquals("count of books ", 0, profileDTO.getBooks().length);
        Assert.assertEquals("user name  ", USER_NAME, profileDTO.getUserName());

    }

    public ProfileDTO getProfileInfo(String userId, String token) {

        ProfileDTO profileDTO = given()
                .spec(requestSpecification)
                .auth().oauth2(token)

                .when()
                .get(EndPointsBook.PROFILE_INFO, userId)

                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(ProfileDTO.class);

        Assert.assertEquals("user name ", USER_NAME, profileDTO.getUserName());

        return profileDTO;

    }


    public BooksDTO getAllBooks() {

        return given()
                .spec(requestSpecification)

                .when()
                .get(EndPointsBook.LIST_OF_ALL_BOOKS)

                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(BooksDTO.class);
    }

    public void addBookToUser(String userId, String token, String isbn) {

        Map<String, Object> bodyParam = new HashMap<String, Object>();
        bodyParam.put("userId", userId);
        List<Map> listIsbn = new ArrayList<>();
        Map<String, String> mapIsbn = new HashMap<>();
        mapIsbn.put("isbn", isbn);
        listIsbn.add(0, mapIsbn);
        bodyParam.put("collectionOfIsbns", listIsbn);

        BookIsbnDTO responseAddBook = given()
                .spec(requestSpecification)
                .auth().oauth2(token)
                .body(bodyParam)

                .when()
                .post(EndPointsBook.LIST_OF_ALL_BOOKS)

                .then()
                .statusCode(201)
                .log().all()
                .extract().response().as(BookIsbnDTO.class);

        Assert.assertEquals("Isbn ", isbn, responseAddBook.getBooks()[0].getIsbn());

    }

    public void deleteDetermineBook(String isbn, String userId, String token) {

        JSONObject bodyParam = new JSONObject();
        bodyParam.put("isbn", isbn);
        bodyParam.put("userId", userId);

        given()
                .spec(requestSpecification)
                .body(bodyParam.toMap())
                .auth().oauth2(token)

                .when()
                .delete(EndPointsBook.DELETE_DETERMINE_BOOK)

                .then()
                .statusCode(204)
                .log().all();


    }
}
