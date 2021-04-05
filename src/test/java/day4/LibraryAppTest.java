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

    @DisplayName("GET /dashboard_stats endpoint")
    @Test
    public void testDashboardNumbers() {

       // String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiMjA5MCIsImZ1bGxfbmFtZSI6IlRlc3QgTGlicmFyaWFuIDY5IiwiZW1haWwiOiJsaWJyYXJpYW42OUBsaWJyYXJ5IiwidXNlcl9ncm91cF9pZCI6IjIifSwiaWF0IjoxNjE3NTk2MTczLCJleHAiOjE2MjAxODgxNzN9.XmrfU1uypIvRy-v5_7ZevCnwcFvpk3J6udQwDxAtqh8";

        String myToken = given()
                .contentType(ContentType.URLENC)
                .formParam("email",libraryEmail)
                .formParam("password",libraryPassword)
                .when()
                .post("login")
                .path("token")
                ;

        given()
                .header("x-library-token",myToken)
        .when()
                .get("dashboard_stats")
        .then()
                .log().all()
                .statusCode(is(200))
                .body("book_count", is("2889"))
                .body("borrowed_books", is("794"))
                .body("users", is("8778"))
                ;
    }




}
