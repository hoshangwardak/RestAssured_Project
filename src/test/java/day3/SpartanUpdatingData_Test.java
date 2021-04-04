package day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import utils.SpartansNoAuthBaseTest;
import utils.Utility;

import javax.xml.ws.Response;
import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanUpdatingData_Test extends SpartansNoAuthBaseTest {

    // You may repeat everything we did previously in post test for providing body
    // We will just look at Map

    @DisplayName("PUT/ spartans/{id}")
    @Test
    public void testUpdateDataWithMapObject() {


        given()
                .log().all()
                .pathParam("id",33)
                .contentType(ContentType.JSON)
                .body(Utility.randomSpartanAsMapObject())
        .when()
                .put("spartans/{id}")
        .then()
                .statusCode(204)
                ;
    }

    @DisplayName("PUT/ spartans/{id} as POJO")
    @Test
    public void testUpdateDataWithPOJO() {

        given()
                .log().all()
                .pathParam("id",33)
                .contentType(ContentType.JSON)
                .body(Utility.randomSpartanAsPOJOObject())
        .when()
                .put("spartans/{id}")
        .then()
                .statusCode(204)
        ;
    }

    @DisplayName("PATCH/ spartans/{id} as String")
    @Test
    public void testPartialUpdateWithString() {



    }



}
