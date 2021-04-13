package day8;

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

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static utils.DB_Utility.*;

public class Library_API_DB_Test extends LibraryApp_BaseTest {

    // 1. set up db connection in base test

    @Test
    public void testDashboardStatsNumbers(){

        runQuery("SELECT COUNT(*) FROM books ");
//        System.out.println(  getFirstRowFirstColumn()   );
        String expectedBookCount =  getFirstRowFirstColumn()  ;

        runQuery("SELECT COUNT(*) FROM users ");
        String expectedUserCount =  getFirstRowFirstColumn()  ;

        runQuery("SELECT count(*) FROM book_borrow WHERE returned_date IS NULL ");
        String expectedBorrowedBookCount =  getFirstRowFirstColumn()  ;

        given()
                .header("X-LIBRARY-TOKEN", librarianToken).
                when()
                .get("/dashboard_stats").
                then()
                .log().body()
                .statusCode(200)
                .body("book_count" , is(expectedBookCount )  )
                .body("borrowed_books" , is(expectedBorrowedBookCount) )
                .body("users", is(expectedUserCount))
        ;
/*
{
    "book_count": "2943",
    "borrowed_books": "795",
    "users": "9057"
}
 */

        // in previous version of RestAssured the given when then style
        // was actually written in given expect when format
        // it will assert all the result and give one answer and does not fail whole thing if first one fail
        // this will show the all 3 failure in the error stacktrace
//        given()
//                .header("X-LIBRARY-TOKEN", librarianToken).
//                expect()
//                .log().body()
//                .statusCode(200)
//                .body("book_count" , is(expectedBookCount + 123 )   )
//                .body("borrowed_books" , is(expectedBorrowedBookCount + 123) )
//                .body("users", is(expectedUserCount + 123 )).
//                when()
//                .get("/dashboard_stats")
//
//        ;

        // if you wanted do this separately after getting response object like day 1
        // and see all your assertion result no matter any of them failed or not
        // here is how
        // https://junit.org/junit5/docs/current/user-guide/#writing-tests-assertions

    }
}
