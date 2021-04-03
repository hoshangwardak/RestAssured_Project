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





