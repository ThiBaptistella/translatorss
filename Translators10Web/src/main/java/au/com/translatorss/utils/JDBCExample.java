package au.com.translatorss.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import au.com.translatorss.bean.Language;

public class JDBCExample {
	
	public static void main(String[] argv) {

		System.out.println("-------- PostgreSQL " + "JDBC Connection Testing ------------");

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? "+ "Include in your library path!");
			e.printStackTrace();
			return;
		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://translatorssdb.cig8rtyh7xvk.us-west-2.rds.amazonaws.com:5432/translatorss2017", "Translatorss2017","Success2017");
			PreparedStatement ps = connection.prepareStatement("select * from language;");
			//PreparedStatement ps = connection.prepareStatement("insert into language values(113,'Guarani');");
			//get customer data from database
			ResultSet result =  ps.executeQuery();
	
			while(result.next()){
				System.out.println(result.getString("id") + result.getString("description"));
			}
			
			
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}

}
