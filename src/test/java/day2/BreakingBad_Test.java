package day2;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.BreakingBadAPI_BaseTest;

import static io.restassured.RestAssured.*;

public class BreakingBad_Test extends BreakingBadAPI_BaseTest {

    @Test
    public void testFilterCharacterName() {
        given()
                .log().uri()
                .queryParam("name","Walter")
                .when()
                .get("characters")
                .then()
                .log().all()
                //.and() // This is just for readability, not much of
                //.assertThat() // This is coming from RestAssured. It does not take any parameter and is just for readable
                .statusCode(200)
                .contentType("application/json; charset=utf-8")

                ;
    }

    @DisplayName("Test GET characters/{char_id}")
    @Test
    public void test1Character() {

        given()
                .log().uri()
                .pathParam("char_id", 1)
                .when()
                .get("characters/{char_id}")
                .then()
                .log().all()
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                .contentType("application/json; charset=utf-8")
                ;

    }

    @DisplayName("Testing episode #60 ")
    @Test
    public void test1Episode() {

        given()
                .log().all()
                .pathParam("episode_id",60)
                .when()
                .get("episodes/{episode_id}")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                ;


    }



}
