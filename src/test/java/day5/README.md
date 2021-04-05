# Day 5

- If you don't have a test_base class for a specific API, you can add the baseUri and
  basePath directly in the request specification of your chain
  given().baseUri().basePath()
  
- We can do 2 types of assertion of the data. One with assigning our response to JsonPath
  and then extracting the value and assert. The other one we can directly assert in the then()
  section of our request with body like this: .body("Search.Title",hasSize(10))
  
- Parameterized Test: Running our tests in DDT manner
  @ParameterizedTest
  
- @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10})
  public void testPrintMultipleValue( int num) {
  //int num = 10;
  System.out.println("num = " + num);
  }
  so basically whatever value of ints you want to pass to your variable you can do it in the
  ValueSource and then pass variable in method signature but dataTypes must be small with an
  s at the end. But you can only provide one value per iteration in the parameterization
  
- @CsvFileSource

  