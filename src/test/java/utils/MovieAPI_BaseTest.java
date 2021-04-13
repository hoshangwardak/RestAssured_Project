package utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.reset;

public abstract class MovieAPI_BaseTest {

    @BeforeAll
    public static void setupURIAndBasePath() {
        RestAssured.baseURI = "http://www.omdbapi.com/";
    }

    @AfterAll
    public static void cleanUp() {
        reset();
    }
}
