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
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanPost_NegativeTest extends SpartansNoAuthBaseTest {

    Spartan spartan = Utility.randomSpartanAsPOJOObject();

    @DisplayName("Post request without content type expect 415")
    @Test
    public void test1(){

        // 415 No Content Type

        given()
                .log().all()
                .body(spartan)
        .when()
                .post("spartans")
        .then()
                .log().all()
                .statusCode(415)
                ;

    }

    @DisplayName("Post request without valid json expect 400")
    @Test
    public void test2(){

        // 400 Bad Data Type
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("BAD JSON STRUCTURE HERE")
        .when()
                .post("spartans")
        .then()
                .log().all()
                .statusCode(400)
                ;
    }

    @DisplayName("Post request with valid json , bad name - expect 400 with detailed name error message")
    @Test
    public void test3(){

        // here we are providing wrong data. It is Json but instead of name character is 1
        // so we should get 400 with a message stating Invalid input.
        Spartan spartan = new Spartan("1","Male",1234567891L);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan)
        .when()
                .post("spartans")
        .then()
                .log().all()
                .statusCode(400)
                .body("message", is("Invalid Input!"))
                .body("errorCount", equalTo(1))
                .body("errors[0].reason", is("name should be at least 2 character and max 15 character"))

                ;
    }

    @DisplayName("Post request with bad name, phone - expect 400 with detailed name, phone error message")
    @Test
    public void test4(){

    }


    @DisplayName("Post request with bad name, phone , gender - expect 400 with 3 errors")
    @Test
    public void test5(){

    }
}
