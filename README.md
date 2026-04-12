## Final Project BookShop 
  -Springboot
## Functional Features
1.	Create a Book
2.	Read a Book
3.	Update a Book
   o Partial Update (Author,Title,Price,Created Date)
   o Full Update
5.	Delete a Book 
   -	A book can be deleted only if it was created at least one (1) week ago
   -	A book cannot be deleted if it is older than one (1) year
## Security
   - Authentication: Basic Auth
   - Role Required: Admin
   - All API Endpoints need login
## API Endpoints / Sample API Requests
   1. GET http://localhost:8080/api/bookshop
   2. POST http://localhost:8080/api/bookshop
     JSON BODY:
         {
            "title": "The Hobbit",
            "author": "J.R.R. Tolkien",
            "price": 499.99,
            "createdAt": "2025-06-10"
        }
   3. PUT http://localhost:8080/api/bookshop/{id}
        JSON BODY:
         {
            "title": "Update Title",
            "author": "Update Author",
            "price": 20.00,
            "createdAt": "2026-01-10"
        }
   4. DEL http://localhost:8080/api/bookshop/{id}
## Project Setup
  1. Update application.properties
       spring.datasource.url=jdbc:mysql://localhost:3306/yourdb
       spring.datasource.username=youruser
       spring.datasource.password=yourpassword
 2. Run the Project as Springboot App
 3. For the login credentials check UserCreationUtil.java 
