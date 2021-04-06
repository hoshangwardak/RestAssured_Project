package day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MethodSourceForParameterizedTest {

    @ParameterizedTest
    @MethodSource("getNames")
    public void testPrintNames(String name) {
        System.out.println("name = " + name);
        assertTrue(name.length() >= 5 || name.length() <= 9);
    }

    // Create a static method to return many names
    public static Stream<String> getNames() {
        List<String> nameList = new ArrayList<>(Arrays.asList("Emre","Mustafa","Diana","Shirin",
                "Tucky","Don Corleone","Erjon","Muhammad","Saya","Afrooz"));
        return nameList.stream();
    }

//    // Create a static method to return many names
//    public static List<String> getNames() {
//       List<String> nameList = new ArrayList<>(Arrays.asList("Emre","Mustafa","Diana","Shirin",
//               "Tucky","Don Corleone","Erjon","Muhammad","Saya","Afrooz"));
//       return nameList;
//    }


}
