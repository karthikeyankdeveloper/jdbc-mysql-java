# Setting Up JDBC Connection Between Java and MySQL

  

## 1. Download MySQL JDBC Driver

- Download the latest MySQL Connector/J from [Maven Repo](https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/9.0.0/mysql-connector-j-9.0.0.jar).

- Add the downloaded `.jar` file (e.g., `mysql-connector-j-9.0.0.jar`) to your project’s classpath.

  

## 2. Set Up MySQL Database

- Ensure MySQL is installed and running.

- Create a database:

```sql

	CREATE  DATABASE incvo;


	CREATE  TABLE users (

	id INT AUTO_INCREMENT PRIMARY  KEY,

	username VARCHAR(50),

	password  VARCHAR(50)

	);

  

	INSERT INTO users (username, password) VALUES ('user1', 'pass1'), ('user2', 'pass2');

```

## 3. Write Java Code to Connect to MySQL
```
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.Statement;

	public class Connectivity {
	    public static void main(String[] args) {
	        Connection connection = null;
	        Statement statement = null;

	        try {
	            // Load and register MySQL JDBC driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Establish a connection
	            String url = "jdbc:mysql://localhost:3306/incvo"; // Replace with your DB name
	            String user = "root"; // Replace with your MySQL username
	            String password = "password"; // Replace with your MySQL password
	            connection = DriverManager.getConnection(url, user, password);

	            // Create a statement object
	            statement = connection.createStatement();

	            // Execute a query
	            String query = "SELECT * FROM users";
	            ResultSet resultSet = statement.executeQuery(query);

	            // Process the result set
	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String username = resultSet.getString("username");
	                String passwordFromDB = resultSet.getString("password");

	                System.out.println("ID: " + id + ", Username: " + username + ", Password: " + passwordFromDB);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Close resources
	            try {
	                if (statement != null) statement.close();
	                if (connection != null) connection.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
```

## 4. Compile and Run the Code

-   Ensure the MySQL server is running.
-   Compile the Java code:
    `javac Connectivity.java` 
    
-   Run the compiled Java program:
    `java Connectivity`

## 5. Troubleshooting

-   **Driver Not Found**: Ensure the MySQL Connector/J `.jar` file is in your classpath.
-   **Connection Issues**: Verify MySQL credentials and that the MySQL server is running.