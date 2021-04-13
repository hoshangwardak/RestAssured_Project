package day8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.SpartanPOJO;
import utils.DB_Utility;
import utils.SpartansNoAuthBaseTest;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SpartanAPI_DB_Test extends SpartansNoAuthBaseTest {

    @Test
    public void testDB_Connection() {

        // expected data -- DB query result
        // actual   data -- api response json

        // we want to test spartan with ID of 8003 , api response json match database data
        // RUN QUERY  SELECT * FROM SPARTANS WHERE SPARTAN_ID = 8003
        int spartanIdToCheck = 2;

        DB_Utility.runQuery("SELECT * FROM SPARTANS WHERE SPARTAN_ID = 2");
        DB_Utility.displayAllData();

        Map<String,String> firstRowAsMap = DB_Utility.getRowMap(1);
        System.out.println("firstRowAsMap = " + firstRowAsMap);

        // send api request
        given()
                .pathParam("id",spartanIdToCheck)
                .log().uri()
                .when()
                .get("spartans/{id}")
                .then()
                .log().all()
                .statusCode(is(200))
                .body("id", is(spartanIdToCheck))
                .body("name",is(firstRowAsMap.get("NAME")))
                .body("gender",is(firstRowAsMap.get("GENDER")))
                .body("phone", is(Integer.parseInt(firstRowAsMap.get("PHONE"))))
        ;
    }

    @DisplayName("Test GET /spartans/{id} match DB Data with POJO")
    @Test
    public void testGetSingleSpartanResponsePOJO_MatchDB_Result(){

        int spartanIdToCheck = 2 ;

        DB_Utility.runQuery("SELECT * FROM SPARTANS WHERE SPARTAN_ID = " + spartanIdToCheck ) ;
        Map<String,String> expectedResultMap = DB_Utility.getRowMap(1) ;

        SpartanPOJO actualResultInPojo =  given()
                .pathParam("id" , spartanIdToCheck).
                        when()
                .get("/spartans/{id}")
                .as(SpartanPOJO.class) ;
        System.out.println("actualResultInPojo = " + actualResultInPojo);

        assertThat( actualResultInPojo.getId() , is (spartanIdToCheck) ) ;
        assertThat( actualResultInPojo.getName() , is ( expectedResultMap.get("NAME") )    );
        assertThat( actualResultInPojo.getGender() , is ( expectedResultMap.get("GENDER") )    );
        assertThat(actualResultInPojo.getPhone() ,  is(  Long.parseLong( expectedResultMap.get("PHONE"))   )   );
    }


    @DisplayName("Test GET /spartans/{id} match DB Data with POJO")
    @Test
    public void testGetSingleSpartanResponsePOJO_MatchDB_Result2(){

        DB_Utility.runQuery("SELECT * FROM SPARTANS") ;
        Map<String,String> expectedResultMap = DB_Utility.getRowMap(1) ;
        int spartanIdToCheck = Integer.parseInt(expectedResultMap.get("SPARTAN_ID")) ;

        SpartanPOJO actualResultInPojo =  given()
                .pathParam("id" , spartanIdToCheck).
                        when()
                .get("/spartans/{id}")
                .as(SpartanPOJO.class) ;
        System.out.println("actualResultInPojo = " + actualResultInPojo);

        assertThat( actualResultInPojo.getId() , is (spartanIdToCheck) ) ;
        assertThat( actualResultInPojo.getName() , is ( expectedResultMap.get("NAME") )    );
        assertThat( actualResultInPojo.getGender() , is ( expectedResultMap.get("GENDER") )    );
        assertThat(actualResultInPojo.getPhone() ,  is(  Long.parseLong( expectedResultMap.get("PHONE"))   )   );
    }

}
