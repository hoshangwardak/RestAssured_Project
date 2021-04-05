# Day 5

- If you don't have a test_base class for a specific API, you can add the baseUri and
  basePath directly in the request specification of your chain
  given().baseUri().basePath()
  
- We can do 2 types of assertion of the data. One with assigning our response to JsonPath
  and then extracting the value and assert. The other one we can directly assert in the then()
  section of our request with body like this: .body("Search.Title",hasSize(10))
  
