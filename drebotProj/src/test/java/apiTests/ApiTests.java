package apiTests;

import api.EndPoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    @Test
    public void getAllPostsByUser(){
        String user_name="autoapi";
        given()//подготовка
                .contentType(ContentType.JSON)
                .log().all()//увитеть запрос
                .when()//сам запрос
                .get(EndPoints.POST_BY_USER,user_name)
                .then()//проверка
                .statusCode(200)
                .log().all() //то что он получает,respons
                ;
    }
}
