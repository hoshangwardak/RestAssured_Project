package day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import utils.SpartansNoAuthBaseTest;
import utils.Utility;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanRandomPOST_Test extends SpartansNoAuthBaseTest {

    @DisplayName("POST/ spartans with Map of random data")
    @Test
    public void addOneRandomSpartanTest() {

        Map<String, Object> randomBody = Utility.randomSpartanAsMapObject();
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(randomBody)
        .when()
                .post("spartans")
        .then()
                .log().all()
                .statusCode(is(201))
                .body("data.name", is(randomBody.get("name")))
                .body("data.gender", equalTo(randomBody.get("gender")))
                .body("data.phone",is(randomBody.get("phone")))
                ;
    }

    @DisplayName("POST/ spartans with random Spartan POJO")
    @Test
    public void addOneRandomSpartanPOJOTest() {

        Spartan spartan = Utility.randomSpartanAsPOJOObject();

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan)
        .when()
                .post("spartans")
        .then()
                .log().all()
                .body("data.name", is(spartan.getName()))
                .body("data.gender", equalTo(spartan.getGender()))
                .body("data.phone", is(spartan.getPhone()))
                ;
    }

    @DisplayName("POST spartans and then GET spartans to verify the data with jsonPath() ")
    @Test
    public void testAddOneDataThenGetOneDataToVerify() {

        Spartan spartan = Utility.randomSpartanAsPOJOObject();

        Response response =
                given()
                .log().body()
                .contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .post("spartans")
                .prettyPeek()
                ;
          int myId = response.path("data.id");
        System.out.println("myId = " + myId);
        //int myId = response.jsonPath().getInt("data.id");
        assertThat(response.statusCode(), is(201));

        given()
                .log().uri()
                .pathParam("id",myId)
                .contentType(ContentType.JSON)
                .when()
                .get("spartans/{id}")
                .then()
                .log().body()
                .statusCode(200)
                .body("id",is(myId))
                .body("name", is(spartan.getName()))
                .body("gender", is(spartan.getGender()))
                .body("phone", is(spartan.getPhone()))
                ;
    }

    @DisplayName("POST spartans and then GET spartans/{id} to verify the data with Extract method ")
    @Test
    public void testAddOneDataThenGetOneDataToVerifyInTheChain() {

        Spartan spartan = Utility.randomSpartanAsPOJOObject();

        int newID = given()
                .log().body()
                .contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .post("spartans")
                .then()
                .log().body()
                .statusCode(is(201))
                .extract()
                //.path("data.id")
                .jsonPath().get("data.id")
                ;
        System.out.println("newID = " + newID);

    }







    }
