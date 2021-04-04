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
  
- If you the whole json array as your data then you don't have to do .id, you can just pass 
  the index number like [0]
  
- Storing jsonObject into a map
  Map<String, Object> firstJsonObjectInMap = jsonPath.getJsonObject("content");
  
- Saving the whole spartan into a list of Map
  List<Map<String,Object>> spartans = jsonPath.getList("content");
  
- List<Integer> ids2 = jsonPath.getList("content.id",Integer.class);
  This is the overloaded version of getting data from the list. It requires another parameter
  to be passed such as "Integer.class" It means that this is a type I am storing.
  
- We can assert the size of list with assertThat(allIds, hassize(171))

- POST request needs the following
  body, data format
  
- What is the difference between content-Type header or Accept header
  Content-Type header in the request is you are telling the server what are you sending
  Accept header is you are telling the server this is format I want, or I'll accept
  
- 201 means no content ==> Successful

- Adding a huge payload through external file type
  Right click on your project root level and create a file and save it as .json
  
- Serialization: means turning Java Object into Json or text
- De-serialization: means turning Json or text to Java object
- This is possible with the help of Jackson Databind dependency
- All you have to do is to add it into your pom.xml file


- POJO: Plain Old Java Object
  This POJO class needs to have encapsulated fields and getter
  and setter, also No Arg Constructor and toString() method for
  preview
  
- POST, PUT, PATCH

- 204 means Success No Content
- 404 You will definitely get this if you run your test after deleting the specific data
- 415 means no content type




  
  


  
  
  

