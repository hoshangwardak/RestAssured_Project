package day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.SpartansNoAuthBaseTest;

import java.util.ArrayList;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanJsonPath_Test extends SpartansNoAuthBaseTest {

    @Test
    public void testOne() {

       Response response =
               given()
                       .log().all()
                       .pathParam("spartan_id",180)
                       .when()
                       .get("spartans/{spartan_id}")
                       .prettyPeek()
                ;
       // Extracting data through path method
       int myId = response.path("id");
        System.out.println("myId = " + myId);

        // Extracting data through jsonPath

        JsonPath jp = response.jsonPath();
        int id = jp.getInt("id");
        System.out.println("id = " + id);
        
        long phoneNumber = jp.getLong("phone");
        System.out.println("phoneNumber = " + phoneNumber);

        Map<String, Object> payloadData = jp.getMap("");
        System.out.println("payloadData = " + payloadData);
    }

    @DisplayName("Extract data from GET/ Spartans")
    @Test
    public void getAllSpartans() {

        //Response response = get("spartans");
        //JsonPath jsonPath = response.jsonPath();
        
        JsonPath jsonPath = get("spartans").jsonPath();
        
        int id = jsonPath.getInt("[0].id");
        System.out.println("id = " + id);

        String name = jsonPath.getString("[1].name");
        System.out.println("name = " + name);

        Map<String, Object> mapJsonArray = jsonPath.getMap("[0]");
        System.out.println("jsonArray = " + mapJsonArray);

        // We can do this way as well but line 56 makes much more sense
        //System.out.println("jsonPath.getInt(\"id[0]\") = " + jsonPath.getInt("id[0]"));

    }

    @DisplayName("Extract data from GET spartans/search")
    @Test
    public void testGetSearchSpartans() {

    JsonPath jsonPath =
            given()
                        .queryParam("nameContains","S")
                        .queryParam("gender","Male")
                        .log().all()
                .when()
                        .get("spartans/search")
                        .prettyPeek()
                        .jsonPath()
                        ;

        int id = jsonPath.getInt("content[0].id");
        System.out.println("id = " + id);

        String name = jsonPath.getString("content[0].name");
        System.out.println("name = " + name);

        String gender = jsonPath.getString("content[0].gender");
        System.out.println("gender = " + gender);

        long phone = jsonPath.getLong("content[0].phone");
        System.out.println("phone = " + phone);

        // storing first jsonObject into a map
        Map<String, Object> firstJsonObjectInMap = jsonPath.getJsonObject("content[0]");
        System.out.println("firstJsonObjectInMap = " + firstJsonObjectInMap);




    }


}
