package day1;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {


    @DisplayName("Matcher Assertion")
    @Test
    public void test1() {

        // asserting 10 is equla 5+5

        assertThat(5+5, is(10));
        assertThat(5+5, equalTo(10));
        assertThat(10, is(equalTo(10)));

        assertThat(5+5, not(11));
        assertThat(5+5, is(not(11)));
        assertThat(5+5, is(not(equalTo(11))));

        assertThat(20, is(greaterThan(10)));
        assertThat(10, is(lessThan(20)));
        assertThat(20, is(greaterThanOrEqualTo(20)));
        assertThat(10, is(lessThanOrEqualTo(20)));

    }

    @DisplayName("Matchers with Strings")
    @Test
    public void test2() {

        String message = "B21 learning Hamcrest";

        // checking for equality
        assertThat(message, is("B21 learning Hamcrest"));
        assertThat(message, equalTo("B21 learning Hamcrest"));
        assertThat(message, is(equalTo("B21 learning Hamcrest")));

        assertThat(message, startsWith("B21"));
        assertThat(message, startsWithIgnoringCase("b21"));
        assertThat(message, endsWith("rest"));

        assertThat(message, containsString("learning"));
        assertThat(message, containsStringIgnoringCase("LEARNING"));

        String str = "   ";

        assertThat(str, blankString());
        assertThat(str, blankOrNullString());
        assertThat(str.trim(), emptyString());
    }

    @DisplayName("Hamcrest Support for Collection")
    @Test
    public void testCollection() {

        List<Integer> list = Arrays.asList(1,4,7,3,7,44,88,99,44);

        // Checking the size of this list
        assertThat(list, hasSize(9));
        assertThat(list, hasItem(44));
        assertThat(list, hasItems(1,4,7));
        assertThat(list, everyItem(greaterThan(0)));
    }




}
