package utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.reset;
public abstract class BreakingBadAPI_BaseTest {

    @BeforeAll
    public static void settingUpBreakingUpAPI() {
        RestAssured.baseURI = "https://www.breakingbadapi.com";
        RestAssured.basePath = "/api/";
    }

    @AfterAll
    public static void cleaningUpBreakingBadAPI() {
        reset();
    }
}
