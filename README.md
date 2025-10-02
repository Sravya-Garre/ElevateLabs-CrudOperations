Oracle CRUD Application (Java + JDBC)
📌 Objective
A simple Java application that connects to an Oracle Database using JDBC and performs basic CRUD operations (Create, Read, Update, Delete) through a menu-driven program.

=> Tools & Technologies
1.Java (JDK 8 or above)
2.Oracle Database (19c)
3.JDBC Driver (ojdbc8.jar)
4.Eclipse 

=> Features
1.Connect to Oracle Database using JDBC.
2.Create an employees table automatically if it doesn’t exist.

=>Perform CRUD operations:
1.Insert Employee
2.View Employees
3.Update Employee Salary
4.Delete Employee

Menu-driven console application for end-user interaction.

Project Structure
OracleCRUDMenu.java   # Main Java file with CRUD operations
lib/ojdbc8.jar        # Oracle JDBC Driver
README.md             # Project documentation

How to Run
1. Clone the Repository
git clone https://github.com/your-username/oracle-crud-java.git
cd oracle-crud-java

2. Add Oracle JDBC Driver

Download ojdbc8.jar (from Oracle’s official site)
and place it inside the lib/ folder.

3. Compile & Run
javac -cp .;lib/ojdbc8.jar OracleCRUD.java
java -cp .;lib/ojdbc8.jar OracleCRUD

Database Configuration

Update these values in OracleCRUDMenu.java as per your DB setup:

static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
static final String USER   = "system";
static final String PASS   = "sravya";

 Sample Output
Connected to Oracle database!

==== Employee Management System ====
1. Insert Employee
2. View Employees
3. Update Employee Salary
4. Delete Employee
5. Exit
Enter your choice:
