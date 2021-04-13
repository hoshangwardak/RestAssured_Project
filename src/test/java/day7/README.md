# Day 7

# Deserialization

- Serialization: Java Object to Json (or any other type of text)
- Deserialization: Json (or any other type of text) to Java

- Created Spartan POJO without a constructor so that I can save my response in it
  and I do not want to create an object out of it

- This is how we store our data like this:
  SpartanPOJO sp = response.as(SpartanPOJO.class);

- The other is like this:
  JsonPath jp = response.jsonPath();
  SpartanPOJO sp = jp.getObject("content[0]",SpartanPOJO.class);
  but you have to provide a path:
  
- response.as will not work if you want a specific data because the response.as stores the whole object
- that's why SpartanPOJO sp = jp.getObject("content[0]",SpartanPOJO.class); is a good way because you 
  can provide the path ot the specific data.
  
- project Lombok library or dependency is used to prevent boiler plate such as repeated steps of
  @Getter @Setter @AllArgsConstructor and @ToString
  This dependency Helps us creat POJO a lot easier, all we have to do is declare the fields and all
  the getter, setters, constructor and toString method can be created with @annotations
  

  