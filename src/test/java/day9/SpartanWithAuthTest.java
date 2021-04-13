package day9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.SpartansWithAuthBaseTest;
import io.restassured.http.ContentType;
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

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static utils.DB_Utility.*;


public class SpartanWithAuthTest extends SpartansWithAuthBaseTest {

    @DisplayName("GET /spartans as public user expect 401")
    @Test
    public void testPublicUser(){

        when()
                .get("/spartans").
                then()
                .statusCode(401)
                .log().all();


    }

    @DisplayName("GET /spartans as admin user expect 200")
    @Test
    public void testAdmin(){

        given()
                .auth().basic("admin","admin").
                when()
                .get("/spartans").
                then()
                .log().headers()
                .statusCode(200) ;


    }


    @DisplayName("DELETE /spartans/{id} as editor user expect 403")
    @Test
    public void testEditor(){

        given()
                .pathParam("id", 100)
                .auth().basic("editor", "editor").
                when()
                .delete("/spartans/{id}").
                then()
                .log().all()
                .statusCode(403)
        ;
    }

    // As a homework ,write a detailed test for Role base access control (RBAC)
    /*
    in Spartan App with auth
     Admin should be able to take all CRUD
     Editor should be able to take all CRUD
        other than Delete
     User Should be able to only Read Data
        Should not be able to add , update , delete
     +

     */



}
