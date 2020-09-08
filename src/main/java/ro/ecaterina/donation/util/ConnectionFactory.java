package ro.ecaterina.donation.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String CONNECTION_STRING="jdbc:mysql://localhost:3306/mydatabase";
	private static final String DRIVER="com.mysql.cj.jdbc.Driver";//
	private static final String USERNAME="root";//definire username
	private static final String PASSWORD="";//definire parola

	//metoda pentru a deschide conexiunea cu baza de date
	public static Connection openConnection(){

		try{
			Class.forName(DRIVER);
			return DriverManager.getConnection(CONNECTION_STRING,USERNAME,PASSWORD);

		}
		catch(ClassNotFoundException e) {
			System.out.println("Clasa Driver nu s-a gasit"+e.getMessage());
			return null;
		} catch (SQLException e) {
			System.out.println("Nu s-a putut conecta la BD"+e.getMessage());
			return null;		
		}
	}
}
