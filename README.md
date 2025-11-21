# Employee Performance Evaluation System

A Java **web-based** project built with **Spring Boot, Spring MVC, Spring Data JPA, Thymeleaf, MySQL**.

## Objective

To provide a platform where:

- **Managers**
    - Log in
    - Evaluate employee performance
    - Set goals
    - Give feedback

- **Employees**
    - Log in
    - View goals, evaluations, feedback
    - Track their own performance

## Technology Stack

- Java 17
- Spring Boot 3
- Spring MVC
- Spring Data JPA
- Thymeleaf
- MySQL / MariaDB
- Bootstrap 5, Bootstrap Icons

## How to Run

## ✅ **1. Download the Project**

You can get the project in two ways:

### **Option A — Clone the repository**

```bash
git clone https://github.com/malikasingh2509/employee-performance-system.git
```

### **Option B — Download ZIP**

* Go to your GitHub repo
* Click **Code → Download ZIP**
* Extract the zip file


## ✅ **2. Open the Project in IntelliJ IDEA**

1. Open **IntelliJ IDEA**
2. Click **Open**
3. Select the folder:

```
employee-performance-system/
```

IntelliJ will automatically load all dependencies.


## ✅ **3. Configure Database**

Open:

```
src/main/resources/application.properties
```

Make sure these settings match your MySQL setup:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_performance_db
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```


## ✅ **4. Start MySQL Database**

Start MySQL depending on your system:

* **XAMPP** → Click *Start* on MySQL
* **MAMP** → Start servers
* **MySQL Server** → Start manually

Create the database:

```sql
CREATE DATABASE employee_performance_db;
```

You don’t need tables. Spring Boot will create them automatically.

## ✅ **5. Run the Application**

### Option A — Run from IntelliJ

Open this file:

```
src/main/java/com/example/employee_performance/EmployeePerformanceApplication.java
```

Click the **green ▶ RUN button**.

### Option B — Run using terminal

```bash
./mvnw spring-boot:run
```

If successful, you will see:

```
Tomcat started on port 8080
Started EmployeePerformanceApplication
```

## ✅ **6. Open in Browser**

Go to:

👉 **[http://localhost:8080](http://localhost:8080)**


## ✅ **7. Login Credentials**

### **Manager Login**

* Email: `manager@example.com`
* Password: `1234`

### **Employee Login**

* Email: `employee@example.com`
* Password: `1234`

## 💡 **Project Endpoints**

* **Manager Dashboard:**
  `http://localhost:8080/manager/dashboard`

* **Employee Dashboard:**
  `http://localhost:8080/employee/dashboard`

## 🔁 **Running Again Later**

Every time you want to run the project:

1. Start MySQL
2. Open IntelliJ
3. Click **RUN ▶** again

