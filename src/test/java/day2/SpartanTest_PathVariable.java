package day2;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.SpartansNoAuthBaseTest;

import static io.restassured.RestAssured.*;

public class SpartanTest_PathVariable extends SpartansNoAuthBaseTest {


    @DisplayName("Path Variable")
    @Test
    public void test1() {

       Response response =  get("spartans/16").prettyPeek();

       System.out.println("====================================");

       given()
               .pathParam("id",16)
               .header("Accpet","application/json")
       .when()
               .get("spartans/{id}")
                . prettyPrint()
               ;

        System.out.println("=====================================");

        given()
                .accept("application/json"). // This is similar to .header("Accept","application/json")
        when()
                .get("spartans/{id}", 16)
                .prettyPrint()
                ;







    }


}
