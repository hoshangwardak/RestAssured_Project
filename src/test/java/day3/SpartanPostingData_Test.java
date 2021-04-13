package day3;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import utils.ExcelReader;
import utils.SpartansNoAuthBaseTest;
import utils.Utility;

import java.io.File;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testing adding data to Spartan app multiple way")
public class SpartanPostingData_Test extends SpartansNoAuthBaseTest {

    @DisplayName("POST spartans with String")
    @Test
    public void testPostDataWithStringBody() {

        /*
        {
        "id": 188,
        "name": "Abigale",
        "gender": "Female",
        "phone": 12345678913
    }
         */

        String body = "{\n" +
                "        \"name\": \"Abigale\",\n" +
                "        \"gender\": \"Female\",\n" +
                "        \"phone\": 12345678913\n" +
                "    }";

        given()
                .log().all()
                .body(body)
                //.header("Content-Type","application/json")
                //.contentType("application/json")
                .contentType(ContentType.JSON)
        .when()
                .post("spartans")
        .then()
                .log().all()
                .statusCode( is(201))
                .contentType(ContentType.JSON)
                .body("success",is("A Spartan is Born!"))
                .body("data.name", is("Abigale"))
                ;
    }

    @DisplayName("POST spartans with external file")
    @Test
    public void testPostDataAsJsonFileWithBody() {

        // singleSpartan.json with below content

                /*
        {
        "name": "Abigale",
        "gender": "Female",
        "phone": 12345678913
    }
         */

        File jsonFile = new File("singleSpartan.json");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(jsonFile)
        .when()
                .post("spartans")
        .then()
                .log().all()
                .statusCode(201)
                ;
    }

    @DisplayName("POST spartans with Map Object")
    @Test
    public void testPostDataWithMapObjectAsBody() {
/*
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name","Abigale");
        bodyMap.put("gender","Male");
        bodyMap.put("phone",9876543211l);

        System.out.println("bodyMap = " + bodyMap);

 */     // Here instead of this map I am gonna call the method from my util class
        // so that I can add random map object.

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(Utility.randomSpartanAsMapObject())
        .when()
                .post("spartans")
        .then()
                .log().all()
                .statusCode(201)
        ;
    }

    @DisplayName("POST/ spartans with POJO")
    @Test
    public void testPostDataWithPOJOAsBody() {

        Spartan spartan = new Spartan("Abigale", "Female", 18000233232L);
        // above will turn into below:
/*
        {
            "name": "Abigale",
            "gender": "Female",
            "phone": 12345678913
        }
 */
       // System.out.println(spartan);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan)
        .when()
                .post("spartans")
        .then()
                .log().all()
                .statusCode(201)
                ;
    }

    @DisplayName("POST/ spartans from Excel")
    @Test
    public void addingSpartansFromExcel() {

        String path = "MOCK_DATA.xlsx";

        List<Map<String,String>> listOfMap = ExcelReader.listOfMaps(path,"data");

        for (int i=0; i<listOfMap.size(); i++) {

            given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(listOfMap.get(i))
                    .when()
                    .post("spartans")
                    .then()
                    .log().all()
                    .statusCode(201)

            ;

        }


    }



}
