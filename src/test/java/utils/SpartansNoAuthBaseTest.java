package utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.reset;

public abstract class SpartansNoAuthBaseTest {


    @BeforeAll
    public static void setupURIAndBasePath() {
        RestAssured.baseURI = "http://18.234.107.235:8000";
        RestAssured.basePath = "/api/";
    }

    @AfterAll
    public static void cleanUp() {
        reset();
    }

}
