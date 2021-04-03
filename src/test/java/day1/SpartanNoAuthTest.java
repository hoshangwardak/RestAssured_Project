package day1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_util.SpartansNoAuthBaseTest;

import static io.restassured.RestAssured.*;

@DisplayName("Spartan App Get Request")
public class SpartanNoAuthTest extends SpartansNoAuthBaseTest {

    @Test
    public void sayHello() {
        get("hello").prettyPeek();
    }

    @Test
    public void getAllSpartans() {
        get("spartans").prettyPeek();
    }


}
