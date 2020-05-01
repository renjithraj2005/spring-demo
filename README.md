# Demo App with Course Model

### Assumptions

- Apart from the assumptions in the document we assume tax is same across the country
- Unique id that is exposed via API is auto generated. But hardcoding this since there is no listing API 
- H2 Database used for the test to run code quickly
- Added Unit Test for controller which can be executed via IDE

### Build & Run

- *>mvn spring-boot:run* : to run independently 


### API END POINT

```
$ curl -X GET "http://localhost:8080/api/v1/course/price/eb01803e-0a8c-4a10-bfce-4cabbea90944" -H "accept: */*"
```


### Swagger-UI

**Swagger UI** has been added to the code to directly hit the endpoints without the use of a separate application.

For embedded tomcat the url is *http://localhost:8080/swagger-ui.html#/*
