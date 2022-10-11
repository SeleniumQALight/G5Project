package apiTests;

import api.EndPoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    String user_name = "autoapi";

    @Test
    public void getALLPostsByUser() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get(EndPoints.POST_BY_USER, user_name)
        .then()
                .statusCode(200)
                .log().all();
    }
}
