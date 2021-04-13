package day7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.BreakingBadAPI_BaseTest;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import pojo.Character ;

import java.util.List;

public class BreakingBad_POJO_Test extends BreakingBadAPI_BaseTest {

    //https://www.breakingbadapi.com/api/characters
    @DisplayName("GET /characters")
    @Test
    public void testPracticeDeserialization(){

        // Send request to GET https://www.breakingbadapi.com/api/characters
        // save first item into Character pojo
        Character c1 = get("characters")
                .jsonPath()
                .getObject("[0]",Character.class)
                ;
        System.out.println("c1 = " + c1);

        // save all items into List<Character>
        // print all character name who appeared exactly 5 times  --- check appearance list size
        List<Character> allCharacters = get("characters")
                .jsonPath()
                .getList("",Character.class)
                ;

        allCharacters.forEach(each -> System.out.println(each));

        for (Character each : allCharacters) {
            if (each.getAppearance().size() == 5) {
                System.out.println(each);
            }
        }

        for (Character each : allCharacters) {
            if (each.getAppearance().size() == 1 && each.getAppearance().get(0)==3) {
                System.out.println(each.getName());
            }
        }

    }

}
