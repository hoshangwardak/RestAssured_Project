Day 1

-   When it comes to assertion in JUnit 5, you'll have to the static import of junit jupiter
    "import static org.junit.jupiter.api.Assertions.assertEquals;" If you don't want to do this static
    import everytime just put a * at the end instead of the method.

-   assertEquals has one particular version where you can add the error message at the end

-   Life cycle annotations
    @BeforeALL, @AfterAll, @BeforeEach, @AfterEach

-   @BeforeAll and @AfterAll will run one time and method must be static
-   @BeforeEach and @AfterEach methods do not need to be static

-   @Disabled is a method that is used to ignore a test. If you don't want any particular test to run
    you can disabled it.

-   Hamcrest is a matcher library. It is an assertion library. It is not a complete running test
    framework. It is not able to run test because it does not have any testing engine.
    It is always accompanied with some other testing framework. It has a dependency that needs to
    be added in order to use HamcrestMatcher but RestAssured dependency already includes Hamcrest
    Matcher dependency in it.

-   Adding the RestAssured dependency

-   Hamcrest Matcher Library is heavily used in RestAssured library








