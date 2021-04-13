package day9;

import io.restassured.http.ContentType;
import org.junit.jupiter.params.provider.CsvSource;
import utils.LibraryApp_BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utils.HR_ORDS_API_BaseTest;
import pojo.Region;
import utils.HR_ORDS_API_BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.SpartanPOJO;
import utils.DB_Utility;
import utils.LibraryApp_BaseTest;
import utils.SpartansNoAuthBaseTest;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static utils.DB_Utility.*;

public class SpartanSearchTest extends SpartansNoAuthBaseTest {

    @DisplayName("Test GET/spartans/search result with DB result")
    @Test
    public void testSearch() {

        String query = "SELECT * FROM SPARTANS WHERE LOWER(NAME) LIKE '%a%' and GENDER = 'Female'";
        DB_Utility.runQuery(query);
        //displayAllData();
        int expectedCount = DB_Utility.getRowCount();
        System.out.println("rowCount = " + expectedCount);

        given()
                .queryParam("nameContains","a")
                .queryParam("gender","Female")
        .when()
                .get("spartans/search")
                .then()
                .statusCode(is(200))
                //.body("totalElement", is(equalTo(expectedCount)))
                .body("content", hasSize(expectedCount))
                ;

    }

    @ParameterizedTest
    @CsvSource({
            "e, Male",
            "le, Female",
            "k, Male",
            "g, Male",
            "u, Female",
            "f, Male"
    })
    public void testSearchParameterized(String nameArg, String genderArg) {

        String query = "SELECT * FROM SPARTANS WHERE LOWER(NAME) LIKE '%"+nameArg+"%' and GENDER = '"+genderArg+"'";
        System.out.println("query = " + query);

        DB_Utility.runQuery(query);
        //displayAllData();
        int expectedCount = DB_Utility.getRowCount();
        System.out.println("rowCount = " + expectedCount);

        given()
                .queryParam("nameContains",nameArg)
                .queryParam("gender",genderArg)
                .when()
                .get("spartans/search")
                .then()
                .statusCode(is(200))
                //.body("totalElement", is(equalTo(expectedCount)))
                .body("content", hasSize(expectedCount))
                // Optionally, continue from here and check
                // if every name contains String ignoreCase what we search for
                .body("content.name", everyItem(containsStringIgnoringCase(nameArg) ))
                // every item gender is what we search for
                .body("content.gender", everyItem(is(genderArg)))
                        ;

        ;


    }

}
