package apiTests;

import api.Endpoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ApiTest {

    String userName="autoapi";
    @Test
    public void getAllPostsByUser(){
                given()
                          .log().all()
                             .contentType(ContentType.JSON)
                                .when()
                                    .log().all()
                                       .get(Endpoints.POST_BY_USER,userName)
                                         .then()
                        .statusCode(200);

    }
}
