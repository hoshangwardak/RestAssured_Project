package utils;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import utils.SpartansNoAuthBaseTest;
import utils.Utility;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public abstract class LibraryApp_BaseTest {

    static String libraryEmail = ConfigurationReader.getProperty("libraryUsername");
    static String libraryPassword = ConfigurationReader.getProperty("libraryPassword");
    public static String librarianToken;

    @BeforeAll
    public static void settingUpLibraryApp() {
        RestAssured.baseURI = "http://library1.cybertekschool.com";
        RestAssured.basePath = "/rest/v1/";
        librarianToken = getToken(libraryEmail, libraryPassword);
    }

    @AfterAll
    public static void cleanUpLibraryApp() {
        reset();
    }

    public static String getToken(String username, String password) {
        String myToken = given()
                .contentType(ContentType.URLENC)
                .formParam("email",username)
                .formParam("password",password)
                .when()
                .post("login")
                .path("token")
                ;
        return myToken;
    }

    public static Map<String, Object> getRandomBook() {
        Faker faker = new Faker();
        Map<String,Object> myBookMap = new HashMap<>();
        myBookMap.put("name", faker.book().title()      );
        myBookMap.put("isbn", faker.number().digits(8) );
        myBookMap.put("year", faker.number().numberBetween(1600, 2021));
        myBookMap.put("author",faker.book().author() );
        myBookMap.put("book_category_id", faker.number().numberBetween(1,20)  );
        myBookMap.put("description",faker.chuckNorris().fact()  );
        return myBookMap;
    }




}
