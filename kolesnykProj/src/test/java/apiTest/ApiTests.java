package apiTest;

import api.EndPoints;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    String username =  "autoapi";

    @Test
    public void getAllPostByUser(){

        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.POST_BY_USER, username)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all();
    }
}
