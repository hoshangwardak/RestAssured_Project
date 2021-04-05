package day5;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CsvSourceParameterizedTest {

    // Test first number + second number = third number
    // "1, 3 , 4",
    // "2, 3 , 5",
    // "8, 7 , 15",
    @ParameterizedTest
    @CsvSource({
            "1, 3 , 4",
            "2, 3 , 5",
            "8, 7 , 15",
            "10, 9, 19"
    })
    public void testAddition( int num1, int num2, int sum ) {
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("sum = " + sum);

        assertThat(num1+num2,is(equalTo(sum)));
    }


    // Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{{state}}/{{city}}
    @ParameterizedTest
    @CsvSource({
            "NY, New York,166",
            "CO, Denver,76",
            "VA, Fairfax,10",
            "VA, Arlington,33",
            "MA, Boston,56",
            "VA,McLean,3",
            "MD, Annapolis,9"
    })
    public void testStateCityToZipEndpoint(String stateArg, String cityArg) {
        System.out.println("stateArg = " + stateArg);
        System.out.println("cityArg = " + cityArg);

        int placeCount = given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/us/")
                .pathParam("state",stateArg)
                .pathParam("city",cityArg)
                .log().uri()
        .when()
                .get("{state}/{city}")
        .then()
                //.log().body()
                .statusCode(is(200))
                .extract()
                .jsonPath()
                .getList("places")
                .size()
                ;
        System.out.println("placeCount = " + placeCount);


    }

}
