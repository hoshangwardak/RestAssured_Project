package day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import utils.ConfigurationReader;
import utils.SpartansNoAuthBaseTest;
import utils.Utility;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import utils.LibraryApp_BaseTest;

public class LibraryAppAuthorizedRequestTest extends LibraryApp_BaseTest {

    @DisplayName("GET /get_user_by_id/366")
    @Test
    public void testOneUser() {

        //System.out.println("myToken = " + myToken);
        given()
                .log().all()
                .header("x-library-token",librarianToken)
                .pathParam("id","1")
        .when()
                .get("get_user_by_id/{id}")
        .then()
                .log().all()
                .statusCode(is(200))
                .body("id", is("1"))
                ;
    }

    @DisplayName("GET /get_all_users")
    @Test
    public void testGetAllUsers() {

    List<String> allLibrarianNames =
        given()
                .header("x-library-token",librarianToken)
        .when()
                .get("get_all_users")
        .then()
                //.log().all()
                .statusCode(is(200))
                .extract()
                .jsonPath()
                .getList("name",String.class)
               ;

        // assert the size is 8778
        assertThat(allLibrarianNames.size(), is(8778));

        // print the unique names count
        Set<String> uniqueNames = new HashSet<>( allLibrarianNames );
        System.out.println("uniqueNames = " + uniqueNames.size());
    }




}
