package apiTests;

import api.ApiHelper;
import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class CreatePostByApi {
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deletePostsTillPresent() {
        apiHelper.deletePostsTillPresent();
    }

    @Test
    public void createPostByApi() {
        String token = apiHelper.getToken();

        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("title", "New post from Api");
        requestParams.put("body", "post body");
        requestParams.put("select1", "One Person");
        requestParams.put("uniquePost", "yes");
        requestParams.put("token", token);

        String response =
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(requestParams)
        .when()
                .post(EndPoints.CREATE_POST)
        .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().asString();

        Assert.assertEquals("Message ", "\"Congrats.\"", response);


        PostDTO[] actualPostDTO = apiHelper.getAllPostsByUser();
        Assert.assertEquals("Number of posts ", 1, actualPostDTO.length);

        PostDTO[] expectedPostDTO = {
                PostDTO.builder()
                        .title(requestParams.get("title"))
                        .body(requestParams.get("body"))
                        .select1(requestParams.get("select1"))
                        .uniquePost(requestParams.get("uniquePost"))
                        .author(AuthorDTO.builder().username(TestData.USER_NAME).build())
                        .build()
        };

        SoftAssertions softAssertions = new SoftAssertions();

        /*
        успішна спроба замінити deprecated метод [isEqualToIgnoringGivenFields] на новий [usingRecursiveComparison]
        посилання - https://www.javadoc.io/doc/org.assertj/assertj-core/latest/org/assertj/core/api/AbstractObjectAssert.html#isEqualToIgnoringGivenFields(java.lang.Object,java.lang.String...)
        */
        softAssertions.assertThat(actualPostDTO[0])
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "isVisitorOwner", "author.avatar")
                .isEqualTo(expectedPostDTO[0]);

//            softAssertions.assertThat(actualPostDTO[0])
//                    .isEqualToIgnoringGivenFields(expectedPostDTO[0],  "id", "createdDate", "author", "isVisitorOwner");
//            softAssertions.assertThat(actualPostDTO[0].getAuthor())
//                    .isEqualToIgnoringGivenFields(expectedPostDTO[0].getAuthor(), "avatar");

        softAssertions.assertAll();
    }
}
