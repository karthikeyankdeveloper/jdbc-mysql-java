package com.incvo;

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
			String password = "karthikeyan"; // Replace with your MySQL password
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
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
