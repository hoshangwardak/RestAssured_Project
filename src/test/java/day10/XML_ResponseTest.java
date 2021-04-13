package day10;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.SpartansWithAuthBaseTest;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class XML_ResponseTest extends SpartansWithAuthBaseTest {

    @DisplayName("Test GET/spartans xml response")
    @Test
    public void testXML() {

        // provide credentials and provide header as xml and send request
        // assert status code of 200
        // assert content type is xml
        // assert first data name is Meade

        given()
                .auth().basic("admin","admin")
                .accept(ContentType.XML)
        .when()
                .get("spartans")
        .then()
                .log().all()
                .statusCode(is(200))
                .contentType(ContentType.XML)
                .body("List.item[0].name", is("Meade"))
                .body("List.item[0].id", is("1"))
                ;
    }

    @DisplayName("Test GET/spartans xml response with xmlPath")
    @Test
    public void testXML_extractData() {

        Response response = given()
                .auth().basic("admin","admin")
                .accept(ContentType.XML)
                .when()
                .get("spartans")
                ;

        XmlPath xp = response.htmlPath();

        int firstId = xp.getInt("List.item[0].id");
        System.out.println("firstId = " + firstId);

        String firstName = xp.getString("List.item[0].name");
        System.out.println("name = " + firstName);

        String gender = xp.getString("List.item[0].gender");
        System.out.println("gender = " + gender);

        long phone = xp.getLong("List.item[0].phone");
        System.out.println("phone = " + phone);

        long phone2 = xp.getLong("List.item[2].phone");
        System.out.println("phone2 = " + phone2);

        // get all Ids into String
        List<Integer> allIds = xp.getList("List.item.id");
        System.out.println("allIds = " + allIds);

        assertAll(
                ()-> assertEquals(1, firstId),
                ()-> assertEquals("Meade", firstName),
                ()-> assertEquals(9994128232l, phone),
                ()-> assertEquals(108, allIds.size())
        );

    }

}
