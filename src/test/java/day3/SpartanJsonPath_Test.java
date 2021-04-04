package day3;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.SpartansNoAuthBaseTest;

import java.util.List;
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

    @DisplayName("Saving json array fields into a list")
    @Test
    public void testSavingJsonArrayFieldsIntoList() {

        JsonPath jsonPath =
                given()
                        .queryParam("nameContains","P")
                        .queryParam("gender","Male")
                        .log().all()
                        .when()
                        .get("spartans/search")
                        .prettyPeek()
                        .jsonPath()
                ;

        // Save all the id into a list
        List<Integer> ids = jsonPath.getList("content.id");
        List<Integer> ids2 = jsonPath.getList("content.id",Integer.class);
        System.out.println("ids = " + ids2);

        List<String> names = jsonPath.getList("content.name");
        List<String> names2 = jsonPath.getList("content.name",String.class);
        System.out.println("names = " + names2);

        List<Long> phoneNumbers = jsonPath.getList("content.phone");
        List<Long> phoneNumbers2 = jsonPath.getList("content.phone",Long.class);
        System.out.println("phoneNumbers = " + phoneNumbers2);

        // Saving the whole spartan into a list of Map
        List<Map<String,Object>> spartans = jsonPath.getList("content");
        System.out.println("spartans = " + spartans);
    }

    @DisplayName("Get List Practice for Get spartans")
    @Test
    public void getListOutOfAllSpartans() {

        JsonPath jsonPath = get("spartans").jsonPath();

        List<Integer> allIds = jsonPath.getList("id", Integer.class);
        assertThat(allIds, hasSize(171));

        List<String> allNames = jsonPath.getList("names",String.class);
        assertThat(allNames, hasSize(171));

        List<Long> allPhoneNumbers = jsonPath.getList("phone",Long.class);
        assertThat(allNames, hasSize(171));

    }




}
