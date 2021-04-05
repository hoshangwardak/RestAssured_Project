package day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CSVFileSourceParameterizedTest {

    @ParameterizedTest
    @CsvFileSource(resources = {"/state_city_zipCount.csv"}, numLinesToSkip = 1)
    public void testStateCityToZipWithCSVFile(String stateArg, String cityArg, int zipCountArg) {
        System.out.println("stateArg = " + stateArg);
        System.out.println("cityArg = " + cityArg);
        System.out.println("zipCountArg = " + zipCountArg);

        // Write a parameterized test for this request
        // Get the data from Csv resource
        // GET https://api.zippopotam.us/us/{{state}}/{{city}}
        given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/us/")
                .pathParam("state",stateArg)
                .pathParam("city",cityArg)
        .when()
                .get("{state}/{city}")
        .then()
                .statusCode(is(200))
                .body("places", hasSize( zipCountArg ))
                ;



    }


}
