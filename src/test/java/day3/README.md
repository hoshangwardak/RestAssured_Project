# Day 3

# Extracting response data using JsonPath

- One way is to extract a data is path method
- The other ways is jsonPath() method of Response object in order to get the jsonPath object
- JsonPath is a class that is coming from RestAssured that provides a lot of reusable methods
  to extract data
  
- JsonPath() method is coming from Response object. This is used
- JsonPath is just like a xpath, used to locate certain data from json payload object

- Response object contains information about everything in the response
- JsonPath object contains information about the body of the response

- How do you handle data issue or in other words if the response you are getting is correct. 
  Create your dummy data, query it from your database
  
- The JsonPath object has a method getMap() that requires a parameter to be passed. 
  If your payload does not carry any index or name then simply pass an empty string.
  
- We can also get JsonPath object directly with get().jsonPath() making a request
  JsonPath jsonPath = get("spartans").jsonPath();
  
- In the jsonPath() if you want to extract a specific data, just simply pass the index
  number and then . operator and you can all store them into there respective return types.
  e.g: jsonPath("[0].id");
  

