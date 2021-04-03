package day1;

import org.junit.jupiter.api.*;
import utility.SpartansNoAuthBaseTest;

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
