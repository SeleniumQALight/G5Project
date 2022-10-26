package api.BookHW;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.*;

public class ApiHelperBooks {
    public final String USER_NAME_DEFAULT = "StanLeeReader";
    public final String USER_PASS_DEFAULT = "Qwerty123@";

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public UserDTO login(){
        return login(USER_NAME_DEFAULT, USER_PASS_DEFAULT);
    }


    public UserDTO login(String userName, String userPass){
        JSONObject requestParams = new JSONObject();
        requestParams.put("password", userPass);
        requestParams.put("userName", userName);

        UserDTO responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                .when()
                        .post(EndPointBook.LOGIN)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().as(UserDTO.class);

        return responseBody;
    }

    public void deleteBooksByUserId( String userId, String token) {
         given()
                .spec(requestSpecification)
                .auth().oauth2(token)
                .queryParam("UserId", userId)
         .when()
                .delete(EndPointBook.BOOKS)
         .then()
                .statusCode(204)
                .log().all()
                .extract().response().getBody().asString();
    }

    public AllBooksDTO getAllBooksFromSite(){
        return given()
                .spec(requestSpecification)
              .when()
                .get(EndPointBook.BOOKS)
              .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(AllBooksDTO.class);
    }

    public UserBooksDTO getBookByUser(String userId, String token){
        return given()
                .spec(requestSpecification)
                .auth().oauth2(token)
            .when()
                .get(EndPointBook.GET_USER_BOOKS, userId)
            .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().as(UserBooksDTO.class);
    }

}
