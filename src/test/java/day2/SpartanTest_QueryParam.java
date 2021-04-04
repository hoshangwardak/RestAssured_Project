package day2;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.SpartansNoAuthBaseTest;

import static io.restassured.RestAssured.*;


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
