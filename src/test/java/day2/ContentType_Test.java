package day2;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.SpartansNoAuthBaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ContentType_Test extends SpartansNoAuthBaseTest {

    @DisplayName("GET /hello in text")
    @Test
    public void testHelloContentType() {

        when()
                .get("hello")
                .then()
                .contentType(ContentType.TEXT)
                .body( is("Hello from Sparta") )
                ;
    }

    @DisplayName("GET /spartans in json")
    @Test
    public void testSpartansJsonContentType() {

        given()
                .accept(ContentType.JSON)
        .when()
                .get("spartans")
        .then()
                .contentType(ContentType.JSON)
                ;


    }
    @DisplayName("GET /spartans in xml")
    @Test
    public void testSpartansXmlContentType() {

        given()
                .accept(ContentType.XML)
                .when()
                .get("spartans")
                .then()
                .contentType(ContentType.XML)
                ;

    }

}
