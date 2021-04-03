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

-   Hamcrest Matcher assertion library also needs to be imported. These assertions are very human
    readable. Like assertThat()

-   All matchers methods have two overloaded version. First that accept actual value and second
    that accepts another matcher

-   Each and every matcher accepts another matcher

-   Method Chaining is very important in RestAssured

    When()
    .get()
    .Then() etc...

-   4 important static imports while working with RestAssured, JUnit5 and HamcrestMatcher

-   get() method is a restAssured static method through which you can make a request to retrieve data.

-   You can save the response of the get() request into a Response Interface. Then you'll be able to
    do the validation

-   Response response = get(YOUR ENDPOINT);
    response.statusCode; gives you the status code
    response.getHeader("Content-Type") // This one gives you your data type format
    response.asString(); // This one gives you the body payload

-   200 means OK








