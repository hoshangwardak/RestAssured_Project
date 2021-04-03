package day1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("First Day of JUnit 5")
public class JUnit5_Intro {

    @DisplayName("Asserting Numbers")
    @Test
    public void test1() {

        assertEquals(1,2, "Failed!"); // This one is going to be failed
        assertEquals(2,2); // This one is going to be passed

    }

    @DisplayName("Asserting Character")
    @Test
    public void test2() {

        String name = "Yalcin";
        assertEquals('Y',name.charAt(0),"Failed, they are not equal");

        assertTrue(name.endsWith("n"),"Failed, named does not end with letter n");

    }

}
