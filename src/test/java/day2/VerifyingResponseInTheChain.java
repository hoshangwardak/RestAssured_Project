package day2;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.SpartansNoAuthBaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
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


}
