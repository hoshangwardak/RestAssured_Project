package utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.reset;

public abstract class NewsApi_BaseTest {

    @BeforeAll
    public static void setUpNewsApi() {

        RestAssured.baseURI = "https://newsapi.org";
        RestAssured.basePath = "/v2";
    }

    @AfterAll
    public static void cleanUpNewsApi() {
        reset();
    }


}
