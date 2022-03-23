

### Getting Started
    Clone this repository: 
        [Spring Boot Project](git@github.com:kpokala/foodtrucks.git)

### Prerequisites
    - Jdk 8+
    - Maven or maven wrapper

### Run
    mvn clean package spring-boot:run
"# foodtrucks" 

###Types of Truck searches
    
    -Get All Trucks
    -/trucks

-Get By Food Type
/trucks/type/{foodType}

-Get By Food Type and current location and desired distance
/trucks/type/{foodType}/latitude/{latitude}/distance/{distance}
