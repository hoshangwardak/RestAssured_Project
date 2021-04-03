Day 2

- Path Parameter
- In the documentation the path variable is defined with :giveItAName
- e.g: /:spartan_id give this as a path after your endpoint, then you can add the value in the path
  value in the path variable box.

- In method chaining given() is not always required but if you have some filters then yes

- 404 means not Found. This happens when you type wrong resource

- In method chaining anything that goes under given() is known as Request Specification
  and any that goes under when() is the part where you are sending a request
  and anything that goes under then() is mostly validating the response

- The pathParam() accepts key and value as parameter. e.g: pathParam("id",16);

- Another style of pathParam() is this .get("spartans/{id}", 16) see above

- log() is a method that will provide you all the information about the
  request you make. Mostly used one is log().all()
  Also, there are some other methods of log() such as log().ValidationFails
  Very Useful

- you can assert when you reach the then section with using the following
- .body() gives you by path. e.g: .body("id", 16 )

