package apiTests;

import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ISelect;

import static io.restassured.RestAssured.given;

public class ApiTests {
    String user_name = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser() {

        PostDTO[] responseBody = //респонс разбивается на массивы
                given()//подготовка
                        .contentType(ContentType.JSON)
                        .log().all()//увитеть запрос
                        .when()//сам запрос
                        .get(EndPoints.POST_BY_USER, user_name)
                        .then()//проверка
                        .statusCode(200)
                        .log().all() //то что он получает,respons
                        .extract()//достань респонс
                        .response().as(PostDTO[].class)//возьми описание в этом классе и создай описание
                ;
        logger.info("Number of posts = " + responseBody.length);
        logger.info("post title post1 = " + responseBody[0].getTitle());
        logger.info("User Name = " + responseBody[0].getAuthor().getUsername());

        for (int i = 0; i < responseBody.length; i++) {
            Assert.assertEquals("User Name is not matched", user_name, responseBody[i].getAuthor().getUsername());
        }

        PostDTO[] expectedResult = {
//                new PostDTO("test", "test body", "All Users", "no", new AuthorDTO("autoapi"), false),
//                new PostDTO("test2", "test body2", "All Users", "no", new AuthorDTO("autoapi"), false)
                PostDTO.builder().title("test").body("test body").select1("All Users").uniquePost("no")
                        .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                        .build(),
                PostDTO.builder().title("test2").body("test body2").select1("All Users").uniquePost("no")
                        .author(AuthorDTO.builder().username("autoapi").build()).isVisitorOwner(false)
                        .build(),
        };

        Assert.assertEquals("Number of post ",expectedResult.length, responseBody.length);

        SoftAssertions softAssertions=new SoftAssertions();

        for (int i = 0; i < expectedResult.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResult[i],"id","createdDate","author");
            softAssertions.assertThat(responseBody[i].getAuthor())
                    .isEqualToIgnoringGivenFields(expectedResult[i].getAuthor(),"avatar");
        }

        softAssertions.assertAll();
    }
}
