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
  
- @CsvSource
  Reads the file based on Column, not row
  CsvSource does take any header 
  
- @CsvFileSource
  For this you need to create a file under resource directory as .csv
  then you have to pass @CsvFileSource(resources = {"state_city_zipCount.csv"}, THE HEADER THAT YOU WANT TO SKIP GOES HERE)
  you can use resources as address for the file but make sure it starts with a /
  If you are using file instead of resource then you'll have to copy content root.
  If you want to add more than one file then please open curley braces and put  a comma between
  each file address you passing
  
- github Rest Api 
- And generating token

  
  
  