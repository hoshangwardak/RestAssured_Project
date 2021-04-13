package utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.reset;

public abstract class SpartansWithAuthBaseTest {

    @BeforeAll
    public static void setupURIAndBasePath() {
        RestAssured.baseURI = "http://18.234.107.235:7000";
        RestAssured.basePath = "/api/";
//        String url      = ConfigurationReader.getProperty("spartan.database.url") ;
//        String username = ConfigurationReader.getProperty("spartan.database.username") ;
//        String password = ConfigurationReader.getProperty("spartan.database.password") ;
//        DB_Utility.createConnection(url,username,password);
    }

    @AfterAll
    public static void cleanUp() {

        reset();
        DB_Utility.destroy();
    }
}
