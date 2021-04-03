package day2;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.SpartansNoAuthBaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanTest_PathVariable extends SpartansNoAuthBaseTest {


    @DisplayName("Path Variable")
    @Test
    public void test1() {

       Response response =  get("spartans/16").prettyPeek();

       System.out.println("====================================");

       Response r1 = given()
               .pathParam("id",16)
               .header("Accpet","application/json")
       .when()
               .get("spartans/{id}")
                . prettyPeek()
               ;

        System.out.println("=====================================");

        Response r2 = given()
                .accept("application/json"). // This is similar to .header("Accept","application/json")
        when()
                .get("spartans/{id}", 16)
                .prettyPeek()
                ;
    }

    @DisplayName("Logging the request")
    @Test
    public void getOneSpartanWithLog() {

        Response response =
                given()
                        .log().all()
                        .pathParam("id",16)
                        .accept(ContentType.JSON).
                when()

                        .get("spartans/{id}")
                        .prettyPeek()
                ;

        assertThat(response.statusCode(), is(equalTo(200)));
        assertThat(response.contentType(), is(equalTo("application/json")));
        assertThat(response.path("name"), is(equalTo("Sinclair")));



    }


}
