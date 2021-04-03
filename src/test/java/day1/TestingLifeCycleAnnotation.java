package day1;

import org.junit.jupiter.api.*;

@DisplayName("Learning Test LifeCycle Annotation")
public class TestingLifeCycleAnnotation {

    @BeforeAll
    public static void beforeAllMethod() {
        System.out.println("BeforeAll Method is running one time only");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("BeforeEach method will run before each test");
    }

    @DisplayName("Test #1")
    @Test
    public void test1() {
        System.out.println("Test #1 is running");
    }

    @Disabled
    @Test
    public void test2() {
        System.out.println("Test #2 is running");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("AfterEach method will also run after each test");
    }

    @AfterAll
    public static void afterAllMethod() {
        System.out.println("AfterAll method is running one time as well");
    }

    /*
            This is the output of the above tests and methods:

            BeforeAll Method is running one time only
            BeforeEach method will run before each test
            Test #1 is running
            AfterEach method will also run after each test
            BeforeEach method will run before each test
            Test #2 is running
            AfterEach method will also run after each test
            AfterAll method is running one time as well
     */


}
