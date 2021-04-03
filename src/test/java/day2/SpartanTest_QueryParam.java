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


public class SpartanTest_QueryParam extends SpartansNoAuthBaseTest {

    @DisplayName("Test GET/ spartans/search endpoint")
    @Test
    public void testSearch() {

        Response response =
                given()
                .log().all()
                .queryParam("nameContains","B")
                .queryParam("gender","Male")
                .when()
                .get("spartans/search")
                .prettyPeek()
                ;
        // printing the number of elements
        System.out.println("Total Elements = " + response.path("totalElement"));

    }


}
