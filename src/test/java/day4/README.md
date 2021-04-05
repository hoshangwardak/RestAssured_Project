# Day 4

# Post Practice with Random Data

- When we are sending a map object as random data, that is going to be our expected result.
  If you want to validate the data, you can easily access the response body and assert it.
  
- You can extract data from the response body and validate it as shown below:
  body("data.name", is(randomBody.get("name"))) Since the data is store as a key and value
  format in Map we can use the map get() method and provide the key as parameter in order to
  achieve the value data.
  
- You can do the same thing with POJO class as shown below:
  .body("data.name", is(spartan.getName()))  
  
- There are 2 ways to extract the data from payload and store it into a variable:

  1. response.path() or response.jasonPath() = basically store it into a variable, then you
     can use that variable in your get request to validate the data. 
     Note: Then you can validate that data in the validationResponse's body as shown below:
     .body("id",is(myId)) or with the help of POJO class .body("name", is(spartan.getName()))
     
  2. extract() method returns extractable response. When you put . after extract() you grab
     anything you want through the .path("data.id") you pass the parameter and store it in your variable.
     you can also use .jsonPath().get("data.id")
     Note: you cannot extract two different data type because you can store only one value
     in a variable.
     
- You can get the data as well with the locationHeader which is a complete url because the
  moment it starts with http it's going to be treated like full url and get you the data. 
  
- The Library App Content Type is URLENCODED
- Also in order to pass username and password we need to provide a key and value which 
  is exactly like form that needs to be filled. See below:
  .formParam("email",libraryEmail)
  .formParam("password",libraryPassword)
  
- 401 means not authorized

- Extracting token while logging into library app and saving it into a variable and use it
  for next login. e.g: .path("token")
  
- formParams() returns Map in which we can pass multiple param




