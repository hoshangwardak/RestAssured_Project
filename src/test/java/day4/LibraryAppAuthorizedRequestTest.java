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

import java.util.*;

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

    @DisplayName("POST /add_book")
    @Test
    public void testAddOneBook() {

    /*  These are my formParams in order to add a book
            name Awesome book
            isbn IMDBS132
            year 2019
            author Ike
            book_category_id 2
            description good book
    */
        Map<String, Object> newBook = getRandomBook();

        int newBookId =
                given()
                        .log().all()
                        .header("x-library-token", librarianToken)
                        .contentType(ContentType.URLENC)
                        // using formParams with s we can pass multiple param in one shot
                        .formParams(  newBook  ).
                when()
                        .post("add_book").
                then()
                        .log().body()
                        .statusCode(200)
                        .extract()
                        .jsonPath().getInt("book_id")
                ;

        // Send additional request to GET /get_book_by_id/{book_id}
        // to verify all data has been added correctly

        given()
                .header("x-library-token", librarianToken)
                .log().uri()
                .pathParam("book_id" , newBookId).
        when()
                .get("get_book_by_id/{book_id}").
        then()
                .log().body()
                .statusCode(200)
                .body("id" , is( newBookId+"" ) )
                .body("name",  is ( newBook.get("name") ) )
                .body("isbn",  is ( newBook.get("isbn") ) )
                .body("year",  is ( newBook.get("year")+"" ) )
                .body("author",  is ( newBook.get("author") ) )
                .body("book_category_id",  is ( newBook.get("book_category_id")+"" ) )
                .body("description",  is ( newBook.get("description") ) )
                ;
    }

}
