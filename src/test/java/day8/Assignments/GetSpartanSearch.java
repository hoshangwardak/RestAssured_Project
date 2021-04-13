package day8.Assignments;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.Spartan;
import utils.DB_Utility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.SpartanPOJO;
import utils.DB_Utility;
import utils.SpartansNoAuthBaseTest;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GetSpartanSearch extends SpartansNoAuthBaseTest {

    @DisplayName("GET /spartans/search with query parameters")
    @Test
    public void spartanSearchWithQueryParam() {

        // AS HOMEWORK  GET /spartans/search
        // search for nameContains a and Female
        // compare DB Result total count with API Result total count
        // SELECT * FROM SPARTANS WHERE LOWER(NAME) LIKE '%a%' and GENDER = 'Female'
        DB_Utility.runQuery("SELECT * FROM SPARTANS WHERE LOWER(NAME) LIKE '%a%' and GENDER = 'Female'");
        List<Map<String,String>> mapOfDB = DB_Utility.getAllRowAsListOfMap();
        System.out.println("Spartans size in DB = " + mapOfDB.size());


        // Make sure all your data in json array match exact criteria above (nameContains a and Female
        // Optionally , Write a parametrized Test with multiple different search criteria
        List<SpartanPOJO> apiListOfSpartans = given()
                .log().uri()
                .queryParam("nameContains","a")
                .queryParam("gender","Female")
                .when()
                .get("spartans/search")
                .jsonPath()
                .getList("content")
                ;
        System.out.println("Spartans size in Api = " + apiListOfSpartans.size());
        assertThat(mapOfDB.size(), is(equalTo(apiListOfSpartans.size())));

        for (SpartanPOJO eachSpartan : apiListOfSpartans) {

        }




    }



}
