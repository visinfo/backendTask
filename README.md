heycar - backend challenge 

```problem context```
 
Imagine you are creating the first version of heycar. We need to provide a platform that can receive the listings from the dealers through different providers, and make them available in the platform. 

• Dealer - the company that is publishing the car 

• Listing - the car that is being published 

• Provider - the platform the dealers already use to manage their own listings 
description 

We need to get vehicle listings from different sources and make it available in the platform in a standardized format. Some of our providers are sending data via CSV, with the following format (+example) to the /upload_csv/<dealer_id>/endpoint via POST, where <dealer_id> will be the id of the dealer who is sending the data: 

code,make/model,power-in-ps,year,color,price 

1,mercedes/a 180,123,2014,black,15950 
2,audi/a3,111,2016,white,17210 
3,vw/golf,86,2018,green,14980 
4,skoda/octavia,86,2018,blue,16990 

The rest of the providers are sending the information via JSON API, using the following format to the /vehicle_listings/endpoint via POST: 

[ 
{ 
"code": "a", "make": "renault", "model": "megane", "kW": 132, "year": 2014, "color": "red", "price": 13990 } ]
 
 requirements 
 
• Different dealers may have listings with the same code, and the listings should be treated as different listings 

• If a listing is sent again by the same dealer (based on the code), it should be updated 

• All the uploaded listings should be available in json on the /search endpoints via GET 

• A client should be able to search using the following parameters: make, model, year and color 
For this challenge you don't need to cover: 

• Authentication 

• Authorization 

• Data removal 

```Solution```

   Steps to Build 
   
    . git clone https://github.com/visinfo/backendTask.git
    . ./mvnw clean package 
    . Run Application
    
        java -jar target/demo-0.0.1-SNAPSHOT.jar
    
  API Documentation 
  
    . Use http://localhost:8080/swagger-ui.html#/  for API Documentation and Execution 
        
     




• Problems you discovered 

 Concurrency Issue while Updating Listing 
 
  . Used JPA Locking LockModeType.OPTIMISTIC
 
 Mapping CSV data to POJO 
  . Used customer parsing logic to convert CSV data to pojo
 
 Multiple combination  of Query Parameters 
 
  . Used JPA Criteria API to generated query 
 
 
 Executed tests and results
 
  . Used Test Driven Development method to write test cases 
  
  . Tests can be execute with below command 
   
   ```./mvnw clean test ``` 
   
   
 • Ideas you would like to implement if you had time - explain how you would implement them(To Dos)
  
    . Make Design more Generalise to Accept any  Item (Different Vehicle types  ) 
    . Proper Messages/Errors Codes for Exceptions 
    . API Monitoring 

 

Decisions you had to take and why 

    . Used Spring framework for Faster Development and Configurations
   
    . Used Different Domain Models for Create/Update Listing and Query Listing (CQRS approach)
   
    . In Given Test Data row (4,skoda/octavia,86,2018,16990 ) missing color column so I added color and such type of csv will be result in Error 
   
   
Tested architectures and reasons why you chose them instead of other options 
technical requirements 

    . Used Hexagonal Architecture which is one of the popular way for Domain Driven Architecture 
   
   

