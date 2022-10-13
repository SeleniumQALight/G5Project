package apiTests;

import api.EndPoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ApiTests {
    String userName="autoapi";

    @Test
    public void getAllPostsByUser(){
        given()
                    .contentType(ContentType.JSON)
                    .log().all()
                .when()
                    .get(POST_BY_USER, userName)
                .then()
                    .statusCode(200)
                    .log().all()
                    ;
    }
}
