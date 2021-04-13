package day9;

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

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static utils.DB_Utility.*;

public class Library_API_DB_Post_Test extends LibraryApp_BaseTest {

    // Add a random book using POST /add_book
    // grab the id and write a query to get book information with this id
    // assert all data match exactly as it was posted

    @DisplayName("Add one book, Validate from DB")
    @Test
    public void testAddBookPersisted() {

        Map<String,Object> randomBookMapBody = getRandomBook() ;
        System.out.println("randomBookMapBody = " + randomBookMapBody);

        int newBookId =  given()
                .header("X-LIBRARY-TOKEN" , librarianToken)
                .contentType(ContentType.URLENC)
                .formParams(randomBookMapBody).
                        when()
                .post("/add_book").
                        then()
                // normally it return 201 , this one decided to return 200
                .statusCode(200)
                .log().body()
                .extract()
                .jsonPath().getInt("book_id") ;
        System.out.println("newBookId = " + newBookId);

        DB_Utility.runQuery("SELECT * FROM books where id = " + newBookId ) ;
//        DB_Utility.displayAllData();
        Map<String,String> dbResultMap =  DB_Utility.getRowMap(1) ;
        System.out.println("dbResultMap = " + dbResultMap);

        //randomBookMapBody = {year=1682, author=Myrtis Barrows IV, isbn=71104517, name=Noli Me Tangere, description=There is nothing regular about Chuck Norris' expressions., book_category_id=13}
        //dbResultMap =       {id=3133, name=Noli Me Tangere, isbn=71104517, year=1682, author=Myrtis Barrows IV, book_category_id=13, description=There is nothing regular about Chuck Norris' expressions., added_date=2021-04-09 18:54:16}
        assertThat(dbResultMap.get("name") , is(randomBookMapBody.get("name"))   ) ;
        // keep going and do the rest , or find a better way.

    }


}