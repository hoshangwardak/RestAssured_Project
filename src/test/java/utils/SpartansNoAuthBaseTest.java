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
        String url      = ConfigurationReader.getProperty("spartan.database.url") ;
        String username = ConfigurationReader.getProperty("spartan.database.username") ;
        String password = ConfigurationReader.getProperty("spartan.database.password") ;
        DB_Utility.createConnection(url,username,password);

        /*
        RestAssured.baseURI = "http://YOURIDADDRESS:8000";
        RestAssured.basePath = "/api/";
        String url = "spartan.database.url="jdbc:oracle:thin:@YOURIPADDRESS:1521:XE"
        String username = "SP"
        String password = "SP"
        DB_Utility.createConnection(url,username,password);

         */
    }

    @AfterAll
    public static void cleanUp() {

        reset();
        DB_Utility.destroy();
    }

}
