# Orders-and-Notifications-Management

## Description
This project is a simple Orders and Notifications Management System. It is a RESTful API that allows users to create orders and send notifications to the users who created the orders. The API is built using Spring Boot and uses in-memory  database to store the data. The API is secured using  JWT tokens. The API is documented using Swagger. The API is tested using Postman.

## Documentation & Postman collections 
* https://documenter.getpostman.com/view/26527995/2s9YsDkah8
  
## Requirements
* Java 17
* Maven 3.8.2
* Postman 8.12.5
* IntelliJ IDEA 2021.2.2 at least or any other IDE

## How to run the application
* Clone the repository
* Open the project in your IDE
* Run the application
* Open Postman and import the collection from the project folder
* Run the collection
* You can also use Swagger to check the API documentation by going to http://localhost:8080/swagger-ui.html after running the server


## Requests Examples
1- create a user by sending POST request to http://localhost:8080/api/user/register with the following body:
```

   {
    "username": "ranaessam",
    "password": "123456",
    "email": "ranaessam03@gmail.com",
    "phoneNumber": "01111111111",
    "address":  {
              "city": "string",
              "region": "string",
              "street": "string",
              "apartmentNum": 0
      },
    "walletBalance": 1000.0
}


```

2- login to the system by sending GET request to http://localhost:8080/api/user/check with the following body:
```
{
    "username": "user1",
    "password": "password1"
}
```
to get the following response:
```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTYzNDQ0NjQ5NiwiaWF0IjoxN"
}
```
save the token to use it in the next requests by adding it to the header of the request.


## Authors
* [Rana Essam](mailto:ranaessam03@gmail.com)
* [Nour Muhammed](mailto:nourmuhammad835@gmail.com)
* [Noor Eyad]( mailto:nooreyadd39@gmail.com)
* [Mariam Haitham](mailto:username@gmail.com)









