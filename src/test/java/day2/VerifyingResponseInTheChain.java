package day2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.SpartansNoAuthBaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class VerifyingResponseInTheChain extends SpartansNoAuthBaseTest {

    @DisplayName("Verifying the GET spartans/{id} response directly in the chain")
    @Test
    public void testOneSpartanInOneShot() {

        given()
                .log().all()
                .pathParam("id", 16)
                .when()
                .get("spartans/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .header("Content-Type",is("application/json"))
                .contentType("application/json")
                .body("id", equalTo(16))
                .body("name", equalTo("Sinclair"))
                .body("gender", is("Male"))
                .body("phone", is(equalTo(9714460354l)))
                ;

    }

    @DisplayName("All different Logging Options")
    @Test
    public void testOneSpartanLogRequestAndResponse(){

        given()
                .log().all()
                //      .log().uri()  // just for the request url
                //      .log().body()   // for logging request body
                //      .log().params() // logging only request parameters
                //      .log().method() // just log the http method
                //      .log().ifValidationFails() // only log the request if validation in then section has failed
                .pathParam("id",16).
                when()
                .get("/spartans/{id}").
                then()
//                .log().all() // this will log the response
//                .log().body()
//                .log().ifValidationFails()
//                .log().status()
//                .log().headers()
//                .log().ifError() // anything not 2xx status is seen as error for this method
//                .log().ifStatusCodeIsEqualTo(200)
                .statusCode(200) ;

    }


}
