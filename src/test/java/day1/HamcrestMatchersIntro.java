package day1;

import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {


    @DisplayName("Matcher Assertion")
    @Test
    public void test1() {

        // asserting 10 is equla 5+5

        assertThat(5+5, is(10));

    }




}
