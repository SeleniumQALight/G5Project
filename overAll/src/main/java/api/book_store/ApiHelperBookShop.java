package api.book_store;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import api.book_store.dto.BookInStoreDTO;
import api.book_store.dto.BooksByUserDTO;
import api.book_store.dto.UserDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiHelperBookShop {
     static final String USER_NAME_DEFAULT = "test";
     static final String PASSWORD_FOR_DEFAULT_USER = "123456QwErTy!";

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public UserDTO loginByDefaultUser(){
       return given().contentType(ContentType.JSON)
                .body(Map.of("userName", USER_NAME_DEFAULT,"password", PASSWORD_FOR_DEFAULT_USER))
                .log().all()
                .when().post(EndPoints.LOGIN)
                .then()
                .statusCode(200).log().all()
                .extract().response().as(UserDTO.class);
    }


    public void deleteAllPostsForUser(String token, String userId){
        given()
                .contentType(ContentType.JSON).log().all()
                .auth().oauth2(token)
                .queryParam("UserId", userId)
                .when().delete(EndPoints.ALL_BOOKS_FOR_USER);
    }

    public BooksByUserDTO getBooksByUser(String token, String userId) {
        return given()
                .auth()
                .oauth2(token)
                .log().all()
                .when()
                .get(EndPoints.BOOKS_BY_USER, userId)
                .then()
                .statusCode(200).log().all()
                .extract().response().as(BooksByUserDTO.class);
    }

    public BookInStoreDTO getAllBooksInStore() {
        return given()
                .contentType(ContentType.JSON).log().all()
                .when().get(EndPoints.ALL_BOOKS_FOR_USER)
                .then().statusCode(200)
                .extract().response().getBody().as(BookInStoreDTO.class);
    }

    public void addBookToUserList(String token, String userId, String addedBookId) {
        given().contentType(ContentType.JSON).log().all()
                .body(Map.of("userId", userId, "collectionOfIsbns", List.of(Map.of("isbn", addedBookId))))
                .auth().oauth2(token)
                .when()
                .post(EndPoints.ALL_BOOKS_FOR_USER)
                .then().statusCode(201);
    }

    public void deleteBookFromUserList(String token, String addedBookId, String userId) {
        given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(Map.of("isbn", addedBookId , "userId", userId)).log().all()
                .when().delete(EndPoints.BOOK_FOR_USER)
                .then().statusCode(204);
    }
}
