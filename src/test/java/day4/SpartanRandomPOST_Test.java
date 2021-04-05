package day4;

import groovy.transform.ToString;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import utils.SpartansNoAuthBaseTest;
import utils.Utility;

import javax.xml.ws.Response;
import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.*;
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


}
