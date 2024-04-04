<h1 align="center">Shopping Application</h1>

<p align="center">
    <a href="https://www.java.com/" target="_blank">
        <img src="https://img.shields.io/badge/Java-17-red" alt="Java 17">
    </a>
    <a href="https://maven.apache.org/" target="_blank">
        <img src="https://img.shields.io/badge/Maven-4.0.0-blue" alt="Maven 4.0.0">
    </a>
    <a href="https://spring.io/projects/spring-boot" target="_blank">
        <img src="https://img.shields.io/badge/Spring Boot-3.2.4-brightgreen" alt="Spring Boot 3.2.4">
    </a>
</p>

<hr>

This e-commerce application allows users to browse products, place orders with optional coupons for discounts, and proceed with payments. Key features include user registration, product inventory viewing, order management with coupon application for discounts, and a simulated payment process with various outcomes. It provides RESTful endpoints for operations like ordering, payment processing, and viewing order details. The system uses a relational database to store users, products, orders, and coupons, ensuring a seamless shopping experience. Designed to handle errors and validations effectively, it offers a simplified yet comprehensive e-commerce platform for users.

<hr>

## Dependencies

- [Spring Boot Starter Data JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa): Provides support for Spring Data JPA, enabling easy interaction with databases using JPA.

- [Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web): Provides basic web support, including embedded Tomcat server and Spring MVC.

- [MySQL Connector/J](https://mvnrepository.com/artifact/com.mysql/mysql-connector-j): JDBC driver for connecting to MySQL databases.

- [Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok): Reduces boilerplate code by providing annotations to generate getters, setters, constructors, and more.
 
- [Springdoc OpenAPI Starter WebMVC UI](https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui): Integrates OpenAPI documentation into Spring Boot applications.

- [Spring Boot Starter Test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test): Includes dependencies for testing Spring Boot applications.

<hr>

## Enpoints

## User Management

### Register a New User

- **URL:** `/user/register`
- **Method:** `POST`
- **Description:** Registers a new user.
- **Request Body:**
    - JSON object representing the user to be registered.
    - Includes details such as username, email etc.
  ```json
  {
  "name": "string",
  "email": "yourMail",
  }

![Screenshot (330)](https://github.com/sjha24/shoppingApp/assets/98340874/3bacc4d4-57a6-44d6-a48f-1b23da6ed443)


### Get Users by userId

- **URL:** `/user?userId=1`
- **Method:** `GET`
- **Description:** Retrieves a single user.
  
- **Response Body:**
  - Success Response:
    - Status Code: `200 OK`.
 ```json
{
  "userId": 1,
  "userName": "string",
  "userEmail": "your mail",
}
```

![Screenshot (331)](https://github.com/sjha24/shoppingApp/assets/98340874/8cd75c58-b80f-4750-a0aa-0e91f627cb18)


## Order Management

### Place Order
 **URL:** `/orders/{userId}/order?qty=10&coupon="OFF10"`
- **Method:** `POST`
- **Description:** Allows User To place an order to specified Quantity of products.Optionally, a coupon code can be applied to receive a discount.
  
- **Response Body:**
  - Success Response:
    - Status Code: `200 OK`.
```json
{
  "orderId": 1,
  "userId": 1,
  "quantity": 10,
  "amount": 900,
  "coupon": "OFF10"
}
```
![Screenshot (332)](https://github.com/sjha24/shoppingApp/assets/98340874/c403285f-2929-4087-b7fc-a5a5a1bcad8c)


![Screenshot (333)](https://github.com/sjha24/shoppingApp/assets/98340874/b779bf45-6437-4b97-8775-6087c5be35d2)


### Process Payment

**URL:**  `/orders/{userId}/{orderId}/pay?amount=900`
- **Method:** `POST`
- **Description:** Processes payment for an order by a specified user.
- **Path Variables:**
    - userId (Long): Unique identifier for the user making the payment.
    - orderId (Long): Unique identifier for the order being paid.
- **Query Parameters:**
    - amount (double): Amount to be paid.
- **Responses Body:**
    - Success Response:
        - Status code: `200 OK`,
```json
{
	"userId": 1,
	"orderId": 1,
	"transactionId": tran826162332,
	"status": "successful"
 }
 ````
![Screenshot (335)](https://github.com/sjha24/shoppingApp/assets/98340874/b3b38045-99e1-4429-b5eb-97cf4bcc692d)

![Screenshot (336)](https://github.com/sjha24/shoppingApp/assets/98340874/1e1e6558-948e-4a91-b5bf-70eefc1ecd77)


### Get User Orders
**URL:**: `/orders/{userId}/orders`
- **Method:** `GET`
- **Description:** Retrieves all orders placed by a specified user.
- **Path Variables:**
    - userId (long): Unique identifier for the user whose orders are being retrieved.
**Responses Body:**
    - Success Response:
        - Status code: `200 OK`
```json
[
  {
    "orderId": 1,
    "amount": 900,
    "date": "2024-04-04",
    "coupon": "OFF10"
  },
  {
    "orderId": 2,
    "amount": 950,
    "date": "2024-04-04",
    "coupon": "OFF5"
  },
  {
    "orderId": 3,
    "amount": 1000,
    "date": "2024-04-04"
  }
]
```

![Screenshot (342)](https://github.com/sjha24/shoppingApp/assets/98340874/9f610058-6586-44fa-a0ac-9c25fe6ca3ad)


### Get Order Details
**URL:**: `/orders/{userId}/orders/{orderId}`
- **Method:** `GET`
- **Description:**  Retrieves details for a specific order placed by a user.
- **Path Variables:**
    - userId (long): Unique identifier for the user whose orders are being retrieved.
    - orderId (long): Unique identifier for the order.
**Responses Body:**
    - Success Response:
        - Status code: `200 OK`
```json
[
  {
    "orderId": 3,
    "amount": 1000,
    "date": "2024-04-04",
    "transactionId": "tran469551108",
    "status": "Failed"
  }
]
```

![Screenshot (338)](https://github.com/sjha24/shoppingApp/assets/98340874/6c93e8df-5bcb-48a3-8f2f-082145f10548)


### Fetch Available Coupons
**URL:**: `/fetchCoupons`
- **Method:** `GET`
- **Description:**  Retrieves a list of available coupons along with their discount percentages.
- **Responses Body:**
    - Success Response:
        - Status code: `200 OK`
```json
{
  "OFF5": 5,
  "OFF10": 10
}
```
![Screenshot (343)](https://github.com/sjha24/shoppingApp/assets/98340874/447f487b-198e-4ca4-80de-10b07f03d00e)


### Get Inventory Status
**URL:**: `/Inventory`
- **Method:** `GET`
- **Description:**  Retrieves the current status of the inventory, including the available quantities of products.
- **Responses Body:**
    - Success Response:
        - Status code: `200 OK`
```json
{
  "ordered": 30,
  "price": 100,
  "available": 970
}
```
![Screenshot (344)](https://github.com/sjha24/shoppingApp/assets/98340874/f9795581-680e-4a4e-920f-fc2a59ac2fe1)


## Swagger Support for API Documentation

The project includes Swagger support for API documentation. Swagger is a powerful tool that provides interactive documentation for RESTful APIs, enabling developers to explore and test endpoints easily. By integrating Swagger into your project, you can enhance the usability and understanding of your API, making it easier for developers to integrate with your system.

### Key Benefits of Swagger:
- **Interactive Documentation:** Swagger generates interactive documentation that allows developers to explore and test endpoints directly from the documentation interface.
- **Ease of Integration:** Developers can easily understand and integrate with your API by referring to the comprehensive documentation provided by Swagger.
- **Consistency:** Swagger ensures that the API documentation remains consistent with the actual implementation, reducing the chances of discrepancies.
- **Time-Saving:** With Swagger, developers can quickly understand the API structure and functionalities, saving time on integration and troubleshooting.

## ER Diagram

![ER_diagram](https://github.com/sjha24/shoppingApp/assets/98340874/116a90c1-7aa2-4fa6-bab6-589778e42a74)


## Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/sjha24/shoppingApp.git
   ```

2. Navigate to the project directory:

   ```bash
   cd shoppingApp
   ```

3. Build the project using Maven:

   ```bash
   mvn clean install
   ```

4. Run the application:

   ```bash
   mvn spring-boot:run
   ```

## Database Setup

To set up the database for the WishlistManagement application, follow these steps:

1. Open the `application.properties` file in the project's `src/main/resources` directory.

2. Configure the datasource properties according to your MySQL database setup:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/<Your Database Name>?autoReconnect=true&useSSL=false&createDatabaseIfNotExist=true
   spring.datasource.username=your_database_username
   spring.datasource.password=your_database_password
   spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
   spring.jpa.hibernate.ddl-auto=update
   ```

   Replace `your_database_username` and `your_database_password` with your MySQL database credentials.

3. Optionally, configure Hibernate properties for SQL logging:
   ```
   spring.jpa.properties.hibernate.show_sql=true
   spring.jpa.properties.hibernate.use_sql_comments=true
   spring.jpa.properties.hibernate.format_sql=true
   ```

   These properties enable SQL logging in the console for debugging purposes.


## Accessing the Application

You can access the application through your web browser by navigating to `http://localhost:8080`.

## Custom Port Configuration

If you need to define your own port for accessing the application, you can do so by configuring it in the `application.properties` file. Simply specify the desired port number in the configuration file, and the application will listen on that port upon startup.

Example:
```properties
server.port=9000
```
## Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues for any improvements or features you'd like to see.
