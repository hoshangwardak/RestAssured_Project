package day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import utils.ConfigurationReader;
import utils.LibraryApp_BaseTest;
import utils.SpartansNoAuthBaseTest;
import utils.Utility;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayName("Library App Simple Test")
public class LibraryAppTest extends LibraryApp_BaseTest {

    String libraryEmail = ConfigurationReader.getProperty("libraryUsername");
    String libraryPassword = ConfigurationReader.getProperty("libraryPassword");

    @DisplayName("test/ POST Login")
    @Test
    public void testPostLogin() {

        given()
                .log().all()
                .contentType(ContentType.URLENC) // library app contentType is URLENCODED
                .formParam("email",libraryEmail)
                .formParam("password",libraryPassword)
                .when()
                .post("login")
                .then()
                .log().all()
                .statusCode(is(200))
                .body("token", is(  not(blankOrNullString())))
        ;
    }

    @DisplayName("Test the Token, Decode Token")
    @Test
    public void testGetTokenDecodeToken() {

        String token = given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email",libraryEmail)
                .formParam("password",libraryPassword)
        .when()
                .post("login")
        .then()
                .log().all()
                .statusCode(is(200))
                .extract()
                .path("token")
                ;

        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("token",token)
        .when()
                .post("/decode")
        .then()
                .log().all()
                .statusCode(is(200))
                .body("email", is(libraryEmail))
                ;
    }




}
